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
        printf("%d  ",tab[i]);
    }
}

int gagne(int nbRouleau){
    int valeurRouleau[N];
    //S'accroche au segment
    if((valeurRouleau=shmat(shmid,NULL,NULL))==-1){
        printf("Probleme sur shmat\n");
        exit(6);
    }

    //Détermine si le joueur gagne
    int premier=0;
    int nbPrece=0;
    int erreur=2;

    for(int i=0;i<N;i++){
        if(i==0){
            premier=valeurRouleau[i];

        }else{
            if((premier!=valeurRouleau[i])&&(nbPrece!=valeurRouleau[i])){
                erreur--;
            }
        }
        nbPrece=valeurRouleau[i];
    }

    //Se détache du segment
    shmdt(valeurRouleau);

    if(erreur<=0){
        printf("Perdue\n");
    }else{
        printf("Gagne\n");
    }
}

int codefils(int semid,int shmid, int N,int numeroRouleau){
    struct sembuf op;
    struct sigaction action;
    int* valeurRouleau;
    int rc;
    //Arme la réception du signal
    action.sa_flags=0;
    action.sa_handler=die;
    rc=sigaction(SIGUSR1,&action,NULL);
    sigset_t set;
    while(1){
        //Réserve sa place pour le segment
        op.sem_num=0;
        op.sem_op=-1;
        op.sem_flg=0;
        if((semop(semid,&op,1))==-1){
            printf("Probleme sur semop\n");
            exit(8);
        }
        //Bloque les SIGUSR1
        sigaddset(&set,SIGUSR1);
        sigprocmask(SIG_SETMASK,&set,NULL);

        //S'accroche au segment
        if((valeurRouleau=shmat(shmid,NULL,NULL))==-1){
            printf("Probleme sur shmat\n");
            exit(7);
        }

        //Modifie son rouleau
        valeurRouleau[numeroRouleau]=valeurRouleau[numeroRouleau]+1;
        if(valeurRouleau[numeroRouleau]>9){
            valeurRouleau[numeroRouleau]=0;
        }

        //Affiche les rouleaux
        affichage(N,valeurRouleau);
        printf("\n");

        //Se détache du segment
        shmdt(valeurRouleau);

        //Libère sa place sur le segment
        op.sem_num=0;
        op.sem_op=1;
        op.sem_flg=0;
        if((semop(semid,&op,1))==-1){
            printf("Probleme sur semop\n");
            exit(8);
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
    ushort sem[1]={1};
    int semid;
    int shmid;

    //Récupère la clé
    if((cle=ftok(argv[0],'0'))==-1){
        printf("Probleme sur ftok\n");
        exit(2);
    }
    //Récupère l'id du sémaphore
    if((semid=semget(cle,1,IPC_CREAT|IPC_EXCL|0666))==-1){
        printf("Probleme sur semget\n");
        exit(3);
    }
    //Initialise tout les sémaphores
    if(semctl(semid,1,SETALL,sem)==-1){
        printf("Probleme sur semctl\n");
        exit(4);
    }
    //Récupère l'id du segment partagé
    if((shmid=shmget(cle,4096,IPC_CREAT|0644))==-1){
        printf("Probleme sur shmget\n");
        exit(5);
    }


    //S'accroche au segment et l'initialise
    int *valeurRouleau;
    if((valeurRouleau=shmat(shmid,NULL,NULL))==-1){
        printf("Probleme sur shmat\n");
        exit(6);
    }
    for(int i=0;i<N;i++){
        valeurRouleau[i]=random()%10;
    }
    affichage(N,valeurRouleau);
    printf("\n");
    //Se décroche du segment
    shmdt(valeurRouleau);

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
        op.sem_num=0;
        op.sem_op=-1;
        op.sem_flg=0;
        if(semop(semid,&op,1)==-1){
            printf("Problême sur semop\n");
            exit(8);
        }
        kill(pidTab[i],SIGUSR1);
        op.sem_num=0;
        op.sem_op=1;
        op.sem_flg=0;
        if(semop(semid,&op,1)==-1){
            printf("Problême sur semop\n");
            exit(8);
        }
    }

    gagne(N);

    //Détruit les sémaphore
    if(semctl(semid,0,IPC_RMID,0)==-1){
        printf("Probleme sur la destruction des semaphore\n");
        exit(7);
    }
    printf("Merci d'avoir joue\n");
}
