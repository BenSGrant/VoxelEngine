#version 400 core

in vec2 pass_texCoords;
in float visibility;

uniform sampler2D texSampler;
uniform vec3 skyColour;

out vec4 out_Color;

void main (void) {
	
	out_Color = texture(texSampler, pass_texCoords);
	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);
	
}