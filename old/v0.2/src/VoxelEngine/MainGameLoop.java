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
import shaders.StaticShader;
import terrain.Block;
import terrain.Chunk;
import textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;
	public static StaticShader shader1 = null;
	
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

		StaticShader shader = new StaticShader();
		shader1 = shader;

		MasterRenderer renderer = new MasterRenderer(shader);

		System.out.println("Created MasterRenderer, Loader and StaticShader...");
		writeToFile("Created MasterRenderer, Loader and StaticShader");
		
		Block block = new Block();
		

		RawModel model = loader.loadToVAO(block.getVertices(), block.getIndices(), block.getUV()); // create model
		ModelTexture tex = new ModelTexture(loader.loadTexture("blocks/dirtTex"));
		writeToFile("Loaded asset res/textures/dirtTex.PNG and bound it to a model...");
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
		
		
		// terrain clean up thread
		new Thread(new Runnable() {
			public void run() {
				while(!Display.isCloseRequested()) {
					Chunk.cleanChunks(entities, camPos);
				}
			}
		}).start();
		
		System.out.println("Entering game loop...");
		writeToFile("Entering game loop...");

		while (!Display.isCloseRequested()) {
			cam.move();
			camPos = cam.getPosition();
			
			renderer.prepare();
			// always start shader before you start rendering
			shader.start();
			shader.loadViewMatrix(cam);

			for(int i = 0; i < entities.size(); i++) {
				renderer.render(entities.get(i), shader);
			}

			shader.stop();
			DisplayManager.updateDisplay();
		}

		System.out.println("Exited game loop, closing display and exiting game...");
		writeToFile("Exited game loop, closing display and exiting game...");
		DisplayManager.closeDisplay();

	}
}