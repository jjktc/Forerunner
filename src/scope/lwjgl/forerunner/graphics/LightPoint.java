package scope.lwjgl.forerunner.graphics;

public class LightPoint {

	public float x, y, z, strength;
	public float[] lightPosition = new float[4];

	public LightPoint(float x, float y, float z, float strength) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.strength = strength;
		lightPosition[0] = x;
		lightPosition[1] = y;
		lightPosition[2] = z;
		lightPosition[3] = strength;
	}

	public void set(float x, float y, float z, float strength) {
		lightPosition[0] = x;
		lightPosition[1] = y;
		lightPosition[2] = z;
		lightPosition[3] = strength;
	}

}
