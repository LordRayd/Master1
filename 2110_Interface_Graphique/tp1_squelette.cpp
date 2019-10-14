//
//  tp1_squelette.cpp
//  
//
//  Created by Caroline Larboulette on 25/09/2017.
//
//

//Those includes are the good ones for Mac OSX to use OpenGL and GLUT (The OpenGL Utility Toolkit) -- to make windows among other things.
//Those includes are the good ones for Mac OSX to use OpenGL and GLUT (The OpenGL Utility Toolkit) -- to make windows among other things.
#ifdef __gnu_linux__
    #include <GL/gl.h>
    #include <GL/glut.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif



void drawLine(int x1, int y1, int x2, int y2){
    glBegin(GL_LINES);
    glColor3f(1.0, 6.0, 1.0); //White color
    glVertex2f(x1, y1);
    glVertex2f(x2, y2);
    glEnd();
}

void drawGrid(void){
    
}

/**
 void display(void)
 Draw function to render the scene
 **/
void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    
    
    /*glBegin(GL_POINTS); //Draws points
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glEnd();

    glBegin(GL_LINES); //Draws lines
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glEnd();

    glBegin(GL_LINE_STRIP); //Draws line
    glLineWidth(130);
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glEnd();

    glBegin(GL_TRIANGLES); //Draws triangla
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,2,0);
    glEnd();

    glBegin(GL_TRIANGLE_STRIP); //Draws triangla
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,2,0);
    glEnd();

    glBegin(GL_TRIANGLE_FAN); //Draws tringles
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,2,0);
    glEnd();

    glBegin(GL_QUADS); //Draws 4 points
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,-2,0);
    glVertex3f(5,-4,0);
    glEnd();

    glBegin(GL_QUAD_STRIP); //Draws 4points
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,-2,0);
    glVertex3f(5,-4,0);
    glEnd();*/

    glBegin(GL_POLYGON); //Draws 8 points
    glColor3f(3.0,0.0,0); //red 
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glVertex3f(2,-2,0);
    glVertex3f(5,-4,0);
    glColor3f(0.0,5.0,0); //green au changement de couleur un degradé apparait
    glVertex3f(-1,-1,0);
    glVertex3f(-3,-4,0);
    glColor3f(0.0,0.0,5); //blue
    glPointSize(10.0); // elargit le champ d'un point (couleur)
    glVertex3f(-2,+2,0);
    glVertex3f(-5,+4,0);
    glEnd();

    //drawLine(0,0,3,3);

    glFlush();
}

/**
 void init(void)
 Init function where you load your scene or create procedural objects
 **/
void init(void)
{
    glClearColor(0.0, 0.0, 0.0, 0.0); //Background color
    glMatrixMode(GL_PROJECTION); //Projection matrix
    glLoadIdentity();
    //left, right, bottom, top, near, far
    glOrtho(-5.0, 5.0, -5.0, 5.0, -1.0, 1.0);
}


int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize (250, 250); //Window size in pixels
    glutInitWindowPosition (100, 100); //Window position on screen in pixels
    glutCreateWindow ("Bonjour les amis !"); //Creates a window with the title of your choice^^
    init();
    glutDisplayFunc(display);
    //glutDisplayFunc(drawGrid);
    glutMainLoop(); //Calls the rendering loop
    return 0;
}

