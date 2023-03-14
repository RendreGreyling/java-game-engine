package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import javax.swing.*;

public class Camera {

    private Vector3f position = new Vector3f(0, 200, 0);
    private float pitch = 30;
    private float yaw = 0;
    private float roll = 0;
    private float zoom = 0;

    public Camera() {

    }

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= 0.6f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += 0.6f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += 0.6f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= 0.6f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            position.y += 0.6f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            position.y -= 0.6f;
        }
        calculatePitchAndYaw();
        
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void calculateZoom() {
        float zoomLevel = Mouse.getDWheel() * 0.1f;
        zoom -= zoomLevel;
    }

    public void calculatePitchAndYaw() {
        if (Mouse.isButtonDown(1)) {
            float pitchLevel = Mouse.getDY() * 0.1f;
            float yawLevel = Mouse.getDX() * 0.1f;
            pitch -= pitchLevel;
            yaw -= yawLevel;
        }
    }
}
