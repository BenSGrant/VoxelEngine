package toolBox;

import org.lwjgl.util.vector.Vector3f;

import Mesh.voxelTypes.cube.Block;

public class WorldEditing {
	
	public static void addBlock(Vector3f position, int type) {
		Block block = new Block((int) position.x,(int) position.y,(int) position.z, Block.TREE_TRUNK);
		/*
		 * need to find what chunk this block is in and then call update()
		 * on that chunk.
		 */
	}
}