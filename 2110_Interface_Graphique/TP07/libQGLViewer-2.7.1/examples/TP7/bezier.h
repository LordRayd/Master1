#ifndef BEZIER_H
#define BEZIER_H


#include <QGLViewer/qglviewer.h>

#include "QGLViewer/vec.h"
class bezier: public QGLViewer{
public:
protected:
    virtual void draw();
    virtual void init();
    virtual void drawBezier();
};

#endif // BEZIER_H
