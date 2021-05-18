package Models;

import static logging.WriteToTXT.writeToFile;

import textures.ModelTexture;

public class TexturedModel {
	
	RawModel model;
	ModelTexture tex;
	
	public TexturedModel(RawModel model, ModelTexture tex) {
		this.model = model;
		this.tex = tex;
		writeToFile("Created Textured model using rawmodel in VAO " + model.vaoID);
	}

	public RawModel getModel() {
		return model;
	}

	public ModelTexture getTex() {
		return tex;
	}

}
