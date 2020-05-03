#ifndef FACE_H
#define FACE_H
#include "vertex.h"
#include "QGLViewer/vec.h"

using namespace qglviewer;
class Face
{
public:
    Vertex *p1, *p2, *p3;
    Vec normale;
    Face(Vertex* p1, Vertex* p2, Vertex *p3);
};

#endif // FACE_H
