package scope.lwjgl.forerunner.demo;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.graphics.GUI_Icons;
import scope.lwjgl.forerunner.graphics.obj3d.OBJModel;
import scope.lwjgl.forerunner.graphics.render3d.TridemDisplay;
import scope.lwjgl.forerunner.sprites.Sprites;

public class TridemDisplayDemo {
	
	public static int width = 800;
	public static int height = 480;
	public static String title = "Forerunner Demo - TridemDisplay";
	public static boolean running = true;
	public static float mouseSpeed = 2.0f;
	public static DemoCamera camera = new DemoCamera(30, width, height, 0.3f, 75);
	
	public static OBJModel model_computer = new OBJModel("/resources/models/demo_computer.obj", false);
	public static TridemDisplay tdDisplay = new TridemDisplay(0.05f, -0.55f, 0.08f, 0, 0, 0, 2, 2, 512, 512, new Runnable() {
		public void run() {
			
		}
	}, new Runnable() {
		public void run() {
			TridemDisplay.renderSprite(Sprites.bg_text2, tdDisplay, 0, 0, 512, 512, 0, 0, 1, 1);
		}
	});
	
	public TridemDisplayDemo() {
		try {
			init();
			Mouse.setGrabbed(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		camera.initPerspective();
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			update();
			render();
			Time.updateFPS();
			Display.sync(60);
			Display.update();
			if(Display.isCloseRequested()) {
				running = false;
			}
		}
		clean();
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	public static void update() {
		Time.time1 = Time.getTime();
		Time.update();
		DemoInputHandler.update();
		//update demo
		tdDisplay.update();
		Time.time2 = Time.time1;
		camera.update();
	}
	
	public static void render2d() {
		
	}
	
	public static void render3d() {
		//model_computer.render(-1, 1.5f, -6, 0, 0, 0);
		model_computer.render(0, 0, 0, 0, 0, 0);
		tdDisplay.render();
	}
	
	public static void render() {
		camera.light.set(camera.x, camera.y, camera.z, 1);
		camera.setupPerspective();
		camera.apply();
		render3d();
		camera.setupOrtho();
		render2d();
	}
	
	public static void init() {
		model_computer.init();
	}
	
	public static void clean() {
		model_computer.clean();
	}
	
	public static void main(String[] args) {
		try {
			Display.setResizable(false);
			Display.setVSyncEnabled(true);
			Display.setDisplayMode(new DisplayMode(width, height));
			GUI_Icons.start();
			Display.setTitle(title);
			Display.create();
			Mouse.create();
			Keyboard.create();
			Time.lastFPS = Time.getTime();
			Time.time2 = Time.getTime();
			DemoInputHandler.init();
		} catch(Exception e) {
			System.err.println("Failed to initialize Display!!! Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		new TridemDisplayDemo();
	}

}
