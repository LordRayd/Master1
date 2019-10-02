#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>
#include <sys/time.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sched.h>
#include <time.h>

void glue(){
    int x,j,i;
    pid_t pid,pidPere,pidGrp;
    if((pidPere = fork()) == 0){
        printf("gid : %u\n",getpgid(pidPere));
        pidGrp = getpgid(pidPere)+1;
        setpgid(pidPere, pidGrp);
        printf("gid : %u\n",pidGrp);
        
        while((pid =fork())>=0){
            printf("pid : %u\n",pid);
            for (i = 1; i <= 10; i++)
            {
                for (j = 0; j < 1000; j++) x=1./i;
            }
        }
    }
    int c = getchar();
    kill(-pidGrp,SIGKILL);
}

int main (int argc,char **argv) {
    glue();
}