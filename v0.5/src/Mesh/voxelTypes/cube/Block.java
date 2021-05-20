package Mesh.voxelTypes.cube;

public class Block {
	
	public int x, y, z;

	public static enum TYPE {
		
		DIRT, GRASS;
		
	};

	public TYPE type;
	
	public Block(int x, int y, int z, TYPE type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}

}
