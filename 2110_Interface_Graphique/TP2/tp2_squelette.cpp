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

//Letter S down to high
GLubyte rasters1[24] = {
    0xff, 0xc0, 
    0xff, 0xc0,  
    0x00, 0xc0, 
    0x00, 0xc0, 
    0x00, 0xc0,
    0xff, 0xc0, 
    0xff, 0xc0,
    0xc0, 0x00, 
    0xc0, 0x00, 
    0xc0, 0x00,
    0xff, 0xc0, 
    0xff, 0xc0
};

//Letter L
GLubyte rasters2[24] = {
    0xff, 0xc0, 
    0xff, 0xc0,  
    0xc0, 0x00, 
    0xc0, 0x00, 
    0xc0, 0x00,   
    0xc0, 0x00, 
    0xc0, 0x00, 
    0xc0, 0x00,   
    0xc0, 0x00, 
    0xc0, 0x00, 
    0xc0, 0x00, 
    0xc0, 0x00 
};

//Letter B
GLubyte rasters3[24] = {
    0xff, 0xc0, 
    0xff, 0xc0,  
    0xc0, 0xc0, 
    0xc0, 0xc0, 
    0xc0, 0xc0,   
    0xff, 0xc0, 
    0xff, 0xc0, 
    0xc0, 0xc0,   
    0xc0, 0xc0, 
    0xc0, 0xc0, 
    0xff, 0xc0, 
    0xff, 0xc0 
};


int BOTTOM = 0b0100, TOP = 0b1000, LEFT = 0b0001, RIGHT = 0b0010;
int xmin, ymin,xmax,ymax;
int getCode(int x, int y){
    if(x>xmax) return RIGHT;
    if(x<xmin) return LEFT;
    if(y>ymax) return TOP;
    if(y<ymin) return BOTTOM;
    return 0;
}

void cohen_suther(int x0, int y0, int x1, int y1){ 
    glColor3f(1.0,1.0,1); //red 
    glBegin(GL_LINES); //Draws line
    glVertex3f(x0,y0,0);
    glVertex3f(x1,y1,0);
    glEnd();
    int y,x;
    int code1 = getCode(x0,y0);
    int code2 = getCode(x1,y1);
    bool accept = false;
    while (true) {
		if (!(code1 | code2)) {
			// bitwise OR is 0: both points inside window; trivially accept and exit loop
			accept = true;
			break;
		} else if (code1 & code2) {
			// bitwise AND is not 0: both points share an outside zone (LEFT, RIGHT, TOP,
			// or BOTTOM), so both must be outside window; exit loop (accept is false)
			break;
		} else {

            int code = (code1) ? code1 : code2;
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
            if (code1 == code){
                x0 = x; 
                y0 = y; 
                code1 = getCode(x0,y0);
            }else{
                x1 = x; 
                y1 = y; 
                code2 = getCode(x1,y1);
            }
        }
    }
    if(accept){
        glColor3f(3.0,0.0,0); //red 
        glBegin(GL_LINES); //Draws line
        glVertex3f(x0,y0,0);
        glVertex3f(x1,y1,0);
        glEnd();
    }

}

void display3(){
    xmin = 100;
    xmax = 250;
    ymin = 100;
    ymax = 250;
    glClear(GL_COLOR_BUFFER_BIT);
    glBegin(GL_LINES); //left square
    glVertex3f(xmin,ymin,0);
    glVertex3f(xmin,ymax,0);
    glEnd();

    glBegin(GL_LINES); //bottom square
    glVertex3f(xmin,ymin,0);
    glVertex3f(xmax,ymin,0);
    glEnd();

    glBegin(GL_LINES); //top square
    glVertex3f(xmin,ymax,0);
    glVertex3f(xmax,ymax,0);
    glEnd();

    glBegin(GL_LINES); //right square
    glVertex3f(xmax,ymin,0);
    glVertex3f(xmax,ymax,0);
    glEnd();

    cohen_suther(75,75,150,150); //Bas gauche -> interieur
    cohen_suther(180,60,300,300); //Bas -> interieur
    cohen_suther(150,60,150,300); // Haut -> Bas
    cohen_suther(120,140,150,190); // interieure

    glFlush();

}

void display2(void){
    glClearColor(0.0, 0.0, 0.0, 0.0); //Background color
    glMatrixMode(GL_PROJECTION); //Projection matrix
    glLoadIdentity();
    glOrtho(-5.0, 5.0, -5.0, 5.0, -1.0, 1.0);
    glLineWidth(7);

    glEnable(GL_LINE_SMOOTH);
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);

    glClear(GL_COLOR_BUFFER_BIT);
    glBegin(GL_LINES); //Draws line
    glVertex3f(1,1,0);
    glVertex3f(3,4,0);
    glEnd();
    glFlush();
}

void init(void)
{
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
    glClearColor(0.0, 0.0, 0.0, 0.0);
}

void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(1.0, 0.0, 0.0);  /* red  */
    glRasterPos2i (200, 200);
    glColor3f(0.0f, 1.0f, 0.0f);
    glBitmap(10, 12, 0.0, 0.0, 11.0, 0.0, rasters1);
    glBitmap(10, 12, 0.0, 0.0, 11.0, 0.0, rasters2);
    glBitmap(10, 12, 0.0, 0.0, 11.0, 0.0, rasters3);
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
    //glutDisplayFunc(display);
    //glutDisplayFunc(display2);
    glutDisplayFunc(display3);
    glutMainLoop();
    return 0;
}
