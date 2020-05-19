#include <fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    char c;

    int tCompress;
    if ((tCompress=open("tubeCompress",O_RDONLY))< 0) {
        exit(1);
    }


    int tMajuscule;
    if ((tMajuscule=open("tubeMajuscule",O_WRONLY))< 0) {
        exit(1);
    }

    int nb;
    // lit dans le tube
    while((nb=read(tCompress,&c,1)) >0){
        if(c != ' ')
            write(tMajuscule,&c,1); // sortie standard
    }
    
    close(tCompress);
    close(tMajuscule);
    exit(0);
}