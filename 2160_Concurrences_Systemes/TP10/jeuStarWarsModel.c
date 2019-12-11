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
int xJedi=0,yJedi=0;
int xRobot=0, yRobot=0;
int nbJediScore = 0;

void robot(){
	srand(time(NULL));
	xRobot  = rand() % COLS;
	yRobot  = rand() % LINES;

	int dx=rand() % 2;
	if (dx ==0) dx=-1;
	int dy=rand() % 2;
	if (dy ==0) dy=-1;

	while(true){
		int toClone =0;
		if(xRobot==0 || xRobot == COLS-1){
			dx = -dx;
			toClone = 1;
		}
		if(yRobot==0 || yRobot == LINES-1){
			dy = -dy;
			toClone = 1;
		}
		pthread_mutex_lock(&mutex);
		mvaddch(yRobot,xRobot,' ');
		xRobot+=dx;
		yRobot+=dy;
		mvaddch(yRobot,xRobot,'R');
		refresh();
		pthread_mutex_unlock(&mutex);
		usleep(250000);
	}
}

void jedi(){
	srand(time(NULL));
	xJedi  = rand() % COLS ;
	yJedi  = rand() % LINES ;
	int dx=0,dy=0;
	noecho();
	char c = getch();
	echo();
	while(c=='z' || c =='q' || c=='s' || c=='d' || c=='r'){
		if(c=='r'){
			pthread_t th;
			pthread_create(&th, NULL, robot, (int *) (0,0));
		}else{
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
			if((xJedi==0 && dx <0)  || (xJedi== COLS-1 && dx > 0)){
				dx = 0;
			}
			if((yJedi==0 && dy <0)  || (yJedi== LINES-1 && dy> 0)){
				dy = 0;
			}
			pthread_mutex_lock(&mutex);
			mvaddch(yJedi,xJedi,' ');
			xJedi+=dx;
			yJedi+=dy;
			mvaddch(yJedi,xJedi,'O');
			refresh();
			pthread_mutex_unlock(&mutex);
		}
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
		if(x==xJedi && y==yJedi){
			nbJediScore++;
			pthread_mutex_unlock(&mutex);
			pthread_exit(0);
		}else if (x==xRobot && y==yRobot){
			pthread_mutex_unlock(&mutex);
			pthread_exit(0);
		}else {
			mvaddch(y,x,'*');
			refresh();
			pthread_mutex_unlock(&mutex);
			usleep(250000);
		}
	}
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