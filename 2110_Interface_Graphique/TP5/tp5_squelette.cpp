//
//  tp5_squelette.cpp
//  
// 
//  Created by Caroline Larboulette on 12/11/2017.
//
//  g++ tp5_squelette.cpp -framework OpenGL -framework GLUT

#include <stdio.h>
#include <stdlib.h>

#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif

using namespace std;

double x = 30;
double y = 20;
double z = 70;

void drawHouse()
{
    glColor3f(1.0, 1.0, 1.0);
    //Add your code here !
    glBegin(GL_LINES);

        //BAS
        glVertex3f(0.0,0.0,30.0);
        glVertex3f(0.0,0.0,54.0);

        glVertex3f(0.0,0.0,30.0);
        glVertex3f(16.0,0.0,30.0);

        glVertex3f(16.0,0.0,30.0);
        glVertex3f(16.0,0.0,54.0);

        glVertex3f(0.0,0.0,54.0);
        glVertex3f(16.0,0.0,54.0);

        //HAUT
        glVertex3f(0.0,10.0,30.0);
        glVertex3f(0.0,10.0,54.0);

        glVertex3f(0.0,10.0,30.0);
        glVertex3f(16.0,10.0,30.0);

        glVertex3f(16.0,10.0,30.0);
        glVertex3f(16.0,10.0,54.0);

        glVertex3f(0.0,10.0,54.0);
        glVertex3f(16.0,10.0,54.0);

        //TOIT
        glVertex3f(8.0,16.0,30.0);
        glVertex3f(8.0,16.0,54.0);

        glVertex3f(8.0,16.0,54.0);
        glVertex3f(16.0,10.0,54.0);

        glVertex3f(8.0,16.0,54.0);
        glVertex3f(0.0,10.0,54.0);

        
        glVertex3f(8.0,16.0,30.0);
        glVertex3f(0.0,10.0,30.0);

        
        glVertex3f(8.0,16.0,30.0);
        glVertex3f(16.0,10.0,30.0);

        //Arrete bas haut
        glVertex3f(0.0,0.0,30.0);
        glVertex3f(0.0,10.0,30.0);
        
        
        glVertex3f(16.0,0.0,30.0);
        glVertex3f(16.0,10.0,30.0);

        
        glVertex3f(16.0,0.0,54.0);
        glVertex3f(16.0,10.0,54.0);
        
        glVertex3f(0.0,0.0,54.0);
        glVertex3f(0.0,10.0,54.0);

    glEnd();

}

void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    
    glLoadIdentity();
    gluLookAt(x,y,z,0,0,30,0,1,0);// Add parameters here that are not default ones
   
    //Draw axes
    glColor3f(1.0, 0.0, 0.0);
    glLineWidth(2.0);
    glBegin(GL_LINES); //Draws x-axis
        glVertex3f(-50,0,0);
        glVertex3f(50,0,0);
    glEnd();
    
    glColor3f(0.0, 1.0, 0.0);
    glBegin(GL_LINES); //Draws y-axis
        glVertex3f(0,-50,0);
        glVertex3f(0,50,0);
    glEnd();
    
    glColor3f(0.0, 0.0, 1.0);
    glBegin(GL_LINES); //Draws z-axis
        glVertex3f(0,0,-50);
        glVertex3f(0,0,50);
    glEnd();
  
    drawHouse();
    
    glFlush();
}

void reshape(int w, int h)
{

    glViewport(0, 0, (GLsizei) w, (GLsizei) h);
  
    glMatrixMode(GL_MODELVIEW); //Ready to transform the object matrix
}

void init(void)
{
    glClearColor(0.0, 0.0, 0.0, 0.0); //Background color
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    //perspective projection left right bottom top near far
    //Change parameters to see your object
    //glFrustum(-50.0,50.0,-50.0,50.0,0,10);
    gluPerspective(90,16/9,0,10);
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
        case 'z' : {
            x = x+1;
            break;
        }
        case 's' : {
            x = x-1;
            break;
        } 
        case 'q' : {
            z = z-1;
            break;
        }
        case 'd' : {
            z = z+1;
            break;
        }
        case 'a' : {
            y = y-1;
            break;
        }
        case 'e' : {
            y = y+1;
            break;
        }
        case 'q': exit(0);  //exits the program
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