#ifndef SPHERE_H
#define SPHERE_H


class Sphere
{
public:
    Sphere(float radius_, const QColor &color_, float x, float y, float z);
    float radius,x,y,z;
    int day;
    QColor color;
    virtual void animate();
    virtual void draw();
    virtual void init();
};

#endif // SPHERE_H
