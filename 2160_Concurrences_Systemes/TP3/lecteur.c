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
#define SEMNBL 1

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int shmid;

void readMdj(){
    printf("Entre readMdj\n");
    struct sembuf op;

    printf("test1");
    /*P(&semNbL)*/
    op.sem_num=SEMNBL;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    if((shmid = shmget(IPC_PRIVATE, 4096, 0)) == -1){
        perror("shmget");
        exit(1);
    }
    printf("test1");
    int * shmint;
    if(*(shmint  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    printf("test1");
    *shmint++;
    if(shmdt(shmint) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
    printf("test1");

    // seul lecteur
    if (*shmint ==1){
        
        printf("P(&info)\n");
        /*P(&info)*/
        op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);
    }

    // en exclusion avec le rédac.
    /*V(&semNbL)*/
    printf("V(&semNbL)\n");
    op.sem_num=SEMNBL;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);

    FILE * fic;
    if((fic=fopen("/tmp/motdj","r"))!=NULL){
        char str[4096];
        while ((fgets(str,4096,fic)!= (char*)NULL)){
            printf("%s",str);
        }
    }
    fclose(fic);
    sleep(5);
    /*P(&semNbL)*/
        printf("P(&semNbL)\n");
    op.sem_num=SEMNBL;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    if((shmid = shmget(IPC_PRIVATE, sizeof(int), 0)) == -1){
        perror("shmget");
        exit(1);
    }

    if(*(shmint  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    *shmint--;
    if(shmdt(shmint) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }

    if (*shmint == 0){
        /*V(&info)*/
        printf("V(&info)\n");
        op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);
    }

    /*V(&semNbL)*/
        printf("V(&semNbL)\n");
    op.sem_num=SEMNBL;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);
}


int main (int argc,char **argv) {

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok("/tmp/motdj",'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid = semget(cle,2,NULL))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    }
    readMdj();
}