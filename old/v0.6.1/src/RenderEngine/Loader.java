package RenderEngine;

import static logging.WriteToTXT.writeToFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Models.RawModel;

public class Loader {
	
	static List<Integer> vaos = new ArrayList<Integer>();
	static List<Integer> vbos = new ArrayList<Integer>();
	static List<Integer> texIDs = new ArrayList<Integer>();
	public static List<String> texNames = new ArrayList<String>();
	
	public RawModel loadToVAO(float[] vertices, int[] indices, float[] uv) {//load vertices into VAOs and VBOs
		
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3); // store vertex coordinates in attribute 0 of the VAO, dimensions are 3D
		storeDataInAttributeList(uv, 1, 2);		  // store UV coordinates in attribute 1 of the VAO, dimensions are 2D
		bindIndicesBuffer(indices); //bind index buffers
		GL30.glBindVertexArray(0); //unbind VAO
		
		return new RawModel(vaoID, indices.length); // any getVertexCount() with this model will return the number of indices
		
	}
	public RawModel loadToVAO(float[] vertices, float[] uv) {//load vertices into VAOs and VBOs
	
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3); // store vertex coordinates in attribute 0 of the VAO, dimensions are 3D
		storeDataInAttributeList(uv, 1, 2);		  // store UV coordinates in attribute 1 of the VAO, dimensions are 2D
		GL30.glBindVertexArray(0); //unbind VAO
		
		return new RawModel(vaoID, vertices.length / 3);
		
	}
	
	private int createVAO() {
		
		int vaoID = GL30.glGenVertexArrays(); // creates VAO
		vaos.add(vaoID); //adds vao to vaos list
		GL30.glBindVertexArray(vaoID); //binds VAO so we can use it
		return vaoID;
	}
	
	public int loadTexture(String filepath) {
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture("png", new FileInputStream("res/textures/" + filepath + ".PNG"));
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -4);
			// 1:04:27
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("LOADER ERROR 001: could not load texture " + filepath);
			writeToFile("LOADER ERROR 001: could not load texture " + filepath);
		}
		
		int textureID = tex.getTextureID();
		texIDs.add(textureID);
		texNames.add(filepath);
		
		return textureID;
		
	}
	
	private void storeDataInAttributeList(float[] data, int attributeNumber, int dimentions) { //stores all vertices into VBO and then store VBO in VAO
		//System.out.println("Storing data in attribute list " + attributeNumber + "...");
		//writeToFile("Strong data in attribute list " + attributeNumber + "...");
		int vboID = GL15.glGenBuffers(); //generates a VBO
		vbos.add(vboID); //adds VBO to vbos list
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); //bind VBO
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,  buffer,  GL15.GL_STATIC_DRAW); //store data in VBO
		GL20.glVertexAttribPointer(attributeNumber, dimentions, GL11.GL_FLOAT, false, 0, 0);//store VBO in attribute 0 of VAO
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //unbindbuffer
	}
	
	private void bindIndicesBuffer(int[] indices) {
		System.out.println("Binding indices buffer...");
		writeToFile("Binding indices buffer...");
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); // everything is static
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data) {
		System.out.println("Storing data in int buffer...");
		writeToFile("Storing data in int buffer...");
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		
		return b;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) { //stores float array (float[]) into a FloatBuffer
		//System.out.println("Storing data in float buffer...");
		//writeToFile("Storing data in float buffer...");
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length); //create FloatBuffer data.length is size
		buffer.put(data); // put 'data' into the buffer
		buffer.flip(); //make buffer readable
		
		return buffer;
	}
	
	public void cleanUp() { //removes vertices from memory
		for(int vao : vaos) { // for each VAO in vaos list
			GL30.glDeleteVertexArrays(vao); // deletes VAO
			System.out.println("Deleted VAO " + vao + "...");
			//writeToFile("Deleted VAO " + vao + "...");
		}
		vaos.clear();
		for(int vbo : vbos) { // for each VBO in vbos list
			GL15.glDeleteBuffers(vbo); // deletes VBO
			System.out.println("Deleted VBO " + vbo + "...");
			//writeToFile("Deleted VBO " + vbo + "...");
		}
		vbos.clear();
		int texCount = 0;
		for(int tex : texIDs) { // for each texture in textures list
			GL11.glDeleteTextures(tex); // deletes texture
			System.out.println("Deleted texture, ID: " + tex + "    File: textures/" + texNames.get(texCount) + ".PNG ...");
			//writeToFile("Deleted texture, ID: " + tex + "    File: textures/" + texNames.get(texCount) + ".PNG ...");
			texCount++;
		}
		texIDs.clear();
	}
	
	public static String getTexNameFromID(int ID) {
		String name = texNames.get(ID);
		return name;
	}
	
}
