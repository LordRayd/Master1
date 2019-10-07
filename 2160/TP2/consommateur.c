#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sched.h>
#include <sys/ipc.h> // services IPC
#include <sys/resource.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define NB_MAX 10

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int fic2tab(char * pathname,int * tab,int size){
    int cible;
    // lecture  du fichier
    if ( (cible  = open(pathname,O_RDONLY)) < 0){
        fprintf(stderr,"fic2tab probleme d'ouverture du fichier\n");
        return -1;
    }
    
    if (read(cible,tab,(size+1) * sizeof(int)) !=(size+1) * sizeof(int)) {    
        fprintf(stderr,"fic2tab probleme de lecture du fichier\n");
        return -1;
    }
    close(cible);
    return 0;
}
int tab2fic(char * pathname,int * tab,int size){
    int cible;
    // creation du fichier
    if ( (cible  = open(pathname,O_WRONLY|O_CREAT|O_TRUNC,0666)) < 0){
        fprintf(stderr,"tab2fic probleme d'ouverture du fichier\n");
        return -1;
    }
    
    if (write(cible,tab,(size+1) * sizeof(int)) !=(size+1) * sizeof(int)) {    
        fprintf(stderr,"tab2fic probleme d'ecriture du fichier\n");
        return -1;
    }
    close(cible);
    return 0;
}

int consommateur(char * pathname){
    struct sembuf op;
    int tab[NB_MAX+1] = {0};
    /*int s;
    for(s=0;s<20;s++){*/
    while(1){
        /*P(&Article)*/
        op.sem_num=0;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);
        /*P(&Fichier)*/
        op.sem_num=2;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);
        
        if(fic2tab(pathname,tab,0) == 0){
            fic2tab(pathname,tab,tab[0]);
            int val = tab[1];
            printf("Consommateur : %d \n", val);
            //write(1,val,1);
            int x;
            for(x=1;x<NB_MAX;x++){
                tab[x] = tab[x+1];
            }
            tab[0]--;
            tab2fic(pathname,tab,tab[0]);

        }

        /*V(&Fichier);*/
        op.sem_num=2;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
        //printf("Consommateur +1 Fichier\n");
        /*V(&Places);*/
        op.sem_num=1;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
        //printf("Consommateur +1 Places\n");
        
    }
    exit(0);
}

int producteur(char * pathname){
    int i=0;
    struct sembuf op;
    int tab[NB_MAX+1];
    /*int s;
    for(s=0;s<20;s++){*/
    while(1){

        /*P(&Places)*/
        op.sem_num=1;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);
        /*P(&Fichier)*/
        op.sem_num=2;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        if(fic2tab(pathname,tab,0) == 0){
            fic2tab(pathname,tab,tab[0]);
            tab[0]++;
            tab[tab[0]] = i;
            printf("Producteur : %d \n",i);
                    
            tab2fic(pathname,tab,NB_MAX);

            i = i+1;
        }
        /*V(&Fichier);*/
        op.sem_num=2;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
        /*V(&Article);*/
        op.sem_num=0;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
       
    }
    exit(0);
}
int main (int argc,char **argv) {
    int pid1; // numero des fils
    int pid2;

    ushort init_sem[3]={0,10,1}; //strucutre par initialise le semaphore mutex

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok(argv[0],'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid=semget(cle,3,IPC_CREAT|IPC_EXCL|0666))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    } 

    // initialise l'ensemble
    if (semctl(semid,3,SETALL,init_sem)==-1) {
        fprintf(stderr,"Probleme sur semctl SETALL\n");
        exit(3);
    }
    int tab[1] = {0};
    if(tab2fic(argv[1],tab,0) == 0){
        if((pid1 = fork()) == 0){   
            producteur(argv[1]);
        }

        if((pid2 = fork()) == 0){
            consommateur(argv[1]);
        }

        wait(NULL);
        wait(NULL);
    }
    semctl(semid,0,IPC_RMID,0);
    
}