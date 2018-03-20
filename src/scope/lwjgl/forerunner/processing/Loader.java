package scope.lwjgl.forerunner.processing;

import scope.lwjgl.forerunner.dlc.DLCLoader;
import scope.lwjgl.forerunner.game.entities.Guns;
import scope.lwjgl.forerunner.game.singleplayer.Credits;
import scope.lwjgl.forerunner.graphics.Graphics;
import scope.lwjgl.forerunner.input.Gamepad;
import scope.lwjgl.forerunner.world.World;

public class Loader {
	
	public static void init() {
		Save.start();
		TXTHandler.vers();
		Guns.init();
		DLCLoader.init();
		World.init();
		Graphics.camera.init();
		Credits.init();
		Gamepad.initiate();
	}
	
	public static void load() {
		
	}

}
