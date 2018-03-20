package scope.lwjgl.forerunner.state;

public class State {
	
	public boolean viewable, paused;
	
	public State(boolean viewable) {
		this.viewable = viewable;
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	public void enter() {
		viewable = true;
	}
	
	public void leave() {
		viewable = false;
	}
	
	public boolean isOpen() {
		return viewable;
	}
	
}
