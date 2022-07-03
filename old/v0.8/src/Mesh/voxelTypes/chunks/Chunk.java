package Mesh.voxelTypes.chunks;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Mesh.voxelTypes.cube.Block;

public class Chunk {

	public List<Block> blocks;
	public Vector3f origin;
	
	public Chunk(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}
	
	public void changeBlock(int index, Block newBlock) {
		blocks.set(index, newBlock);
	}
	
	public void removeBlock(int index) {
		blocks.remove(index);
	}
	
	public int getBlockCount() {
		return blocks.size();
	}
}