package scope.lwjgl.forerunner.graphics;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.sprites.Sprites;

public class BriefGraphics {

	public static int anim = 0;

	public static void update() {
		anim += Time.anim;
		if (anim > 7000) {
			Main.runningB = false;
			Main.runningL = true;
		}
	}

	public static void render() {
		if (anim < 4000) {
			Sprites.stars.render(0, 0);
			Fonts.fontDefault.drawString("SCOPE Studios�",
					(Main.width - Fonts.fontDefault.getWidthByString("SCOPE Studios�", 4)) / 2 + 2,
					(Main.height - Fonts.fontDefault.getHeightByString("SCOPE Studios�", 4)) / 8 + 2, 4, 0.75f, 0.75f,
					0.75f, 1);
			Fonts.fontDefault.drawString("SCOPE Studios�",
					(Main.width - Fonts.fontDefault.getWidthByString("SCOPE Studios�", 4)) / 2,
					(Main.height - Fonts.fontDefault.getHeightByString("SCOPE Studios�", 4)) / 8, 4);
			Sprites.scopestudios.render((Main.width - Sprites.scopestudios.width) / 2,
					(Main.height - Sprites.scopestudios.height) / 2);
		} else if (anim < 5500) {
			Sprites.hex.render(0, 0, 1, 1, 1, 1, 0.2f);
			Fonts.fontDefault.drawString("Powered by ForeEngine",
					(Main.width - Fonts.fontDefault.getWidthByString("Powered by ForeEngine", 3)) / 2,
					(Main.height - Fonts.fontDefault.colH * 3) / 4 * 3, 3);
			Sprites.foreengine.render((Main.width - Sprites.foreengine.width * 2) / 2,
					(Main.height - Sprites.foreengine.height * 2) / 4, 2);
		} else {
			Sprites.hex.render(0, 0, 1, 1, 1, 1, 0.2f);
			Fonts.fontDefault.drawString("Produced with",
					(Main.width - Fonts.fontDefault.getWidthByString("Produced with", 3)) / 2,
					(Main.height - Fonts.fontDefault.colH * 3) / 4 * 3, 3);
			Fonts.fontDefault.drawString("Animation TestBed (ATB)",
					(Main.width - Fonts.fontDefault.getWidthByString("Animation TestBed (ATB)", 3)) / 2,
					(int) ((Main.height - Fonts.fontDefault.colH * 3) / 4 * 3.5f), 3);
			Sprites.atb.render((Main.width - Sprites.atb.width * 2) / 2, (Main.height - Sprites.atb.height * 2) / 4, 2);
		}
	}

}
