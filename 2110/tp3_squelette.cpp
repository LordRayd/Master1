//
//  tp3_squelette.cpp
//  
//
//  Created by Caroline Larboulette on 16/10/2017.
// gcc tp3_squelette.cpp -o tp3.exe -lglut -lGL -lGLU
//

//Those includes are the good ones for Mac OSX to use OpenGL and GLUT (The OpenGL Utility Toolkit) -- to make windows among other things.
#include <stdio.h>
#include <stdlib.h>

#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include <math.h>       /* cos */

#define PI 3.14159265
int _width = 10;
int _height = 10;
double R[16];
double T[16];
double S[16];

double * translation(double x, double y,double z){
    T = {
        1,0,0,0,
        0,1,0,0,
        0,0,1,0,
        x,y,z,1
    };
    return T;
}

double * scale(double x, double y,double z){
    S = {
        x,0,0,0,
        0,y,0,0,
        0,0,z,0,
        0,0,0,1
    };
    return S;
}

double * rotation(double angle, double x, double y, double z){
    R ={
        cos(angle*PI/180),sin(angle*PI/180),0,0,
        -sin(angle*PI/180),cos(angle*PI/180),0,0,
        0,0,1,0,
        0,0,0,1
    };
    return R;
}

void draw(){
    glBegin(GL_POLYGON);
    glVertex3f(2,6,0);
    glVertex3f(2,3,0);
    glVertex3f(4,3,0);
    glVertex3f(4,6,0);
    glVertex3f(3,7,0);
    glVertex3f(2,6,0);
    glVertex3f(4,6,0);
    glEnd();
}

/**
 void display(void)
 Draw function to render the scene
 **/
void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    
    glLoadIdentity();
    glColor3f(1.0, 0.0, 0.0);
    glLineWidth(2.0);
    glBegin(GL_LINES); //Draws x-axis
    glVertex3f(-_width,0,0);
    glVertex3f(_width,0,0);
    glEnd();

    glLoadIdentity();
    glColor3f(0.0, 1.0, 0.0);
    glBegin(GL_LINES); //Draws y-axis
    glVertex3f(0,-_height,0);
    glVertex3f(0,_height,0);
    glEnd();

    glLoadIdentity();
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    draw();
    /*-
    glLoadIdentity();
    glMatrixMode(GL_MODELVIEW);
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    glColor3f(2.0,2.0,1.0);
    glTranslatef(4, 0, 0);
    draw();
    //Rotate
    glLoadIdentity();
    glMatrixMode(GL_MODELVIEW);
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    glColor3f(0.0,0.0,1.0);
    glRotatef(100,0,0,1);
    draw();
    //Scale
    glLoadIdentity();
    glMatrixMode(GL_MODELVIEW);
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    glColor3f(1.0,0.0,0.0);
    glScalef(1.5,1.5,1);
    draw();*/
  
    glLoadIdentity();
    glLoadMatrixd(rotation(100,0,0,1));
    draw();
    glFlush();
}



void reshape(int w, int h)
{
    _width = w/2.; //Used to draw the axis with correct length
    _height = h/2.;
    glViewport(0, 0, (GLsizei) w, (GLsizei) h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho (-w/20., w/20., -h/20., h/20., -1.0, 1.0);
    glMatrixMode(GL_MODELVIEW); //Ready to transform the object matrix
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
    glPolygonMode(GL_FRONT, GL_LINE); //Polygons are outlined by default
    glMatrixMode(GL_MODELVIEW);

}

/**
 void keyboard(unsigned char key, int x, int y)
 key returns the character hit by the user
 **/
void keyboard(unsigned char key, int x, int y)
{
    switch (key)
    {
        case 'q': 
            exit(0); 
            break;  //exits the program
        case 'f': 
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); 
            display();
            break;
        case 'p': 
            glPolygonMode(GL_FRONT_AND_BACK, GL_POINT); 
            display();
            break;
        case 'l': 
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); 
            display();
            break;
        }
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
    glutReshapeFunc(reshape);
    glutKeyboardFunc(keyboard);
    glutMainLoop(); //Calls the rendering loop
    return 0;
}

