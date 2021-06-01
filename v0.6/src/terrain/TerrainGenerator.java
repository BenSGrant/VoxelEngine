package terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Mesh.voxelTypes.chunks.Chunk;
import Mesh.voxelTypes.chunks.ChunkMesh;
import Mesh.voxelTypes.cube.Block;
import Mesh.voxelTypes.cube.BlockModel;
import Models.TexturedModel;
import toolBox.PerlinNoiseGenerator;

public class TerrainGenerator {

	public static final int chunkSize = 8;
	public static final int chunkHeight = 16;
	
	private static final int mcRDist = 8;
	private static final int rDist = mcRDist * 2; // in chunks
	private static final int chunkBlockRadius = rDist * chunkSize;
	public static final int renderDist = chunkBlockRadius / 2;
	
	public static int totalVertexCount;
	public static int entityCount = 0;
	public static final int blockVertexCount = (BlockModel.PX.length + BlockModel.NX.length + BlockModel.PY.length + BlockModel.NY.length + BlockModel.PZ.length + BlockModel.PZ.length);

	static Random r = new Random();
	static final int seed = r.nextInt(2147483647); // max value for an integer
	
	static PerlinNoiseGenerator hGen = new PerlinNoiseGenerator(seed);
	
	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();
	
	public static void generateChunk(List<ChunkMesh> chunks, TexturedModel texModel, Vector3f camPos) {

		for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
			for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
					if (!usedPos.contains(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize))) {
						List<Block> blocks = new ArrayList<Block>();
						
						int index = chunkHeight + 8;
						final int startIndex = index;
						
						// generate chunks
						for (int sx = 0; sx < chunkSize; sx++) {
							for (int sz = 0; sz < chunkSize; sz++) {
								index += (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize));
								for (int sy = 0; sy < index; sy++) {
									if (sy < index - chunkSize) {
										blocks.add(new Block(sx, sy, sz, Block.STONE));
									}
									else if (sy < (index + 3)) {
										blocks.add(new Block(sx, sy, sz, Block.DIRT));
									}
									blocks.add(new Block(sx,  startIndex + (int) hGen.generateHeight(sx + (x * chunkSize), sz + (z * chunkSize)), sz, Block.GRASS));
								}
								index = startIndex;
							}
						}
						
						Chunk chunk = new Chunk(blocks, new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
						ChunkMesh cMesh = new ChunkMesh(chunk);
						
						chunks.add(cMesh);
						usedPos.add(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
						blocks.clear();
				}
			}
		}
		
		setTotalVertexCount(chunks.size() * blockVertexCount);
	}
	
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