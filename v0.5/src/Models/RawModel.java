package Models;

import static logging.WriteToTXT.writeToFile;

public class RawModel {
	
	int vaoID;
	int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		writeToFile("Created a rawmodel in VAO " + vaoID + "   with vertex count " + vertexCount);
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
