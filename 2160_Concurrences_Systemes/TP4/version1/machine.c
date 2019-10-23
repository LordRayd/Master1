//    ramdom.c
//
// exemple d'utilisation du generateur de nombres aleatoires
//
// Luc Courtrai

#include<sys/time.h>
#include<stdio.h>

int main(void) {

  int i =0;

  int init; // valeur pour initialiser le generateur   

  // recupere l'heure systeme pour initialiser le generateur
  time_t t;  //strut
  init= time(&t);
  int val ;

  random(init);  // initialisation du generateur

  while ( i++ <3){
    if(fork() == 0){
      val = random()%10;
      while(1){
        if(getchar()){
          break;
        }
        val = (val+1)%10;
        usleep(((random()%100)+100)*1000);
        printf("val %d : %d",i, val);
      }
      printf("val final %d : %d",i, val);
      exit(0);
    }
  }
  int x ;
  for(x=0; x<3; x++){
    wait(NULL);
  }
    //printf("%ld \n",random()%10);  // genere un nombre aleatoire

}