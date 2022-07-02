package toolBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Mesh.voxelTypes.chunks.ChunkMesh;

public class saveChunk {
	
	public static void saveChunk(ChunkMesh cm) {
		try {
			long count = Files.find(Paths.get("/saves"), 1,  /*how deep do we want to descend*/(path, attributes) -> attributes.isDirectory()).count() - 1;
			String filePath = "saves/game" +count+ "/";
			String fileName = cm.chunk.origin + ".dat";
			File f = new File(filePath);
			FileWriter fw = new FileWriter(filePath+fileName);
			fw.write(cm.chunk.toString());
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadChunk() {
		
	}

}
