package terrain;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Models.TexturedModel;

public class TerrainGenerator {

	public static final int chunkSize = 16;
	public static final int chunkHeight = 1;
	
	private static final int rDist = 6; // in chunks
	private static final int chunkBlockRadius = rDist * chunkSize;
	public static final int renderDist = chunkBlockRadius / 2;
	
	public static int totalVertexCount;
	public static final int blockVertexCount = 36;
	
	
	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();

	public static void generateChunkRF(List<Chunk> chunks, TexturedModel texModel, Vector3f camPos) {
		
		for(int x = (int) (camPos.x - renderDist); x < (camPos.x + renderDist); x++) {
			
			for(int z = (int) (camPos.z - renderDist); z < camPos.z + renderDist; z++) {
				
				for (int y = 0; y < chunkHeight; y++) {
				
					if (!usedPos.contains(new Vector3f(x * chunkSize, y * chunkHeight, z * chunkSize))) {
						
						List<Entity> blocks = new ArrayList<Entity>();
						
						// generate chunks
						for (int cx = 0; cx < chunkSize; cx++) {
							for (int cz = 0; cz < chunkSize; cz++) {
								blocks.add(new Entity(texModel, new Vector3f((x * chunkSize) + cx, (y), (z * chunkSize) + cz), 0, 0, 0, 1));
							}
						}
						chunks.add(new Chunk(blocks, new Vector3f(x * chunkSize, y * chunkHeight, z * chunkSize)));
						usedPos.add(new Vector3f(x * chunkSize, y * chunkHeight, z * chunkSize));
					}
				}
			}
		}
		
		setTotalVertexCount(chunks.size() * blockVertexCount);
	}
	
	public static void generateBlock(List<Chunk> chunks, TexturedModel texModel, Vector3f position) {
		List<Entity> blocks = new ArrayList<Entity>();
		if(!usedPos.contains(position)) {
			blocks.add(new Entity(texModel, position, 0, 0, 0, 1));
		}
		chunks.add(new Chunk(blocks, position));
		usedPos.add(position);
	}
	
	public static int getTotalVertexCount() {
		return totalVertexCount;
	}


	public static void setTotalVertexCount(int newVal) {
		totalVertexCount = newVal;
	}

	
}