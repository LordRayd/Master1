// jeuStarWars.c
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <curses.h>
#include <unistd.h>


// programme principal
int main(void){

	srand(time(NULL));

	initscr();
	clear();
	char cur=219;

	int x  = rand() % COLS ;
	int y  = rand() % LINES ;

	int dx=rand() % 2;
	if (dx ==0) dx=-1;
	int dy=rand() % 2;
	if (dy ==0) dy=-1;

	while (x >0 && x < COLS && y >0 && y < LINES) {
		mvaddch(y,x,' ');
		x+=dx;
		y+=dy;
		mvaddch(y,x,cur);
		refresh();
		usleep(250000);
	}



	endwin();
}

// Compilation et edition des liens
// gcc jeuStarWarModel.c   -lcurses -ltermcap