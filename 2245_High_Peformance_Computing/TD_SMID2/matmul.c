#include <stdio.h>
#include <stdlib.h>

#include <time.h>
#include <limits.h>
#include <math.h>

#include <x86intrin.h> // gcc specific

#define EPS 1e-3f
#define DIM1 8*50
#define DIM2 8*50
#define DIM3 8*200

/*************************************************************************
                           timing utilities
**************************************************************************/

static long time1, time_diff, sum_diff= 0, max=0;

static inline void reset_stats(){
    sum_diff= 0;
    max=0;
}

static inline void start_timer(){
    time1= __rdtsc();
}

static inline void pause_timer(){
    time_diff= __rdtsc()-time1;
    sum_diff += time_diff;
    if (time_diff > max) max= time_diff;
}

static inline void display_stats(char *msg,int nb_loops){
    static long old_avg;
    long avg= (float) (sum_diff-max)/(nb_loops-1);
    printf("%s: average= %12li\n",msg,avg);
    if (old_avg == 0){ // first call
        old_avg= avg;
    }else{ // next call
        float avg_speedup= (float) old_avg/avg;
        printf("Speedup: average= %5.2f\n",avg_speedup);
        old_avg= avg;
    };
}

/*************************************************************************
                           matrix utilities
**************************************************************************/

void initialize_matrix(float m[], int dim1, int dim2)
{
    for (int x = 0; x < dim1; ++x) {
        for (int y = 0; y < dim2; ++y) {
            m[x * dim2 + y] = drand48();
        }
    }
}
 
void output_matrix(float m[], int dim1, int dim2)
{
    for (int x = 0; x < dim1; ++x) {
        for (int y = 0; y < dim2; ++y) {
            printf("%.2f ", m[x * dim2 + y]);
        }
        printf("\n");
    }
    printf("\n");
}
 
int compare_matrix(float m1[], float m2[], int dim1, int dim2)
{
    for (int x = 0; x < dim1; ++x) {
        for (int y = 0; y < dim2; ++y) {
            float diff= m1[x*dim2+y]-m2[x*dim2+y];
            if (fabsf(diff)>EPS){
                printf("*** error: m1[%i][%i]=%e and m2[%i][%i]=%e differ by %e\n",x,y,m1[x*dim2+y],x,y,m2[x*dim2+y],diff);
                return 1;
            }
        }
    }
    return 0;
}

/*************************************************************************
                           scalar function
**************************************************************************/

// not used, just a reminder
void mul_matrix_scal(float A[DIM1][DIM3], float B[DIM3][DIM2], float C[DIM1][DIM2], int dim1, int dim2, int dim3)
{
    for (int x=0;x<dim1;x++) {
        for (int y=0;y<dim2;y++) {
            C[x][y]= 0;
            for (int z=0;z<dim3;z++){
                C[x][y] += A[x][z] * B[z][y]; 
            }
        }
    }
}

/**
 * Matrix multiplication C= A x B
 * size of A is dim1 * dim3
 * size of B is dim3 * dim2
 * size of C is dim1 * dim2 
 */
void mul_matrix_scal_v0(float A[], float B[], float C[], int dim1, int dim2, int dim3)
{
    for (int x=0;x<dim1;x++) {
        for (int y=0;y<dim2;y++) {
            C[x*dim2+y]= 0;
            for (int z=0;z<dim3;z++){
                C[x*dim2+y] += A[x*dim3+z] * B[z*dim2+y]; // cache trashing on B[z*dim2+y]
            }
        }
    }
}

void mul_matrix_scal_v3(float A[], float B[], float C[], int dim1, int dim2, int dim3)
{
     for (int x=0;x<dim1;x++) {
        float t= A[x*dim3]; // extracted from the inner loop
        for (int y=0;y<dim2;y++) { 
            C[x*dim2+y]= t * B[y]; 
        }
        for (int z=1;z<dim3;z++){ // the iterations are permuted
            float s= A[x*dim3+z]; // extracted from the inner loop
            for (int y=0;y<dim2;y++) { 
                C[x*dim2+y] += s * B[z*dim2+y];
            }
        }
    }
}

