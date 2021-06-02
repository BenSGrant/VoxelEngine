#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec2 pass_texCoords;
out float visibility;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

const float density = 0.007;
const float gradient = 0.5;
// the smaller this value is , the smaller the distance until the rendered object's colour is the same as the sky's

void main(void) {
	vec4 worldPosition = transformationMatrix * vec4(position, 1.0);
	vec4 positionRelativeToCam = viewMatrix * worldPosition;
	gl_Position = projectionMatrix * positionRelativeToCam;
	pass_texCoords = textureCoords;
	
	float distance = length(positionRelativeToCam.xyz);
	visibility = exp(-pow((distance*density), gradient));
	visibility = clamp(visibility, 0.0, 1.0);	// not sure if this line is neccessary
}