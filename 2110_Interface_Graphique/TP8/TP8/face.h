#ifndef FACE_H
#define FACE_H
#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include <../../QGLViewer/qglviewer.h>
#include <vertex.h>

class Face
{
public:
    Face();
    Face(Vertex* p1_, Vertex* p2_, Vertex* p3_, qglviewer::Vec normal_);
    Face(Vertex* p1_, Vertex* p2_, Vertex* p3_);
    Vertex* p1;
    Vertex* p2;
    Vertex* p3;
    qglviewer::Vec normal;
};

#endif // FACE_H
