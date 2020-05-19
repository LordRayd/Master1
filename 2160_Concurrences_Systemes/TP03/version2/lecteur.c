#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sched.h>
#include <sys/ipc.h> // services IPC
#include <sys/resource.h>
#include <sys/shm.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define INFO 0
#define SEMLEC 1
#define SEMRED 2

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int shmid;

int * variable;
int * debut;

int redacteur;
int lecteur;
int demandeRedacteur;
int demandeLecteur;

void readMdj(){
    struct sembuf op;

    printf("P(&Info)\n");
    op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }

    //recuperation redacteur et demande redacteur
    debut = variable;
    redacteur = *variable;
    variable++;
    demandeRedacteur = *variable;

    if(redacteur || demandeRedacteur){

        //Demande lecteur +1
        variable = debut ;
        variable++;
        variable++;
        (*variable)++;

        //Libere les variable
        variable = debut;
        if(shmdt(variable) == -1){
            perror("probleme sur shmdt");
            exit(4);
        }
        printf("V(&info)\n");
        op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);

        printf("P(&SEMLEC)\n");
        op.sem_num=SEMLEC;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        printf("P(&Info)\n");
        op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
            perror("probleme shmat");
            exit(4);
        }
        debut = variable;

        //Demande lecteur -1
        variable = debut ;
        variable++;
        variable++;
        (*variable)--;
    }
    // Lecteur +1
    variable = debut;
    variable++;
    variable++;
    variable++;
    (*variable)++;

    //Libere les variables
    variable = debut;
    if(shmdt(variable) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
    printf("V(&info)\n");
    op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);


    // Lire le fichier
    FILE * fic;
    if((fic=fopen("/tmp/motdj","r"))!=NULL){
        char str[4096];
        while ((fgets(str,4096,fic)!= (char*)NULL)){
            printf("%s",str);
            sleep(5);
        }
    }
    fclose(fic);
    
    printf("\nP(&Info)\n");
    op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    debut = variable;

    
    // Lecteur -1
    variable = debut;
    variable++;
    variable++;
    variable++;
    (*variable)--;

    lecteur = *variable;
    variable = debut;
    variable++;
    demandeRedacteur = *variable;

    if(lecteur==0 && demandeRedacteur){
        printf("V(&SEMRED)\n");
        op.sem_num=SEMRED;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
    }


    //Libere les variables
    variable = debut;
    if(shmdt(variable) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
    printf("V(&info)\n");
    op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);
}


int main (int argc,char **argv) {

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok("/tmp/motdj",'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid = semget(cle,3,0))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    }

    if((shmid = shmget(cle, 4*sizeof(int), 0)) == -1){
        perror("shmget");
        exit(1);
    }

    readMdj();
}