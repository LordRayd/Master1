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
    Face(Vertex* p1_, Vertex* p2_, Vertex* p3_, GLfloat u1_,GLfloat u2_, GLfloat u3_,GLfloat v1_,GLfloat v2_, GLfloat v3_, qglviewer::Vec normal_);
    Face(Vertex* p1_, Vertex* p2_, Vertex* p3_, GLfloat u1_,GLfloat u2_, GLfloat u3_,GLfloat v1_,GLfloat v2_, GLfloat v3_);
    Vertex* p1;
    Vertex* p2;
    Vertex* p3;
    GLfloat u1;
    GLfloat v1;
    GLfloat u2;
    GLfloat v2;
    GLfloat u3;
    GLfloat v3;
    qglviewer::Vec normal;
};

#endif // FACE_H
