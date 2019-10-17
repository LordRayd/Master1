#include "tp4_exo1.h"
#include <math.h>
#include "QGLViewer/vec.h"

using namespace qglviewer;
using namespace std;



void ViewerEx::keyPressEvent(QKeyEvent *e) {
    if (e->key() == Qt::Key_T) {
        this->X2 = this->X2 - this->X1;
        this->X3 = this->X3 - this->X1;
        this->X1 = this->X1 - this->X1;

        this->Y2 = this->Y2 - this->Y1;
        this->Y3 = this->Y3 - this->Y1;
        this->Y1 = this->Y1 - this->Y1;

        this->Z2 = this->Z2 - this->Z1;
        this->Z3 = this->Z3 - this->Z1;
        this->Z1 = this->Z1 - this->Z1;
    }else if(e->key() == Qt::Key_Y){

        float X2P = this->X2 - this->X1;
        float Z2P = this->Z2 - this->Z1;
        float D1 = sqrt (pow(Z2P,2) + pow(X2P,2));
        float z2 = this->Z2;
        float x2 = this->X2;

        float z1 = this->Z1;
        float x1 = this->X1;

        float z3 = this->Z3;
        float x3 = this->X3;

        this->X2 = (x2*(Z2P/D1))+ (z2*(-X2P/D1));
        this->Z2 = (x2*(X2P/D1)) + (z2*(Z2P/D1));

        this->X1 = (x1*(Z2P/D1))+ (z1*(-X2P/D1));
        this->Z1 = (x1*(X2P/D1)) + (x1*(Z2P/D1));

        this->X3 = (x3*(Z2P/D1))+ (z3*(-X2P/D1));
        this->Z3 = (x3*(X2P/D1)) + (x3*(Z2P/D1));

        update();
    }else if(e->key() == Qt::Key_X){

        float X2PP = this->X2 - this->X1;
        float Y2PP = this->Y2 - this->Y1;
        float Z2PP = this->Z2 - this->Z1;
        float D2 = sqrt (pow(X2PP,2) + pow(Y2PP,2) + pow(Z2PP,2));

        float z3 = this->Z3;
        float y3 = this->Y3;

        float z2 = this->Z2;
        float y2 = this->Y2;

        float z1 = this->Z1;
        float y1 = this->Y1;

        this->Y1 = (y1*(Z2PP/D2))+(z1*(-Y2PP/D2));
        this->Z1 = (y1*(Y2PP/D2))+(z1*(Z2PP/D2));

        this->Y2 = (y2*(Z2PP/D2))+(z2*(-Y2PP/D2));
        this->Z2 = (y2*(Y2PP/D2))+(z2*(Z2PP/D2));

        this->Y3 = (y3*(Z2PP/D2))+(z3*(-Y2PP/D2));
        this->Z3 = (y3*(Y2PP/D2))+(z3*(Z2PP/D2));

        update();
    }else if(e->key() == Qt::Key_Z){

        float X3PPP = this->X3 - this->X1;
        float Y3PPP = this->Y3 - this->Y1;
        float D3 = sqrt (pow(X3PPP,2) + pow(Y3PPP,2));

        float x3 = this->X3;
        float y3 = this->Y3;

        float x2 = this->X2;
        float y2 = this->Y2;

        float x1 = this->X1;
        float y1 = this->Y1;

        this->X3 = (x3*(y3/D3))+(y3*(-x3/D3));
        this->Y3 = (x3*(x3/D3))+(y3*(y3/D3));

        update();

    }
}

void ViewerEx::draw()
{
    Vec P1 = Vec(this->X1,this->Y1,this->Z1);
    Vec P2 = Vec(this->X2,this->Y2,this->Z2);
    Vec P3 = Vec(this->X3,this->Y3,this->Z3);
    glColor3f(1,0,0);
    glBegin(GL_LINES);
        glVertex3fv(P1);
        glVertex3fv(P2);
        glVertex3fv(P1);
        glVertex3fv(P3);
    glEnd();
}


void ViewerEx::init() {
    // Restore previous viewer state.
    restoreStateFromFile();
    this->X1 = 3;
    this->Y1 = 4;
    this->Z1 = 2;

    this->X2 = 5;
    this->Y2 = 5;
    this->Z2 = 3;

    this->X3 = 4;
    this->Y3 = 6;
    this->Z3 = 1;

    setSceneRadius(10);
    showEntireScene();
    // Opens help window
    help();
}

QString ViewerEx::helpString() const {
  QString text("<h2>S i m p l e V i e w e r</h2>");
  text += "Use the mouse to move the camera around the object. ";
  text += "You can respectively revolve around, zoom and translate with the "
          "three mouse buttons. ";
  text += "Left and middle buttons pressed together rotate around the camera "
          "view direction axis<br><br>";
  text += "Pressing <b>Alt</b> and one of the function keys "
          "(<b>F1</b>..<b>F12</b>) defines a camera keyFrame. ";
  text += "Simply press the function key again to restore it. Several "
          "keyFrames define a ";
  text += "camera path. Paths are saved when you quit the application and "
          "restored at next start.<br><br>";
  text +=
      "Press <b>F</b> to display the frame rate, <b>A</b> for the world axis, ";
  text += "<b>Alt+Return</b> for full screen mode and <b>Control+S</b> to save "
          "a snapshot. ";
  text += "See the <b>Keyboard</b> tab in this window for a complete shortcut "
          "list.<br><br>";
  text += "Double clicks automates single click actions: A left button double "
          "click aligns the closer axis with the camera (if close enough). ";
  text += "A middle button double click fits the zoom of the camera and the "
          "right button re-centers the scene.<br><br>";
  text += "A left button double click while holding right button pressed "
          "defines the camera <i>Revolve Around Point</i>. ";
  text += "See the <b>Mouse</b> tab and the documentation web pages for "
          "details.<br><br>";
  text += "Press <b>Escape</b> to exit the viewer.";
  return text;
}

