package scope.lwjgl.forerunner.processing;

import java.util.ArrayList;
import java.util.List;

public class Property {

	public PropertyType type;
	public String sep;
	public FileHandler.LoadedFile lf;
	public List<String> content = new ArrayList<String>();

	public Property(FileHandler.LoadedFile lf, PropertyType type) {
		this.lf = lf;
		this.type = type;
		sep = type.seperator;
		content = lf.content;
	}

	public String getProperty(String key) {
		String line = "No property with the given key, " + key;
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).contains(key + sep) && !content.get(i).startsWith("#")) {
				line = content.get(i);
				line = line.replace(key + sep, "");
			}
		}
		return line;
	}

	public void setProperty(String key, String value) {
		boolean keyExists = false;
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).contains(key + sep)) {
				keyExists = true;
				content.set(i, key + sep + value);
			}
		}
		if (!keyExists) {
			content.add(key + sep + value);
		}
	}

	public void save() {
		FileHandler.write(lf.name + ".txt", content);
	}

}
