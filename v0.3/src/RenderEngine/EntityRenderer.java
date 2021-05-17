package RenderEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Entities.Entity;
import Models.TexturedModel;
import shaders.StaticShader;
import toolBox.Maths;

public class EntityRenderer {
	
	public static StaticShader shader = new StaticShader();
	
	public void render(Map<TexturedModel, List<Entity>> entities) {
		
		for (TexturedModel model : entities.keySet()) {

			GL30.glBindVertexArray(model.getModel().getVaoID()); // bind VAO
			GL20.glEnableVertexAttribArray(0); // enables the attribute 0 of the VAO
			GL20.glEnableVertexAttribArray(1); // enables the attribute 1 of the VAO
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0); // activate texture 0
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTex().getTexID());
			
			List<Entity> batch = entities.get(model);
			
			for (Entity entity : batch) {
				Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(),
						entity.getRotY(), entity.getRotZ(), entity.getScale());
				shader.loadTransformationMatrix(transformationMatrix);

				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0); 
			}
			
			// disable VAOs after they have been rendered
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL30.glBindVertexArray(0); // unbind VAO
		}
	}

}
