#include "animation.h"
#include <math.h>
#include <stdlib.h> // RAND_MAX

#ifdef __gnu_linux__
    #include <GL/gl.h>
    #include <GL/glut.h>
#else
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
using namespace qglviewer;
using namespace std;

///////////////////////   V i e w e r  ///////////////////////
void Viewer::init() {
  restoreStateFromFile();
  glDisable(GL_LIGHTING);
  terre = new Planete();
  startAnimation();
}

void Viewer::draw() {
    glEnable( GL_POINT_SMOOTH );
    /*glPointSize(200);
    glBegin(GL_POINTS); //Draws points
    QColor colSun = Qt::red;
    glColor3f(colSun.redF(), colSun.greenF(), colSun.blueF());
    glVertex3f(0.0,0.0,0.0);
    glEnd();*/
    terre->draw();
}

void Viewer::animate() {
    terre->animate();
    //particle_[i].animate();
}

///////////////////////   P a r t i c l e   ///////////////////////////////

Planete::Planete() { init(); }

void Planete::animate() {
    pos_.x = (float) (distSun + radius + cos(degrePerDay * day));
    pos_.y = (float) (distSun + sin(degrePerDay * day));
    day = day+1;
    if(day == 365){
        init();
    }
    std::cout << "x : " << pos_.x << "y : " << pos_.y << "z : " << pos_.z << std::endl;
/*
  speed_.z -= 0.05f;
  pos_ += 0.1f * speed_;

  if (pos_.z < 0.0) {
    speed_.z = -0.8 * speed_.z;
    pos_.z = 0.0;
  }

  if (++age_ == ageMax_)
    init();*/
}

void Planete::draw() {

    glEnable( GL_POINT_SMOOTH );
    glPointSize(radius);
    glBegin(GL_POINTS); //Draws points
    glColor3f(color.redF(), color.greenF(), color.blueF());
    glVertex3fv(pos_);
    glEnd();
}

void Planete::init() {
  pos_ = Vec(0.7, 0.0, 0.0);
  day = 1;
  dayInYear = 365;
  degrePerDay = 360/365;
  color = Qt::blue;
  distSun = 0.7;
  radius = 50;

  float angle = 2.0 * M_PI * rand() / RAND_MAX;
  float norm = 0.04 * rand() / RAND_MAX;
  speed_ = Vec(norm * cos(angle), norm * sin(angle),
               rand() / static_cast<float>(RAND_MAX));
  age_ = 0;
  ageMax_ = 50 + static_cast<int>(100.0 * rand() / RAND_MAX);

}
