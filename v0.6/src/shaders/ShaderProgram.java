package shaders;

import static logging.WriteToTXT.writeToFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	
	int programID;
	int vertexShaderID;
	int fragmentShaderID;
	
	FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		programID = GL20.glCreateProgram(); // create program and stored it into the programID variable
		
		// load shaders
		try {
			vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println("SHADER ERROR 004: SOMETHING WENT WRONG WHILE LOADING VERTEX SHADER");
		}
		
		try {
			fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("SHADER ERROR 004: SOMETHING WENT WRONG WHILE LOADING FRAGMENT SHADER");
			writeToFile("SHADER ERROR 004: SOMETHING WENT WRONG WHILE LOADING FRAGMENT SHADER");
		}

		// attach shaders
		GL20.glAttachShader(programID, vertexShaderID);
		writeToFile("Attached vertex shader to shader program...");

		GL20.glAttachShader(programID, fragmentShaderID);
		
		writeToFile("Attached fragment shader to shader program...");
		
		bindAttributes();
		writeToFile("Bound all shader attributes to shader program...");
		
		
		// link and validate shader program
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		writeToFile("Linked and validated shader program...");
		
		
		getAllUniformLocations();
		
	}
	
	protected abstract void getAllUniformLocations();
	
	protected int getUniformLocation(String name) {
		writeToFile("Accessing uniform variable " + name + "...");
		
		return GL20.glGetUniformLocation(programID, name);
	}
	
	
	
	protected abstract void bindAttributes();
	
	
	
	
	/****************STORE VARIABLES INTO GLSL****************/
	
	protected void loadFLoat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void load2DVector(int location, Vector2f value) {
		GL20.glUniform2f(location, value.x, value.y);
	}
	
	protected void load3DVector(int location, Vector3f value) {
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadMatrix4f(int location, Matrix4f value) {
		value.store(matrixBuffer);
		matrixBuffer.flip();
		
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	protected void loadBoolean(int location, boolean bool) {

		/*
			booleans will be represented as 1s and 0s in shader code
		*/
		float val = 0;
		if (bool) {
			val = 1;
		}
		
		GL20.glUniform1f(location, val);
	}
	
	
	
	protected void bindAttribute(String var, int attrib) {
		GL20.glBindAttribLocation(programID, attrib, var);
	}
	
	public void start() {
		GL20.glUseProgram(programID);
	}

	public void stop() {
		/*
			tells OpenGL that we are not using any programs and therefore stops
			any programs currently being used
		*/
		GL20.glUseProgram(0);}
	
	public void cleanUp() {
		stop();
		// removes shaders from program
		GL20.glDetachShader(programID, vertexShaderID);
		writeToFile("Detached vertex shader from shader program...");
		GL20.glDetachShader(programID, fragmentShaderID);
		writeToFile("Detached fragment shader from shader program...");
				
		// delete shaders
		GL20.glDeleteShader(vertexShaderID);
		writeToFile("Deleted vertex shader...");
				
		GL20.glDeleteShader(fragmentShaderID);
		writeToFile("Deleted fragment shader...");
		
		
		// delete program
		GL20.glDeleteProgram(programID);
		writeToFile("Deleted shader program");
		
	}
	
	private int loadShader(String filepath, int type) throws FileNotFoundException {
		// load glsl file as string
		StringBuilder shaderSrc = new StringBuilder();

		InputStream in;
		try {
			in = new FileInputStream(filepath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.err.println("SHADER ERROR 001: COULD NOT CREATE INPUTSTREAM");
			writeToFile("SHADER ERROR 001: COULD NOT CREATE INPUTSTREAM");
			throw e1;
		}
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		
		
		String line;
		try {	
			while ((line = br.readLine()) != null) {
				shaderSrc.append(line).append("//\n"); //adds shader line to shaderSrc and adds new lines
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("SHADER ERROR 002: COULD NOT LOAD SHADER FILE");
			writeToFile("SHADER ERROR 002: COULD NOT LOAD SHADER FILE");
			System.exit(-1);
		}
		
		// create shaderID
		int shaderID = GL20.glCreateShader(type);
		// put shader code into the shaderID
		GL20.glShaderSource(shaderID, shaderSrc);
		// compile shader
		GL20.glCompileShader(shaderID);
		
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("SHADER ERROR 003: FAILED TO COMPILE SHADER");
			writeToFile("SHADER ERROR 03: FAILED TO COMPILE SHADER");
			
			System.out.println("Shader Compile STATUS: " + GL20.glGetShaderInfoLog(shaderID, 1000));
			writeToFile("Shader compile status " + GL20.glGetShaderInfoLog(shaderID, 1000));
			
			System.exit(-1);
		}
		
		return shaderID;
	}

}