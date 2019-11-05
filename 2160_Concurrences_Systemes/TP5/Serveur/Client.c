
#define MAX 128
#define FIFO_FILE "tubeCommun"

char date[MAX];
char tube[MAX];

void getDate(char *date){
    int f2;

    if((f2 = open(tube, O_RDONLY)) < 0){
        printf("Erreur ouverture tube");
        exit(1);
    }
    read(f2,date,MAX);

    close(f2)
}

int main(int argc, char * argv[]){
    int f1;
    char buf[MAX];
    int pid = getpid();

    sprintf(tube, "/tmp/tube_%d",pid);

    umask(0);
    mknod(tube, S_IFIFO|0666, 0);

    if(( f1 = open(FIFO_FILE, O_WRONLY)) < 0){
        printf("Erreur ouverture tube serveur commun");
        exit(1);
    }

    write(f1,&pid,sizeof(int));

    close(f1);

    getDate(date);

    printf("%s\n", date);

    remove(tube);
}