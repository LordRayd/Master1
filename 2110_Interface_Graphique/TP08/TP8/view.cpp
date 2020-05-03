#include "view.h"

using namespace qglviewer;

View::View(){
}

void View::light() {
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glEnable(GL_LIGHT1);
    glEnable(GL_DEPTH_TEST);
    GLfloat white_light[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat blue_light[] = { 0.0, 0.0, 1.0, 1.0 };
    GLfloat red_light[] = { 1.0, 0.0, 0.0, 1.0 };
    GLfloat green_light[] = { 0.0, 1.0, 0.0, 1.0 };

    //
    GLfloat light_ambient[] = { 0.0, 0.0, 0.0, 1.0 };
    GLfloat light_diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_specular[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_position[] = { 1.0, 1.0, 1.0, 0.0 };
    //glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, white_light);
    glLightfv(GL_LIGHT0, GL_SPECULAR, red_light);
    glLightfv(GL_LIGHT0, GL_POSITION, light_position);
    //

    /*
    GLfloat light_ambient[] = { 0.0, 0.0, 1.0, 1.0};
    glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient);
    */

    //glLightfv(GL_LIGHT0, GL_DIFFUSE, blue_light);
    //glLightfv(GL_LIGHT0, GL_SPECULAR, white_light);
    //glLightfv(GL_LIGHT0, GL_POSITION, blue_light);
    //glLightfv(GL_LIGHT0, GL_SPOT_DIRECTION, spot_position);
    //glLightfv(GL_LIGHT1, GL_SPOT_DIRECTION, spot_direction);
    //glLightf(GL_LIGHT1, GL_SPOT_EXPONENT, 2.0);
    //glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 45.0);
    //glLightf(GL_LIGHT0, GL_CONSTANT_ATTENUATION, 2.0);
    //glLightf(GL_LIGHT0, GL_LINEAR_ATTENUATION, 1.0);
    //glLightf(GL_LIGHT0, GL_QUADRATIC_ATTENUATION, 0.5);
    drawLight(GL_LIGHT0);


    /* LIGTH N°2
    GLfloat light1_ambient[] = { 0.2, 0.2, 0.2, 1.0 };
    GLfloat light1_diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light1_specular[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light1_position[] = { -2.0, 2.0, 1.0, 1.0 };
    GLfloat spot_direction[] = { -1.0, -1.0, 0.0 };
    glLightfv(GL_LIGHT1, GL_AMBIENT, light1_ambient);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, light1_diffuse);
    glLightfv(GL_LIGHT1, GL_SPECULAR, light1_specular);
    glLightfv(GL_LIGHT1, GL_POSITION, light1_position);
    glLightf(GL_LIGHT1, GL_CONSTANT_ATTENUATION, 1.5);
    glLightf(GL_LIGHT1, GL_LINEAR_ATTENUATION, 0.5);
    glLightf(GL_LIGHT1, GL_QUADRATIC_ATTENUATION, 0.2);
    glLightf(GL_LIGHT1, GL_SPOT_CUTOFF, 45.0);
    glLightfv(GL_LIGHT1, GL_SPOT_DIRECTION, spot_direction);
    glLightf(GL_LIGHT1, GL_SPOT_EXPONENT, 2.0);
    glEnable(GL_LIGHT1);
    drawLight(GL_LIGHT1);*/
}

void View::materiel() {


    GLfloat white_light[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat no_mat[] = { 0.0, 0.0, 0.0, 1.0 };
    GLfloat mat_ambient[] = { 0.7, 0.7, 0.7, 1.0 };
    GLfloat mat_ambient_color[] = { 0.8, 0.8, 0.2, 1.0 };
    GLfloat mat_diffuse[] = { 0.1, 0.5, 0.8, 1.0 };
    GLfloat mat_specular[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat no_shininess[] = { 0.0 };
    GLfloat low_shininess[] = { 5.0 };
    GLfloat high_shininess[] = { 100.0 };
    GLfloat mat_emission[] = {0.3, 0.2, 0.2, 0.0};

    //glMaterialfv(GL_FRONT, GL_AMBIENT, no_mat);
    //glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
    glMaterialfv(GL_FRONT, GL_SPECULAR, white_light);
    glMaterialfv(GL_FRONT, GL_SHININESS, no_shininess);
    //glMaterialfv(GL_FRONT, GL_EMISSION, no_mat);

    /*glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, mat_amb_diff);*/
   /*glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
    glMaterialfv(GL_FRONT, GL_SHININESS, low_shininess);
    glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
    glMaterialfv(GL_FRONT, GL_EMISSION, mat_emission);
    glMaterialfv(GL_FRONT, GL_COLOR_INDEXES, mat_shininess);*/
}

void View::model() {
    GLfloat lmodel_ambient[] = { 0.2, 0.2, 0.2, 1.0 };
    GLfloat local_view[] = { 0.0 };
    GLfloat model_ambient[] = { 1, 0.7, 0.5, 1.0 };
    //glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
    //glLightModelfv(GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, model_ambient);
    //glLightModel(GL_LIGHT0, GL_LIGHT_MODEL_AMBIENT);
    //glLightModel(GL_LIGHT0, GL_LIGHT_MODEL_LOCAL_VIEWER);
    //glLightModel(GL_LIGHT0, GL_LIGHT_MODEL_TWO_SIDE);
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
    light();
    materiel();
    model();


}


void View::init() {


  // Restore previous viewer state.
  restoreStateFromFile();

  //parser = ObjParser("../TP6/bear-obj.obj");

  parser = ObjParser("./giraffe_obj.obj");

  //parser.readFile();

  parser.parser();

  setSceneRadius(500.0);
}
