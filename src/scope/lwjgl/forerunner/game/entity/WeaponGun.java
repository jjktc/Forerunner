package scope.lwjgl.forerunner.game.entity;

import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.graphics.obj3d.OBJModel;
import scope.lwjgl.forerunner.processing.MathExt;
import scope.lwjgl.forerunner.sprites.Sprite;

public class WeaponGun extends EntityWeapon {

	public static final int STATE_NOTHING = 0;
	public static final int STATE_AIMING = 1;
	public static final int STATE_RUNNING = 2;
	public static final int STATE_GOINGRIGHT = 3;
	public static final int STATE_GOINGLEFT = 4;
	public static final int STATE_RELOADING = 5;
	public static final int STATE_SHOOTING = 6;
	public static final int POS_DEFAULT = 0;
	public static final int POS_STRAFELEFT = 1;
	public static final int POS_STRAFERIGHT = 2;
	public static final int POS_WALK = 3;
	public static final int POS_RUN = 4;
	public static final int POS_JUMP = 5;
	public static final int POS_CROUCH = 6;
	public static final int POS_PRONE = 7;
	public static final int POS_AIM = 8;
	public static final int POS_RELOAD = 9;
	public static final int POS_ROLL = 10;
	public static final int POS_DIVE = 11;
	public String name;
	public ItemMagazine clip;
	public Sprite weaponSprite;
	public OBJModel model;
	public int rateOfFire;
	public boolean automatic;
	public float range;
	public int ammo = 0, defaultAmmo;
	public float accuracy;
	public int anim;
	public int shootAnim = -1;
	public int state = STATE_NOTHING;
	public float xi, yi, zi, rotXi, rotYi, rotZi, scalei;
	public float x, y, z, rotX, rotY, rotZ, scale;
	public GunPositions gpos;
	public GunPosition gposWanted;
	public int elapsedTime = 0, switchTime = 0;
	public int wantedPos = POS_DEFAULT;
	public int currentPos = POS_DEFAULT;
	public float gposChangeX, gposChangeY, gposChangeZ, gposChangeROTX, gposChangeROTY, gposChangeROTZ;
	public float bob, rZ;
	public boolean muzzleFlash = false;
	public float recoil = 0, cRecoil = 0, wRecoil = 0, chRecoil = 0;
	public int eRecoil = 0, tRecoil = 0;
	public boolean reloading = false;
	public boolean reloadingStartClip = false;

	public WeaponGun(String name, GunPositions gpos, ItemMagazine clip, Sprite weaponSprite, OBJModel model,
			boolean automatic, int damage, float accuracy, int rateOfFire, float range, int defaultAmmo, int ammo) {
		this.name = name;
		this.gpos = gpos;
		gposWanted = gpos.def;
		this.clip = clip;
		this.weaponSprite = weaponSprite;
		this.model = model;
		this.automatic = automatic;
		this.accuracy = accuracy;
		this.rateOfFire = rateOfFire;
		this.range = range;
		this.defaultAmmo = defaultAmmo;
		this.ammo = ammo - clip.rounds;
		this.damage = damage;
		x = gpos.def.x;
		y = gpos.def.y;
		z = gpos.def.z;
		rotX = gpos.def.rotX;
		rotY = gpos.def.rotY;
		rotZ = gpos.def.rotZ;
		scale = gpos.def.scale;
		xi = x;
		yi = y;
		zi = z;
		rotXi = rotX;
		rotYi = rotY;
		rotZi = rotZ;
		scalei = scale;
		recoil = gpos.recoil;
	}

