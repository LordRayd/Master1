#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sched.h>

int mySystem(char* string){
    int pid;
    if((pid = fork()) == 0) {
        execl("/bin/sh","sh","-c",string,NULL);
    }
    wait(NULL);
    return 0;
}
int main (int argc,char **argv) {
    mySystem("ls -l");
}