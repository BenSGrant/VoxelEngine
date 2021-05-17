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
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import terrain.Block;
import terrain.Chunk;
import textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;
	
	static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
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
		
		Block block = new Block();

		RawModel model = loader.loadToVAO(block.getVertices(), block.getIndices(), block.getUV()); // create model
		ModelTexture tex = new ModelTexture(loader.loadTexture("blocks/dirt"));
		writeToFile("Loaded asset res/textures/dirt.PNG and bound it to a model...");
		TexturedModel texModel = new TexturedModel(model, tex);
		
		Camera cam = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);

		// terrain generating thread
		new Thread(new Runnable() {
			public void run() {
				while(!Display.isCloseRequested()) {
					Chunk.generateChunkRF(entities, texModel, camPos);
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				
			}
		}).start();
		
		System.out.println("Entering game loop...");
		writeToFile("Entering game loop...");

		while (!Display.isCloseRequested()) {
			cam.move();
			camPos = cam.getPosition();
			
			for(int i = 0; i < entities.size(); i++) {
				
				int distX = (int) (camPos.x - entities.get(i).getPosition().x);
				int distZ = (int) (camPos.z - entities.get(i).getPosition().z);
				
				if (distX < 0) {
					distX = -distX;
				}
				if (distZ < 0) {
					distZ = -distZ;
				}
				
				if ((distX <= Chunk.blockRenderDist) && (distZ <= Chunk.blockRenderDist)) {
					renderer.addEntity(entities.get(i));
				}
			}

			renderer.render(cam);
			
			DisplayManager.updateDisplay();
		}

		System.out.println("Exited game loop, closing display and exiting game...");
		writeToFile("Exited game loop, closing display and exiting game...");
		DisplayManager.closeDisplay();

	}
}