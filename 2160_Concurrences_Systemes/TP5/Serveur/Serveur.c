#include <time.h>
#include <fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include <stdlib.h>

#define MAX 512
#define FIFO_FILE "tubeCommun"

char tube[MAX];

void repondre(char * tube){
    time_t tps;
    char date[128];
    int f;

    tps = time(0);
    strcpy(date,ctime(&tps));

    if((f = open(tube,O_WRONLY)) >= 0){
        write(f,date,128);
        close(f);
    }
}

int main(int argc, char * argv[]){

    int f, pid;

    umask(0);
    mknode(FIFO_FILE, S_IFIFO|0666,0);

    while(1){
        printf("attente client");
        if((f = open(FIFO_FILE, O_RDONLY)) >=0 ){
            int client;
            printf("open\n");
            read(f, &client, sizeof(int));
            sprintf(tube,"/tmp/tube_%d",client);

            printf("client  : %d\n",client);

            if((pid= fork()) == 0){
                repondre(tube);
            }
        }
    }
}