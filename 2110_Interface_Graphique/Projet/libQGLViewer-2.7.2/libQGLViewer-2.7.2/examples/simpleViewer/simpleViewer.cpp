/****************************************************************************

 Copyright (C) 2002-2014 Gilles Debunne. All rights reserved.

 This file is part of the QGLViewer library version 2.7.2.

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
#include <unistd.h>

using namespace std;

// Draws a sphere
void Viewer::draw() {
    drawSun();
    drawEarth();
    drawMars();
    drawVenus();
    drawMercure();
    yearE = (yearE + 10) % 3600;
    dayE = (dayE + 30) % 3600;
    update();
}

void Viewer::drawSun(){
    //RED
    glPushMatrix();
    QColor color = QColor(252,150,1);
    glColor3f(color.redF(),color.greenF(),color.blueF());
    glutSolidSphere(radius_sun, 32, 32);
    glPopMatrix();
}
void Viewer::drawEarth(){
    glPushMatrix();
    QColor color = QColor(140,177,222);
    glColor3f(color.redF(),color.greenF(),color.blueF());
    glRotatef((GLfloat)yearE / 10, 0.0, 0.0, 1.0);
    glTranslatef( radius_sun + dist_earth + radius_earth, 0.0, 0.0);
    glRotatef((GLfloat)dayE / 10, 0.0, 0.0, 1.0);
    glutSolidSphere(radius_earth, 20, 16);

    glColor3f(1.0f, 1.0f, 1.0f);
    glRotatef((GLfloat)yearE / 10, 0.0f, 0.0f, 1.0f);
    glRotatef(dayE / 30.0 * 360.0, 0.0f, 0.0f, 1.0f);
    glTranslatef(radius_earth + radius_moon + dist_moon, 0.0f, 0.0f);
    glutSolidSphere(radius_moon, 20, 16);
    glPopMatrix();
}
void Viewer::drawMercure(){
    glPushMatrix();
    QColor color = QColor(88,78,81);
    glColor3f(color.redF(),color.greenF(),color.blueF());
    glRotatef((GLfloat)(yearE / 10) * mer_rot_earthScale, 0.0, 0.0, 1.0);
    glTranslatef( radius_sun + dist_mercure + radius_mercure, 0.0, 0.0);
    glRotatef((GLfloat)dayE / 10, 0.0, 0.0, 1.0);
    glutSolidSphere(radius_mercure, 20, 16);
    glPopMatrix();
}

void Viewer::drawMars(){
    glPushMatrix();
    QColor color = QColor(226,123,88);
    glColor3f(color.redF(),color.greenF(),color.blueF());
    glRotatef((GLfloat)(yearE / 10)*mars_rot_earthScale, 0.0, 0.0, 1.0);
    glTranslatef( radius_sun + dist_mars + radius_mars, 0.0, 0.0);
    glRotatef((GLfloat)dayE / 10, 0.0, 0.0, 1.0);
    glutSolidSphere(radius_mars, 20, 16);
    glPopMatrix();
}

void Viewer::drawVenus(){
    glPushMatrix();
    QColor color = QColor(187,183,171);
    glColor3f(color.redF(),color.greenF(),color.blueF());
    glRotatef((GLfloat)(yearE / 10)*venus_rot_earthScale, 0.0, 0.0, 1.0);
    glTranslatef( radius_sun + dist_venus + radius_venus, 0.0, 0.0);
    glRotatef((GLfloat)dayE / 10, 0.0, 0.0, 1.0);
    glutSolidSphere(radius_venus, 20, 16);
    glPopMatrix();
}

void Viewer::init() {
    setSceneRadius(1);
  // Restore previous viewer state.
  restoreStateFromFile();
  radius_sun = 30;
  radius_mercure = 0.57; dist_mercure = 13;
  radius_venus = 1.43; dist_venus = 25;
  dayE = 0;  yearE = 0;  radius_earth =1.64;  dist_earth = 33;  radius_moon = 0.446; dist_moon = 0.084;
  dayMa = 0; radius_mars = 0.58;  dist_mars = 53;
  mer_rot_earthScale = 4.19; venus_rot_earthScale = 1.63; mars_rot_earthScale = 0.53; earth_rot_earthScale = 1;
  glEnable(GL_LIGHTING);
  glMatrixMode(GL_MODELVIEW);

  glEnable(GL_DEPTH_TEST);

}
