package scope.lwjgl.forerunner.graphics.obj3d;

import scope.lwjgl.forerunner.processing.MathExt;

public class KeyFrame {

	public float x, y, z, rotX, rotY, rotZ;
	public int time;

	public KeyFrame(float x, float y, float z, float rotX, float rotY, float rotZ, int time) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.time = time;
	}

	public boolean equals(KeyFrame kf) {
		if (MathExt.aproxEqualsf(x, kf.x) && MathExt.aproxEqualsf(y, kf.y) && MathExt.aproxEqualsf(z, kf.z)
				&& MathExt.aproxEqualsf(rotX, kf.rotX) && MathExt.aproxEqualsf(rotY, kf.rotY)
				&& MathExt.aproxEqualsf(rotZ, kf.rotZ)) {
			return true;
		} else {
			return false;
		}
	}

	public void setTo(KeyFrame to) {
		x = to.x;
		y = to.y;
		z = to.z;
		rotX = to.rotX;
		rotY = to.rotY;
		rotZ = to.rotZ;
		time = to.time;
	}

	public String toString() {
		return "KEYFRAME: X:" + x + " Y:" + y + " Z:" + z + " ROTX:" + rotX + " ROTY:" + rotY + " ROTZ:" + rotZ
				+ " time:" + time;
	}

}
