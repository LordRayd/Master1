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

void creatMdj(){
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
    variable = debut +3;
    lecteur = *variable;

    if(lecteur || redacteur || demandeRedacteur){
        //demande redacteur ++
        variable = debut +1;
        (*variable)++;

        //Libere les variables
        if(shmdt(debut) == -1){
            perror("probleme sur shmdt");
            exit(4);
        }
        printf("V(&info)\n");
        op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);

        printf("P(&SEMRED)\n");
        op.sem_num=SEMRED;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        printf("P(&Info)\n");
        op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
            perror("probleme shmat");
            exit(4);
        }
        debut = variable;
        
        //demande redacteur - 1
        variable = debut +1;
        (*variable)--;
    }

    //redacteur +1
    variable = debut;
    (*variable)++;
    
    //Libere les variables
    if(shmdt(debut) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
    printf("V(&info)\n");
    op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);

    //ecrire le mot du jour
    system("cat > /tmp/motdj");

    printf("P(&Info)\n");
    op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    debut = variable;
    
    //redacteur - 1
    (*variable)--;

    variable = debut +1;
    if(*variable){
        printf("V(&SEMRED)\n");
        op.sem_num=SEMRED;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);
    } else {
        variable = debut +2;
        if(*variable){
            int nb;
            for(nb=0;nb < *variable; nb++){
                printf("V(&SEMLEC)\n");
                op.sem_num=SEMLEC;op.sem_op=-1;op.sem_flg=0;
                semop(semid,&op,1);
            }
        }
    }

    //Libere les variables
    if(shmdt(debut) == -1){
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
    if ((semid=semget(cle,2,NULL))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    }

    
    if((shmid = shmget(cle, 4*sizeof(int), 0)) == -1){
        perror("shmget");
        exit(1);
    }
    creatMdj();
}