package scope.lwjgl.forerunner;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.openal.SoundStore;

import scope.lwjgl.forerunner.graphics.BriefGraphics;
import scope.lwjgl.forerunner.graphics.GUI_Icons;
import scope.lwjgl.forerunner.graphics.Graphics;
import scope.lwjgl.forerunner.graphics.LauncherGraphics;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.processing.Cleaner;
import scope.lwjgl.forerunner.processing.Loader;
import scope.lwjgl.forerunner.sound.Sound;
import scope.lwjgl.forerunner.sound.Sounds;

public class Main {
	
	public static int width = 800;
	public static int height = 480;
	public static String title = "Forerunner", version = "";
	public static boolean running = false;
	public static boolean runningL = false;
	public static boolean runningB = true;
	public static float mouseSpeed = 2.0f;
	
	public Main() {
		try {
			Time.lastFPS = Time.getTime();
			Time.time2 = Time.getTime();
			InputHandler.init();
			Graphics.init();
			Loader.init();
			Loader.load();
			Mouse.setGrabbed(false);
		} catch(Exception e) {
			System.err.println("Failed to initialize Display!!! Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		init(0);
		
		Sounds.first_flight.playE(1f, 0.5f);
		
		while(runningB) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			updateB();
			
			SoundStore.get().poll(0);
			
			renderB();
			
			Time.updateFPS();
			
			Display.sync(60);
			Display.update();
			
			if(Display.isCloseRequested()) {
				kill();
			}
		}
		
		//Sounds.first_flight.playE(0.6f, 0.5f);
		
		while(runningL) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			updateL();
			
			SoundStore.get().poll(0);
			
			renderL();
			
			Time.updateFPS();
			
			Display.sync(60);
			Display.update();
			
			if(Display.isCloseRequested()) {
				kill();
			}
		}
		
		Sound.stopAll();
		init(1);
		Mouse.setGrabbed(true);
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			update();
			
			SoundStore.get().poll(0);
			
			render();
			
			Time.updateFPS();
			
			Display.sync(60);
			Display.update();
			
			if(Display.isCloseRequested()) {
				kill();
			}
		}
		
		Sound.stopAll();
		
		Cleaner.clean();
		
		Mouse.destroy();
		Keyboard.destroy();
		AL.destroy();
		Display.destroy();
		
		System.exit(0);
	}
	
	public static void init(int state) {
		if(state == 0) {
			Graphics.camera.initOrtho();
		} else if(state == 1) {
			Graphics.camera.initPerspective();
		}
	}
	
	public static void alwaysUpdate() {
		Time.update();
		InputHandler.update();
	}
	
	public static void update() {
		Time.time1 = Time.getTime();
		alwaysUpdate();
		Graphics.update();
		Time.time2 = Time.time1;
	}
	
	public static void render() {
		Graphics.render();
	}
	
	public static void updateL() {
		Time.time1 = Time.getTime();
		alwaysUpdate();
		LauncherGraphics.update();
		Time.time2 = Time.time1;
	}
	
	public static void renderL() {
		LauncherGraphics.render();
	}
	
	public static void updateB() {
		Time.time1 = Time.getTime();
		alwaysUpdate();
		BriefGraphics.update();
		Time.time2 = Time.time1;
	}
	
	public static void renderB() {
		BriefGraphics.render();
	}
	
	public static void kill() {
		running = false;
		runningL = false;
		runningB = false;
	}
	
	public static void startGame() {
		running = true;
		runningL = false;
		runningB = false;
	}
	
	public static void start() {
		try {
			new Main();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
		} catch(Exception e) {
			System.err.println("Failed to initialize Display!!! Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		start();
	}

}
