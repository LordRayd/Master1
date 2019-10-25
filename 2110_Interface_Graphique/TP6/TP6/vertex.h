#ifndef VERTEX_H
#define VERTEX_H
#include "glm.h"
#include "QGLViewer/vec.h"

using namespace qglviewer;

class Vertex
{
public:
    int x,y,z;
    Vec position;
    Vec normale;
    Vertex(int x,int y,int z, Vec normale);
    Vertex(int x,int y,int z);
    Vertex(Vec pos, Vec norm);
    Vertex();
};

#endif // VERTEX_H
