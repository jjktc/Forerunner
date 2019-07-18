package scope.lwjgl.forerunner.state.mainmenu;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.game.settings.Options;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.gui.ClickerText;
import scope.lwjgl.forerunner.input.Gamepad;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;
import scope.lwjgl.forerunner.state.States;

public class StateMain extends State {

	public ClickerText ctSingleplayer = new ClickerText("Singleplayer",
			(160 - Fonts.fontDefault.getWidthByString("Singleplayer", 2) / 2), 244, 2, new ColorRGB(1, 1, 1, 0.3f),
			new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					States.stateMainMenu.stateSingleplayer.enter();
					leave();
				}
			}, false);
	public ClickerText ctMultiplayer = new ClickerText("Multiplayer",
			(160 - Fonts.fontDefault.getWidthByString("Multiplayer", 2) / 2), 276, 2, new ColorRGB(1, 1, 1, 0.3f),
			new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					States.stateMainMenu.stateMultiplayer.enter();
					leave();
				}
			}, false);
	public ClickerText ctSettings = new ClickerText("Settings",
			(160 - Fonts.fontDefault.getWidthByString("Settings", 2) / 2), 304, 2, new ColorRGB(1, 1, 1, 0.3f),
			new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					States.stateMainMenu.stateSettings.enter();
					leave();
				}
			}, false);
	public ClickerText ctAbout = new ClickerText("About", (160 - Fonts.fontDefault.getWidthByString("About", 2) / 2),
			336, 2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					States.stateMainMenu.stateAbout.enter();
					leave();
				}
			}, false);
	public ClickerText ctHelp = new ClickerText("Help", (160 - Fonts.fontDefault.getWidthByString("Help", 2) / 2), 368,
			2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					States.stateMainMenu.stateHelp.enter();
					leave();
				}
			}, false);
	public ClickerText ctQuit = new ClickerText("Quit", (160 - Fonts.fontDefault.getWidthByString("Quit", 2) / 2), 400,
			2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					Main.kill();
				}
			}, false);

	public StateMain(boolean viewable) {
		super(viewable);
	}

	public void update() {
		ctSingleplayer.update();
		ctMultiplayer.update();
		ctSettings.update();
		ctAbout.update();
		ctHelp.update();
		ctQuit.update();
		if (Options.useJoystick)
			Gamepad.updateLauncherSelection(0, ctSingleplayer, ctMultiplayer, ctSettings, ctAbout, ctHelp, ctQuit);
	}

	public void render() {
		for (int i = 0; i < Sprites.makers.length; i++) {
			Sprites.makers[i].render(Main.width - Sprites.makers[i].width * (Sprites.makers.length - i),
					Main.height - Sprites.makers[i].height);
		}
		ctSingleplayer.render();
		ctMultiplayer.render();
		ctSettings.render();
		ctAbout.render();
		ctHelp.render();
		ctQuit.render();
		if (Options.useJoystick)
			Gamepad.renderLauncherSelection(0, ctSingleplayer, ctMultiplayer, ctSettings, ctAbout, ctHelp, ctQuit);
	}

}
