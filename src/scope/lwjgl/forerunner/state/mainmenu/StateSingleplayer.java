package scope.lwjgl.forerunner.state.mainmenu;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.gui.ClickerText;
import scope.lwjgl.forerunner.state.State;
import scope.lwjgl.forerunner.state.States;

public class StateSingleplayer extends State {

	public ClickerText ctNewGame = new ClickerText("New Game",
			(Main.width - Fonts.fontDefault.getWidthByString("New Game", 3)) / 2, 196, 3,
			new ColorRGB(1f, 1f, 1f, 0.5f), new ColorRGB(1f, 0.2f, 0.2f, 0.85f), new Runnable() {
				public void run() {
					States.stateGame.enter();
					States.stateMainMenu.stateSingleplayer.leave();
					Main.startGame();
				}
			}, false);
	public ClickerText ctContinue = new ClickerText("Continue",
			(Main.width - Fonts.fontDefault.getWidthByString("Continue", 3)) / 2, 236, 3,
			new ColorRGB(1f, 1f, 1f, 0.5f), new ColorRGB(1f, 0.2f, 0.2f, 0.85f), new Runnable() {
				public void run() {

				}
			}, false);
	public ClickerText ctZombieSurvival = new ClickerText("Zombie Survival",
			(Main.width - Fonts.fontDefault.getWidthByString("Zombie Survival", 3)) / 2, 276, 3,
			new ColorRGB(1f, 1f, 1f, 0.5f), new ColorRGB(1f, 0.2f, 0.2f, 0.85f), new Runnable() {
				public void run() {

				}
			}, true);

	public StateSingleplayer(boolean viewable) {
		super(viewable);
	}

	public void update() {
		ctNewGame.update();
		ctContinue.update();
		ctZombieSurvival.update();
	}

	public void render() {
		ctNewGame.render();
		ctContinue.render();
		ctZombieSurvival.render();
		Fonts.fontDefault.drawString("Coming in v3.0",
				(Main.width - Fonts.fontDefault.getWidthByString("Coming in v3.0", 1)) / 2,
				280 + Fonts.fontDefault.colH * 3, 1);
	}

}
