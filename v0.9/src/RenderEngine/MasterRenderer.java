package RenderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Entities.Light;
import Models.TexturedModel;
import shaders.StaticShader;

public class MasterRenderer {

	public Matrix4f projectionMatrix;

	private static final float FOV = 110f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 664f; // essentially final render distance in blocks (lets say 64 * 12 = 664)

	public static Vector3f skyColour = new Vector3f(0.45f, 0.55f, 0.6f);  // day
	//public static Vector3f skyColour = new Vector3f(0.08f, 0.12f, 0.20f);  // night
	//public static Vector3f skyColour = new Vector3f(0.85f, 0.3f, 0.15f,);  // sunset
	
	public static StaticShader shader = new StaticShader();
	EntityRenderer eRenderer = new EntityRenderer();
	Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	public MasterRenderer() {
		//enableCulling();
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		// makes cool coloured glass effect
		//GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glClearColor(skyColour.x,skyColour.y,skyColour.z, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(Light light, Camera cam) {
		prepare();
		// always start shader before rendering
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(cam);
		shader.loadSkyColour(skyColour);
		eRenderer.render(entities);
		//cull();
		shader.stop();
		entities.clear();
	}
	
	public static void enableCulling() { 
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
	public static void cull() {
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	
	public void addEntity(Entity entity) {
		TexturedModel model = entity.getModel();
		
		List<Entity> batch = entities.get(model);
		
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(model, newBatch);
		}
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity.getModel());
	}

	public void createProjectionMatrix() {
		projectionMatrix = new Matrix4f();
		
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV/2f)));
		float xScale = yScale / aspectRatio;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = xScale;	// m00 means x and y component in the first (0th) column
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -zp/zm;
		projectionMatrix.m23 = -1;
		/*
			m23 means:
			2 = component x in third (2nd) column
			3 = component y in fourth (3rd) column
		*/
		projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
		projectionMatrix.m33 = 0;
	}
}
