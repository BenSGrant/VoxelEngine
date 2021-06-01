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
	
	public static Vector2f[] UV_PX = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 0.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f),

			// FLOWER - RED
			new Vector2f(10.f / 16.f, 0.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f),

			// TALL-GRASS
			new Vector2f(11.f / 16.f, 0.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f),

			// DEAD BUSH
			new Vector2f(12.f / 16.f, 0.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f),

			// TREE TRUNK 2
			new Vector2f(14.f / 16.f, 0.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f),

			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
	};
	
	public static Vector2f[] UV_NX = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 0.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f),

			// FLOWER - RED
			new Vector2f(10.f / 16.f, 0.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f),

			// TALL-GRASS
			new Vector2f(11.f / 16.f, 0.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f),

			// DEAD BUSH
			new Vector2f(12.f / 16.f, 0.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f),

			// TREE TRUNK 2
			new Vector2f(14.f / 16.f, 0.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f),

			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
			
	};

	public static Vector2f[] UV_PY = {
			
			// GRASS
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(0.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 1.f),
			new Vector2f(9.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f),

			// FLOWER - RED - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),

			// TALL-GRASS - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),
			
			// DEAD BUSH - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),

			// TREE TRUNK 2
			new Vector2f(13.f / 16.f, 0.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f),
			
			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
	};
	
	public static Vector2f[] UV_NY = {
			
			// GRASS
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 1.f),
			new Vector2f(9.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 2.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f),

			// FLOWER - RED - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),

			// TALL-GRASS - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),
			
			// DEAD BUSH - FINAL SQUARE ON TEX ATLAS
			new Vector2f(15.f / 16.f, 15.f),
			new Vector2f(15.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 16.f / 16.f),
			new Vector2f(16.f / 16.f, 15.f / 16.f),
			new Vector2f(15.f / 16.f, 15.f),

			// TREE TRUNK 2
			new Vector2f(13.f / 16.f, 0.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f),

			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
	};
	
	public static Vector2f[] UV_PZ = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 0.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f),

			// FLOWER - RED
			new Vector2f(10.f / 16.f, 0.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f),

			// TALL-GRASS
			new Vector2f(11.f / 16.f, 0.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f),
			
			// DEAD BUSH
			new Vector2f(12.f / 16.f, 0.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f),

			// TREE TRUNK 2
			new Vector2f(14.f / 16.f, 0.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f),

			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
	};

	public static Vector2f[] UV_NZ = {
		
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// SAND
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),

			// WATER
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f),

			// CACTUS
			new Vector2f(9.f / 16.f, 0.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f),

			// FLOWER - RED
			new Vector2f(10.f / 16.f, 0.f),
			new Vector2f(10.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f / 16.f),
			new Vector2f(10.f / 16.f, 0.f),
			
			// TALL-GRASS
			new Vector2f(11.f / 16.f, 0.f),
			new Vector2f(11.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f / 16.f),
			new Vector2f(11.f / 16.f, 0.f),

			// DEAD BUSH
			new Vector2f(12.f / 16.f, 0.f),
			new Vector2f(12.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 1.f / 16.f),
			new Vector2f(13.f / 16.f, 0.f / 16.f),
			new Vector2f(12.f / 16.f, 0.f),

			// TREE TRUNK 2
			new Vector2f(14.f / 16.f, 0.f),
			new Vector2f(14.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f / 16.f),
			new Vector2f(14.f / 16.f, 0.f),

			// GRAVEL
			new Vector2f(15.f / 16.f, 0.f),
			new Vector2f(15.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 1.f / 16.f),
			new Vector2f(16.f / 16.f, 0.f / 16.f),
			new Vector2f(15.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(0.f, 1.f),
			new Vector2f(0.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 2.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f),
			new Vector2f(0.f, 1.f)
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
