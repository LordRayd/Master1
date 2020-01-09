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

#include <QGLViewer/qglviewer.h>

#ifdef __gnu_linux__
    #include <GL/freeglut.h>
    #include <GL/gl.h>
    #include <GL/glu.h>
    #include <GL/glut.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
#include "sphere.h"

class Viewer : public QGLViewer {
public :
    float dist_sun_earth;
    Sphere* terre, *soleil;
    virtual void drawSun();
    virtual void drawEarth();
    virtual void drawVenus();
    virtual void drawMars();
    virtual void drawMercure();
    float radius_earth, radius_sun,radius_moon,radius_mars, radius_venus, radius_mercure;
    int yearE,dayE, yearMa,dayMa, yearV,dayV, yearMe,dayMe;
    float dist_mercure, dist_venus, dist_earth, dist_mars, dist_moon;
    float mer_rot_earthScale, venus_rot_earthScale, mars_rot_earthScale, earth_rot_earthScale;
protected:
    virtual void draw();
    virtual void init();
};
