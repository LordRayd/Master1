#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sched.h>

int add(int size, int* list){
    int pid;
    if(size > 0){
        int i,n;
        pid_t pid[size];
        for(i=0; i<size;i++){
            if((pid[i] = fork()) ==0){

                exit(arr[i]);
            }
            
        }
    }
}

int main (int argc,char **argv) {
    int tab[3] = {1,2,3};
    printf("%d",add(3,tab));
}