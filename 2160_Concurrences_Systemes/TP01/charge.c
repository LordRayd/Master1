#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/resource.h>
#include<sys/time.h>

/*
    Obtention de la charge CPU courante, necessite une seconde 
*/
double getCpuCurrentLoad(){
    FILE * processStats;
    double totalA, userA, niceA, systemA, idleA, iowaitA, irqA, softirqA;
    double totalB, userB, niceB, systemB, idleB, iowaitB, irqB, softirqB;
    
        processStats = fopen("/proc/stat","r");
        fscanf(processStats, "cpu %lf %lf %lf %lf %lf %lf %lf", 
            &userA, &niceA, &systemA, &idleA, &iowaitA, &irqA, &softirqA);
        fclose(processStats);

        usleep(1000000);

        processStats = fopen("/proc/stat","r");
        fscanf(processStats, "cpu %lf %lf %lf %lf %lf %lf %lf", 
            &userB, &niceB, &systemB, &idleB, &iowaitB, &irqB, &softirqB);
        fclose(processStats);

        totalA = userA+niceA+systemA+idleA+iowaitA+irqA+softirqA;
        totalB = userB+niceB+systemB+idleB+iowaitB+irqB+softirqB;

        double delta = totalB - totalA;

        double totalOccupied = (totalB-idleB)-(totalA-idleA);

        return (totalOccupied*100)/delta;
}

/*
    Permet de charger 4 threads avec une intensité donnée par AMOUNT
    PID sera l'ID du leader du groupe créé
*/
void stressCores(int amount, int * pid){
    int x;
    if((*pid = fork()) == 0){
        setsid();
        for(int thread=0; thread<4; thread++){
            if(fork() == 0){
                for(int i =1; i<=amount; i++){
                    for(int j=0; j<1000000; j++) x = 1.0/i;
                }
                exit(0);//pour ne pas avoir un deuxième pack de fils burner.
                        //Ce qui causerait la perte du pid des premier
                        //et ainsi potentiellement bloquer le système
            }
        }
    }
}

/*
    Fonction permettant de charger le CPU à environ CHARGE % 
    sur une durée de 20 secondes
*/
void softCpuBomb(int charge){
    setsid();//le père est le leader du trio [père, fils burn initial, fils watchdog]
    int load;
    int * pid = malloc(sizeof(int));
    struct timeval start, stop;
    if(charge > 99) printf("[usage]: ./a.out [percent of usage <= 99]\n");
    else{
        gettimeofday(&start, NULL);

        //burst initial
        if(charge <= 95)stressCores(1000, pid);
        else stressCores(1000000, pid);

        usleep(1);//pour être sur que pid est bien set par le fils précedent

        if(fork() == 0){//fils watchdog
            int waited = 0;
            int timeToWait = 20;
            int load;
            int loadToGive;
            
            /*
                Si charge envoyé par salve trop haute ou trop basse
                ==> effet sinusoidale trop prononcé
            */
            if(charge < 25) loadToGive = 20;//pas d'intéret à aller aussi bas
            else if(charge <= 35) loadToGive = 47;
            else if(charge <= 45) loadToGive = 65;
            else if(charge <= 55) loadToGive = 85;
            else if(charge <= 65) loadToGive = 100;
            else if(charge <= 85) loadToGive = 1000;
            else loadToGive = 1000000;

            while(1){
                if((load = getCpuCurrentLoad()) >= charge + 5 && waited < timeToWait){
                    kill(-*pid, 9);
                    //usleep(load*3500);
                    stressCores(loadToGive, pid);
                }
                if(load<=charge-5){//augmenter la charge en cas de décente
                    int previousPID = *pid;
                    stressCores(loadToGive, pid);
                    kill(-previousPID, 9);                    
                }
                if(waited >= timeToWait){//fin du temps
                    printf("process finished, press anything to exit\n");
                    kill(-*pid, 9);
                    break;
                }
                
                gettimeofday(&stop, NULL);
                waited = (stop.tv_sec - start.tv_sec);
            }
            exit(0);
        }
        //père
        getchar();
        free(pid);
        kill(0, 9);
    }
}



int main(int argc, char * argv[]){
    if(argc != 2) printf("One and only one argument, which is the desired charge (in percents) for the CPU\n");
    else{
        //printf("current CPU load: %f %%\n", getCpuCurrentLoad());
        softCpuBomb(atoi(argv[1]));
    }
}