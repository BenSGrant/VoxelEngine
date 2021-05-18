package terrain.voxelTypes;

public class AtlasBlock {

	static float[] vertices= {
			
			// side faces
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			-0.5f,-0.5f,0.5f,
			-0.5f,0.5f,0.5f,
			
			
			// top face
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,

			// bottom face
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
	};
	
	float[] uv = {
			// anticlockwise order
			1.01f / 3f,	1.01f /3f,
			1.01f / 3f,	1.99f / 3f,
			2f / 3f,	1.99f / 3f,
			2f / 3f,	1.01f / 3f,

			1.01f / 3f,	1.01f /3f,
			1.01f / 3f,	1.99f / 3f,
			2f / 3f,	1.99f / 3f,
			2f / 3f,	1.01f / 3f,

			1.01f / 3f,	1.01f /3f,
			1.01f / 3f,	1.99f / 3f,
			2f / 3f,	1.99f / 3f,
			2f / 3f,	1.01f / 3f,
			
			1.01f / 3f,	1.01f /3f,
			1.01f / 3f,	1.99f / 3f,
			2f / 3f,	1.99f / 3f,
			2f / 3f,	1.01f / 3f,

			1.01f / 3f, 2.01f / 3f,
			1.01f / 3f, 0.99f,
			1.99f / 3f,	0.99f,
			1.99f / 3f,	2.01f / 3f,
			
			0.01f,		1.01f / 3f,
			0.01f,		1.99f / 3f,
			0.99f / 3f, 1.99f / 3f,
			0.99f / 3f, 1.01f / 3f
	};

	int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
	};

	/************* COULD JUST MAKE THE VARIABLES PUBLIC AND STATIC *************/

	public float[] getVertices() {
		return vertices;
	}


	public float[] getUV() {
		return uv;
	}


	public int[] getIndices() {
		return indices;
	}

}
