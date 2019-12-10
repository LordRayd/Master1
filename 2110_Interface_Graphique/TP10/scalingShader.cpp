//Vertex Shaders
void main(void) {
	vec4 v = vec4(vertex,1.0);  
	v.x = v.x * 2.0;  //agrandit de 2 foiss la taille en x
	v.z = v.z * 0.5; //diminue par 2 la taille en z
	gl_Position = _mvProj * v; //execute le scaling
}

//Fragment shaders
void main(void)
{
    gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0); // met l'objet en vert
}