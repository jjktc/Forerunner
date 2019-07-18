package scope.lwjgl.forerunner.state;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.game.settings.Options;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.gui.ClickerText;
import scope.lwjgl.forerunner.input.Gamepad;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.mainmenu.StateAbout;
import scope.lwjgl.forerunner.state.mainmenu.StateHelp;
import scope.lwjgl.forerunner.state.mainmenu.StateMain;
import scope.lwjgl.forerunner.state.mainmenu.StateMultiplayer;
import scope.lwjgl.forerunner.state.mainmenu.StateSettings;
import scope.lwjgl.forerunner.state.mainmenu.StateSingleplayer;

public class StateMainMenu extends State {

	public StateMain stateMain = new StateMain(true);
	public StateSingleplayer stateSingleplayer = new StateSingleplayer(false);
	public StateMultiplayer stateMultiplayer = new StateMultiplayer(false);
	public StateSettings stateSettings = new StateSettings(false);
	public StateAbout stateAbout = new StateAbout(false);
	public StateHelp stateHelp = new StateHelp(false);

	public ClickerText ctBack = new ClickerText("Back", (160 - Fonts.fontDefault.getWidthByString("Back", 2) / 2), 442,
			2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
				public void run() {
					stateMain.enter();
					stateSingleplayer.leave();
					stateMultiplayer.leave();
					stateSettings.leave();
					stateAbout.leave();
					stateHelp.leave();
				}
			}, false);

	public StateMainMenu(boolean viewable) {
		super(viewable);
	}

	public void update() {
		Gamepad.update();
		if (stateMain.viewable) {
			stateMain.update();
		} else if (stateSingleplayer.viewable) {
			stateSingleplayer.update();
			ctBack.update();
		} else if (stateMultiplayer.viewable) {
			stateMultiplayer.update();
			if (Options.useJoystick)
				Gamepad.updateLauncherSelection(1, ctBack);
			ctBack.update();
		} else if (stateSettings.viewable) {
			stateSettings.update();
			if (Options.useJoystick)
				Gamepad.updateLauncherSelection(1, ctBack);
			ctBack.update();
		} else if (stateAbout.viewable) {
			stateAbout.update();
			if (Options.useJoystick)
				Gamepad.updateLauncherSelection(1, ctBack);
			ctBack.update();
		} else if (stateHelp.viewable) {
			stateHelp.update();
			if (Options.useJoystick)
				Gamepad.updateLauncherSelection(1, ctBack);
			ctBack.update();
		}
	}

	public void render() {
		Sprites.title.render((int) ((Main.width - Sprites.title.width * 2)) / 2, 4, 2f);
		Fonts.fontDefault.drawString(Main.version,
				(Main.width - Sprites.title.width * 2) / 2 + Sprites.title.width * 2 + 4, Sprites.title.height, 2);
		Fonts.fontDefault.drawString("SCOPE Studios�",
				(Main.width - Fonts.fontDefault.getWidthByString("SCOPE Studios�", 1)) / 2,
				8 + Sprites.title.height * 2, 1);
		if (stateMain.viewable) {
			stateMain.render();
		} else if (stateSingleplayer.viewable) {
			stateSingleplayer.render();
			ctBack.render();
		} else if (stateMultiplayer.viewable) {
			stateMultiplayer.render();
			if (Options.useJoystick)
				Gamepad.renderLauncherSelection(1, ctBack);
			ctBack.render();
		} else if (stateSettings.viewable) {
			stateSettings.render();
			if (Options.useJoystick)
				Gamepad.renderLauncherSelection(1, ctBack);
			ctBack.render();
		} else if (stateAbout.viewable) {
			stateAbout.render();
			if (Options.useJoystick)
				Gamepad.renderLauncherSelection(1, ctBack);
			ctBack.render();
		} else if (stateHelp.viewable) {
			stateHelp.render();
			if (Options.useJoystick)
				Gamepad.renderLauncherSelection(1, ctBack);
			ctBack.render();
		}
	}

}
