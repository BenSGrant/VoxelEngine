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
import Models.TexturedModel;
import shaders.StaticShader;

public class MasterRenderer {

	Matrix4f projectionMatrix;

	private static final float FOV = 110f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 10000f;

	public static Vector3f skyColour = new Vector3f(0.4f, 0.7f, 1.0f);  // day
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
		GL11.glClearColor(skyColour.x,skyColour.y,skyColour.z, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public void render(Camera cam) {
		prepare();
		// always start shader before rendering
		shader.start();
		shader.loadViewMatrix(cam);
		shader.loadSkyColour(skyColour);
		eRenderer.render(entities);
		shader.stop();
		entities.clear();
	}
	
	public static void enableCulling() { 
		GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glCullFace(GL11.GL_BACK);
		GL11.glCullFace(GL11.GL_LEFT);
		GL11.glCullFace(GL11.GL_RIGHT);
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
