package scope.lwjgl.forerunner.state.mainmenu;

import scope.lwjgl.forerunner.game.settings.Options;
import scope.lwjgl.forerunner.gui.CheckBox;
import scope.lwjgl.forerunner.input.Gamepad;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;

public class StateSettings extends State {

	public CheckBox joystick = new CheckBox(8, 150, false, "Use Joystick", Sprites.checkbox, Sprites.checkbox_hover,
			Sprites.check, 1, new Runnable() {
				public void run() {
					Options.useJoystick = !Options.useJoystick;
					if (Options.useJoystick) {
						if (!Gamepad.exists) {
							Options.useJoystick = false;
							joystick.selected = false;
						}
					}
				}
			});
	public CheckBox graphicContent = new CheckBox(8, 168, true, "Graphic Content", Sprites.checkbox,
			Sprites.checkbox_hover, Sprites.check, 1, new Runnable() {
				public void run() {
					Options.graphicContent = !Options.graphicContent;
				}
			});

	public StateSettings(boolean viewable) {
		super(viewable);
	}

	public void update() {
		joystick.update();
		graphicContent.update();
	}

	public void render() {
		joystick.render();
		graphicContent.render();
	}

}
