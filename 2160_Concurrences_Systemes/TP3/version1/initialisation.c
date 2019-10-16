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

int main (int argc,char **argv) {

    system("touch /tmp/motdj");

    ushort init_sem[2]={1,1}; //strucutre par initialise le semaphore mutex
    // creation d'une cle IPC
    if ((cle=ftok("/tmp/motdj",'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid=semget(cle,2,IPC_CREAT|0666))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    } 

    // initialise l'ensemble
    if (semctl(semid,2,SETALL,init_sem)==-1) {
        fprintf(stderr,"Probleme sur semctl SETALL\n");
        exit(3);
    }

    if((shmid = shmget(cle, 4096, IPC_CREAT | 0666)) == -1){
        perror("shmget");
        exit(1);
    }

    int * shmint;
    if(*(shmint  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    *shmint = 0;
    if(shmdt(shmint) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
}