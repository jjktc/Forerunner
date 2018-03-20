package scope.lwjgl.forerunner.graphics;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.game.entities.MobPlayer;
import scope.lwjgl.forerunner.graphics.obj3d.Models;
import scope.lwjgl.forerunner.state.States;

public class Graphics {
	
	public static Camera camera = new Camera(30, Main.width, Main.height, MobPlayer.zNear, MobPlayer.zFar);
	public static LightPoint light = new LightPoint(0, 0, 0, 1f);
	
	public static void init() {
		Models.init();
	}
	
	public static void update() {
		States.stateGame.update();
	}
	
	public static void render() {
		States.stateGame.render();
	}
	
	public static void setup2d() {
		camera.setupOrtho();
	}
	
	public static void setup3d() {
		camera.setupPerspective();
	}
	
}
