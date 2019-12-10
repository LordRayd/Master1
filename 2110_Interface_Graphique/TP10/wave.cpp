//Vertex Shader
void main(void) {
	vec4 v = vec4(vertex,1.0);  
	float s = 1.0 + 0.1*sin(v.s*_time)*sin(v.z*_time);   
	v.y = s * v.y; 
	gl_Position = _mvProj * v;
}

//Fragment Shader
void main(void)
{
    gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);
}