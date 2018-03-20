package scope.lwjgl.forerunner.processing;

import java.util.ArrayList;
import java.util.List;

public class RunList {
	
	public List<RunListObject> list = new ArrayList<RunListObject>();
	
	public void runList() {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).finished) {
				list.remove(i);
			} else {
				list.get(i).method.run();
			}
		}
	}
	
	public void add(Runnable method) {
		list.add(new RunListObject(method));
	}
	
}
