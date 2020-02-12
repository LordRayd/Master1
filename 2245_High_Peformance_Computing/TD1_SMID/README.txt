Study the C program condaddmul.c, 
compile it with the makefile, 
test the speedup obtained over the scalar version. Draw a curve.
Write an another version named condaddmul2.c, using the AVX instruction set (__m256i type) instead of the SSE instruction set ; 
compare the speedup obtained with the SSE version. Draw a curve.
Speedup d'environ 9 lorsqu'on utilise 256 et qu'on formate par paquet de 32 bits
Complete the file saxpy.c with a function simd_saxpy() that leverage SIMD intrinsics and gives the best speedup over the scalar function scalar_saxpy(). Draw a curve.


Pour faire des courbes :
    changer la taille du vecteur