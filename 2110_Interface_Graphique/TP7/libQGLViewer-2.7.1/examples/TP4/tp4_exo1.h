#ifndef TP4_EXO1_H
#define TP4_EXO1_H


#include <QGLViewer/qglviewer.h>

class ViewerEx : public QGLViewer {
public:
    float X1,Y1,Z1;
    float X2,Y2,Z2;
    float X3,Y3,Z3;
protected:
  virtual void draw();
  virtual void init();
  virtual QString helpString() const;
    virtual void keyPressEvent(QKeyEvent *e);
};

#endif // TP4_EXO1_H
