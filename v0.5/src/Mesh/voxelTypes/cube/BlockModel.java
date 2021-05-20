package Mesh.voxelTypes.cube;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class BlockModel {
	
	// WILL NEED TO ADD INDICES BACK AT SOMEPOINT AS THEY TAKE UP USELESS GPU MEMORY
	
	public static Vector3f[] PX = { // positive X face (east)
			
			new Vector3f(0.5f,  0.5f, -0.5f),
			new Vector3f(0.5f, -0.5f, -0.5f),
			new Vector3f(0.5f, -0.5f,  0.5f),
			new Vector3f(0.5f, -0.5f,  0.5f),
			new Vector3f(0.5f,  0.5f,  0.5f),
			new Vector3f(0.5f,  0.5f, -0.5f)
			
	};

	public static Vector3f[] NX = { // negative X face (west)
			
			new Vector3f(-0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f,  0.5f),
			new Vector3f(-0.5f, -0.5f,  0.5f),
			new Vector3f(-0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f,  0.5f, -0.5f)
			
	};
	
	public static Vector3f[] PY = { // positive Y (up)
			
			new Vector3f(-0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f,  0.5f, -0.5f),
			new Vector3f( 0.5f,  0.5f, -0.5f),
			new Vector3f( 0.5f,  0.5f, -0.5f),
			new Vector3f( 0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f,  0.5f,  0.5f)
			
	};
	
	public static Vector3f[] NY = { // negative Y (down)
			
			new Vector3f(-0.5f,  -0.5f,  0.5f),
			new Vector3f(-0.5f,  -0.5f, -0.5f),
			new Vector3f( 0.5f,  -0.5f, -0.5f),
			new Vector3f( 0.5f,  -0.5f, -0.5f),
			new Vector3f( 0.5f,  -0.5f,  0.5f),
			new Vector3f(-0.5f,  -0.5f,  0.5f)
			// 12:26
	};
	
	public static Vector3f[] PZ = { // positive Z
			
			new Vector3f(-0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f, -0.5f,  0.5f),
			new Vector3f( 0.5f, -0.5f,  0.5f),
			new Vector3f( 0.5f, -0.5f,  0.5f),
			new Vector3f( 0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f,  0.5f,  0.5f)
			
	};
	
	public static Vector3f[] NZ = { // negative Z
			
			new Vector3f(-0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f( 0.5f, -0.5f, -0.5f),
			new Vector3f( 0.5f, -0.5f, -0.5f),
			new Vector3f( 0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f,  0.5f, -0.5f)
		
	};

	public static Vector2f[] UV = {
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			new Vector2f(0f, 0f)
	};
	
	public static Vector3f[] NORMALS = {
				
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f)
			
	};
	
	static float[] vertices = {	
			
			/* 
				P = positive
				N = negative
				X, Y, Z = points in 3D space
			*/
			
			// NZ
			-0.5f,  0.5f, -0.5f,	
			-0.5f, -0.5f, -0.5f,	
			 0.5f, -0.5f, -0.5f,	
			 0.5f,  0.5f, -0.5f,		
			
			// PZ
			-0.5f,  0.5f,  0.5f,	
			-0.5f, -0.5f,  0.5f,	
			 0.5f, -0.5f,  0.5f,	
			 0.5f,  0.5f,  0.5f,
			
			// PX
			 0.5f,  0.5f, -0.5f,
			 0.5f, -0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			
			// NX
			-0.5f,  0.5f, -0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f, -0.5f,  0.5f,
			-0.5f,  0.5f,  0.5f,
			
			// PY
			-0.5f,  0.5f,  0.5f,
			-0.5f,  0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			 0.5f,  0.5f,  0.5f,
			
			// NY
			-0.5f, -0.5f,  0.5f,
			-0.5f, -0.5f, -0.5f,
			 0.5f, -0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f
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
