#include <stdlib.h>
#include <stdio.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/sem.h>
#include<sys/time.h>
#include <unistd.h>
#include <signal.h>

void die(){
    exit(0);
}

int affichage(int N, int* tab){
    for(int i=0;i<N;i++){
        printf("%d ",tab[i]);
    }
}

int gagne(int shmid, int nbRouleau){
    int *variables;
    if(*(variables=shmat(shmid,NULL,NULL))==-1){
        printf("Probleme sur shmat\n");
        exit(1);
    }

    //La valeur supposés de tous les rouleaux
    int val=0;
    //Boolean vérifiant les erreurs
    int erreur=0;

    for(int i=0;i<nbRouleau;i++){
        if(i==0){
            val=variables[i];

        }else{
            if(val!=variables[i])
                erreur++;
        }
    }

    //Se détache du segment
    shmdt(variables);

    if(erreur){
        printf("Perdue\n");
    }else{
        printf("Gagne\n");
    }
}

int codefils(int semid,int shmid, int N,int index){
    struct sembuf op;
    struct sigaction action;
    int* variables;
    int rc;
    //Arme le signal
    action.sa_flags=0;action.sa_handler=die;
    rc = sigaction(SIGUSR1,&action,NULL);
    sigset_t set;
    while(1){
        //P(&muttex)
        op.sem_num=0;op.sem_op=-1;op.sem_flg=0;
        if((semop(semid,&op,1))==-1){
            printf("Probleme sur semop\n");
            exit(8);
        }
        //Bloque les SIGUSR1
        sigaddset(&set,SIGUSR1);
        sigprocmask(SIG_SETMASK,&set,NULL);

        if((variables=shmat(shmid,NULL,NULL))==-1){
            printf("Probleme sur shmat\n");
            exit(7);
        }

        //Modifie son rouleau
        variables[index]=variables[index]+1;
        if(variables[index]>9)
            variables[index]=0;

        //Affiche les rouleaux
        affichage(N,variables);

        shmdt(variables);

        //V(&muttex)
        op.sem_num=0;op.sem_op=1;op.sem_flg=0;
        if((semop(semid,&op,1))==-1){
            printf("Probleme sur semop\n");
            exit(1);
        }
        //Réautorise les SIGUSR1
        sigdelset(&set,SIGUSR1);
        sigprocmask(SIG_SETMASK,&set,NULL);
        usleep(((random()%100)+100)*1000);
    }
}


int main(int argc, char* argv[]){
    if(argc<2){
        printf("Veuillez entrez un nombre de rouleau\n");
        exit(1);
    }
    //SEGMENT PARTAGE PORTE SUR UN TABLEAU D'ENTIER CONTENANT LA VALEUR DES ROULEAUX
    int pid;
    int N=atoi(argv[1]);
    int pidTab[N];
    int valRouleau[N];

    key_t cle;
    ushort init_sem[1]={1};
    int semid;
    int shmid;

    //Récupère la clé
    if((cle=ftok(argv[0],'0'))==-1){
        printf("problem ftok");
        exit(1);
    }
    //Récupère l'id du sémaphore
    if((semid=semget(cle,1,IPC_CREAT|IPC_EXCL|0666))==-1){
        printf("problem semget\n");
        exit(1);
    }
    //Initialise tout les sémaphores
    if(semctl(semid,1,SETALL,init_sem)==-1){
        printf("problem semctl");
        exit(1);
    }
    //Récupère l'id du segment partagé
    if((shmid=shmget(cle,4096,IPC_CREAT|0644))==-1){
        printf("problem shmget");
        exit(1);
    }


    //S'accroche au segment et l'initialise
    int *variables;
    if((variables=shmat(shmid,NULL,NULL))==-1){
        printf("problem shmat");
        exit(1);
    }
    for(int i=0;i<N;i++){
        variables[i]=random()%10;
    }
    affichage(N,variables);
    //Se décroche du segment
    shmdt(variables);

    //Creation des rouleaux
    for(int i=0; i<N;i++){
        if((pid=fork())==0){
            codefils(semid,shmid,N,i);
        }else{
            pidTab[i]=pid;
        }
    }

    //Arret des rouleaux
    struct sembuf op;
    for(int i=0; i<N;i++){
        getchar();
        //P(&muttex)
        op.sem_num=0;op.sem_op=-1;op.sem_flg=0;
        if(semop(semid,&op,1)==-1){
            printf("problem semop");
            exit(1);
        }
        kill(pidTab[i],SIGUSR1);
        //V(&muttex)
        op.sem_num=0;op.sem_op=1;op.sem_flg=0;
        if(semop(semid,&op,1)==-1){
            printf("problem semop");
            exit(1);
        }
    }

    gagne(shmid, N);

    //ipcrm
    if(semctl(semid,0,IPC_RMID,0)==-1){
        printf("problem semctl");
        exit(1);
    }
}
