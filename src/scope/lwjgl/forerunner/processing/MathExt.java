package scope.lwjgl.forerunner.processing;

import java.util.Random;

public class MathExt {
	
	public static Random random = new Random();
	
	public static float angle(float x1, float y1, float x2, float y2) {
		return (float) (Math.toDegrees(Math.atan((double) ((y2 - y1) / (x2 - x1)))));
	}
	
	public static boolean aproxEqualsf(float val1, float val2) {
		if(Math.abs(val2 - val1) > 0.00001f) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean aproxEqualsf(float val1, float val2, float acc) {
		if(Math.abs(val2 - val1) > acc) {
			return false;
		} else {
			return true;
		}
	}
	
	public static float sqr(float val) {
		return val * val;
	}
	
	public static float lineDistance(float x1, float y1, float x2, float y2) {
		return (float) (Math.sqrt((double) (sqr(x2 - x1) + sqr(y2 - y1))));
	}
	
	public static float slope(float x1, float y1, float x2, float y2) {
		return (y2 - y1) / (x2 - x1);
	}
	
	public static int randomI(int min, int max) {
		return random.nextInt(max) + min;
	}
	
	public static float randomF(float min, float max) {
		return (random.nextFloat() * (max - min)) + min;
	}
	
}
