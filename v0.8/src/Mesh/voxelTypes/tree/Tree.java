package Mesh.voxelTypes.tree;

import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import Mesh.voxelTypes.cube.Block;

public class Tree {

	public List<Block> blocks;
	public Vector3f origin;
	
	public Tree(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}
	
	public void Create() {
		/*
			these trees are meant to be like MC oak trees - square with the cross shaped 2 block height part on top
		*/
		// trunk 4-6 blocks tall
		Random r = new Random();
		int trunkheight = r.nextInt(2) + 4;
		
		for(int i = 0; i < trunkheight; i++) {
			blocks.add(new Block((int)origin.x,(int)origin.y + i,(int)origin.z, 3));
		}
		
		// leaves in a 5x5x2 cube around top 2 pieces of trunk
		// leave (pun not intended) out the blocks of the trunk
		for (int y = (trunkheight - 2); y < (trunkheight + 1); y++) {
			for (int x = -2; x < 3; x++) {
				for (int z = -2; z < 3; z++) {
					blocks.add(new Block((int)origin.x+x,(int)origin.y+y,(int)origin.z+z, 4));
				}
			}
		}
		
		// generate the  cross shaped part on top
		/*
			positions (relative to origin):
				- centre:
					- 0, trunkheight + 2, 0
					- 0, trunkheight + 3, 0
				- left:
					- -1, trunkheight +2, 0
					- -1, trunkheight +3, 0
				- right:
					- 1, trunkheight + 2, 0
					- 1, trunkheight + 3, 0
				- back:
					- 0, trunkheight + 2, 1
					- 0, trunkheight + 3, 1
				- front:
					- 0, trunkheight + 2, -1
					- 0, trunkheight + 3, -1
					
		*/
		
		for (int y = trunkheight + 1; y < trunkheight + 3; y++) {
			for (int x = -1; x < 2; x++) {
				blocks.add(new Block((int)origin.x+x,(int)origin.y+y,(int)origin.z, 4));
			}
			// now add back and front:
			blocks.add(new Block((int)origin.x,(int)origin.y+y,(int)origin.z - 1, 4));
			blocks.add(new Block((int)origin.x,(int)origin.y+y,(int)origin.z + 1, 4));
		}
	}
}
