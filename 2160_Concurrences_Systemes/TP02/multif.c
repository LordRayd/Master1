//  multif.c
//  affiche sur la sortie standard un ensemble de fichiers
//  chaque fichier est pris en charge par un processus
//  
//  les affichages concurrents surt la sortie standard
//  sont synchronises par un semaphore d'exclusion mutuelle
//
//  Luc courtrai
//

#include <unistd.h> // appel systeme fork
#include <fcntl.h>  // appel system unix ES
#include <stdio.h> // librairie standard C
#include <stdlib.h> // exit
#include <sched.h>  // sche_yield
#include <sys/types.h>
#include <sys/sem.h> // semaphore IPC
#include <sys/ipc.h> // services IPC
#include <sys/wait.h> // wait

key_t cle; /* cle ipc */

int semid; /* nom local de l'ensemble des semaphores */


int MAX = 128 // taille du buffer

//  out
// affiche un fichier sur la sortie standard
// le nom du fichier est passe en parametre

void out(char *s) {
 int f; // descriteru de fichier
 char buf[MAX]; // buffer
 int nb; // nb de caracteres lus
 // ouverture
 if ((f=open(s,O_RDONLY)) >=0) {
   // lecture d'un bloc
    while((nb =read(f,buf,128)) >0) {
      
     // affichage du blocs
     struct sembuf op; // operation sur un semaphore
     /*P(&mutex); demande l'acces la sortie standard */
     op.sem_num=0;op.sem_op=-1;op.sem_flg=0;
     semop(semid,&op,1);
    
     // ecrit le bloc
     write (1,buf,nb);

     /*V(&mutex);*/
     op.sem_num=0;op.sem_op=1;op.sem_flg=0;
     semop(semid,&op,1);

    }
    // fermeture du fichier
    close(f);

  }
}


// programme principal
int main ( int argc , char **argv ) {

  int pid; // numero des fils

  
  ushort init_sem[1]={1}; //strucutre par initialise le semaphore mutex

  // creation d'une cle IPC en fonction du nom du programme
  if ((cle=ftok(argv[0],'0')) == -1 ) {
    fprintf(stderr,"Probleme sur ftoks\n");
    exit(1);
  }

  // demande un ensemble de semaphore (ici un seul mutex)
  if ((semid=semget(cle,1,IPC_CREAT|IPC_EXCL|0666))==-1) {
    fprintf(stderr,"Probleme sur semget\n");
    exit(2);
  } 

  // initialise l'ensemble
  if (semctl(semid,1,SETALL,init_sem)==-1) {
    fprintf(stderr,"Probleme sur semctl SETALL\n");
    exit(3);
  }




  {

  int i;
  for (i=1 ;i < argc; i++) { // boucle sur les argument
      if ((pid =fork())==0) { // creation d'un processus fils
        // processus fils
        out(argv[i]);  //traite le fichier
        exit(0);  
      }
  } 

  // attend la terminaison des fils
  for (i=1 ;i < argc; i++){
    int message;
    pid=wait(&message);
  } 

  }
  /* liberation du s\'emaphore */
   semctl(semid,0,IPC_RMID,0);


}
