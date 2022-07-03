package terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Mesh.voxelTypes.chunks.Chunk;
import Mesh.voxelTypes.chunks.ChunkMesh;
import Mesh.voxelTypes.cube.Block;
import Mesh.voxelTypes.tree.Tree;
import Models.TexturedModel;
import toolBox.Maths;
import toolBox.PerlinNoiseGenerator;
import toolBox.saveChunk;

public class TerrainGenerator {

	public static final int chunkSize = 12;
	public static int chunkHeight = 16;
	public static final int extraIndexVal = 8;
	
	private static final int mcRDist = 6;
	private static final int rDist = mcRDist * 2; // in chunks
	private static final int chunkBlockRadius = rDist * chunkSize;
	public static final int renderDist = chunkBlockRadius / 2;
	
	public static int totalVertexCount;
	public static int entityCount = 0;//
	//public static final int blockVertexCount = (BlockModel.PX.length + BlockModel.NX.length + BlockModel.PY.length + BlockModel.NY.length + BlockModel.PZ.length + BlockModel.PZ.length);

	static Random r = new Random();
	public static final int seed = PerlinNoiseGenerator.getSeed(); // max value for an integer
	
	static PerlinNoiseGenerator hGen = new PerlinNoiseGenerator(seed);

	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();
	public static List<Vector3f> tUsedPos = new ArrayList<Vector3f>();
	
	public static void generateChunk(List<ChunkMesh> chunks, TexturedModel texModel, Vector3f camPos) {
		Random r = new Random();
		for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
			for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
					if (!usedPos.contains(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize))) {
						List<Block> blocks = new ArrayList<Block>();
						
						int index = chunkHeight + extraIndexVal;
						final int startIndex = index;
						
						// generate chunks
						//int caveIndex = 0;
						for (int sx = 0; sx < chunkSize; sx++) {
							
							//r.setSeed(x * 49632 + z * 325176 + seed);
							for (int sz = 0; sz < chunkSize; sz++) {
									index += (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize));
									if (index <= 0) {
										index = 1 + (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize));
									}
									
									for (int sy = 0; sy < index; sy++) {
										float chance = Math.abs(r.nextFloat() * 2f -1f);
										if (chance > 0.1) {
											if (sy < index - 3) { // the number next to index is the amount of dirt there is to be above the stone
												blocks.add(new Block(sx, sy, sz, Block.STONE));
											}
										}
										if (sy < (index + 3) && sy >= index - 3) {
											blocks.add(new Block(sx, sy, sz, Block.DIRT));
										}
										blocks.add(new Block(sx,  startIndex + (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize)), sz, Block.GRASS));
//										if (caveIndex >= 6) {
//											caveIndex = 0;
//										}
										//caveIndex++;
										
										int treechance = r.nextInt(4200);
										
										if (treechance == 1) { // 1/256 chance
											Tree tree = new Tree(blocks, new Vector3f(sx, startIndex + (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize))+1, sz));
											tree.Create();
										}
										
									}
									//generateTrees(trees, texModel, camPos, new Vector3f(0 , index, 0)); // x and z values for last parameter do not matter//
									index = startIndex;
							}
						}
						
						Chunk finalChunk = new Chunk(blocks, new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
						ChunkMesh cMesh = new ChunkMesh(finalChunk);
						
						chunks.add(cMesh);
						usedPos.add(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
						blocks.clear();
				}
			}
		}
		
		for (ChunkMesh cm : chunks) {
			if (Maths.distanceBetween(cm.chunk.origin, new Vector3f(camPos.x + renderDist, camPos.y + renderDist, camPos.z + renderDist)) > renderDist * chunkSize) {
				saveChunk.saveChunk(cm);
				cm = null;
			}
		}
		
		//setTotalVertexCount(chunks.size() * blockVertexCount);
	}
	
//	public static void generateTrees(List<TreeMesh> trees, TexturedModel texModel, Vector3f camPos, Vector3f position) {
//		int trunkHeight = r.nextInt(8);
//		
//		//for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
//		//	for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
//				//int tx = r.nextInt((x * x));
//				//int tz = r.nextInt((z * z));
//				//if (!usedPos.contains(new Vector3f(tx,  position.y, tz))) {
//					List<Block> blocks = new ArrayList<Block>();
//					
//					// generate tree
//					if (trunkHeight < 4) {
//						trunkHeight = 4;
//					}
//
//					for (int ty = 0; ty < trunkHeight; ty++) {
//						//blocks.add(new Block(tx, (int) chunkHeight + ty, (int) tz, Block.TREE_TRUNK));
//						blocks.add(new Block((int) position.x, (int) chunkHeight + (int) position.y, (int) position.z, Block.TREE_TRUNK));
//					}
//					
//					//Tree tree = new Tree(blocks, new Vector3f(tx, position.y, tz));
//					Tree tree = new Tree(blocks, new Vector3f(position.x, position.y, position.z));
//					TreeMesh tMesh = new TreeMesh(tree);
//					
//					trees.add(tMesh);
//					//usedPos.add(new Vector3f(tx, position.y, tz));
//					//blocks.clear();
//				//}
//		//	}
//		//}
//		
//		//		for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
////			for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
////				List<Block> blocks = new ArrayList<Block>();
////				Random r = new Random();
////				int trunkHeight = r.nextInt(7);
////				
////				if (trunkHeight < 4) {
////					trunkHeight = 4;
////				}
////				
////				if (!tUsedPos.contains(new Vector3f(position))) {
////					for (int ty = 0; ty < trunkHeight; ty++) {
////						blocks.add(new Block(x, (int) chunkHeight + ty, (int) z, Block.TREE_TRUNK));
////					}
////					
////					
////			
////				}
////				Tree tree = new Tree(blocks, new Vector3f(position));
////				TreeMesh tMesh = new TreeMesh(tree);
////				
////				trees.add(tMesh);
////				blocks.clear();
////				tUsedPos.add(new Vector3f(position));
////			}
////		}
//	}

	public static void generateBlock(List<Chunk> chunks, TexturedModel texModel, Vector3f position) {
		List<Entity> blocks = new ArrayList<Entity>();
		if(!usedPos.contains(position)) {
			blocks.add(new Entity(texModel, position, 0, 0, 0, 1));
			setEntityCount(getEntityCount() + 1);
		}
		//chunks.add(new Chunk(blocks, position));
		usedPos.add(position);
	}
	
	public static int getTotalVertexCount() {
		return totalVertexCount;
	}


	public static void setTotalVertexCount(int newVal) {
		totalVertexCount = newVal;
	}

	public static int getEntityCount() {
		return entityCount;
	}

	public static void setEntityCount(int entityCount) {
		TerrainGenerator.entityCount = entityCount;
	}
	
}