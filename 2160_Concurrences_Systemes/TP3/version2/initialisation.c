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
#define SEMNBR 2

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int shmid;

int * variable;

int main (int argc,char **argv) {

    system("touch /tmp/motdj");

    ushort init_sem[3]={1,0,0}; //strucutre par initialise le semaphore mutex
    // creation d'une cle IPC
    if ((cle=ftok("/tmp/motdj",'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid=semget(cle,3,IPC_CREAT|0666))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    } 

    // initialise l'ensemble
    if (semctl(semid,3,SETALL,init_sem)==-1) {
        fprintf(stderr,"Probleme sur semctl SETALL\n");
        exit(3);
    }

    if((shmid = shmget(cle+"lec", 4096, IPC_CREAT | 0666)) == -1){
        perror("shmget");
        exit(1);
    }
    if(*(variable  = (int*) shmat(shmid,NULL,0)) == -1){
        perror("probleme shmat");
        exit(4);
    }
    *variable = 0;
    variable++;
    
    *variable = 0;
    variable++;
    
    *variable = 0;
    variable++;
    
    *variable = 0;
    if(shmdt(variable) == -1){
        perror("probleme sur shmdt");
        exit(4);
    }
}