package scope.lwjgl.forerunner.graphics.obj3d;

public class Animation {

	public String ref;
	public KeyFrame ckf;
	public KeyFrame kfDef = new KeyFrame(0, 0, 0, 0, 0, 0, 0);
	public KeyFrame tkf = new KeyFrame(0, 0, 0, 0, 0, 0, 0);// transform values
	public KeyFrame[] keyFrames;
	public KeyFrame[] keyFramesI;// initial values because the values get warped
	public int keyFrame = 0;
	public int eTime = 0;

	public Animation(String ref, KeyFrame ckf, KeyFrame... keyFrames) {
		this.ref = ref;
		this.ckf = ckf;
		kfDef = ckf;
		this.keyFrames = keyFrames;
		keyFramesI = keyFrames;
	}

	public void update() {
		int time = keyFrames[keyFrame].time;
		tkf.x = ((keyFrames[keyFrame].x - ckf.x) / time);
		tkf.y = ((keyFrames[keyFrame].y - ckf.y) / time);
		tkf.z = ((keyFrames[keyFrame].z - ckf.z) / time);
		tkf.rotX = ((keyFrames[keyFrame].rotX - ckf.rotX) / time);
		tkf.rotY = ((keyFrames[keyFrame].rotY - ckf.rotY) / time);
		tkf.rotZ = ((keyFrames[keyFrame].rotZ - ckf.rotZ) / time);
	}

}
