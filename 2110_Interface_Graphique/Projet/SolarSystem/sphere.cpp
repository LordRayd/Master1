#include <QGLViewer/qglviewer.h>
#include <cmath>
#include "sphere.h"

#define LATS 34
#define LONGS 34

Sphere::Sphere(float radius_, const QColor &color_,float x_, float y_, float z_) {
    radius = radius_;
    color = color_;
    x = x_;
    y = y_;
    z = z_;
}

void Sphere::init(){
    x = 0.9f;
    y = 0.0f;
    z = 0.0f;
}

void Sphere::draw() {
    glColor3f(color.redF(), color.greenF(), color.blueF());
    glPointSize(10);
    glBegin(GL_POINTS);
    glVertex3f(x,y,z);
    glEnd();
}

void Sphere::animate(){
    x = 0.7 + cos((360/365)*day);
    y = 0.0f + sin((360/365)*day);
    z = 0.0f;
    day = (day +1)%365;
}
