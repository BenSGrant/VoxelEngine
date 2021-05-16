package VoxelEngine;

import static logging.WriteToTXT.clearLogFile;
import static logging.WriteToTXT.writeToFile;

import java.io.IOException;

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
import textures.ModelTexture;

public class MainGameLoop {

	public static Loader loader1 = null;
	public static StaticShader shader1 = null;

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

		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] uv = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};

		RawModel model = loader.loadToVAO(vertices, indices, uv); // create model
		ModelTexture tex = new ModelTexture(loader.loadTexture("blocks/dirtTex"));
		writeToFile("Loaded asset res/textures/dirtTex.PNG and bound it to a model...");
		TexturedModel texModel = new TexturedModel(model, tex);
		Entity entity = new Entity(texModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);

		Camera cam = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);

		System.out.println("Entering game loop...");
		writeToFile("Entering game loop...");

		while (!Display.isCloseRequested()) {
			cam.move();
			renderer.prepare();
			// always start shader before you start rendering
			shader.start();
			shader.loadViewMatrix(cam);

			renderer.render(entity, shader); // renders model
			shader.stop();
			DisplayManager.updateDisplay();
		}

		System.out.println("Exited game loop, closing display and exiting game...");
		writeToFile("Exited game loop, closing display and exiting game...");
		DisplayManager.closeDisplay();

	}
}