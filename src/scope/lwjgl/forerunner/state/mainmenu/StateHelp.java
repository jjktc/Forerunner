package scope.lwjgl.forerunner.state.mainmenu;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;

public class StateHelp extends State {
	
	public List<String> lines = new ArrayList<String>();

	public StateHelp(boolean viewable) {
		super(viewable);
		lines.add("Controls");
		lines.add("   forward:w, left:a, backward:s, right:d, run:r, jump:space, crouch:lshift");
		lines.add("   prone:lcontrol, action:alt, switch weapon:tab, roll:run+crouch");
		lines.add("   dive:run+prone, hit:lclick, use:rclick, turn:move mouse");
	}
	
	public void update() {
		
	}
	
	public void render() {
		Sprites.bg_text.render(0, Main.height / 8 * 3, Main.width, Main.height / 8 * 5, 1, 1, 1, 0.5f);
		Fonts.fontDefault.drawLines(lines, 8, Main.height / 8 * 3 + 4, 1);
	}

}
