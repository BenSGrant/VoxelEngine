package Mesh.voxelTypes.cube;

public class Block {
	
	public int x, y, z;

	public static final int GRASS		= 0;
	public static final int DIRT		= 1;
	public static final int STONE		= 2;
	public static final int TREE_TRUNK  = 3;
	public static final int LEAF		= 4;
	public static final int SAND		= 5;
	public static final int WATER		= 6;
	public static final int CACTUS		= 7;
	public static final int RED_FLOWER	= 8;
	public static final int TALL_GRASS	= 9;
	public static final int DEAD_BUSH	= 10;
	public static final int TREE_TRUNK2	= 11;
	public static final int GRAVEL		= 12;
	public static final int BEDROCK		= 13;
	
	public int type;
	
	public Block(int x, int y, int z, int type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}

}
