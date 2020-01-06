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
/*
 * x = R Cos(B) Cos(L)
y = R Cos(B) sin(L)
z = R sin(B)

avec
L la longitude en °
B la latitude en °
R la distance au Soleil en la ramenant en UA (R Terre-Soleil = 1 UA)*/


    soleil->draw();

    terre->draw();


}

void Viewer::init() {
  // Restore previous viewer state.
  restoreStateFromFile();
    dist_sun_earth = 0.3;
    terre = new Sphere(0.3, Qt::blue,0.9f,0.0f,0.0f);
    soleil = new Sphere(0.5, Qt::red,0.0f,0.0f,0.0f);
    startAnimation();
}

void Viewer::animate() {
    terre->animate();
}
