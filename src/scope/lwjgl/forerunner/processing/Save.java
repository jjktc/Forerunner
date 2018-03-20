package scope.lwjgl.forerunner.processing;

import java.io.File;

public class Save {
	
	public static void start() {
		FileSystem.gethome();
		FileSystem.getstatus();
		if(FileSystem.newuser) {
			if(!new File(FileSystem.home.replace(".Forerunner", "")).exists()) {
				FileSystem.newDirs(FileSystem.home.replace(".Forerunner", ""));
			}
			FileSystem.mkdirs();
			FileSystem.newDirs(FileSystem.home + "/config");
			FileSystem.newDirs(FileSystem.home + "/bin");
			FileSystem.newDirs(FileSystem.home + "/resources");
			FileSystem.newDirs(FileSystem.home + "/sounds");
			FileSystem.newDirs(FileSystem.home + "/profiles");
			FileSystem.newDirs(FileSystem.home + "/config/dlc");
			createFile(new File(FileSystem.home + "/config/options.properties"));
			createFile(new File(FileSystem.home + "/profiles/log.txt"));
			createFile(new File(FileSystem.home + "/bin/version.txt"));
			createFile(new File(FileSystem.home + "/bin/credits.txt"));
			createFile(new File(FileSystem.home + "/bin/critics.txt"));
		}
		
	}
	
	public static void save() {
		
	}
	
	public static void createFile(File file) {
		if(FileSystem.home.equals("UNKNOWN") || FileSystem.home.isEmpty()) {
			FileSystem.gethome();
		}
		try {
			boolean success = file.createNewFile();
			if (success) {
				System.out.println("File: " + file.toString() + " created");
			} else {
				System.err.println("File: " + file.toString() + " not created");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
