package RenderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Entities.Entity;
import shaders.StaticShader;
import toolBox.Maths;

public class EntityRenderer {

	public static void render(Entity entity, StaticShader shader) {
		GL30.glBindVertexArray(entity.getModel().getModel().getVaoID()); // bind VAO
		GL20.glEnableVertexAttribArray(0); // enables the attribute 0 of the VAO
		GL20.glEnableVertexAttribArray(1); // enables the attribute 1 of the VAO

		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(),
				entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);

		GL13.glActiveTexture(GL13.GL_TEXTURE0); // activate texture 0
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTex().getTexID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0); 
		// draws vertices on screen

		// disable VAOs after they have been rendered
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0); // unbind VAO
	}

}
