package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	Vector3f position;
	float rotX;
	float rotY;
	float rotZ;
	
	float speed = 0.4f;
	float speedY = speed;
	float mouseSensitivity = 0.1f;
	float moveDirZ = 0f;
	float speedBoost = speed / 2;
	float moveDirX = 0f;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveDirZ =- speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveDirZ = speed;
		}
		
		/*
		 * else if (Keyboard.isKeyDown(Keyboard.KEY_A)) { moveDirX = -speed; } else if
		 * (Keyboard.isKeyDown(Keyboard.KEY_D)) { moveDirX = speed; } */
		else {
			moveDirZ = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_W)){
			moveDirZ =- (speed + speedBoost);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveDirX =- speed;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveDirX = speed;
		} else {
			moveDirZ = 0;
			moveDirX = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speedY;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speedY;
		}
		 
		 
		
		rotX += -Mouse.getDY() * mouseSensitivity;
		rotY += Mouse.getDX() * mouseSensitivity;

		float dx = (float) -(moveDirZ * Math.sin(Math.toRadians(rotY)));
		float dy = (float) (moveDirZ * Math.sin(Math.toRadians(rotX)));
		float dz = (float) (moveDirZ * Math.cos(Math.toRadians(rotY)));
		
		position.x += dx;
		position.y += dy;
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
