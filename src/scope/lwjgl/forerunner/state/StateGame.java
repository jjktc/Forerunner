package scope.lwjgl.forerunner.state;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import scope.lwjgl.forerunner.graphics.Graphics;
import scope.lwjgl.forerunner.gui.Menu;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.world.World;

public class StateGame extends State {
	
	public boolean paused = false;

	public StateGame(boolean viewable) {
		super(viewable);
	}
	
	public void update() {
		World.player.update();
		if(InputHandler.clicked(InputHandler.KEY_ESCAPE)) {
			Menu.changeVis(!Menu.viewable);
		}
		if(Menu.viewable) {
			Menu.update();
		}
		Graphics.camera.update();
	}
	
	public void render() {
		Graphics.setup3d();
		render3d();
		Graphics.setup2d();
		render2d();
	}
	
	public void render2d() {
		World.player.renderHud();
		if(Menu.viewable) {
			Menu.render();
		}
	}
	
	public void render3d() {
		glPushMatrix();
		Graphics.camera.apply();
		World.render();
		glPopMatrix();
		glPushMatrix();
		World.player.render();
		glPopMatrix();
	}
	
}
