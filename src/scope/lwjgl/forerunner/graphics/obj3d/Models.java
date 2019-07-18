package scope.lwjgl.forerunner.graphics.obj3d;

import java.util.ArrayList;
import java.util.List;

public class Models {

	public static int MODEL_STANDARDASSAULTRIFLE = 0;
	public static int MODEL_STANDARDASSAULTRIFLECLIP = 1;
	public static int MODEL_STANDARDPISTOL = 2;
	public static int MODEL_STANDARDPISTOLCLIP = 3;

	public static List<OBJModel> models = new ArrayList<OBJModel>();

	public static void init() {
		models.add(new OBJModel("/resources/models/model_standardassaultrifle.obj", false));
		models.add(new OBJModel("/resources/models/model_standardassaultrifle_clip.obj", false));
		models.add(new OBJModel("/resources/models/model_standardpistol.obj", false));
		models.add(new OBJModel("/resources/models/model_standardpistol_clip.obj", false));
		for (int i = 0; i < models.size(); i++) {
			models.get(i).init();
		}
	}

	public static void clean() {
		for (int i = 0; i < models.size(); i++) {
			models.get(i).clean();
		}
	}

}
