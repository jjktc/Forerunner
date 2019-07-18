package scope.lwjgl.forerunner.graphics.obj3d;

import scope.lwjgl.forerunner.Time;

public class AnimModel {

	public OBJModel model;
	public Animation[] animations;
	public int currentAnim = -1, lastAnim = -1;
	public KeyFrame stored = new KeyFrame(0, 0, 0, 0, 0, 0, 0);

	public AnimModel(OBJModel model, KeyFrame stored, Animation... animations) {
		this.model = model;
		this.stored = stored;
		this.animations = animations;
	}

	public void runAnim(int index) {
		currentAnim = index;
		lastAnim = index;
	}

	public void update() {
		if (currentAnim >= 0) {// an animation is running
			if (!animations[currentAnim].ckf
					.equals(animations[currentAnim].keyFrames[animations[currentAnim].keyFrame])) {// the current
																									// keyframe does not
																									// equal what the
																									// current keyframe
																									// should be
				animations[currentAnim].eTime += Time.anim;
				if (animations[currentAnim].eTime >= animations[currentAnim].keyFrames[animations[currentAnim].keyFrame].time) {
					animations[currentAnim].ckf
							.setTo(animations[currentAnim].keyFrames[animations[currentAnim].keyFrame]);
					animations[currentAnim].eTime = 0;
					if (animations[currentAnim].keyFrame + 1 < animations[currentAnim].keyFrames.length) {
						animations[currentAnim].keyFrame++;
						animations[currentAnim].update();
					} else {
						reset();
					}
				} else {
					float chX = animations[currentAnim].tkf.x;
					float chY = animations[currentAnim].tkf.y;
					float chZ = animations[currentAnim].tkf.z;
					float chROTX = animations[currentAnim].tkf.rotX;
					float chROTY = animations[currentAnim].tkf.rotY;
					float chROTZ = animations[currentAnim].tkf.rotZ;
					animations[currentAnim].ckf.x += chX * Time.anim;
					animations[currentAnim].ckf.y += chY * Time.anim;
					animations[currentAnim].ckf.z += chZ * Time.anim;
					animations[currentAnim].ckf.rotX += chROTX * Time.anim;
					animations[currentAnim].ckf.rotY += chROTY * Time.anim;
					animations[currentAnim].ckf.rotZ += chROTZ * Time.anim;
				}
			} else {// the keyframe is already ready so wait the set time out before switching to
					// the next keyframe or ending
				animations[currentAnim].eTime += Time.anim;
				if (animations[currentAnim].eTime >= animations[currentAnim].keyFrames[animations[currentAnim].keyFrame].time) {
					animations[currentAnim].ckf
							.setTo(animations[currentAnim].keyFrames[animations[currentAnim].keyFrame]);
					animations[currentAnim].eTime = 0;
					if (animations[currentAnim].keyFrame + 1 < animations[currentAnim].keyFrames.length) {
						animations[currentAnim].keyFrame++;
						animations[currentAnim].update();
					} else {
						reset();
					}
				} else {

				}
			}
		} else {// no animation is running
			// if(lastAnim >= 0) {
			// stored.setTo(animations[lastAnim].kfDef);
			// }
		}
	}

	public void renderOffSet(float xOff, float yOff, float zOff, float rotXOff, float rotYOff, float rotZOff) {
		float x, y, z, rotX, rotY, rotZ;
		if (currentAnim >= 0) {
			x = animations[currentAnim].ckf.x;
			y = animations[currentAnim].ckf.y;
			z = animations[currentAnim].ckf.z;
			rotX = animations[currentAnim].ckf.rotX;
			rotY = animations[currentAnim].ckf.rotY;
			rotZ = animations[currentAnim].ckf.rotZ;
		} else {
			x = stored.x;
			y = stored.y;
			z = stored.z;
			rotX = stored.rotX;
			rotY = stored.rotY;
			rotZ = stored.rotZ;
		}
		model.render(x + xOff, y + yOff, z + zOff, rotX + rotXOff, rotY + rotYOff, rotZ + rotZOff);
	}

	public void render(float x, float y, float z, float rotX, float rotY, float rotZ) {
		float xa = x;
		float ya = y;
		float za = z;
		float rotXa = rotX;
		float rotYa = rotY;
		float rotZa = rotZ;
		if (currentAnim >= 0) {
			xa += animations[currentAnim].ckf.x;
			ya += animations[currentAnim].ckf.y;
			za += animations[currentAnim].ckf.z;
			rotXa += animations[currentAnim].ckf.rotX;
			rotYa += animations[currentAnim].ckf.rotY;
			rotZa += animations[currentAnim].ckf.rotZ;
		} else {
			xa += stored.x;
			ya += stored.y;
			za += stored.z;
			rotXa += stored.rotX;
			rotYa += stored.rotY;
			rotZa += stored.rotZ;
		}
		model.render(xa, ya, za, rotXa, rotYa, rotZa);
	}

	public void renderAtPoint(float x, float y, float z, float rotX, float rotY, float rotZ) {
		model.render(x, y, z, rotX, rotY, rotZ);
	}

	public void reset() {
		if (lastAnim >= 0) {
			animations[lastAnim].ckf.setTo(animations[lastAnim].kfDef);
			animations[lastAnim].keyFrame = 0;
			animations[lastAnim].eTime = 0;
			currentAnim = -1;
		}
	}

	public void debug() {
		String printLog = "DEBUG: " + animations[0].ckf.x + " " + animations[0].ckf.y + " " + animations[0].ckf.z
				+ "\n";
		System.out.println(printLog);
		String printLog2 = "DEBUG2: ";
		for (int i = 0; i < animations[0].keyFrames.length; i++) {
			printLog2 += animations[0].keyFrames[i].x + " " + animations[0].keyFrames[i].y + " "
					+ animations[0].keyFrames[i].z + "\n";
		}
		System.out.println(printLog2);
	}

}
