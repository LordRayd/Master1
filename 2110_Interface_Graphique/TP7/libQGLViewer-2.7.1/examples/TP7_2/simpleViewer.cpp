/****************************************************************************

 Copyright (C) 2002-2014 Gilles Debunne. All rights reserved.

 This file is part of the QGLViewer library version 2.7.1.

 http://www.libqglviewer.com - contact@libqglviewer.com

 This file may be used under the terms of the GNU General Public License 
 versions 2.0 or 3.0 as published by the Free Software Foundation and
 appearing in the LICENSE file included in the packaging of this file.
 In addition, as a special exception, Gilles Debunne gives you certain 
 additional rights, described in the file GPL_EXCEPTION in this package.

 libQGLViewer uses dual licensing. Commercial/proprietary software must
 purchase a libQGLViewer Commercial License.

 This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.

*****************************************************************************/

#include "simpleViewer.h"
#include "math.h"

#include "QGLViewer/vec.h"

using namespace std;
using namespace qglviewer;

double f1(double t){
    return pow(1-t,3);
}

double f2(double t){
    return 3*t*pow(1-t,2);
}

double f3(double t){
    return 3*(1-t)*pow(t,2);
}

double f4(double t){
    return pow(t,3);
}

float binomial(int n, int x){
    int facN = 1;
    for(int i=n; i>1; i--){
        facN = facN*i;
    }
    int facI = 1;
    for(int i=x; i>1; i--){
        facI = facI*i;
    }
    int facNI = 1;
    for(int i=(n-x); i>1; i--){
        facNI = facNI * i;
    }
    float ret = facN / (facI*facNI);

    return ret;
}

float bernstein_poly(int u, int n , int x){
    float bin = binomial(n,x);
    return bin *pow(u,x) * pow((1-u),n-x);
}

void Viewer::draw(){
    //drawBezier();
    drawSurface();
}

void Viewer::drawSurface(){

    Vec p1 = Vec(1,2,3);
    Vec p2 = Vec(6,1,3);
    Vec p3 = Vec(7,6,3);
    Vec p4 = Vec(13,3,3);

    Vec p5 = Vec(3,2,3);
    Vec p6 = Vec(8,1,3);
    Vec p7 = Vec(9,6,6);
    Vec p8 = Vec(15,3,3);

    Vec p9 = Vec(5,2,3);
    Vec p10 = Vec(10,4,3);
    Vec p11 = Vec(11,6,3);
    Vec p12 = Vec(17,3,5);

    Vec p13 = Vec(7,2,1);
    Vec p14 = Vec(12,1,3);
    Vec p15 = Vec(13,6,3);
    Vec p16 = Vec(19,3,3);

    vector<vector<Vec>> matrice = vector<vector<Vec>>();
    vector<Vec> ligne0 = vector<Vec>();
    ligne0.push_back(p1);
    ligne0.push_back(p2);
    ligne0.push_back(p3);
    ligne0.push_back(p4);
    vector<Vec> ligne1 = vector<Vec>();
    ligne1.push_back(p5);
    ligne1.push_back(p6);
    ligne1.push_back(p7);
    ligne1.push_back(p8);
    vector<Vec> ligne2 = vector<Vec>();
    ligne2.push_back(p9);
    ligne2.push_back(p10);
    ligne2.push_back(p11);
    ligne2.push_back(p12);
    vector<Vec> ligne3 = vector<Vec>();
    ligne3.push_back(p13);
    ligne3.push_back(p14);
    ligne3.push_back(p15);
    ligne3.push_back(p16);

    matrice.push_back(ligne0);
    matrice.push_back(ligne1);
    matrice.push_back(ligne2);
    matrice.push_back(ligne3);

    glColor3f(1,0,1);
    glPointSize(3);
    glBegin(GL_POINTS);
    glVertex3fv(p1);
    glVertex3fv(p2);
    glVertex3fv(p3);
    glVertex3fv(p4);
    glVertex3fv(p5);
    glVertex3fv(p6);
    glVertex3fv(p7);
    glVertex3fv(p8);
    glVertex3fv(p9);
    glVertex3fv(p10);
    glVertex3fv(p11);
    glVertex3fv(p12);
    glVertex3fv(p13);
    glVertex3fv(p14);
    glVertex3fv(p15);
    glVertex3fv(p16);
    glEnd();


    glBegin(GL_POINTS);
    int nbPoints = 100;
    double resI = 0, resJ = 0;
    for(int u=0; u<nbPoints; u++){
        for(int v=0; v<nbPoints; v++){
            double x=0,y=0,z=0;
            for(int i=0; i<4; i++){
                switch(i){
                    case 0 :
                        resI=f1(u/nbPoints);
                        break;
                    case 1 :
                        resI=f2(u/nbPoints);
                        break;
                    case 2 :
                        resI=f3(u/nbPoints);
                        break;
                    case 3 :
                        resI=f4(u/nbPoints);
                        break;
                }
                for(int j=0; j<4;j++){
                    switch(j){
                       case 0 :
                           resJ=f1(v/nbPoints);
                           break;
                       case 1 :
                           resJ=f2(v/nbPoints);
                           break;
                       case 2 :
                           resJ=f3(v/nbPoints);
                           break;
                       case 3 :
                           resJ=f4(v/nbPoints);
                           break;
                    }
                   x=x+(resI*resJ*matrice.at(i).at(j).x);
                   y=y+(resI*resJ*matrice.at(i).at(j).y);
                   z=z+(resI*resJ*matrice.at(i).at(j).z);
                }
            }
            glVertex3d(x,y,z);
        }
    }
    glEnd();
    glFlush();
}


// Draws a spiral
void Viewer::drawBezier() { // Marche pas forcement
    Vec p1 = Vec(1,2,3);
    Vec p2 = Vec(6,1,3);
    Vec p3 = Vec(7,6,3);
    Vec p4 = Vec(13,3,3);

    unsigned int i;
    vector<Vec> courbe = vector<Vec>();
    for(i=0; i<=10;i++){
        float t = i/10.0;
        courbe.push_back(p1*pow(1-t,3) + 3*p2*t*pow(1-t, 2.0) + 3*pow(t,2)*(1-t)*p3 + pow(t,3.0)*p4);
    }
    glColor3f(1,0,1);
    glPointSize(3);
    glBegin(GL_POINTS);
    glVertex3fv(p1);
    glVertex3fv(p2);
    glVertex3fv(p3);
    glVertex3fv(p4);
    glEnd();
    glColor3f(1,1,1);
    glBegin(GL_LINE_STRIP);
    for(i=0; i<courbe.size();i++){
        glVertex3fv(courbe.at(i));
    }
    glEnd();
    glFlush();
}

void Viewer::init() {
    // Restore previous viewer state.
    restoreStateFromFile();
    setSceneRadius(10);
    showEntireScene();
    glDisable(GL_LIGHTING);
    // Opens help window
    draw();
}

QString Viewer::helpString() const {
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
