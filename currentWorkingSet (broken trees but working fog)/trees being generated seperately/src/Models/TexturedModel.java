package Models;

import textures.ModelTexture;

public class TexturedModel {
	
	RawModel model;
	ModelTexture tex;
	
	public TexturedModel(RawModel model, ModelTexture tex) {
		this.model = model;
		this.tex = tex;
	}

	public RawModel getModel() {
		return model;
	}

	public ModelTexture getTex() {
		return tex;
	}

}
