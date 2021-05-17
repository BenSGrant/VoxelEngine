package terrain.voxelTypes;

public class HorSlab {

	float[] vertices= {			
			-0.5f,0.25f,-0.5f,	
			-0.5f,-0.25f,-0.5f,	
			0.5f,-0.25f,-0.5f,	
			0.5f,0.25f,-0.5f,		
			
			-0.5f,0.25f,0.5f,	
			-0.5f,-0.25f,0.5f,	
			0.5f,-0.25f,0.5f,	
			0.5f,0.25f,0.5f,
			
			0.5f,0.25f,-0.5f,	
			0.5f,-0.25f,-0.5f,	
			0.5f,-0.25f,0.5f,	
			0.5f,0.25f,0.5f,
			
			-0.5f,0.25f,-0.5f,
			-0.5f,-0.25f,-0.5f,
			-0.5f,-0.25f,0.5f,
			-0.5f,0.25f,0.5f,
			
			-0.5f,0.25f,0.5f,
			-0.5f,0.25f,-0.5f,
			0.5f,0.25f,-0.5f,
			0.5f,0.25f,0.5f,
			
			-0.5f,-0.25f,0.5f,
			-0.5f,-0.25f,-0.5f,
			0.5f,-0.25f,-0.5f,
			0.5f,-0.25f,0.5f
	};
	
	float[] uv = {
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0
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
