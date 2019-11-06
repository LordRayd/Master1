/**
 * autheur Samuel LE BERRE
 */
#include <unistd.h> // appel systeme fork
#include <fcntl.h>  // appel system unix ES
#include <stdio.h> // librairie standard C
#include <stdlib.h> // exit
#include <sched.h>  // sche_yield
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <sys/wait.h> // wait
#include <time.h>
#include <sys/stat.h>
#include <string.h>

#define MUTEX 0
#define NB_ESCLAVE 5
#define NB_ELEMENT 10

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int shmid;

int * tab;

struct sembuf op;

void maitre(){
    char c;
    unsigned int rep;
    unsigned int delai;
    while ( scanf(" %c%u%u",&c, &rep,&delai) == 3){
        
        printf("P(&MUTEX)\n");
        op.sem_num=MUTEX;op.sem_op=-1;op.sem_flg=0;
        semop(semid,&op,1);

        
        if(*(tab  = (int*) shmat(shmid,NULL,0)) == -1){
            perror("probleme shmat");
            exit(4);
        }

        int pos = *tab;

        tab = tab + sizeof(int) + pos*(2*sizeof(int)+sizeof(char));

        *tab = (int)c;
        tab = tab + sizeof(char);
        *tab = rep;
        tab = tab + sizeof(int);
        *tab = delai;

        if(shmdt(tab) == -1){
            perror("probleme sur shmdt");
            exit(4);
        }

        printf("V(&MUTEX)\n");
        op.sem_num=MUTEX;op.sem_op=1;op.sem_flg=0;
        semop(semid,&op,1);


    }
}

void esclave(){

    int x;
    for(x=0;x<5;x++){
        write(1,4,1);
        sched_yield();
        sleep(1);
    }
}

int main(int argc, char * argv[]){

    //init sem
    ushort init_sem[1]={10};

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok(argv[0],'0')) == -1 ) {
        fprintf(stderr,"Probleme sur ftoks\n");
        exit(1);
    }

    // demande un ensemble de semaphore (ici deux mutex)
    if ((semid=semget(cle,1,IPC_CREAT|0666))==-1) {
        fprintf(stderr,"Probleme sur semget\n");
        exit(2);
    }

    // initialise l'ensemble
    if (semctl(semid,1,SETALL,init_sem)==-1) {
        fprintf(stderr,"Probleme sur semctl SETALL\n");
        exit(3);
    }

    //Allocation espace necessaire tab
    if((shmid = shmget(cle, sizeof(int)+NB_ELEMENT*(sizeof(char)+sizeof(int)*2), IPC_CREAT | 0666)) == -1){
        perror("shmget");
        exit(1);
    }


    int i =0;
    int pid;
    while(i<NB_ESCLAVE){
        if((pid = fork()) == 0){
            esclave();
            exit(0);
        }
    }
    maitre();



}