package scope.lwjgl.forerunner.state.mainmenu;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;

public class StateAbout extends State {
	
	public List<String> lines = new ArrayList<String>();

	public StateAbout(boolean viewable) {
		super(viewable);
		lines.add("The population of the Earth was growing. Too fast to be sustained. This");
		lines.add("reality forced mankind to join together and abandon Earth to the polution,");
		lines.add("toxins, and other hazards. In 2060, ships prepared to leave the atmosphere in");
		lines.add("pursuit of a better life. One that would be hard to find, and may force people");
		lines.add("to rethink not living on Earth. Though they might not be the only ones. Will");
		lines.add("people have to deal with polution, toxins, and... aliens?");
		lines.add("You are a Forerunner, a specially trained super soldier, and you will be one");
		lines.add("of the first to find out.");
		lines.add("");
		lines.add("Made by SCOPE Studios™");
		lines.add("Head of programming: sandspro");
		lines.add("Head of music: sandspro");
		lines.add("Head of sound effects: cdt");
		lines.add("Head of art: sandspro and cdt");
	}
	
	public void update() {
		
	}
	
	public void render() {
		Sprites.bg_text.render(0, Main.height / 8 * 3, Main.width, Main.height / 8 * 5, 1, 1, 1, 0.5f);
		Fonts.fontDefault.drawLines(lines, 8, Main.height / 8 * 3 + 4, 1);
	}

}
