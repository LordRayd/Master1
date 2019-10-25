#include "vertex.h"

Vertex::Vertex(){

}
Vertex::Vertex(int x, int y, int z, Vec normale)
{
    this->x = x;
    this->y = y;
    this->z = z;
    this->normale = normale;
}
Vertex::Vertex(int x,int y,int z){
    this->x = x;
    this->y = y;
    this->z = z;
}
Vertex::Vertex(Vec pos, Vec norm){
    this->normale=norm;
    this->position = pos;
}