void mul_matrix_scal_v4(float A[], float B[], float C[], int dim1, int dim2, int dim3)
{
     for (int x=0;x<dim1;x++) {
        float t= A[x*dim3]; 
        float *pC= &(C[x*dim2]); // replace array by pointeur
        for (int y=0;y<dim2;y++) { 
            *(pC+y)= t * B[y];
        }
        float *pA= &(A[x*dim3]); // replace array by pointeur
        for (int z=1;z<dim3;z++){ 
            float s= *(pA+z); 
            float *pB= &(B[z*dim2]); // replace array by pointeur
            for (int y=0;y<dim2;y++) { 
                *(pC+y) += s * *(pB+y);
            }
        }
    }
}

void mul_matrix_scal_v5(float A[], float B[], float C[], int dim1, int dim2, int dim3)
{
     for (int x=0;x<dim1;x++) {
        float t= A[x*dim3]; 
        float *pC= &(C[x*dim2]);
        for (int y=0;y<dim2;y++) { 
            *(pC+y)= t * B[y];
        }
        float *pA= &(A[x*dim3]);
        for (int z=1;z<dim3;z++){ 
            float s= *(pA+z); 
            float *pB= &(B[z*dim2]);
            for (int y=0;y<dim2;y+=8) { // unrool internal loop
                *(pC+y+0) += s * *(pB+y+0);
                *(pC+y+1) += s * *(pB+y+1);
                *(pC+y+2) += s * *(pB+y+2);
                *(pC+y+3) += s * *(pB+y+3);
                *(pC+y+4) += s * *(pB+y+4);
                *(pC+y+5) += s * *(pB+y+5);
                *(pC+y+6) += s * *(pB+y+6);
                *(pC+y+7) += s * *(pB+y+7);
            }
        }
    }
}


/*************************************************************************
                           intrinsics function
**************************************************************************/

void mul_matrix_simd(float A[], float B[], float C[], int dim1, int dim2, int dim3)
{
    
}

float A[DIM1*DIM3] ;
float B[DIM3*DIM2] ;
float C1[DIM1*DIM2];
float C2[DIM1*DIM2];

int main(int argc, char *argv[])
{
    
    int nb_loops= 2; // default value
    if (argc==2){
        nb_loops= atoi(argv[1]);
    }
    srand48(time (0));
    
    initialize_matrix(A,DIM1,DIM3);
    initialize_matrix(B,DIM3,DIM2);
    
    printf("Starts running tests...\n");
    
    for(int i=0; i<nb_loops; i++){
        start_timer();
        mul_matrix_scal_v0(A,B,C1,DIM1,DIM2,DIM3);
        pause_timer();
    }
    display_stats("Scalar V0 time stats",nb_loops);
    reset_stats();
    for(int i=0; i<nb_loops; i++){
        start_timer();
        mul_matrix_scal_v3(A,B,C2,DIM1,DIM2,DIM3);
        pause_timer();
    }
    compare_matrix(C1,C2,DIM1,DIM2);
    display_stats("Scalar V3 time stats",nb_loops);
    reset_stats();
    for(int i=0; i<nb_loops; i++){
        start_timer();
        mul_matrix_scal_v4(A,B,C2,DIM1,DIM2,DIM3);
        pause_timer();
    }
    compare_matrix(C1,C2,DIM1,DIM2);
    display_stats("Scalar V4 time stats",nb_loops);
    reset_stats();
    for(int i=0; i<nb_loops; i++){
        start_timer();
        mul_matrix_scal_v5(A,B,C2,DIM1,DIM2,DIM3);
        pause_timer();
    }
    compare_matrix(C1,C2,DIM1,DIM2);
    display_stats("Scalar V5 time stats",nb_loops);
    return 0;
}