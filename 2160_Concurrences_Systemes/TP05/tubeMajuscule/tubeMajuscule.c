#include <fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>
#include <stdlib.h>

int main ( int argc , char **argv ) {
    int t;
    char c;
    if ((t=open("tubeMajuscule",O_RDONLY))< 0) {
        exit(1);
    }    
    while((read(t,&c,1)) > 0){
        c = toupper(c);
        write(1,&c,1);
    }
    close(t); 
}