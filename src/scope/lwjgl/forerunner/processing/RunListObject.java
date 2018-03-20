package scope.lwjgl.forerunner.processing;

public class RunListObject {
	
	public Runnable method;
	public boolean finished = false;
	
	public RunListObject(Runnable method) {
		this.method = method;
	}

}
