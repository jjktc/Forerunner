package scope.lwjgl.forerunner.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.Main;

public class TXTHandler {

	public static void read(String location) {
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(location));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String lookFor(String location, String sequence) {
		if (new File(location).exists()) {
			String line = "";
			try {
				InputStream input = TXTHandler.class.getResourceAsStream("/" + location);
				// BufferedReader in = new BufferedReader(new FileReader(location));
				BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				if (in != null) {
					while ((line = in.readLine()) != null) {
						if (line.contains(sequence)) {
							return line;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "N/A";
	}

	public static void vers() {
		List<String> versions = extContent(FileSystem.home + "/bin/version.txt");
		if (versions.size() > 0)
			Main.version = "v" + versions.get(0);
	}

	public static List<String> content(String location) {
		String line = "";
		List<String> lines = new ArrayList<String>();
		if (TXTHandler.class.getResourceAsStream("/" + location) != null) {
			try {
				InputStream input = TXTHandler.class.getResourceAsStream("/" + location);
				BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				while ((line = in.readLine()) != null) {
					lines.add(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lines;
		}
		lines.add("N/A");
		return lines;
	}

	public static List<String> extContent(String location) {
		String line = "";
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(location));
			if (br != null) {
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
				return lines;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lines.add("N/A");
		return lines;
	}

}
