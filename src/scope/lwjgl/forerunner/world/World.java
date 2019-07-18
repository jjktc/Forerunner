package scope.lwjgl.forerunner.world;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import scope.lwjgl.forerunner.game.entities.MobPlayer;
import scope.lwjgl.forerunner.game.entity.EntityMob;

public class World {

	public static List<EntityMob> mobs = new ArrayList<EntityMob>();
	public static MobPlayer player = new MobPlayer();
	public static float gravity = 9.8f;

	public static void init() {
		player.init();
	}

	public static void update() {

	}

	public static void render() {
		GL11.glColor4f(1, 1, 1, 1);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex3f(-10, -2, -10);
		glTexCoord2f(0, 10 * 10 * 0.2f);
		glVertex3f(-10, -2, 10);
		glTexCoord2f(10 * 10 * 0.2f, 10 * 10 * 0.2f);
		glVertex3f(10, -2, 10);
		glTexCoord2f(10 * 10 * 0.2f, 0);
		glVertex3f(10, -2, -10);
		glEnd();
	}

}
