package scope.lwjgl.forerunner;

import org.lwjgl.Sys;

public class Time {

	public static long time;
	public static long lastFrame;
	public static long lastFPS;
	public static int frames;
	public static int fps = 1;
	public static double delta;
	public static int anim = 0;
	public static long time1, time2;
	public static int total = 0;

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static void update() {
		delta = (double) time1 - (double) time2;
		anim = (int) (time1 - time2);
		total += anim;
	}

	public static long updateFPS() {
		if (getTime() - lastFPS > 1000) {
			fps = frames;
			frames = 0; // reset the FPS counter
			lastFPS += 1000; // add one second
		}
		frames++;
		return frames;
	}

}
