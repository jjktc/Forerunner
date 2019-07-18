package scope.lwjgl.forerunner.state.mainmenu;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.game.multiplayer.MPGameModes;
import scope.lwjgl.forerunner.gui.Btn;
import scope.lwjgl.forerunner.gui.Tab;
import scope.lwjgl.forerunner.gui.TabMenu;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.State;

public class StateMultiplayer extends State {

	public Btn play1v1 = new Btn(Sprites.play, Sprites.play_hover, Sprites.play_clicked, "",
			38 + Fonts.fontDefault.getWidthByString("Play IP-Based 1v1 MP Beta 1:", 1), 56, 1, 1, new Runnable() {
				public void run() {

				}
			}, false);

	public TabMenu tabMenu = new TabMenu(0, 0, 1, new Tab[] {
			new Tab(true, "Main", Sprites.mpTHome, Sprites.tab, Sprites.tab_hover, Sprites.tab_clicked, new Runnable() {
				public void run() {// update content

				}
			}, new Runnable() {
				public void run() {// render content

				}
			}), new Tab(false, "Game Info", Sprites.mpTInfo, Sprites.tab, Sprites.tab_hover, Sprites.tab_clicked,
					new Runnable() {
						public void run() {

						}
					}, new Runnable() {
						public void run() {
							// Fonts.fontDefault.drawLines(gameModes, 38, 56, 1);
							Fonts.fontDefault.drawLines(gameModes2, 38, 56, 1);
						}
					}),
			new Tab(false, "Loadouts", Sprites.mpTLoadouts, Sprites.tab, Sprites.tab_hover, Sprites.tab_clicked,
					new Runnable() {
						public void run() {

						}
					}, new Runnable() {
						public void run() {

						}
					}),
			new Tab(false, "Friends", Sprites.mpTFriends, Sprites.tab, Sprites.tab_hover, Sprites.tab_clicked,
					new Runnable() {
						public void run() {

						}
					}, new Runnable() {
						public void run() {

						}
					}),
			new Tab(false, "Play", Sprites.mpTMatch, Sprites.tab, Sprites.tab_hover, Sprites.tab_clicked,
					new Runnable() {
						public void run() {
							play1v1.update();
						}
					}, new Runnable() {
						public void run() {
							Fonts.fontDefault.drawString("Play IP-Based 1v1 MP Beta 1:", 38, 64, 1);
							play1v1.render();
						}
					}) },
			new Runnable() {
				public void run() {// update background

				}
			}, new Runnable() {
				public void run() {// render background
					Sprites.mpTB.render(0, 47);
				}
			}, true);

	public List<String> gameModes = new ArrayList<String>();
	public List<String> gameModes2 = new ArrayList<String>();

	public StateMultiplayer(boolean viewable) {
		super(viewable);
		for (int i = 0; i < MPGameModes.gameModes.length; i++) {
			gameModes.add(">>>" + MPGameModes.gameModes[i].name.replaceAll("&PER", "."));
			gameModes2.add(MPGameModes.gameModes[i].name);
			String desc = MPGameModes.gameModes[i].desc;
			String[] sen = desc.split("\\.");
			for (int i2 = 0; i2 < sen.length; i2++) {
				gameModes.add("     " + sen[i2].replaceAll("&PER", "."));
			}
		}
	}

	public void update() {
		tabMenu.update();
	}

	public void render() {
		Sprites.hex.render(0, 0, 1, 1, 1, 1, 0.5f);
		Sprites.bg_text.render(0, 0, Main.width, Main.height, 1, 1, 1, 0.65f);
		tabMenu.render();
	}

}
