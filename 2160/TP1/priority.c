#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>
#include <sys/time.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sched.h>
#include <time.h>

void calcul(nbProccess){
    int n,i,j,x;
    pid_t pid;
    clock_t start_t, end_t, total_t;
    struct timeval start, end;
    for (n = 0; n < nbProccess; n++){
        if((pid = fork()) == 0){
            start_t = clock();
            gettimeofday(&start, NULL);
            if(n%2 != 0) setpriority(PRIO_PROCESS,pid,19);
            for (i = 1; i <= 100; i++)
            {
                for (j = 0; j < 1000000; j++) x=1./i;
            }
            end_t = clock();
            gettimeofday(&end, NULL);
            total_t = (double)(end_t - start_t) / CLOCKS_PER_SEC;
            printf("temps clock %d: %f\n",n,total_t);
            printf("temps timeofday %d : %ld\n\n",n, (end.tv_sec-start.tv_sec) * 1000000 + end.tv_usec - start.tv_usec);
            exit(0);
        }
    }
    int c = getchar();
}

int main (int argc,char **argv) {
    calcul(100);
}