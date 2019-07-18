package scope.lwjgl.forerunner.game.entity;

public class GunPosition {

	public float x, y, z, rotX, rotY, rotZ, scale;
	public int elapsedTime = 0, switchTime = 0;
	public boolean bob;
	public float bobAmount, rotateAmount;

	public GunPosition(float x, float y, float z, float rotX, float rotY, float rotZ, float scale, int switchTime,
			boolean bob, float bobAmount, float rotateAmount) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.switchTime = switchTime;
		this.bob = bob;
		this.bobAmount = bobAmount;
		this.rotateAmount = rotateAmount;
	}

}
