package shaders;

import static logging.WriteToTXT.writeToFile;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import toolBox.Maths;

public class StaticShader extends ShaderProgram {

	private static final String vertexFile = "src/shaders/vertexShader.glsl";
	private static final String fragmentFile = "src/shaders/fragmentShader.glsl";
	
	int location_transformationMatrix;
	int location_projectionMatrix;
	int location_viewMatrix;
	int location_skyColour;
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
		System.out.println("Loaded asset " + vertexFile + "...");
		writeToFile("Loaded asset " + vertexFile + "...");
		System.out.println("Loaded asset " + fragmentFile + "...");
		writeToFile("Loaded asset " + fragmentFile + "...");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0); // link vertex shader variable position to vao index 0
		writeToFile("Bound attribute position to VAO index 0...");
		super.bindAttribute("textureCoords", 1);
		writeToFile("Bound attribute textureCoords to VAO index 1...");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		writeToFile("Retrieved transformationMatrix location : " + location_transformationMatrix);
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		writeToFile("Retrieved projectionMatrix location : " + location_projectionMatrix);
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		writeToFile("Retrieved viewMatrix location : " + location_viewMatrix);
		location_skyColour = super.getUniformLocation("skyColour");
		writeToFile("Retrieved skyColour location : " + location_skyColour);
		
		writeToFile("Finished retrieving uniform locations...");
	}
	
	public void loadSkyColour(Vector3f vec) {
		super.load3DVector(location_skyColour, vec);
	}
	
		/* Model View Projection Matrices */
	
	public void loadTransformationMatrix(Matrix4f mat) {
		super.loadMatrix4f(location_transformationMatrix, mat);
	}
	
	public void loadProjectionMatrix(Matrix4f mat) {
		super.loadMatrix4f(location_projectionMatrix, mat);
	}
	
	public void loadViewMatrix(Camera cam) {
		super.loadMatrix4f(location_viewMatrix, Maths.createViewMatrix(cam));
	}
	
}