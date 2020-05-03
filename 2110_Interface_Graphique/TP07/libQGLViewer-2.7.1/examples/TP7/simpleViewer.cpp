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
#include "glm.h"

using namespace std;

// Draws a spiral
void Viewer::draw() {
    /* Ne marche pas alors qu'il marchait au debut de l'heure
        GLMmodel* model = glmReadOBJ("/home/lordrayd/Documents/Master1/2110_Interface_Graphique/TP6/libQGLViewer-2.7.1/examples/TP6/giraffe_obj.obj");
        glmFacetNormals(model);//Optional-recalculates and smooths the normals
        glBegin(GL_POINTS);
        glmDraw(model,GLM_SMOOTH);
        glEnd();
    */

    GLMmodel* model = glmReadOBJ("/home/lordrayd/Documents/Master1/2110_Interface_Graphique/TP6/libQGLViewer-2.7.1/examples/TP6/giraffe_obj.obj");
    GLfloat* v = model->vertices;

    int n = model->numvertices;
    for(int i=3; i<(int)(n+1)*3;i++){
        Vertex *p = new Vertex;
        p->position[0] = v[i];
        ++i;
        p->position[1] = v[i];
        ++i;
        p->position[2] = v[i];
        this->vertex.push_back(*p);
    }
    glBegin(GL_POINTS);
    for(uint j=0; j<this->vertex.size();j++){
        glVertex3f(this->vertex.at(j).position[0],this->vertex.at(j).position[1],this->vertex.at(j).position[2]);
    }
    glEnd();

    /*
    glmFacetNormals(model);//Optional-recalculates and smooths the normals

    glBegin(GL_POINTS);
    for(uint i=0; i<model->numvertices;i=i+3){
        glVertex3f(model->vertices[i],model->vertices[i+1],model->vertices[i+2]);
        /*Vertex vert = Vertex(Vec(model->vertices[i],model->vertices[i+1],model->vertices[i+2]),Vec(0,0,0));
        this->vertex.push_back(vert);}*/
    /*cout<< this->vertex.at(0).position <<endl;
    cout<< this->vertex.at(0).position <<endl;*/

    //P2.pos-P1.pos)^P3.pos-P1.pos

    //glmDraw(model,GLM_SMOOTH);

    /*for(uint j=0; j<this->vertex.size();j++){
        glVertex3f(this->vertex.at(j).x,this->vertex.at(j).y,this->vertex.at(j).z);
        glNormal3f(this->vertex.at(j).normale[0],this->vertex.at(j).normale[1],this->vertex.at(j).normale[2]);
    }
    glEnd();*/
}

void Viewer::init() {
    // Restore previous viewer state.
    restoreStateFromFile();
    setSceneRadius(500.0);
}
