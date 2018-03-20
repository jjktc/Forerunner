package scope.lwjgl.forerunner.processing;

public class QueryObject {
	
	public Runnable method;
	public boolean finished = false;
	
	public QueryObject(Runnable method) {
		this.method = method;
	}

}
