package terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Mesh.voxelTypes.chunks.Chunk;
import Mesh.voxelTypes.chunks.ChunkMesh;
import Mesh.voxelTypes.cube.Block;
import Mesh.voxelTypes.tree.Tree;
import Models.TexturedModel;
import logging.WriteToTXT;
import toolBox.FastNoiseLite;

public class TerrainGenerator {

	public static final int chunkSize = 8; // keep as powers of 2
	// limit world size to 256*256 like old MC
	public static final int worldsize = 256;
	private static final int numChunksMaxEitherDir = worldsize / chunkSize;
	public static int chunkHeight = 16;
	public static final int extraIndexVal = 8;
	
	private static final int mcRDist = 8;
	public static final int rDist = mcRDist * 2; // in chunks
	private static final int chunkBlockRadius = rDist * chunkSize;
	public static final int renderDist = chunkBlockRadius / 2;
	//private static final int UnLoadDistance = rDist;
	
	public static int totalVertexCount;
	public static int entityCount = 0;//
	//public static final int blockVertexCount = (BlockModel.PX.length + BlockModel.NX.length + BlockModel.PY.length + BlockModel.NY.length + BlockModel.PZ.length + BlockModel.PZ.length);

	static Random seedGen = new Random();
	public static final int seed = seedGen.nextInt(2^32-1); // max value for an integer
	static Random r = new Random(seed);
	

	public static List<Vector3f> usedPos = new ArrayList<Vector3f>();
	public static List<Vector3f> tUsedPos = new ArrayList<Vector3f>();

	private static float[][] noiseData = new float[256][256];
	
	public static void generateHeights() {
		long start = getCurrentTime();
		System.out.println("Generating simplex heights");
		WriteToTXT.writeToFile("Generating Simplex Noise heights");
		FastNoiseLite noise = new FastNoiseLite(seed);
		noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
		noise.SetFractalType(FastNoiseLite.FractalType.Ridged);
		
		// Gather noise data
		int NoiseIndexY= 0;
	
		for (int y = 0; y < 256; y++)
		{
			int NoiseIndexX = 0;
		    for (int x = 0; x < 256; x++)
		    {
		        noiseData[NoiseIndexX][NoiseIndexY] = noise.GetNoise(x, y)*12;
		        NoiseIndexX++;
		    }
		    NoiseIndexY++;
		}
		long finish = getCurrentTime();
		System.out.println("Done generating simplex heights in " + (finish - start) + "ms");
		WriteToTXT.writeToFile("Finished Generating Simplex Noise heights in " + (finish - start) + "ms");
	}
	
	private static int numX = 0;
	private static int numZ = 0;
	
	
	public static void generateChunk(List<ChunkMesh> chunks, TexturedModel texModel, Vector3f camPos) {
		Random r = new Random();
		List<Block> blocks = new ArrayList<Block>();
		int chunkIndexX = 0;
		for(int x = (int) (camPos.x - renderDist) / chunkSize; x < (camPos.x + renderDist) / chunkSize; x++) {
			if (numX < numChunksMaxEitherDir) {
				int chunkIndexZ = 0;
				for(int z = (int) (camPos.z - renderDist) / chunkSize; z < (camPos.z + renderDist) / chunkSize; z++) {
					if (numZ < numChunksMaxEitherDir) {
						if (!usedPos.contains(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize))) {
							
							int index = chunkHeight + extraIndexVal;
							final int startIndex = index;
							
							for (int sx = 0; sx < chunkSize; sx++) {
								
								for (int sz = 0; sz < chunkSize; sz++) {
										index += (int) noiseData[(chunkIndexX*chunkSize) + sx][ (chunkIndexZ*chunkSize) + sz];
										if (index <= 0) {
											index = 1 + (int) noiseData[(chunkIndexX*chunkSize) + sx][ (chunkIndexZ*chunkSize) + sz];
										}
										
										for (int sy = 0; sy < index; sy++) {
											if (sy < index - 3) { // the number next to index is the amount of dirt there is to be above the stone
												blocks.add(new Block(sx, sy, sz, Block.STONE));
											}
											if (sy < (index + 3) && sy >= index - 3) {
												blocks.add(new Block(sx, sy, sz, Block.DIRT));
											}
											blocks.add(new Block(sx,  startIndex + (int) noiseData[(chunkIndexX*chunkSize) + sx][ (chunkIndexZ*chunkSize) + sz], sz, Block.GRASS));
											
											int treechance = r.nextInt(4200);
											
											if (treechance == 1) {
												Tree.Create(blocks, new Vector3f(sx, startIndex + (int) noiseData[(chunkIndexX*chunkSize) + sx][ (chunkIndexZ*chunkSize) + sz] + 1, sz));
											}
											
										}
										index = startIndex;
								}
							}
							
							Chunk finalChunk = new Chunk(blocks, new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
							ChunkMesh cMesh = new ChunkMesh(finalChunk);
							
							chunks.add(cMesh);
							usedPos.add(new Vector3f(x * chunkSize, 0 * chunkHeight, z * chunkSize));
							blocks.clear();
						}
						numZ++;
					}
					chunkIndexZ++;
				}
				numX++;
				numZ = 0;
			}
			chunkIndexX++;
		}
	}

	public static void generateBlock(List<Chunk> chunks, TexturedModel texModel, Vector3f position) {
		List<Entity> blocks = new ArrayList<Entity>();
		if(!usedPos.contains(position)) {
			blocks.add(new Entity(texModel, position, 0, 0, 0, 1));
			setEntityCount(getEntityCount() + 1);
		}
		//chunks.add(new Chunk(blocks, position));
		usedPos.add(position);
	}
	
	public static int getTotalVertexCount() {
		return totalVertexCount;
	}


	public static void setTotalVertexCount(int newVal) {
		totalVertexCount = newVal;
	}

	public static int getEntityCount() {
		return entityCount;
	}

	public static void setEntityCount(int entityCount) {
		TerrainGenerator.entityCount = entityCount;
	}
	
	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}