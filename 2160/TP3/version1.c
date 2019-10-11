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

void initialisation(char * nom) {
    ushort init_sem[2]={1,1}; //strucutre par initialise le semaphore mutex

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok(nom,'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici un seul mutex)
    if ((semid=semget(cle,2,IPC_CREAT|IPC_EXCL|0666))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    } 

    // initialise l'ensemble
    if (semctl(semid,2,SETALL,init_sem)==-1) {
        fprintf(stderr,"Probleme sur semctl SETALL\n");
        exit(3);
    }

    if(shmid = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0666) == -1){
        perror("shmget");
        exit(1);
    }

    int * shmint = shmat(shmid,NULL,0);
}


void creatMdj(){
    struct sembuf op;
    /*P(&info)*/
    op.sem_num=INFO;op.sem_op=-1;op.sem_flg=0;
    semop(semid,&op,1);

    //system("cat > /tmp/motdj");

    /*V(&info)*/
    op.sem_num=INFO;op.sem_op=1;op.sem_flg=0;
    semop(semid,&op,1);
}

int main (int argc,char **argv) {

    int pid ;

    initialisation(argv[0]);
    
    if((pid = fork()) == 0){
        readMdj();
        exit(0);
    }

    /*if((pid = fork()) == 0){
        creatMdj();
        exit(0);
    }*/


    semctl(semid,0,IPC_RMID,0);
}