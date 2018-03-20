package scope.lwjgl.forerunner.graphics;

import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.States;

public class LauncherGraphics {
	
	public static void update() {
		if(States.stateLogin.viewable) {
			States.stateLogin.update();
		} else if(States.stateMainMenu.viewable) {
			States.stateMainMenu.update();
		}
	}
	
	public static void render() {
		Sprites.launcher_bg.render(0, 0);
		if(States.stateLogin.viewable) {
			States.stateLogin.render();
		} else if(States.stateMainMenu.viewable) {
			States.stateMainMenu.render();
		}
	}

}
