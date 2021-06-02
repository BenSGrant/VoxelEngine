package RenderEngine;

import static logging.WriteToTXT.writeToFile;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import VoxelEngine.MainGameLoop;
import terrain.TerrainGenerator;

public class DisplayManager {

	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	private static final int FPS_CAP = 500;

	private static long lastFrameTime;
	public static float delta;
	public static int fps;

	private static int totalFPS;
	private static int FPScount;

	private static float totalDelta;
	private static int deltaCount;
	
	private static boolean polygonMode = false;

	public static void createDisplay() {

		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat().withDepthBits(24), attribs); // put 8,8,0,8 in new PixelFOrmat for basic
																			// anti-aliasing
			Display.setTitle("GaryTheBlobfish::VoxelEngine v0.7");
			// Display.setFullscreen(true);//comment out display mode line for this line to
			// work
			Display.setResizable(true);
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);

	}

	public static void updateDisplay() {

		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		setDelta((currentFrameTime - lastFrameTime) / 1000f);
		lastFrameTime = currentFrameTime;
		fps = (int) (1 / delta);

		if (fps != 2147483647) {
			totalFPS += fps;
			FPScount++;

			deltaCount++;
			if(deltaCount != 1) {
				totalDelta += delta;
			}
		}
		
		// TVC is total vertex count, not necessarily rendered															E is entity count
		System.out.println(fps + " FPS    Delta: " + delta + "    TVC: " + TerrainGenerator.getTotalVertexCount() + "    E: " + TerrainGenerator.getEntityCount());

		GL11.glCullFace(GL11.GL_BACK);
		GL11.glCullFace(GL11.GL_LEFT);
		GL11.glCullFace(GL11.GL_RIGHT);

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {

//				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {// if esc is pressed, game closes, should be changed to pause menu later on
//					closeDisplay();
//				}
//				
				if (Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) { // If e is pressed then mouse is
																				// ungrabbed, mouse can move around
																				// without moving camera later on in
																				// game development
					Mouse.setGrabbed(false);
				} else if (Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) { // If esc is pressed then mouse is
																					// ungrabbed, mouse can move around
																					// without moving camera later on in
																					// game development
					Mouse.setGrabbed(false);
				} else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_P) && polygonMode == false) {
					GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
					polygonMode = true;
				} else if (Keyboard.isKeyDown(Keyboard.KEY_P) && polygonMode == true) {
					GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
					polygonMode = false;
				}

			}
		}

	}

	public static void closeDisplay() {
		MainGameLoop.loader1.cleanUp(); // makes vertices not stay on screen forever
		System.out.println("Cleaned up Loader...");
		EntityRenderer.shader.cleanUp();
		MasterRenderer.shader.cleanUp();
		System.out.println("Cleaned up StaticShader...");
		Display.destroy();
		writeToFile("Average FPS this game session: " + totalFPS / FPScount);
		writeToFile("Average delta time (time taken to render) this game session: " + totalDelta / deltaCount);
		writeToFile("LOGGING FINISHED");
		System.exit(0);

	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	private static void setDelta(float newVal) {
		delta = newVal;
	}

	public static float getDelta() {
		return delta;
	}

}
