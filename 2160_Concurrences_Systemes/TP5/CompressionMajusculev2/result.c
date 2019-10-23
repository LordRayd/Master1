#include <fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include <stdlib.h>

int main ( int argc , char **argv ) {
    int nb,tResult,tCompress;
    char c;
    if ((tCompress=open("tubeCompress",O_WRONLY))< 0) {
        exit(1);
    }
    if ((tResult=open("tubeResult",O_RDONLY))< 0) {
        exit(1);
    }

    while(nb > 0){
        while((nb = read(0,&c,1)) >0){
            write(tCompress,&c,1);
        }
        if(nb > 0){
            while((nb = read(tResult,&c,1)) > 0)
                write(1,&c,1);
        }
    }
    

        /*read(tResult,&c,1);
        //while((read(tResult,&c,1)) > 0)
            write(1,&c,1);*/
    close(tCompress); 
    close(tResult); 
}