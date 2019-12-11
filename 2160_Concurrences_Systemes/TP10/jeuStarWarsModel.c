// jeuStarWars.c

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <curses.h>
#include <unistd.h>

#include <pthread.h>
#include <semaphore.h>

// semaphores
pthread_mutex_t mutex =PTHREAD_MUTEX_INITIALIZER;

void jedi(){
	srand(time(NULL));
	int	x  = rand() % COLS ;
	int	y  = rand() % LINES ;
	int dx=0,dy=0;
	noecho();
	char c = getch();
	echo();
	while(c=='z' || c =='q' || c=='s' || c=='d'){
		if(c=='s'){
			dx=0;
			dy=1;
		}else if(c=='q'){
			dx=-1;
			dy=0;
		}else if(c=='z'){
			dx=0;
			dy=-1;
		}else {
			dx=1;
			dy=0;
		}
		if((x==0 && dx <0)  || (x== COLS-1 && dx > 0)){
			dx = 0;
		}
		if((y==0 && dy <0)  || (y== LINES-1 && dy> 0)){
			dy = 0;
		}
		pthread_mutex_lock(&mutex);
		mvaddch(y,x,' ');
		x+=dx;
		y+=dy;
		mvaddch(y,x,'o');
		refresh();
		pthread_mutex_unlock(&mutex);
		noecho();
		c = getch();
		echo();
	}
}
void etoile(int x, int y){
	srand(time(NULL));
	if(x==0)
		x  = rand() % COLS;
	if(y==0)
		y  = rand() % LINES;

	int dx=rand() % 2;
	if (dx ==0) dx=-1;
	int dy=rand() % 2;
	if (dy ==0) dy=-1;

	while(true){
		int toClone =0;
		if(x==0 || x == COLS-1){
			dx = -dx;
			toClone = 1;
		}
		if(y==0 || y == LINES-1){
			dy = -dy;
			toClone = 1;
		}
		if(toClone==1){
			pthread_t th;
			pthread_create(&th, NULL, etoile, (int *) (0,0));
		}
		pthread_mutex_lock(&mutex);
		mvaddch(y,x,' ');
		x+=dx;
		y+=dy;
		mvaddch(y,x,'*');
		refresh();
		pthread_mutex_unlock(&mutex);
		usleep(250000);
	}
	pthread_exit(0);
}

// programme principal
int main(void){


	initscr();
	clear();

	pthread_t th1,th2,jed;
	pthread_create(&th1, NULL, etoile, (int *) (0,0));
	pthread_create(&th2, NULL, etoile, (int *) (10,10));
	pthread_create(&jed, NULL, jedi, (void *) NULL);
	pthread_join(th1, NULL);
	pthread_join(th2, NULL);
	pthread_join(jed, NULL);
	endwin();
}


// Compilation et edition des liens
// gcc jeuStarWarModel.c   -lcurses -ltermcap