#ifndef VERTEX_H
#define VERTEX_H
#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include <../../QGLViewer/qglviewer.h>

class Vertex
{
public:
    Vertex();
    Vertex(double x_, double y_, double z_, qglviewer::Vec normal_);
    Vertex(double x_, double y_, double z_);
    void setNormal(qglviewer::Vec normal_);

    qglviewer::Vec position;
    qglviewer::Vec normal;

};

#endif // VERTEX_H
