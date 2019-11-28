#ifndef VIEW_H
#define VIEW_H
#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include <../../QGLViewer/qglviewer.h>
#include <vertex.h>
#include <face.h>
#include <stdlib.h>
#include <stdio.h>
#include <objparser.h>
//#include <GL/gl.h>
//#include <../../../freeglut-3.2.1/include/GL/glut.h>
//#include <GL/glut.h>

class View : public QGLViewer
{
public:
    View();

    virtual void draw();
    virtual void init();
    virtual void materiel();
    virtual void model();
    virtual void light();

    ObjParser parser;
};

#endif // VIEW_H
