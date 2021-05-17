package terrain;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Models.TexturedModel;

public class Chunk {
	
	private static final int chunkSize = 64;
	public static final int blockRenderDist = chunkSize / 2;
	
	public static int totalVertexCount;
	public static final int blockVertexCount = 36;
	
	
	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();

	/* runs faster on single thread but doesn't work with multi-threading */
	public static void generateChunkGTB(List<Entity> entities, TexturedModel texModel, Vector3f camPos) {
		entities.removeAll(entities);
		
		for(int x = (int) (camPos.x - blockRenderDist); x < camPos.x + blockRenderDist; x++) {
			for(int z = (int) (camPos.z - blockRenderDist); z < camPos.z + blockRenderDist; z++) {
				entities.add(new Entity(texModel, new Vector3f(x, 0, z), 0f, 0f, 0f, 1f ));
			}
		}
	}

	
	/* Reon Fourie's method, slower on single thread but works with multi-threading */
	public static void generateChunkRF(List<Entity> entities, TexturedModel texModel, Vector3f camPos) {
		for(int x = (int) (camPos.x - blockRenderDist); x < camPos.x; x++) {
			for(int z = (int) (camPos.z); z < camPos.z + blockRenderDist; z++) {
				if (!usedPos.contains(new Vector3f(x, 0, z))) {
					entities.add(new Entity(texModel, new Vector3f(x, 0, z), 0f, 0f, 0f, 1f ));
					usedPos.add(new Vector3f(x, 0, z));
				}
			}
		}
		
		for(int x = (int) (camPos.x); x < camPos.x + blockRenderDist; x++) {
			for(int z = (int) (camPos.z); z < camPos.z + blockRenderDist; z++) {
				if (!usedPos.contains(new Vector3f(x, 0, z))) {
					entities.add(new Entity(texModel, new Vector3f(x, 0, z), 0f, 0f, 0f, 1f ));
					usedPos.add(new Vector3f(x, 0, z));
				}
			}
		}
		
		for(int x = (int) (camPos.x); x < camPos.x + blockRenderDist; x++) {
			for(int z = (int) (camPos.z - blockRenderDist); z < camPos.z; z++) {
				if (!usedPos.contains(new Vector3f(x, 0, z))) {
					entities.add(new Entity(texModel, new Vector3f(x, 0, z), 0f, 0f, 0f, 1f ));
					usedPos.add(new Vector3f(x, 0, z));
				}
			}
		}
		
		for(int x = (int) (camPos.x - blockRenderDist); x < camPos.x; x++) {
			for(int z = (int) (camPos.z - blockRenderDist); z < camPos.z; z++) {
				if (!usedPos.contains(new Vector3f(x, 0, z))) {
					entities.add(new Entity(texModel, new Vector3f(x, 0, z), 0f, 0f, 0f, 1f ));
					usedPos.add(new Vector3f(x, 0, z));
				}
			}
		}
		setTotalVertexCount(entities.size() * blockVertexCount);
	}
	
	public static int getTotalVertexCount() {
		return totalVertexCount;
	}


	public static void setTotalVertexCount(int newVal) {
		totalVertexCount = newVal;
	}

	
}