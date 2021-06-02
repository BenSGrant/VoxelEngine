package Mesh.voxelTypes.tree;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Mesh.voxelTypes.cube.Block;

public class Tree {

	public List<Block> blocks;
	public Vector3f origin;
	
	public Tree(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}
}