	public void update() {
		if (currentPos != wantedPos) {
			if (switchTime > 0) {
				elapsedTime += Time.anim;
				if (elapsedTime >= switchTime) {
					currentPos = wantedPos;
					elapsedTime = 0;
					switchTime = 0;
					gpos.current = gposWanted;
					x = gposWanted.x;
					y = gposWanted.y;
					z = gposWanted.z;
					rotX = gposWanted.rotX;
					rotY = gposWanted.rotY;
					rotZ = gposWanted.rotZ;
				} else {
					int anim = Time.anim;
					boolean justSet = false;
					if (x < gposWanted.x) {
						if (x + gposChangeX * anim >= gposWanted.x) {
							justSet = true;
						}
					} else {
						if (x + gposChangeX * anim <= gposWanted.x) {
							justSet = true;
						}
					}
					if (justSet) {
						x = gposWanted.x;
					} else {
						x += gposChangeX * anim;
					}
					justSet = false;
					if (y < gposWanted.y) {
						if (y + gposChangeY * anim >= gposWanted.y) {
							justSet = true;
						}
					} else {
						if (y + gposChangeY * anim <= gposWanted.y) {
							justSet = true;
						}
					}
					if (justSet) {
						y = gposWanted.y;
					} else {
						y += gposChangeY * anim;
					}
					justSet = false;
					if (z < gposWanted.z) {
						if (z + gposChangeZ * anim >= gposWanted.z) {
							justSet = true;
						}
					} else {
						if (x + gposChangeZ * anim <= gposWanted.z) {
							justSet = true;
						}
					}
					if (justSet) {
						z = gposWanted.z;
					} else {
						z += gposChangeZ * anim;
					}
					justSet = false;
					if (rotX < gposWanted.rotX) {
						if (rotX + gposChangeROTX * anim >= gposWanted.rotX) {
							justSet = true;
						}
					} else {
						if (rotX + gposChangeROTX * anim <= gposWanted.rotX) {
							justSet = true;
						}
					}
					if (justSet) {
						rotX = gposWanted.rotX;
					} else {
						rotX += gposChangeROTX * anim;
					}
					justSet = false;
					if (rotY < gposWanted.rotY) {
						if (rotY + gposChangeROTY * anim >= gposWanted.rotY) {
							justSet = true;
						}
					} else {
						if (rotY + gposChangeROTY * anim <= gposWanted.rotY) {
							justSet = true;
						}
					}
					if (justSet) {
						rotY = gposWanted.rotY;
					} else {
						rotY += gposChangeROTY * anim;
					}
					justSet = false;
					if (rotZ < gposWanted.rotZ) {
						if (rotZ + gposChangeROTZ * anim >= gposWanted.rotZ) {
							justSet = true;
						}
					} else {
						if (rotZ + gposChangeROTZ * anim <= gposWanted.rotZ) {
							justSet = true;
						}
					}
					if (justSet) {
						rotZ = gposWanted.rotZ;
					} else {
						rotZ += gposChangeROTZ * anim;
					}
				}
			} else {
				currentPos = wantedPos;
				elapsedTime = 0;
				gpos.current = gposWanted;
				x = gposWanted.x;
				y = gposWanted.y;
				z = gposWanted.z;
				rotX = gposWanted.rotX;
				rotY = gposWanted.rotY;
				rotZ = gposWanted.rotZ;
			}
		}
		if (cRecoil != wRecoil) {
			eRecoil += Time.anim;
			if (eRecoil >= tRecoil) {
				cRecoil = wRecoil;
				if (cRecoil == recoil) {
					wRecoil = 0;
					chRecoil = ((-cRecoil) / tRecoil);
				}
				eRecoil = 0;
			} else {
				int anim = Time.anim;
				boolean justSet = false;
				if (cRecoil < wRecoil) {
					if (cRecoil + chRecoil * anim >= wRecoil) {
						justSet = true;
					}
				} else {
					if (cRecoil + chRecoil * anim <= wRecoil) {
						justSet = true;
					}
				}
				if (justSet) {
					cRecoil = wRecoil;
					if (cRecoil == recoil) {
						wRecoil = 0;
						chRecoil = ((-cRecoil) / tRecoil);
					}
				} else {
					cRecoil += chRecoil * anim;
				}
			}
		}
		clip.model.update();
		if (currentPos == POS_RELOAD && wantedPos == POS_RELOAD && reloading) {
			if (reloadingStartClip) {
				clip.model.runAnim(0);
				reloadingStartClip = false;
			}
			if (clip.model.currentAnim == -1) {
				reloading = false;
				if (ammo >= (clip.maxRounds - clip.rounds)) {
					ammo -= (clip.maxRounds - clip.rounds);
					clip.rounds = clip.maxRounds;
				} else {
					clip.rounds = ammo;
					ammo = 0;
				}
				clip.model.reset();
				clip.model.runAnim(1);
				gotoPos(POS_DEFAULT);
			}
		}
		bob = (float) (Math.sin(Time.total / 170) * gpos.current.bobAmount);
		rZ = (float) (Math.sin(Time.total / 170) * gpos.current.rotateAmount) + (gpos.current.rotateAmount / 2);
	}

	public void render() {
		if (gpos.current.bob) {
			if (gpos.handHolds == 2) {
				clip.model.renderAtPoint(x, y + bob, z, rotX, rotY, rotZ);
				model.render(x, y + bob, z, rotX, rotY, rotZ);
			} else {
				if (currentPos == POS_RUN) {
					clip.model.renderAtPoint(x, y + bob + (rZ / 20), z, rotX, rotY, rotZ - rZ);
					model.render(x, y + bob + (rZ / 20), z, rotX, rotY, rotZ - rZ);
				} else {
					clip.model.renderAtPoint(x, y + bob, z, rotX, rotY, rotZ - cRecoil);
					model.render(x, y + bob, z, rotX, rotY, rotZ - cRecoil);
				}
			}
		} else {
			if (clip.model.currentAnim >= 0) {
				clip.model.renderOffSet(0, 0, 0, 0, 0, -cRecoil);
			} else {
				clip.model.renderAtPoint(x, y, z, rotX, rotY, rotZ - cRecoil);
			}
			model.render(x, y, z, rotX, rotY, rotZ - cRecoil);
		}
	}

	public void reload() {
		state = STATE_RELOADING;
		gotoPos(POS_RELOAD);
		reloading = true;
		reloadingStartClip = true;
	}

