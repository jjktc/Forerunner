package scope.lwjgl.forerunner.processing;

import java.util.ArrayList;
import java.util.List;

public class Query {

	public List<QueryObject> query = new ArrayList<QueryObject>();

	public void update() {
		if (query.get(0).finished) {
			for (int i = 1; i < query.size(); i++) {
				query.set(i - 1, query.get(i));
			}
		}
		query.get(0).method.run();
	}

	public void add(Runnable method) {
		query.add(new QueryObject(method));
	}

}
