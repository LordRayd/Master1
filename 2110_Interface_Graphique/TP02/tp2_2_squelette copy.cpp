//
//  tp2_squelette.cpp
//  
//
//  Created by Caroline Larboulette on 02/10/2017.
//
//

#include <stdio.h>
#include <stdlib.h>

#ifdef __gnu_linux__
    #include <GL/gl.h>
    #include <GL/glut.h>
#else 
    #include <OpenGL/gl.h>
    #include <GLUT/glut.h>
#endif
//Letter F
GLubyte rasters[24] = {
    0xc0, 0x00, 0xc0, 0x00, 0xc0, 0x00, 0xc0, 0x00, 0xc0, 0x00,
    0xff, 0x00, 0xff, 0x00, 0xc0, 0x00, 0xc0, 0x00, 0xc0, 0x00,
    0xff, 0xc0, 0xff, 0xc0};

void init(void)
{
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
    glClearColor(0.0, 0.0, 0.0, 0.0);
}

int BOTTOM = 1, TOP = 2, LEFT = 3, RIGHT = 4;
int xmin, ymin,xmax,ymax;
int getCode(int x, int y){
    if(x>xmax) return RIGHT;
    if(x<xmin) return LEFT;
    if(y>ymax) return TOP;
    if(y<ymin) return BOTTOM;
    return 0;
}

int cohen_suther(int x0,int y0,int x1,int y1){
    int code1 = getCode(x0,y0);
    int code2 = getCode(x1,y1);
    int code = (code1) ? code1 : code2;
    int choose = (code1) ? 0 : 1;
    while(code != 0){

        if (code == TOP){
            x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
            y = ymax;
        }else if (code == BOTTOM){
            x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
            y = ymin;
        }else if (code == RIGHT){
            y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
            x = xmax;
        }else if (code == LEFT){
            y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
            x = xmin;
        }
        if (choose == 0){
            x0 = x; 
            y0 = y; 
            code1 = getCode(x0,y0);
            code = (code1) ? code1 : code2;
            choose = (code1) ? 0 : 1;
        }else{
            x1 = x; 
            y1 = y; 
            code2 = getCode(x1,y1);
            code = (code1) ? code1 : code2;
            choose = (code1) ? 0 : 1;
        }
    }
    


}




void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    //glEnable(GL_LINE_SMOOTH);
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
    glColor3f(1.0, 0.0, 0.0);  /* red  */
    glRasterPos2i (200, 200);
    glColor3f(0.0f, 1.0f, 0.0f);
    glBitmap(10, 12, 0.0, 0.0, 11.0, 0.0, rasters);
    glFlush();
}

void reshape(int w, int h)
{
    glViewport(0, 0, (GLsizei) w, (GLsizei) h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(0, w, 0, h, -1.0, 1.0);
    glMatrixMode(GL_MODELVIEW);
}

void keyboard(unsigned char key, int x, int y)
{
    switch(key) {
        case 'q':
        exit(0);
    }
}

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(400, 400);
    glutInitWindowPosition(100, 100);
    glutCreateWindow(argv[0]);
    init();
    glutReshapeFunc(reshape);
    glutKeyboardFunc(keyboard);
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}
