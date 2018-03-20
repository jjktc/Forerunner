package scope.lwjgl.forerunner.dlc;

import scope.lwjgl.forerunner.processing.FileSystem;

public class DLCLoader {
	
	public static String basic;
	public static DLC test;
	
	public static void init() {
		basic = FileSystem.home + "/config/dlc/";
		//test = new DLC(basic + "dlc_test.txt");
	}

}
