#version 400 core

// input variables
in vec2 pass_texCoords;

// uniform variables
uniform sampler2D texSampler;

// output variables
out vec4 out_Color;

void main (void) {
	
	out_Color = texture(texSampler, pass_texCoords);
	
}