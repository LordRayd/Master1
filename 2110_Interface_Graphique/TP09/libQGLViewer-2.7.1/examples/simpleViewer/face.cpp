#include "face.h"

using namespace qglviewer;

Face::Face(Vertex* p1_, Vertex* p2_, Vertex* p3_,GLfloat u1_,GLfloat u2_, GLfloat u3_,GLfloat v1_,GLfloat v2_, GLfloat v3_, Vec normal_)
{
    this->p1 = p1_;
    this->p2 = p2_;
    this->p3 = p3_;
    this->u1 = u1_;
    this->u2 = u2_;
    this->u3 = u3_;
    this->v1 = v1_;
    this->v2 = v2_;
    this->v3 = v3_;
    this->normal = normal_;
}

Face::Face(Vertex* p1_, Vertex* p2_, Vertex* p3_, GLfloat u1_,GLfloat u2_, GLfloat u3_,GLfloat v1_,GLfloat v2_, GLfloat v3_)
{
    this->p1 = p1_;
    this->p2 = p2_;
    this->p3 = p3_;
    this->u1 = u1_;
    this->u2 = u2_;
    this->u3 = u3_;
    this->v1 = v1_;
    this->v2 = v2_;
    this->v3 = v3_;
}
