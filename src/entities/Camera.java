package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

public class Camera extends Entity{

    private static final float MOVE_SPEED = 150;
    private static final float ROTATION_SPEED = 30;
    private float currentSpeed = 0;
    private float currentRotationSpeed = 0;
    private float pitch = 30;
    private float yaw = 0;
    private float zoom = 0;


    public Camera(Vector3f position) {
        super(position);
    }

    public void move() {
        checkInputs();
        calculateZoom();
        calculatePitch();
        calculateRotation();

        super.increaseRotation(0, currentRotationSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        currentRotationSpeed = 0;

        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);

        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        zoom = 0;

        yaw = -super.getRotY();
    }

    public Vector3f getPosition() {
        return super.position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
        float theta = super.getRotY() + yaw;
        float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
        super.increasePosition(offsetX,verticalDistance, offsetZ);
    }

    private void checkInputs() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = -MOVE_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = MOVE_SPEED;
        } else {
            this.currentSpeed = 0;
        }
    }

    private float calculateHorizontalDistance() {
        return (float) (zoom * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance() {
        return (float) (zoom * Math.sin(Math.toRadians(pitch)));
    }

    public void calculateZoom() {
        zoom -= Mouse.getDWheel() * 0.2f;
    }

    public void calculatePitch() {
        if (Mouse.isButtonDown(1)) {
            pitch -= Mouse.getDY() * 0.1f;
        }
    }

    public void calculateRotation() {
        if (Mouse.isButtonDown(1)) {
            currentRotationSpeed = (Mouse.getDX() * ROTATION_SPEED);
        }
    }
}
