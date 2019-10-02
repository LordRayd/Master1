#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sched.h>


int main (int argc,char **argv) {
    int pid;
    char c;
    unsigned int rep;
    unsigned int delai;
    while ( scanf(" %c%u%u",&c, &rep,&delai) == 3){
        if ((pid = fork()) == 0) {
            int x;
            for(x=0;x<rep;x++){
                write(1,&c,1);
                sched_yield();
                sleep(delai);
            }
            exit(0);
        }
    }
}