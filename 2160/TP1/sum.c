#include<sys/wait.h>
#include<stdlib.h>
#include<unistd.h>
#include<stdio.h>
int longueur;

int sum(int numbers[], int remaining, int pos){
    int resultFromChild, currentValue, pid;
    int valueFromChild;
    if(remaining > 1){
        if((pid = fork()) == 0){
            currentValue = numbers[pos+1];
            sum(numbers, remaining-1,pos+1);
        }else{
            wait(&resultFromChild);
            valueFromChild = WEXITSTATUS(resultFromChild);
            if(remaining == longueur)printf("sum = %d\n", valueFromChild + numbers[pos]);
            exit(valueFromChild + numbers[pos]);
        }
    }else{
        if(remaining == longueur)printf("sum = %d\n", numbers[pos]);
        exit(numbers[pos]);
    }
}

int main(int argc, char * argv[]){
    int pid, result = 0, current;
    int numbers[argc-1];
    for(int i=1; i< argc; i++){
        numbers[i-1] = atoi(argv[i]);
    }
    longueur = argc-1;
    for(int i=0; i< argc-1; i++){
        printf("tab[%d] =  %d\n", i, numbers[i]);
    }
    printf("remaining: %d\n\n", argc-1);
    sum(numbers, argc-1, 0);
}