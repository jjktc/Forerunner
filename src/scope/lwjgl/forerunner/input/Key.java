package scope.lwjgl.forerunner.input;

import scope.lwjgl.forerunner.Time;

public class Key {

	public int type = 0;
	public boolean clicked = false;
	public boolean active = false;
	public int holdTime = 0;

	public void update() {
		clicked = false;
		if (type == 0 && active) {
			type = 1;
		}
		if (type == 1 && !active) {
			active = false;
			type = 2;
		} else if (type == 1 && active) {
			holdTime += Time.anim;
		}
		if (type == 2) {
			if (holdTime < 1500) {
				clicked = true;
			}
			active = false;
			holdTime = 0;
			type = 0;
		}
	}

}