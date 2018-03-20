package scope.lwjgl.forerunner.input;

public class MouseButton {
	
	public int type = 0;
	public boolean clicked = false;
	public boolean active = false;
	
	public void update() {
		clicked = false;
		if(type == 0 && active) {
			type = 1;
		}
		if(type == 1 && !active) {
			active = false;
			type = 2;
		} else if(type == 1 && active) {
		}
		if(type == 2) {
			clicked = true;
			active = false;
			type = 0;
		}
	}

}
