package VoxelEngine;

import static logging.WriteToTXT.clearLogFile;
import static logging.WriteToTXT.writeToFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Entities.Light;
import Mesh.voxelTypes.chunks.ChunkMesh;
import Mesh.voxelTypes.cube.BlockModel;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import terrain.TerrainGenerator;
import textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;

	static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>());
	static Vector3f camPos = new Vector3f(0,TerrainGenerator.chunkHeight,0);
	
	static List<Entity> entities = new ArrayList<Entity>();
	
	static Random random = new Random();

	public static void main(String[] args) {
		try {
			clearLogFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("FILE WRITING ERROR 001: COULD NOT CLEAR LOG FILE");
		}

		System.out.println("Starting game..."); writeToFile("Starting game...");
		DisplayManager.createDisplay();
		System.out.println("Created display..."); writeToFile("Created display...");

		Loader loader = new Loader();
		loader1 = loader;
		MasterRenderer renderer = new MasterRenderer();

		System.out.println("Created MasterRenderer and Loader...");
		writeToFile("Created MasterRenderer and Loader...");
		
		//AtlasBlock block = new AtlasBlock();
		BlockModel block = new BlockModel();
		
		RawModel model = loader.loadToVAO(block.getVertices(), block.getNormals(), block.getIndices(), block.getUV()); // create model
		ModelTexture tex = new ModelTexture(loader.loadTexture("blocks/pack"));
		writeToFile("Created ModelTexture using asset res/textures/blocks/pack ...");
		TexturedModel texModel = new TexturedModel(model, tex);
		
		Light light = new Light(new Vector3f(0,2000,0), new Vector3f(3,3,3)); // 3,3,3
		Camera cam = new Camera(new Vector3f(0, TerrainGenerator.chunkHeight + 10, 0), 0, 0, 0);

		
		// terrain generating thread
		new Thread(new Runnable() {
			public void run() {
				writeToFile("Terrain handling thread starting...");
				while(!Display.isCloseRequested()) {
					TerrainGenerator.generateChunk(chunks, texModel, camPos);
					if (Mouse.isButtonDown(0)) {
						
					}
				}
			}
		}).start();
		
		/*List<Block> blocks = new ArrayList<Block>();
		
		for (int x = 0; x < TerrainGenerator.chunkSize; x++) {
			for (int y = 0; y < TerrainGenerator.chunkHeight; y++) {
				for (int z = 0; z < TerrainGenerator.chunkSize; z++) {
					blocks.add(new Block(x, y, z, Block.TYPE.DIRT));
				}
			}
		}
		
		Chunk chunk = new Chunk(blocks, new Vector3f(0,0,0));
		ChunkMesh cMesh = new ChunkMesh(chunk);
		
		RawModel cMeshModel = loader.loadToVAO(cMesh.positions, cMesh.uvs);
		TexturedModel cMeshTexModel = new TexturedModel(cMeshModel, tex);
		Entity entity = new Entity(cMeshTexModel, new Vector3f(0,0,0),0,0,0,1);
		*/
		
		System.out.println("Entering main game loop...");
		writeToFile("Entering main game loop...");

		int index = 0;
		TerrainGenerator.generateHeights();

		while (!Display.isCloseRequested()) {
			// find current chunk
			//ChunkMesh currentChunk;
			
//			for (ChunkMesh cm : chunks) {
//				System.out.println("looping through chunk meshes");
//				Chunk c = cm.chunk;
//				Vector3f camChunkPos = new Vector3f(camPos.x-TerrainGenerator.chunkSize, 0, camPos.z-TerrainGenerator.chunkSize);
//				if (Maths.distanceBetween(camChunkPos, c.origin) < Math.sqrt(Math.pow(TerrainGenerator.chunkSize, 2) + Math.pow(TerrainGenerator.chunkSize, 2) + Math.pow(TerrainGenerator.chunkSize, 2))) {
//					currentChunk = cm;
					cam.move(DisplayManager.getDelta());
//					System.out.println("current chunk found");
//					break;
//				}
//			}
			
			camPos = cam.getPosition();
			
			if (index < chunks.size()) {
				RawModel cMeshModel = loader.loadToVAO(chunks.get(index).positions, chunks.get(index).uvs);
				TexturedModel cMeshTexModel = new TexturedModel(cMeshModel, tex);
				Entity entity = new Entity(cMeshTexModel, chunks.get(index).chunk.origin, 0, 0, 0, 1);
				entities.add(entity);
				
				// delete arrays because they are in GPU memory we don't need them in RAM anymore
				
				chunks.get(index).positions = null;
				chunks.get(index).uvs = null;
				chunks.get(index).normals = null;
				
				// these are now in entities list, no longer needed in RAM
				cMeshModel = null;
				cMeshTexModel = null;
				entity = null;
				index++;
				
			}
			
			// number of chunks loaded in should only be renderDist^2

			for(int i = 0; i < entities.size(); i++) {
				
				Vector3f origin = entities.get(i).getPosition();
				
				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);
				
				if (distX < 0) {
					distX = -distX;
				}
				if (distZ < 0) {
					distZ = -distZ;
				}
				
				if ((distX <= TerrainGenerator.renderDist) && (distZ <= TerrainGenerator.renderDist)) {
					renderer.addEntity(entities.get(i));
				} else {
					renderer.removeEntity(entities.get(i));
				}
				//TerrainGenerator.setTotalVertexCount(chunks.size() * TerrainGenerator.blockVertexCount);
			}
			renderer.render(light, cam);

			DisplayManager.updateDisplay();
		}

		System.out.println("Exited game loop, closing display and exiting game...");
		writeToFile("Exited game loop, closing display and exiting game...");
		DisplayManager.closeDisplay();

	}
}