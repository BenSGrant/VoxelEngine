package VoxelEngine;

import static logging.WriteToTXT.clearLogFile;
import static logging.WriteToTXT.writeToFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import terrain.Chunk;
import terrain.TerrainGenerator;
import terrain.voxelTypes.AtlasBlock;
import textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;
	
	static List<Chunk> chunks = Collections.synchronizedList(new ArrayList<Chunk>());
	static Vector3f camPos = new Vector3f(0,0,0);

	public static void main(String[] args) {
		try {
			clearLogFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("FILE WRITING ERROR 001: COULD NOT CLEAR LOG FILE");
		}

		System.out.println("Starting game...");
		writeToFile("Starting game...");
		DisplayManager.createDisplay();
		System.out.println("Creating display...");
		writeToFile("Creating display...");

		Loader loader = new Loader();
		loader1 = loader;

		MasterRenderer renderer = new MasterRenderer();

		System.out.println("Created MasterRenderer, Loader and StaticShader...");
		writeToFile("Created MasterRenderer, Loader and StaticShader");
		
		AtlasBlock block = new AtlasBlock();

		RawModel model = loader.loadToVAO(block.getVertices(), block.getIndices(), block.getUV()); // create model
		ModelTexture tex = new ModelTexture(loader.loadTexture("blocks/grass_atlas"));
		writeToFile("Loaded asset res/textures/grass_atlas.PNG and bound it to a model...");
		TexturedModel texModel = new TexturedModel(model, tex);
		
		Camera cam = new Camera(new Vector3f(0, TerrainGenerator.chunkHeight, 0), 0, 0, 0);

		// terrain generating thread
		new Thread(new Runnable() {
			public void run() {
				while(!Display.isCloseRequested()) {
					TerrainGenerator.generateChunkRF(chunks, texModel, camPos);
				}
			}
		}).start();
		
		System.out.println("Entering game loop...");
		writeToFile("Entering game loop...");

		while (!Display.isCloseRequested()) {
			cam.move();
			System.out.println("POSITION:    X: " + cam.getPosition().x * TerrainGenerator.chunkSize + "    Y: " + cam.getPosition().y * TerrainGenerator.chunkHeight + "    Z: " + cam.getPosition().x * TerrainGenerator.chunkSize);
			camPos = cam.getPosition();
			
			for(int i = 0; i < chunks.size(); i++) {
				
				Vector3f origin = chunks.get(i).getOrigin();
				
				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);
				
				if (distX < 0) {
					distX = -distX;
				}
				if (distZ < 0) {
					distZ = -distZ;
				}
				
				if ((distX <= TerrainGenerator.renderDist) && (distZ <= TerrainGenerator.renderDist)) {
					for(int j = 0; j < chunks.get(i).getBlocks().size(); j++) {
						// for every block in the chunk
						renderer.addEntity(chunks.get(i).getBlocks().get(j));
					}
				}
				TerrainGenerator.setTotalVertexCount(chunks.size() * TerrainGenerator.blockVertexCount);
			}

			renderer.render(cam);
			
			DisplayManager.updateDisplay();
		}

		System.out.println("Exited game loop, closing display and exiting game...");
		writeToFile("Exited game loop, closing display and exiting game...");
		DisplayManager.closeDisplay();

	}
}