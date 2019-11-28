#include <iostream>
#include <stdlib.h>
#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include <math.h>
#include "view.h"
#include <qapplication.h>

/*
using namespace std;
void myInit() {
    glClearColor(1.0,1.0,1.0,0.0);
    glColor3f(0.0,0.0,0.0);
    glPointSize(3);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,640.0,0.0,500.0);

}

void myDisplay() {
    glClear(GL_COLOR_BUFFER_BIT);
    glFlush();
}*/

int main(int argc, char **argv) {
  // Read command lines arguments.
  QApplication application(argc, argv);

  // Instantiate the viewer.
  View viewer;

  viewer.setWindowTitle("simpleViewer");

  // Make the viewer window visible on screen.
  viewer.show();

  // Run main loop.
  return application.exec();
}
