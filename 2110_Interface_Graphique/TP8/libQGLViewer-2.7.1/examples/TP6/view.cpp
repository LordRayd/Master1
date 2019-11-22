#include "view.h"

using namespace qglviewer;

View::View(){
}


void View::draw() {

    glClear(GL_COLOR_BUFFER_BIT);

    for(std::vector<Vertex*>::iterator vit = parser.vertexArray.begin(); vit != parser.vertexArray.end(); vit++){
       Vec P1 = (*vit)->position;

       glBegin(GL_POINTS);
        glNormal3dv((*vit)->normal);
        glVertex3d(P1.x,P1.y,P1.z);
       glEnd();
    }


    glPolygonMode(GL_FRONT, GL_FILL);
    //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    for(std::vector<Face*>::iterator fit = parser.faceArray.begin();
                                fit != parser.faceArray.end(); fit++)
    {

        Vec P1 = (*fit)->p1->position;
        Vec P2 = (*fit)->p2->position;
        Vec P3 = (*fit)->p3->position;

        // Flat shading
        /*glBegin(GL_TRIANGLES);
         glNormal3d((*fit)->normal.x,(*fit)->normal.y,(*fit)->normal.z);
         glVertex3d(P1.x,P1.y,P1.z);
         glVertex3d(P2.x,P2.y,P2.z);
         glVertex3d(P3.x,P3.y,P3.z);
        glEnd();*/

        // Smooth shading
        glBegin(GL_TRIANGLES);
         glNormal3dv((*fit)->p1->normal);
         glVertex3dv(P1);

         glNormal3dv((*fit)->p2->normal);
         glVertex3dv(P2);

         glNormal3dv((*fit)->p3->normal);
         glVertex3dv(P3);

        glEnd();
   }

}


void View::init() {
  // Restore previous viewer state.
  restoreStateFromFile();

  //parser = ObjParser("../TP6/bear-obj.obj");

  parser = ObjParser("../TP6/giraffe_obj.obj");

  //parser.readFile();

  parser.parser();

  setSceneRadius(500.0);

  // Opens help window
  help();
}
