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

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */

int main(int argc, char * argv[]){
    
}