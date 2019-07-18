package scope.lwjgl.forerunner.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	public static List<LoadedFile> files = new ArrayList<LoadedFile>();
	public static String fS = File.separator;
	public static String lS = System.getProperty("line.separator");

	public static boolean createFile(String name) {
		boolean success = true;
		String baseContent = "#" + name;
		try {
			FileOutputStream out = new FileOutputStream(name);
			byte[] content = baseContent.getBytes();
			out.write(content);
			out.flush();
			out.close();
			if (success) {
				System.out.println("File " + name + " successfully created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	public static void loadFile(String name, String location) {
		List<String> lineContent = new ArrayList<String>();
		// Load the content of the given file into lineContent
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(location)));
			while ((line = in.readLine()) != null) {
				lineContent.add(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileHandler fh = new FileHandler();
		files.add(fh.new LoadedFile(name, lineContent));
	}

	public static void write(String file, String text) {
		try {
			FileOutputStream out = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(out);
			writer.append(text);
			writer.flush();
			writer.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void write(String file, List<String> lines) {
		try {
			FileOutputStream out = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(out);
			for (int i = 0; i < lines.size(); i++) {
				writer.append(lines.get(i));
				writer.append(lS);
			}
			writer.flush();
			writer.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printData(LoadedFile lf) {
		for (int i = 0; i < lf.content.size(); i++) {
			System.out.println("Contents: " + lf.name + ":line:" + i + ":" + lf.content.get(i));
		}
	}

	public static String getLine(int i, int line) {
		return files.get(i).content.get(line);
	}

	public static String getLine(String name, int line) {
		String content = "";
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).name.equals(name)) {
				content = files.get(i).content.get(line);
				return content;
			}
		}
		return "No file found with name: " + name;
	}

	public static int getId(String name) {
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).name.equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean exists(String name) {
		File file = new File(name);
		return file.exists();
	}

	public class LoadedFile {

		public String name;
		public List<String> content;

		public LoadedFile(String name, List<String> content) {
			this.name = name;
			this.content = content;
		}

		public String getLine(int i) {
			return content.get(i);
		}

		public void addLine(String line) {
			content.add(line);
		}

		public void setLine(int i, String line) {
			content.set(i, line);
		}

		public void removeLine(int i) {
			content.remove(i);
		}

		public void clear() {
			content.clear();
		}

	}

}
