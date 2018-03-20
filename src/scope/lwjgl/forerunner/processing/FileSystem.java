package scope.lwjgl.forerunner.processing;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileSystem {
	
	public static boolean newuser = true;
	public static String userOS = "UNKNOWN";
	public static String home = "UNKNOWN";
	public static String unHome = "";
	
	public static void getos() {
		userOS = System.getProperty("os.name");
	}
	
	public static void gethome() {
		if(userOS.equals("UNKNOWN")) {
			getos();
		}
		if(userOS.contains("Windows 7")) {
			home = System.getenv("APPDATA") + "/SCOPE/.Forerunner";
		} else if(userOS.contains("Windows XP")) {
			home = System.getProperty("user.home") + "/SCOPE/.Forerunner";
		} else if(userOS.contains("Mac OS X")) {
			home = System.getProperty("user.home") + "/Library/Application Support/SCOPE/.Forerunner";
		} else if(userOS.contains("Mac OS")) {
			home = System.getProperty("user.home") + "/Library/Application Support/SCOPE/.Forerunner";
		} else if(userOS.contains("Linux")) {
			home = System.getProperty("user.home") + "/SCOPE/.Forerunner";
		} else {
			home = System.getProperty("user.home") + "/SCOPE/.Forerunner";
		}
		unHome = home.replace("SCOPE/.Forerunner", "");
	}
	
	public static void getstatus() {
		File folder = new File(home);
		if(folder.exists() || folder.isDirectory()) {
			newuser = false;
		}
	}
	
	public static void mkdir(String loc) {
		boolean success = (new File(home + loc)).mkdirs();
		if (success) {
			System.out.println("Directories: " + loc + " created");
		} else {
			System.err.println("Directories: " + loc + " not created");
		}
	}
	
	public static void mkfile(String dir, String name) {
		try {
			boolean success = (new File(home + dir + name)).createNewFile();
			if (success) {
				System.out.println("File: " + dir + name + " created");
			} else {
				System.err.println("File: " + dir + name + " not created");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mkdirs() {
		if(newuser) {
			boolean success = (new File(home)).mkdirs();
			if (success) {
				System.out.println("Directories: " + home + " created");
			} else {
				System.err.println("Directories: " + home + " not created");
			}
		}
	}
	
	public static void newDirs(String loc) {
		boolean success = (new File(loc)).mkdirs();
		if (success) {
			System.out.println("Directories: " + home + " created");
		} else {
			System.err.println("Directories: " + home + " not created");
		}
	}
	
	public static void getio() {
		if(newuser) {
			//for(int i = 0; i < ReqFile.amount; i++) {
				//io(ReqFile.from[i], ReqFile.to[i]);
			//}
		}
	}
	
	public static void io(String from, String to) {
		try {
			URL url = new URL("" + from);
			URLConnection urlConnection = url.openConnection();
	
			BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(home + to));
	
			int i;
			while ((i = in.read()) != -1) {
			    out.write(i);
			}
			out.flush();
	
			out.close();
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void io2(String from, String to) {
		try {
			URL url = new URL(from);
			URLConnection urlConnection = url.openConnection();
	
			BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to));
			
			int i;
			while ((i = in.read()) != -1) {
			    out.write(i);
			}
			out.flush();
	
			out.close();
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
