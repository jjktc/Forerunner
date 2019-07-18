package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.world.World;

public class HudMap {

	public static void update() {

	}

	public static void render() {
		Sprites.map.render(4, Main.height - 184, 1, World.player.hudR, World.player.hudG, World.player.hudB, 0.75f);
		renderTerrain();
		renderFriendlies();
		renderEnemies();
		renderDirection();
		Sprites.map_player.render(4 + (Sprites.map.width - Sprites.map_player.width) / 2,
				Main.height - 184 + ((Sprites.map.height + 18) - Sprites.map_player.height) / 2, 1, 1, 1, 1, 0.9f);
	}

	public static void renderTerrain() {

	}

	public static void renderFriendlies() {

	}

	public static void renderEnemies() {

	}

	public static void renderDirection() {

	}

}
