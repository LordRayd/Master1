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

int _width = 10;
int _height = 10;


/**
 void display(void)
 Draw function to render the scene
 **/
void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    
    glColor3f(1.0, 0.0, 0.0);
    glLineWidth(2.0);
    glBegin(GL_LINES); //Draws x-axis
    glVertex3f(-_width,0,0);
    glVertex3f(_width,0,0);
    glEnd();

    glColor3f(0.0, 1.0, 0.0);
    glBegin(GL_LINES); //Draws y-axis
    glVertex3f(0,-_height,0);
    glVertex3f(0,_height,0);
    glEnd();

    glColor3f(1.0,1.0,1.0);
    glBegin(GL_POLYGON);
    glVertex3f(2,3,0);
    glVertex3f(2,6,0);
    glVertex3f(4,6,0);
    glVertex3f(4,3,0);
    glEnd();
    
  
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
        case 'q': exit(0);  //exits the program
        case 'f': 
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); 
            display();
            reshape(_width,_height);
            break;
        case 'p': 
            init();
            glPolygonMode(GL_FRONT_AND_BACK, GL_POINT); 
            display();
            reshape(_width,_height);
            break;
        case 'l': 
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); 
            display();
            reshape(_width,_height);
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

