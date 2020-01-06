#include "exo1.h"
#include <qapplication.h>
#include <stdio.h>
#include <stdlib.h>

#ifdef __gnu_linux__
    #include <GL/gl.h>
    #include <GL/glut.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
int main(int argc, char** argv){

  int* pargc=&argc;
  glutInit(pargc,argv);

  QApplication application(argc,argv);

  Viewer viewer;

  viewer.setWindowTitle("Snake");

  viewer.show();

  return application.exec();
}

