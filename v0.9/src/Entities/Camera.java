package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	Vector3f position;
	float rotX;
	float rotY;
	float rotZ;
	
	float speed = 8.0f; // units per second
	float speedY = speed;
	float mouseSensitivity = 0.1f;
	float moveDirZ = 0f;
	float speedBoost = speed / 2; // 50% boost
	float moveDirX = 0f;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	
	public void move(float delt/*ChunkMesh currentChunk*/) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveDirZ = -speed * delt;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveDirZ = speed * delt;
		}
		
		/*
		 * else if (Keyboard.isKeyDown(Keyboard.KEY_A)) { moveDirX = -speed; } else if
		 * (Keyboard.isKeyDown(Keyboard.KEY_D)) { moveDirX = speed; } */
		else {
			moveDirZ = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_W)){
			moveDirZ =- (speed + speedBoost) * delt;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveDirX =- speed * delt;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveDirX = speed * delt;
		} else {
			moveDirZ = 0;
			moveDirX = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speedY * delt;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speedY * delt;
		}
		 
		 
		
		rotX += -Mouse.getDY() * mouseSensitivity;
		rotY += Mouse.getDX() * mouseSensitivity;

		float dx = (float) -(moveDirZ * Math.sin(Math.toRadians(rotY)));
		float dz = (float) (moveDirZ * Math.cos(Math.toRadians(rotY)));
		
		// pos x you cannot go past = -TerrainGenerator.worldsize
		position.x += dx;
		position.z += dz;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRotX() {
		return rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

}
