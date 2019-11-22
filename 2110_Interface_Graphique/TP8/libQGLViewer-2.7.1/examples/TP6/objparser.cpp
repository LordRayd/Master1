#include "objparser.h"

using namespace qglviewer;

ObjParser::ObjParser(){
}

ObjParser::ObjParser(char* fileName_)
{
    this->fileName = fileName_;
}

/*void ObjParser::readFile(){

    FILE* file;
    char line[50];
    file = std::fopen(this->fileName,"r");
    if(file != NULL){
        int nbVertex= 0;
        while(std::fgets(line, 50, file) != NULL){
            std::string newLine(line);
            if(newLine[0] == 'v'){
                if(newLine[1] == ' '){
                    size_t pos = 0;
                    std::string token;
                    std::string tokens[5];
                    int i = 0;
                    while ((pos = newLine.find(" ")) != std::string::npos) {
                        token = newLine.substr(0, pos);
                        tokens[i] = token;
                        i++;
                        newLine.erase(0, pos + 1);
                    }
                    this->vertexArray.push_back(Vertex(std::stod(tokens[2]),std::stod(tokens[3]),std::stod(tokens[4])));
                }else if(newLine[1] == 'n'){
                    size_t pos = 0;
                    std::string token;
                    std::string tokens[5];
                    int i = 0;
                    while ((pos = newLine.find(" ")) != std::string::npos) {
                        token = newLine.substr(0, pos);
                        std::cout << token << std::endl;
                        tokens[i] = token;
                        std::cout << tokens[i] << std::endl;
                        i++;
                        newLine.erase(0, pos + 1);
                    }
                    this->vertexArray[nbVertex].setNormal(Vec(std::stod(tokens[2]),std::stod(tokens[3]),std::stod(tokens[4])));
                    nbVertex++;
                }
            }
        }
    }
}
*/
void ObjParser::parser(){

    this->model = glmReadOBJ(this->fileName);



    for(int i = 3; i < (int)(this->model->numvertices+1)*3; i++){
        Vertex* p = new Vertex();
        p->position[0] = this->model->vertices[i];
        i++;
        p->position[1] = this->model->vertices[i];
        i++;
        p->position[2] = this->model->vertices[i];
        this->vertexArray.push_back(p);
    }
        std::cout<<(this->vertexArray[0])->position<<std::endl;
        std::cout<<(this->vertexArray[this->vertexArray.size()-1])->position<<std::endl;
        std::cout<<this->vertexArray.size()<<std::endl;

        std::cout<<this->model->numtriangles<<std::endl;
        this->faceArray.clear();
        std::cout<< "taille avant" << this->faceArray.size()<<std::endl;

    for(int j = 0; j < (int)this->model->numtriangles; j++){

        /*Vec normalP1 = Vec(this->model->normals[this->model->triangles[j].vindices[0]*3],
                        this->model->normals[this->model->triangles[j].vindices[0]*3+1],
                        this->model->normals[this->model->triangles[j].vindices[0]*3+2]);*/

        Vertex *P1 = this->vertexArray[this->model->triangles[j].vindices[0]-1];

        /*new Vertex(this->model->vertices[this->model->triangles[j].vindices[0]*3],
                                this->model->vertices[this->model->triangles[j].vindices[0]*3+1],
                                this->model->vertices[this->model->triangles[j].vindices[0]*3+2]);*/
        //this->vertexArray[this->model->triangles[j].vindices[0]];
        //std::cout<<this->model->triangles[j].vindices[0]<<std::endl;


        //this->vertexArray.push_back(P1);

        /*Vec normalP2 = Vec(this->model->normals[this->model->triangles[j].vindices[1]*3],
                        this->model->normals[this->model->triangles[j].vindices[1]*3+1],
                        this->model->normals[this->model->triangles[j].vindices[1]*3+2]);*/


        Vertex *P2 = this->vertexArray[this->model->triangles[j].vindices[1]-1];/*new Vertex(this->model->vertices[this->model->triangles[j].vindices[1]*3],
                                this->model->vertices[this->model->triangles[j].vindices[1]*3+1],
                                this->model->vertices[this->model->triangles[j].vindices[1]*3+2]);*/
        //this->vertexArray[this->model->triangles[j].vindices[1]];
        //std::cout<<this->model->triangles[j].vindices[1]<<std::endl;

        //this->vertexArray.push_back(P2);

        /*Vec normalP3 = Vec(this->model->normals[this->model->triangles[j].vindices[2]*3],
                        this->model->normals[this->model->triangles[j].vindices[2]*3+1],
                        this->model->normals[this->model->triangles[j].vindices[2]*3+2]);*/

        Vertex *P3 = this->vertexArray[this->model->triangles[j].vindices[2]-1];
                                /*new Vertex(this->model->vertices[this->model->triangles[j].vindices[2]*3],
                                this->model->vertices[this->model->triangles[j].vindices[2]*3+1],
                                this->model->vertices[this->model->triangles[j].vindices[2]*3+2]);*/
        //this->vertexArray[this->model->triangles[j].vindices[2]];
        //std::cout<<this->model->triangles[j].vindices[2]<<std::endl;

        //this->vertexArray.push_back(P3);

        Vec normal = (P2->position-P1->position)^(P3->position-P1->position);

        P1->normal+=normal;
        P2->normal+=normal;
        P3->normal+=normal;

        //Vec normal = normalP1+normalP2+normalP3;

        Face *nface = new Face(P1,P2,P3,normal/normal.norm());
        this->faceArray.push_back(nface);
    }
    std::cout<< "taille apres" << this->faceArray.size()<<std::endl;

    for(uint k = 0; k < this->faceArray.size(); k++){
        faceArray[k]->p1->normal = faceArray[k]->p1->normal/faceArray[k]->p1->normal.norm();
        faceArray[k]->p2->normal = faceArray[k]->p2->normal/faceArray[k]->p2->normal.norm();
        faceArray[k]->p3->normal = faceArray[k]->p3->normal/faceArray[k]->p3->normal.norm();
    }

}



























