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
#include <stdio.h>
#include <stdlib.h>
#include "exo1.h"
#include "QGLViewer/vec.h"
#include <math.h>
#ifdef __gnu_linux__
    #include <GL/gl.h>
    #include <GL/glut.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
using namespace std;
using namespace qglviewer;
//QTime t;
int t;
int nbSphere;
float position[4];
float positionAfter[10];

float positionE[4];
float rgb[4];
float rgb2[4];
float rgb3[4];

float rotation=1.0;
float rotationSnake=3.0;
float snakeOscillation=25.0;
GLenum currentLight;


void initLight(GLenum light){
    glLightfv(light,GL_AMBIENT,rgb2);
    glLightfv(light,GL_DIFFUSE,rgb);
    glLightfv(light,GL_SPECULAR,rgb3);
    glLightfv(light,GL_POSITION,positionE);

}

void Viewer::keyPressEvent(QKeyEvent *e){
    if(e->key()==Qt::Key_A){
        rotation=rotation-0.3;
    }
    if(e->key()==Qt::Key_Z){
        rotation=rotation+0.3;
    }
    if(e->key()==Qt::Key_Q){
        rotationSnake=rotationSnake-0.3;
    }
    if(e->key()==Qt::Key_S){
        rotationSnake=rotationSnake+0.3;
    }
    if(e->key()==Qt::Key_W){
        snakeOscillation--;
    }
    if(e->key()==Qt::Key_X){
        snakeOscillation++;
    }
}

void drawSnake(int snakeOscillation){
    //RED
    glColor3f(1,0,0);
    glPushMatrix();
    glTranslatef(position[0],position[1],position[2]+snakeOscillation);

    glTranslatef(0,0,-snakeOscillation);
    glRotatef(rotationSnake*t,1,0,0);
    glTranslatef(0,0,snakeOscillation);

    glTranslatef(position[0],position[1],position[2]+15);
    glTranslatef(0,position[1],-15);
    glRotatef(rotation*t,1,0,0);
    glTranslatef(0,position[1],15);

    glutSolidSphere(2,10,10);
    glPopMatrix();

    //BLUE
    glColor3f(0,0,1);
    glPushMatrix();
    glTranslatef(position[0],position[1],position[2]+snakeOscillation);

    glTranslatef(0,0,-snakeOscillation);
    glRotatef(rotationSnake*t,1,0,0);
    glTranslatef(0,0,snakeOscillation);

    glTranslatef(position[0],position[1],position[2]-15);
    glTranslatef(0,position[1],15);
    glRotatef(rotation*t,1,0,0);
    glTranslatef(0,position[1],-15);

    glutSolidSphere(2,10,10);
    glPopMatrix();


    t++;
    //HEAD
    glColor3f(0.9,0,0.7);
    glPushMatrix();
    glTranslatef(position[0],position[1],position[2]+snakeOscillation);

    glTranslatef(0,0,-snakeOscillation);
    glRotatef(rotationSnake*t,1,0,0);
    glTranslatef(0,0,snakeOscillation);

    glutSolidSphere(nbSphere+1,10,10);
    glPopMatrix();
    //BODY
    for(int i=0; i<nbSphere;i++){
        glPushMatrix();

        glTranslatef(position[0]+10+i*10,position[1],position[2]+snakeOscillation);
        glTranslatef(0,0,position[2]-snakeOscillation);
        glRotatef(rotationSnake*(t-(i+1)*5),1,0,0);
        glTranslatef(0,0,position[2]+snakeOscillation);

        glutSolidSphere(nbSphere-i,10,10);
        glPopMatrix();
    }
}

void Viewer::draw() {
    drawSnake(-snakeOscillation);
    drawSnake(snakeOscillation);
    update();
}



void Viewer::init() {
    positionE[0]=2; positionE[1]=4; positionE[2]=4; positionE[3]=0;
    rgb[0]=0.9; rgb[1]=0.0; rgb[2]=0.7; rgb[3]=0.0;
    rgb2[0]=0.1; rgb2[1]=0.1; rgb2[2]=0.1; rgb2[3]=0.0;
    rgb3[0]=1.0; rgb3[1]=1.0; rgb3[2]=1.0; rgb3[3]=0.0;
    initLight(GL_LIGHT0);
    t=0.0;
    nbSphere=7;
    position[0]=0;
    position[1]=0.0;
    position[2]=0;
    position[3]=0.0;
    for(int i=0; i<nbSphere;i++){
        positionAfter[i]=2+i;
    }
    glEnable(GL_LIGHTING);
    glMatrixMode(GL_MODELVIEW);

    glEnable(GL_DEPTH_TEST);
    setSceneRadius(500);
    help();

}

QString Viewer::helpString() const {
    QString text("<h1>Snake</h1>");
    text+=("<h2>Contrôles</h2>");
    text+="Molette de la souris = Zoomer/Dézoomer<br><\br>";
    text+="Clic droit appuyé + glisser = Déplacer la caméra<br><\br>";
    text+="Clic gauche appuyé + glisser = Tourner la caméra<br><\br>";
    text+="A = Diminuer la vitesse de rotation des satellites<br><\br>";
    text+="Z = Augmenter la vitess de rotation des satellites<br><\br>";
    text+="Q = Diminuer la vitesse de rotation des serpents<br><\br>";
    text+="S = Augmenter la vitess de rotation des serpents<br><\br>";
    text+="W = Diminuer la distance entres les serpents<br><\br>";
    text+="X = Augmenter la distance entres les serpents<br><\br>";
    return text;
}
