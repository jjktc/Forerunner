package scope.lwjgl.forerunner.game.singleplayer;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.processing.TXTHandler;

public class Credits {
	
	public static List<String> credits = new ArrayList<String>();
	
	public static void init() {
		credits = TXTHandler.content("credits.txt");
	}
	
	public static void update() {
		
	}
	
	public static void render() {
		
	}

}
