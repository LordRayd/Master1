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
    

    FILE * fic;
    if((fic=fopen("/tmp/motdj","r"))!=NULL){
        char str[4096];
        while ((fgets(str,4096,fic)!= (char*)NULL)){
            printf("%s",str);
        }
    }
    fclose(fic);
    
}


int main (int argc,char **argv) {

    // creation d'une cle IPC en fonction du nom du programme
    if ((cle=ftok(argv[1],'0')) == -1 ) {
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