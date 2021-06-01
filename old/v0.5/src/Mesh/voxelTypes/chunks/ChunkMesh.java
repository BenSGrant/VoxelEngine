package Mesh.voxelTypes.chunks;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Mesh.Vertex;
import Mesh.voxelTypes.cube.Block;
import Mesh.voxelTypes.cube.BlockModel;

public class ChunkMesh {

	private List<Vertex> vertices;
	
	private List<Float> positionsList;
	private List<Float> uvsList;
	private List<Float> normalsList;
	
	public float[] positions, uvs, normals;
	
	public Chunk chunk;

	public ChunkMesh(Chunk chunk) {
		this.chunk = chunk;
		
		vertices = new ArrayList<Vertex>();

		positionsList = new ArrayList<Float>();
		uvsList = new ArrayList<Float>();
		normalsList = new ArrayList<Float>();
	
		buildMesh();
		populateLists();
	}
	
	// if the chunk needs updating, ie a block is destroyed / added
	public void update(Chunk chunk) {
		this.chunk = chunk;
		buildMesh();
		populateLists();
	}
	
	private void buildMesh() {
		/*
			loop through blocks in chunk and if a face is exposed to air,
			add it to the mesh, otherwise do not render the mesh.
			
		*/
		
		for (int i = 0; i < chunk.blocks.size(); i++) {
			Block blockI = chunk.blocks.get(i);
			
			boolean px = false, nx = false,
					py = false, ny = false,
					pz = false, nz = false;
			
			
			for(int j = 0; j < chunk.blocks.size(); j++) {
				Block blockJ = chunk.blocks.get(j);
				
				// PX face, positive X facing face
				if (((blockI.x + 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
					// we have found a block next to the current block on the PZ side
					px = true;
				}
				
				// NX face, negative X facing face
				if (((blockI.x - 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
					// we have found a block next to the current block on the PZ side
					nx = true;
				}
				
				// PY face, positive Y facing face
				if (((blockI.x) == (blockJ.x)) && ((blockI.y + 1) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
					// we have found a block next to the current block on the PY side
					py = true;
				}
				
				// NY face, negative Y facing face
				if (((blockI.x) == (blockJ.x)) && ((blockI.y - 1) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
					// we have found a block next to the current block on the NY side
					ny = true;
				}
				
				// PZ face, positive Z facing face
				if (((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z + 1) == (blockJ.z))) {
					// we have found a block next to the current block on the PZ side
					pz = true;
				}
				
				// PZ face, positive Z facing face
				if (((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z - 1) == (blockJ.z))) {
					// we have found a block next to the current block on the NZ side
					nz = true;
				}
			}
			
			// add visible faces to chunk mesh

			if (!px) { // PX face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.PX[k].x + blockI.x,
														 BlockModel.PX[k].y + blockI.y,
														 BlockModel.PX[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}

			if (!nx) { // NX face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.NX[k].x + blockI.x,
														 BlockModel.NX[k].y + blockI.y,
														 BlockModel.NX[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}

			if (!py) { // PY face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.PY[k].x + blockI.x,
														 BlockModel.PY[k].y + blockI.y,
														 BlockModel.PY[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}

			if (!ny) { // NY face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.NY[k].x + blockI.x,
														 BlockModel.NY[k].y + blockI.y,
														 BlockModel.NY[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}

			if (!pz) { // PZ face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.PZ[k].x + blockI.x,
														 BlockModel.PZ[k].y + blockI.y,
														 BlockModel.PZ[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}

			if (!nz) { // NZ face is visible
				for (int k = 0; k < 6; k++) {
					vertices.add(new Vertex(new Vector3f(BlockModel.NZ[k].x + blockI.x,
														 BlockModel.NZ[k].y + blockI.y,
														 BlockModel.NZ[k].z + blockI.z),
											BlockModel.UV[k],
											BlockModel.NORMALS[k]));
				}
			}
			
		}
	}
	
	private void populateLists() {
		
		for (int i = 0; i < vertices.size(); i++) {
			positionsList.add(vertices.get(i).positions.x);
			positionsList.add(vertices.get(i).positions.y);
			positionsList.add(vertices.get(i).positions.z);
			uvsList.add(vertices.get(i).uvs.x);
			uvsList.add(vertices.get(i).uvs.y);
			normalsList.add(vertices.get(i).normals.x);
			normalsList.add(vertices.get(i).normals.y);
			normalsList.add(vertices.get(i).normals.z);
		}
		positions = new float[positionsList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];

		for (int i = 0; i < positionsList.size(); i++) {
			positions[i] = positionsList.get(i);
		}
		for (int i = 0; i < uvsList.size(); i++) {
			uvs[i] = uvsList.get(i);
		}
		for (int i = 0; i < normalsList.size(); i++) {
			normals[i] = normalsList.get(i);
		}
		
		positionsList.clear();
		uvsList.clear();
		normalsList.clear();
		
	}
}