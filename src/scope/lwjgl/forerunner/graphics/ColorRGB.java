package scope.lwjgl.forerunner.graphics;

public class ColorRGB {
	
	public float r, g, b, a;
	
	public ColorRGB(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public ColorRGB(float[] colors, float a) {
		r = colors[0];
		g = colors[1];
		b = colors[2];
		this.a = a;
	}
	
	public float[] convertColor(int r, int g, int b) {
		float newR = r / 255;
		float newG = g / 255;
		float newB = b / 255;
		float[] newColors = {newR, newG, newB};
		return newColors;
	}
	
	public int[] unconvertColor(float r, float g, float b) {
		int newR = (int) (r * 255);
		int newG = (int) (g * 255);
		int newB = (int) (b * 255);
		int[] newColors = {newR, newG, newB};
		return newColors;
	}

}
