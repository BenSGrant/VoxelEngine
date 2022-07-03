#version 400 core

in vec2 pass_texCoords;
in float visibility;
in vec3 surfaceNormal;
in vec3 toLightVector;

uniform sampler2D texSampler;
uniform vec3 lightColour;
uniform vec3 skyColour;

out vec4 out_Color;

void main (void) {

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);
	
	float nDot1 = dot(unitNormal, unitLightVector);
	float brightness = max(nDot1, 0.2);
	vec3 diffuse = brightness * lightColour;
	
	out_Color = vec4(diffuse, 1.0) * texture(texSampler, pass_texCoords);
	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);
	
}