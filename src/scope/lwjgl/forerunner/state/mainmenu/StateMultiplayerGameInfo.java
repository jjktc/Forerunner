package scope.lwjgl.forerunner.state.mainmenu;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.game.multiplayer.MPGameModes;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.gui.Btn;
import scope.lwjgl.forerunner.gui.ClickerText;
import scope.lwjgl.forerunner.gui.ScrollBar;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;

public class StateMultiplayerGameInfo extends State {
	
	public ScrollBar scrollbar = new ScrollBar(Main.width - Sprites.scrollbar_bar.width, 0, Main.width - Sprites.scrollbar_bar.width, 0, Sprites.scrollbar_bar.width, Main.height, 2, Sprites.scrollbar_btn, Sprites.scrollbar_btn_hover, Sprites.scrollbar_btn_clicked, Sprites.scrollbar_bar, false);
	public Btn help = new Btn(Sprites.btn_help, Sprites.btn_help_hover, Sprites.btn_help_clicked, "", Fonts.fontDefault.getWidthByString("Multiplayer", 2), 0, 1, 1, new Runnable() {
		public void run() {
			helpOpen = true;
		}
	}, false);
	public Btn up = new Btn(Sprites.btn_up, Sprites.btn_up_hover, Sprites.btn_up_clicked, "", Main.width / 5 * 4 - 4, (Main.height - Sprites.btn_up.height * 3) / 2 - Sprites.btn_up.height * 3 - 4, 3, 1, new Runnable() {
		public void run() {
			if(helpCurrent < MPGameModes.gameModes.length - 1) helpCurrent++;
		}
	}, false);
	public Btn down = new Btn(Sprites.btn_down, Sprites.btn_down_hover, Sprites.btn_down_clicked, "", Main.width / 5 * 4 - 4, (Main.height - Sprites.btn_up.height * 3) / 2  + 4, 3, 1, new Runnable() {
		public void run() {
			if(helpCurrent > 0) helpCurrent--;
		}
	}, false);
	public ClickerText closeHelp = new ClickerText("Close", (Main.width - Fonts.fontDefault.getWidthByString("Close", 2)) / 2, Main.height - Fonts.fontDefault.colH * 2 - 32, 2, new ColorRGB(1, 1, 1, 0.3f), new ColorRGB(0.6f, 0.6f, 1f, 0.3f), new Runnable() {
		public void run() {
			helpOpen = false;
		}
	}, false);
	
	public List<String> gameModes = new ArrayList<String>();
	public List<List<String>> gmHelp = new ArrayList<List<String>>();
	public boolean helpOpen = false;
	public int helpCurrent = 0;

	public StateMultiplayerGameInfo(boolean viewable) {
		super(viewable);
		for(int i = 0; i < MPGameModes.gameModes.length; i++) {
			gameModes.add(">>>" + MPGameModes.gameModes[i].name.replaceAll("&PER", "."));
			String desc = MPGameModes.gameModes[i].desc;
			String[] sen = desc.split("\\.");
			for(int i2 = 0; i2 < sen.length; i2++) {
				gameModes.add("     " + sen[i2].replaceAll("&PER", "."));
			}
			String descHelp = MPGameModes.gameModes[i].help;
			String[] helpSen = descHelp.split("\\.");
			List<String> helpSen2 = new ArrayList<String>();
			for(int i3 = 0; i3 < helpSen.length; i3++) {
				helpSen2.add(helpSen[i3].replaceAll("&PER", "."));
			}
			gmHelp.add(helpSen2);
		}
	}
	
	public void update() {
		if(!helpOpen) {
			scrollbar.update();
			help.update();
		} else {
			up.update();
			down.update();
			closeHelp.update();
		}
	}
	
	public void render() {
		Sprites.bg_text.render(Main.width / 4 * 3, 0, Main.width / 4 * 3, Main.height, 1, 1, 1, 0.75f);
		Sprites.bg_text.render(0, Main.height - 48, Main.width / 4 * 3, 48, 1, 1, 1, 0.75f);
		scrollbar.render();
		help.render();
		Fonts.fontDefault.drawString("Game Modes:", 0, 26, 1f);
		Fonts.fontDefault.drawLines(gameModes, 0, 48, 1);
		if(helpOpen) {
			Sprites.bg_text.render(0, 0, Main.width, Main.height, 1, 1, 1, 0.65f);
			Sprites.grey_section.render((Main.width - Sprites.grey_section.width) / 2, (Main.height - Sprites.grey_section.height) / 2);
			up.render();
			down.render();
			Sprites.mplayer_modes[helpCurrent].render(Main.width / 4 - 90, ((Main.height - Sprites.grey_section.height) / 2) + 9);
			Fonts.fontDefault.drawLines(gmHelp.get(helpCurrent), (Main.width - Sprites.grey_section.width) / 2 + 12, ((Main.height - Sprites.grey_section.height) / 2) + 12 + Sprites.mplayer_modes[0].height, 1);
			closeHelp.render();
		}
	}

}
