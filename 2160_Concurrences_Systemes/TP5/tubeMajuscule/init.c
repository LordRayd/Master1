#include<unistd.h>
#include<sys/types.h>
#include<sys/stat.h>

int main (int argc,char **argv) {
    mode_t mode=S_IRUSR|S_IWUSR|S_IRGRP;
    // creation du tube
    if (mkfifo("tubeMajuscule",mode) == -1) {
        perror( "mkfifo");
        exit(1);
    }
    
}