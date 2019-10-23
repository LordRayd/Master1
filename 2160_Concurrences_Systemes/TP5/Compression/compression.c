#include<unistd.h> 
int main ( ) {
    pid_t pid;
    int tube[2];// 2 descriteurs pour le tube
    // creation du tube
    if (pipe(tube) == -1) {
        perror( "pipe");
        exit(1);
    }
    // creation d ’un fils
    if ((pid=fork())> 0) {
        char c;
        int nb;
        // processus pere     
        close(tube[0]); // ferme la lecture
        while((nb=read(0,&c,1)) >0)
            write(tube[1],&c,1); // ecrit dans le tube
        close(tube[1]);
    } else {
        //  processus fils 
        char c;
        int nb;close(tube[1]); // ferme l’écriture
        // lit dans le tube
        while((nb=read(tube[0],&c,1)) >0)
            if(c != ' ')
                write(1,&c,1); // sortie standard
        close(tube[0]);
        exit(0);
    } //else
} // main