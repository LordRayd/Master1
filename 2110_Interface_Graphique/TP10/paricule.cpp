//Vertex Shader
attribute vec3 vertex;

uniform mat4 _mvProj;
uniform float _time; // time in seconds

void main(void) {
    float g = 0.0981; 
    float m = 1.0; 
    vec3 object_pos; 
    vec3 velocite = vec3(0.2,-0.7,0.1);
    object_pos.x = vertex.x + velocite.x*_time/5000.0; 
    object_pos.y = vertex.y + velocite.y*_time/5000.0 - g/m*_time/5000.0*_time/5000.0; 
    object_pos.z = vertex.z + velocite.z*_time/5000.0; 
    gl_Position = _mvProj * vec4(object_pos,7.0);
    
} 

//Fragment Shader
void main(void)
{
    gl_FragColor = vec4(1.0,1.0,1.0,1.0);
}