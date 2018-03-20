package scope.lwjgl.forerunner.graphics;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Time;

public class ClipScene {
	
	public int frame = 0;
	@SuppressWarnings("rawtypes") public List frames = new ArrayList();
	public int fps = 0;
	public int anim = 0;
	public boolean playing = false;
	
	public ClipScene() {
		
	}
	
	public void update() {
		if(playing) {
			anim += Time.anim;
			if(anim >= 1000 / fps) {
				frame++;
				anim -= (1000 / fps);
			}
			if(frame > frames.size()) {
				playing = false;
			}
		}
	}
	
	public void render() {
		if(playing) {
			
		}
	}

}
