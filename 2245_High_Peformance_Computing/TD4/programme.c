voidmulmatrixscal (
    float A[DIM1][DIM3] ,// maximal  s i z e s
    float B[DIM3][DIM2] ,
    float C[DIM1][DIM2] ,
    int dim1 ,//  real  s i z e s
    int dim2 ,int dim3 ){
    for(int x=0; x<dim1 ; x++){
        for(int y=0; y < dim2; y++){
            C[x][y] = 0;
            for(int z=0; z < dim3 ; z++){
                C[x][y] += A[x][z] ∗ B[z][y];
            }
        }
    }
}