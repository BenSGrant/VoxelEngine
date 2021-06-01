package terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Mesh.voxelTypes.chunks.Chunk;
import Mesh.voxelTypes.chunks.ChunkMesh;
import Mesh.voxelTypes.cube.Block;
import Models.TexturedModel;
import toolBox.PerlinNoiseGenerator;

public class TerrainGenerator {

	public static final int chunkSize = 16;
	public static final int chunkHeight = 8; // is multiplied by 2 for some reason at some point
	
	private static final int rDist = 22; // in chunks
	private static final int chunkBlockRadius = rDist * chunkSize;
	public static final int renderDist = chunkBlockRadius / 2;
	
	public static int totalVertexCount;
	public static int entityCount = 0;
	public static final int blockVertexCount = 36;

	static Random r = new Random();
	static int seed = r.nextInt(100);
	
	static PerlinNoiseGenerator hGen = new PerlinNoiseGenerator(seed);
	
	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();
	
	public static void generateChunk(List<ChunkMesh> chunks, TexturedModel texModel, Vector3f camPos) {

		for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
			for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
					if (!usedPos.contains(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize))) {
						
						List<Block> blocks = new ArrayList<Block>();
						
						// generate chunks
						for (int cx = 0; cx < chunkSize; cx++) {
							for (int cz = 0; cz < chunkSize; cz++) {
								for (int cy = 0; cy < chunkHeight; cy++) {
									blocks.add(new Block(cx, cy + (int) hGen.generateHeight(cx + (x * chunkSize), cz + (z * chunkSize)), cz, Block.TYPE.DIRT));
									setEntityCount(getEntityCount() + 1);
								}
							}
						}
						Chunk chunk = new Chunk(blocks, new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
						ChunkMesh cMesh = new ChunkMesh(chunk);
						
						chunks.add(cMesh);
						usedPos.add(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
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