	public void noAmmo() {
		state = STATE_NOTHING;
	}

	public void shoot() {
		state = STATE_SHOOTING;
		if (automatic) {
			tRecoil = 30;
			if (shootAnim == -1) {
				clip.rounds -= 1;
				muzzleFlash = true;
				wRecoil = recoil;
				shootAnim = Time.anim;
			} else {
				shootAnim += Time.anim;
				if (shootAnim >= (1000 / rateOfFire)) {
					muzzleFlash = true;
					wRecoil = recoil;
					clip.rounds--;
					shootAnim -= (1000 / rateOfFire);
				}
			}
		} else {
			tRecoil = 50;
			muzzleFlash = true;
			wRecoil = recoil;
			clip.rounds -= rateOfFire;
		}
		chRecoil = ((wRecoil - cRecoil) / tRecoil);
	}

	public void letGoOfTrigger() {
		shootAnim = -1;
	}

	public void pullTrigger() {
		if (clip.rounds > 0) {
			if (!reloading) {
				shoot();
			}
		} else {
			if (ammo > 0) {
				reload();
			} else {
				noAmmo();
			}
		}
	}

	public void gotoPos(int posId) {
		if (currentPos != posId) {
			switch (posId) {
			case POS_DEFAULT:
				wantedPos = POS_DEFAULT;
				gposWanted = gpos.def;
				switchTime = gpos.def.switchTime;
				break;
			case POS_STRAFELEFT:
				wantedPos = POS_STRAFELEFT;
				gposWanted = gpos.strafeLeft;
				switchTime = gpos.strafeLeft.switchTime;
				break;
			case POS_STRAFERIGHT:
				wantedPos = POS_STRAFERIGHT;
				gposWanted = gpos.strafeRight;
				switchTime = gpos.strafeRight.switchTime;
				break;
			case POS_WALK:
				wantedPos = POS_WALK;
				gposWanted = gpos.walk;
				switchTime = gpos.walk.switchTime;
				break;
			case POS_RUN:
				wantedPos = POS_RUN;
				gposWanted = gpos.run;
				switchTime = gpos.run.switchTime;
				break;
			case POS_JUMP:
				wantedPos = POS_JUMP;
				gposWanted = gpos.jump;
				switchTime = gpos.jump.switchTime;
				break;
			case POS_CROUCH:
				wantedPos = POS_CROUCH;
				gposWanted = gpos.crouch;
				switchTime = gpos.crouch.switchTime;
				break;
			case POS_PRONE:
				wantedPos = POS_PRONE;
				gposWanted = gpos.prone;
				switchTime = gpos.prone.switchTime;
				break;
			case POS_AIM:
				wantedPos = POS_AIM;
				gposWanted = gpos.aim;
				switchTime = gpos.aim.switchTime;
				break;
			case POS_RELOAD:
				reloading = true;
				reloadingStartClip = true;
				wantedPos = POS_RELOAD;
				gposWanted = gpos.reload;
				switchTime = gpos.reload.switchTime;
				break;
			case POS_ROLL:
				wantedPos = POS_ROLL;
				gposWanted = gpos.def;
				switchTime = gpos.def.switchTime;
				break;
			case POS_DIVE:
				wantedPos = POS_DIVE;
				gposWanted = gpos.def;
				switchTime = gpos.def.switchTime;
				break;
			}
			gposChangeX = ((gposWanted.x - x) / switchTime);
			gposChangeY = ((gposWanted.y - y) / switchTime);
			gposChangeZ = ((gposWanted.z - z) / switchTime);
			gposChangeROTX = ((gposWanted.rotX - rotX) / switchTime);
			gposChangeROTY = ((gposWanted.rotY - rotY) / switchTime);
			gposChangeROTZ = ((gposWanted.rotZ - rotZ) / switchTime);
		}
	}

	public boolean atGPos(GunPosition gpos) {
		if (MathExt.aproxEqualsf(x, gpos.x) && MathExt.aproxEqualsf(y, gpos.y) && MathExt.aproxEqualsf(z, gpos.z)
				&& MathExt.aproxEqualsf(rotX, gpos.rotX) && MathExt.aproxEqualsf(rotY, gpos.rotY)
				&& MathExt.aproxEqualsf(rotZ, gpos.rotZ) && MathExt.aproxEqualsf(scale, gpos.scale)) {
			return true;
		}
		return false;
	}

	public void shootFlash() {
		/*
		 * if(muzzleFlash) { FloatBuffer lightPosition =
		 * BufferUtils.createFloatBuffer(4);
		 * lightPosition.put(-x).put(-y).put(z).put(1.0f).flip(); FloatBuffer whiteLight
		 * = BufferUtils.createFloatBuffer(4);
		 * whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		 * GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);
		 * GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, whiteLight);
		 * GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, whiteLight);
		 * //GL11.glLight(GL_LIGHT0, GL11.GL_POSITION, Camera.asFloatBuffer(new float[]
		 * {-x, -y, z, 1})); muzzleFlash = false; }
		 */
	}

}
