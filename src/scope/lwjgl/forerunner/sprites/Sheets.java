package scope.lwjgl.forerunner.sprites;

import java.util.ArrayList;
import java.util.List;

public class Sheets {

	public static Spritesheet fontDefault = new Spritesheet("resources/fonts/font.png", false);

	public static List<Spritesheet> sheets = new ArrayList<Spritesheet>();

	public static boolean contains(String location) {
		for (int i = 0; i < sheets.size(); i++) {
			if (sheets.get(i).location.equals(location)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(Spritesheet sheet) {
		for (int i = 0; i < sheets.size(); i++) {
			if (sheets.get(i).location.equals(sheet.location)) {
				return true;
			}
		}
		return false;
	}

	public static int getId(String location) {
		for (int i = 0; i < sheets.size(); i++) {
			if (sheets.get(i).location.equals(location)) {
				return i;
			}
		}
		return -1;
	}

	public static int getId(Spritesheet sheet) {
		for (int i = 0; i < sheets.size(); i++) {
			if (sheets.get(i).location.equals(sheet.location)) {
				return i;
			}
		}
		return -1;
	}

}
