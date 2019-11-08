#include "bezier.h"
#include "math.h"

using namespace std;
using namespace qglviewer;
void bezier::draw()
{
    Vec p1 = Vec(1,2,3);
    Vec p2 = Vec(6,1,3);
    Vec p3 = Vec(7,6,3);
    Vec p4 = Vec(13,3,3);
    unsigned int i;
    vector<Vec> courbe = vector<Vec>();
    for(int i=0; i<=1;i=i+0.001){
        courbe.push_back(p1*pow(1-i,3) + 3*p2*i*pow(1-i, 2.0) + 3*pow(i,2)*(1-i)*p3 + pow(i,3.0)*p4);
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

void bezier::drawBezier(){
    /*
    Vec p1 = Vec(1,2,3);
    Vec p2 = Vec(6,1,3);
    Vec p3 = Vec(7,6,3);
    Vec p4 = Vec(13,3,3);
    unsigned int i;
    vector<Vec> courbe = vector<Vec>();
    for(int i=0; i<1;i=i+0.001){
        courbe.push_back(p1*pow(1-i,3) + 3*p2*i*pow(1-i, 2.0) + 3*pow(i,2)*(1-i)*p3 + pow(i,3.0)*p4);
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

    Vec P1 = Vec(0.0,0.0,0.0);
    Vec P2 = Vec(3.0,3.0,0.0);
    Vec P2 = Vec(6.0,1.0,0.0);
    std::vector<Vec> arrayVec ();
    arrayVec.push_back(P1);
    arrayVec.push_back(P2);
    arrayVec.push_back(P3);

    int size = arrayVec.size();
    std::vector<Vec> result();
    switch(size){
    case 3:
        for(int i=0; i<=1; i=i+0.001){
            result.push_back(pow(1-i,2.0) * arrayVec.at(0) + 2*(1-i) * arrayVec.at(1));
        }
        break;

    case 4:
        for(int i=0; i<=1; i=i+0.001){
            result.push_back(pow(1-i, 3.0) * arrayVec.at(0) + 3*pow(1-i,2.0) * arrayVec.at(1) + 3(1-i)*pow(i,2.0) * arrayVec.at(2) + pow(i,3.0)*arrayVec.at(3));
        }
        break;
    }

    glBegin(GL_POINTS);
    for(int i=0; i<result.size();i++){
        glVertex3fv(result.at(i));
    }
    glEnd();*/

}


void bezier::init(){
    setSceneRadius(10);
    showEntireScene();
    //draw();
}
