package scope.lwjgl.forerunner.demo;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightModel;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.graphics.LightPoint;

public class DemoCamera {
	
	public float x, y, z;
	public int fov, width, height;
	public float zNear, zFar, pitch, yaw, roll, aspectRatio;
	public float maxLookUp = 80, maxLookDown = -80;
	public LightPoint light = new LightPoint(0, 0, 0, 1);
	
	public DemoCamera(int fov, int width, int height, float zNear, float zFar) {
		this.fov = fov;
		this.width = width;
		this.height = height;
		this.zNear = zNear;
		this.zFar = zFar;
		aspectRatio = (float) width / (float) height;
		x = 0;
		y = 0;
		z = 0;
	}
	
	public void reset() {
		glRotatef(pitch, 1, 0, 0);
		glRotatef(yaw, 0, 1, 0);
		glRotatef(roll, 0, 0, 1);
		glTranslatef(-x, -y, -z);
	}
	
	public void apply() {
		glRotatef(pitch, 1, 0, 0);
        glRotatef(yaw, 0, 1, 0);
        glRotatef(roll, 0, 0, 1);
        glTranslatef(-x, -y, -z);
	}
	
	public void update() {
		processKeyboard();
		if(Mouse.isGrabbed()) {
			float mouseDX = DemoInputHandler.DX * 1.5f * 0.16f;
			float mouseDY = -DemoInputHandler.DY * 1.5f * 0.16f;
			if (yaw + mouseDX >= 360) {
			    yaw = yaw + mouseDX - 360;
			} else if (yaw + mouseDX < 0) {
			    yaw = 360 - yaw + mouseDX;
			} else {
			    yaw += mouseDX;
			}
			if (pitch - mouseDY >= maxLookDown && pitch - mouseDY <= maxLookUp) {
			    pitch += -mouseDY;
			} else if (pitch - mouseDY < maxLookDown) {
			    pitch = maxLookDown;
			} else if (pitch - mouseDY > maxLookUp) {
			    pitch = maxLookUp;
			}
		}
	}
	
	public void initOrtho() {
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glViewport(0, 0, width, height);
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void initPerspective() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective((float) fov, aspectRatio, zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_COLOR_MATERIAL);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(light.lightPosition));
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		//glEnable(GL_CULL_FACE);
		//glCullFace(GL_BACK);
	}
	
	public void setupOrtho() {
		//glEnable(GL_DEPTH_TEST);
		//glDisable(GL_CULL_FACE);
		glDisable(GL_LIGHTING);
		glDisable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0, Main.width, Main.height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
	}
	
	public void setupPerspective() {
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		glMatrixMode(GL_MODELVIEW);
		glPopMatrix();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(light.lightPosition));
		//glEnable(GL_CULL_FACE);
		//glDisable(GL_DEPTH_TEST);
		glLoadIdentity();
	}
	
	public void processKeyboard() {
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_W);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_D);
		boolean flyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		boolean flyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		
		if (keyUp && keyRight && !keyLeft && !keyDown) {
			moveFromLook((float) (0.005f * Time.delta), 0, (float) (-0.005f * Time.delta));
		}
		if (keyUp && keyLeft && !keyRight && !keyDown) {
			moveFromLook((float) (-0.005f * Time.delta), 0, (float) (-0.005f * Time.delta));
		}
		if (keyUp && !keyLeft && !keyRight && !keyDown) {
			moveFromLook(0, 0, (float) (-0.005f * Time.delta));
		}
		if (keyDown && keyLeft && !keyRight && !keyUp) {
			moveFromLook((float) (-0.005f * Time.delta), 0, (float) (0.005f * Time.delta));
		}
		if (keyDown && keyRight && !keyLeft && !keyUp) {
			moveFromLook((float) (0.005f * Time.delta), 0, (float) (0.005f * Time.delta));
		}
		if (keyDown && !keyUp && !keyLeft && !keyRight) {
			moveFromLook(0, 0, (float) (0.005f * Time.delta));
		}
		if (keyLeft && !keyRight && !keyUp && !keyDown) {
			moveFromLook((float) (-0.005f * Time.delta), 0, 0);
		}
		if (keyRight && !keyLeft && !keyUp && !keyDown) {
			moveFromLook((float) (0.005f * Time.delta), 0, 0);
		}
		if (flyUp && !flyDown) {
	    	y += 0.005f * Time.delta;
		}
		if (flyDown && !flyUp) {
			y -= 0.005f * Time.delta;
		}
    }

    public void moveFromLook(float dx, float dy, float dz) {
    	float nX = this.x;
    	float nY = this.y;
    	float nZ = this.z;
    	
    	float hypotenuseX = dx;
    	float adjacentX = hypotenuseX * (float) Math.cos(Math.toRadians(yaw - 90));
    	float oppositeX = (float) Math.sin(Math.toRadians(yaw - 90)) * hypotenuseX;
    	nZ += adjacentX;
    	nX -= oppositeX;
    	
    	nY += dy;
    	
    	float hypotenuseZ = dz;
    	float adjacentZ = hypotenuseZ * (float) Math.cos(Math.toRadians(yaw));
    	float oppositeZ = (float) Math.sin(Math.toRadians(yaw)) * hypotenuseZ;
    	nZ += adjacentZ;
    	nX -= oppositeZ;
    	
    	this.x = nX;
    	this.y = nY;
    	this.z = nZ;
    }
    
    public static FloatBuffer asFloatBuffer(float... values) {
    	FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
    	buffer.put(values);
    	buffer.flip();
    	return buffer;
    }

}
