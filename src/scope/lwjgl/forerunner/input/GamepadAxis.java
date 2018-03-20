package scope.lwjgl.forerunner.input;

import scope.lwjgl.forerunner.Time;

public class GamepadAxis {
	
	public int analog, analog2;
	public int type;
	public boolean active, clicked;
	public boolean up, down, right, left;
	public int xPos , yPos;
	public int holdTime = 0;
	
	public GamepadAxis(int analog) {
		this.analog = analog;
		analog2 = analog + 9;
	}
	
	public void update() {
		active = Gamepad.gbtns[analog2];
		clicked = false;
		if(type == 0 && active) {
			type = 1;
		}
		if(type == 1 && !active) {
			active = false;
			type = 2;
		} else if(type == 1 && active) {
			holdTime += Time.anim;
		}
		if(type == 2) {
			if(holdTime < 1500) {
				clicked = true;
			}
			active = false;
			holdTime = 0;
			type = 0;
		}
	}

}
