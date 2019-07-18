package scope.lwjgl.forerunner.game.entities;

import org.lwjgl.opengl.GL11;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.game.entity.EntityMob;
import scope.lwjgl.forerunner.game.entity.WeaponGun;
import scope.lwjgl.forerunner.gui.HudMap;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprites;
import scope.lwjgl.forerunner.state.States;

public class MobPlayer extends EntityMob {

	public String name = "Player";
	public static float maxLookUp = 85;
	public static float maxLookDown = -90;
	public static float zNear = 0.3f;
	public static float zFar = 75f;
	public static float fNear = 20f;
	public static float fFar = 28f;
	public float walkSpeed = 1f;
	public float runSpeed = 4f;
	public int maxLives = 100;
	public int lives = 100;
	public float lifePercent = 1.0f;
	public int armor = 100;
	public int maxEnergy = 100;
	public int energy = 100;
	public float energyPercent = 1.0f;
	public float hudR = 1, hudG = 1, hudB = 1;
	public float hudwhR = 1, hudwhG = 1, hudwhB = 1;
	public float hudwhR2 = 1, hudwhG2 = 1, hudwhB2 = 1;
	public int hudColor = 0;
	public int hudAnim = 0;
	public int hudSpeed = 50;
	public int direction = 0;
	public boolean running = false;
	public boolean walkingForward = false;
	public boolean walkingBackward = false;
	public boolean strafingLeft = false;
	public boolean strafingRight = false;
	public boolean jumping = false;
	public boolean crouching = false;
	public boolean proning = false;
	public boolean rolling = false;
	public boolean diving = false;
	public boolean swimming = false;
	public boolean aiming = false;
	public boolean cloaked = false;
	public long timeSinceDamage = 0, timeSinceEnergyLoss = 0;
	public int lifeAnim = 0, energyAnim = 0;
	public int weaponSelection = 0;
	public WeaponGun primary = Guns.guns.get(Guns.GUN_STANDARDASSAULTRIFLE);
	public WeaponGun secondary = Guns.guns.get(Guns.GUN_STANDARDPISTOL);
	public WeaponGun tertiary;
	public WeaponGun selectedWeapon = primary;
	public float w1Scale = 1;
	public float w2Scale = 0.75f;
	public float x = 0, y = 0, z = 0;
	public float width = 1, height = 2, depth = 1;
	public float jumpHeight = 1;
	public int inclineWalkAngle = 35;
	public int cloakAnim = 0;
	public int cloakTransAnim = 0;
	public int cloakTransSpeed = 50;
	public int cloakColor = -1;
	public float armorCloakR = 1f;
	public float armorCloakG = 1f;
	public float armorCloakB = 1f;
	public float armorCloakA = 1f;

	public void init() {
		// primary = Guns.guns.get(3);
		// selectedWeapon = primary;
	}

	public void update() {
		/*
		 * if(!Tutorial.finished) { Tutorial.update(); }
		 */
		if (!States.stateGame.paused) {
			updateStats();
			updateControls();
			selectedWeapon.update();
		}
		HudMap.update();
		if (InputHandler.clicked(InputHandler.KEY_UP)) {
			gainLife(5);
			gainEnergy(5);
		} else if (InputHandler.clicked(InputHandler.KEY_DOWN)) {
			loseLife(5);
			loseEnergy(5);
		}
		switch (hudColor) {
		case 0:
			hudR = 1;
			hudG = 1;
			hudB = 1;
			break;
		case 1:
			hudAnim += Time.anim;
			if (hudAnim >= hudSpeed) {
				if (hudR != 1 || hudG != 0.05f || hudB != 0.05f) {
					if (hudR + 0.075f < 1f) {
						hudR += 0.075f;
					} else if (hudR - 0.075f > 1f) {
						hudR -= 0.075f;
					} else {
						hudR = 1f;
					}
					if (hudG - 0.075f > 0.05f) {
						hudG -= 0.075f;
					} else if (hudG + 0.075f < 0.05f) {
						hudG += 0.075f;
					} else {
						hudG = 0.05f;
					}
					if (hudB - 0.075f > 0.05f) {
						hudB -= 0.075f;
					} else if (hudB + 0.075f < 0.05f) {
						hudB += 0.075f;
					} else {
						hudB = 0.05f;
					}
					hudAnim -= hudSpeed;
				} else {
					hudR = 1;
					hudG = 0.05f;
					hudB = 0.05f;
					hudColor = 2;
				}
			}
			break;
		case 2:
			hudAnim += Time.anim;
			if (hudAnim >= hudSpeed) {
				if (hudR != 1 || hudG != 1f || hudB != 1f) {
					if (hudR + 0.05 < 1) {
						hudR += 0.075f;
					} else if (hudR - 0.075f > 1f) {
						hudR -= 0.075f;
					} else {
						hudR = 1f;
					}
					if (hudG - 0.075f > 1) {
						hudG -= 0.075f;
					} else if (hudG + 0.075f < 1f) {
						hudG += 0.075f;
					} else {
						hudG = 1f;
					}
					if (hudB - 0.05 > 1f) {
						hudB -= 0.075f;
					} else if (hudB + 0.075f < 1f) {
						hudB += 0.075f;
					} else {
						hudB = 1f;
					}
					hudAnim -= hudSpeed;
				} else {
					hudR = 1f;
					hudG = 1f;
					hudB = 1f;
					hudColor = 1;
				}
			}
			break;
		}
		if (lives <= 25) {
			if (hudColor != 1 && hudColor != 2) {
				hudColor = 1;
			}
			if (lives > 10) {
				hudSpeed = 50;
			} else {
				hudSpeed = 35;
			}
		} else {
			hudColor = 0;
		}
		if (((float) primary.ammo + (float) primary.clip.rounds) / (float) primary.defaultAmmo <= 0.3) {
			hudwhR = 1;
			hudwhG = 0.05f;
			hudwhB = 0.05f;
		} else {
			hudwhR = 1;
			hudwhG = 1;
			hudwhB = 1;
		}
		if (((float) secondary.ammo + (float) secondary.clip.rounds) / (float) secondary.defaultAmmo <= 0.3) {
			hudwhR2 = 1;
			hudwhG2 = 0.05f;
			hudwhB2 = 0.05f;
		} else {
			hudwhR2 = 1;
			hudwhG2 = 1;
			hudwhB2 = 1;
		}
		switch (weaponSelection) {
		case 0:
			w1Scale = 1;
			w2Scale = 0.75f;
			break;
		case 1:
			w1Scale = 0.75f;
			w2Scale = 1;
			break;
		}
		switch (cloakColor) {
		case 0:
			cloakTransAnim += Time.anim;
			if (cloakTransAnim >= cloakTransSpeed) {
				if (armorCloakR != 0.3f || armorCloakG != 0.9f || armorCloakB != 1f || armorCloakA != 0.25f) {
					if (armorCloakR + 0.075f < 0.3f) {
						armorCloakR += 0.075f;
					} else if (armorCloakR - 0.075f > 0.3f) {
						armorCloakR -= 0.075f;
					} else {
						armorCloakR = 0.3f;
					}
					if (armorCloakG + 0.075f < 0.9f) {
						armorCloakG += 0.075f;
					} else if (armorCloakG - 0.075f > 0.9f) {
						armorCloakG -= 0.075f;
					} else {
						armorCloakG = 0.9f;
					}
					if (armorCloakB + 0.075f < 1f) {
						armorCloakB += 0.075f;
					} else if (armorCloakB - 0.075f > 1f) {
						armorCloakB -= 0.075f;
					} else {
						armorCloakB = 1f;
					}
					if (armorCloakA + 0.075f < 0.25f) {
						armorCloakA += 0.075f;
					} else if (armorCloakA - 0.075f > 0.25f) {
						armorCloakA -= 0.075f;
					} else {
						armorCloakA = 0.25f;
					}
					cloakTransAnim -= cloakTransSpeed;
				} else {
					armorCloakR = 0.3f;
					armorCloakG = 0.9f;
					armorCloakB = 1f;
					armorCloakA = 0.25f;
					cloakColor = -1;
				}
			}
			break;
		case 1:
			cloakTransAnim += Time.anim;
			if (cloakTransAnim >= cloakTransSpeed) {
				if (armorCloakR != 1f || armorCloakG != 1f || armorCloakB != 1f || armorCloakA != 1f) {
					if (armorCloakR + 0.075f < 1f) {
						armorCloakR += 0.075f;
					} else if (armorCloakR - 0.075f > 1f) {
						armorCloakR -= 0.075f;
					} else {
						armorCloakR = 1f;
					}
					if (armorCloakG + 0.075f < 1f) {
						armorCloakG += 0.075f;
					} else if (armorCloakG - 0.075f > 1f) {
						armorCloakG -= 0.075f;
					} else {
						armorCloakG = 1f;
					}
					if (armorCloakB + 0.075f < 1f) {
						armorCloakB += 0.075f;
					} else if (armorCloakB - 0.075f > 1f) {
						armorCloakB -= 0.075f;
					} else {
						armorCloakB = 1f;
					}
					if (armorCloakA + 0.075f < 1f) {
						armorCloakA += 0.075f;
					} else if (armorCloakA - 0.075f > 1f) {
						armorCloakA -= 0.075f;
					} else {
						armorCloakA = 1f;
					}
					cloakTransAnim -= cloakTransSpeed;
				} else {
					armorCloakR = 1f;
					armorCloakG = 1f;
					armorCloakB = 1f;
					armorCloakA = 1f;
					cloakColor = -1;
				}
			}
			break;
		}
	}

	public void render() {
		GL11.glColor4f(armorCloakR, armorCloakG, armorCloakB, armorCloakA);
		selectedWeapon.render();
		GL11.glColor4f(1, 1, 1, 1);
	}

	public void renderHud() {
		if (dead) {
			Sprites.hud_cracked.render(0, 0);
		}
		Sprites.hud.render(0, 0, 1, hudR, hudG, hudB, 0.5f);
		Sprites.hud_health_bg.render(8, 8, Sprites.hud_health_bg.width,
				(int) (Sprites.hud_health_bg.height * lifePercent), hudR, hudG, hudB, 0.5f);
		Sprites.hud_health.render(8, 8, 1, hudR, hudG, hudB, 0.5f);
		Fonts.fontDefault.drawString(lives + "%",
				8 + ((Sprites.hud_health.width) - Fonts.fontDefault.getWidthByString(lives + "%", 2)) / 2,
				8 + ((Sprites.hud_health.height) - Fonts.fontDefault.getHeightByString(lives + "%", 2)) / 2, 2, hudR,
				hudG, hudB, 0.75f);
		Sprites.hud_energy.render((Main.width - Sprites.hud_energy.width) / 2, 8, 1, hudR, hudG, hudB, 0.5f);
		Sprites.hud_energy_section.render((Main.width - Sprites.hud_energy.width) / 2 + 1, 8,
				(int) (energyPercent * (Sprites.hud_energy.width - 2)), 66, hudR, hudG, hudB, 0.75f);
		HudMap.render();
		Sprites.hud_weaponHolder.render((int) (Main.width - Sprites.hud_weaponHolder.width * w1Scale
				- Sprites.hud_weaponHolder.width * w2Scale), 0, w1Scale, hudwhR, hudwhG, hudwhB, 0.5f);
		Sprites.hud_weaponHolder.render((int) (Main.width - Sprites.hud_weaponHolder.width * w2Scale), 0, w2Scale,
				hudwhR2, hudwhG2, hudwhB2, 0.5f);
		primary.weaponSprite.render(
				(int) (Main.width - Sprites.hud_weaponHolder.width * w1Scale - Sprites.hud_weaponHolder.width * w2Scale)
						+ (int) (((Sprites.hud_weaponHolder.width - primary.weaponSprite.width) * w1Scale) / 2),
				(int) (((Sprites.hud_weaponHolder.height - primary.weaponSprite.height) * w1Scale) / 2), w1Scale);
		secondary.weaponSprite.render(
				(int) (Main.width - Sprites.hud_weaponHolder.width * w2Scale)
						+ (int) (((Sprites.hud_weaponHolder.width - secondary.weaponSprite.width) * w2Scale) / 2),
				(int) (((Sprites.hud_weaponHolder.height - secondary.weaponSprite.height) * w2Scale) / 2), w2Scale);
		Fonts.fontDefault.drawString(selectedWeapon.name,
				(int) (Main.width - Fonts.fontDefault.getWidthByString(selectedWeapon.name, 1) - 8),
				2 + Sprites.hud_weaponHolder.height, 1);
		if (!aiming)
			Sprites.hud_aimer.render((Main.width - Sprites.hud_aimer.width * 2) / 2,
					(int) (((Main.height - Sprites.hud_aimer.height * 2) / 2)), 2, 1, 1, 1, 0.9f);
		Fonts.fontDefault.drawString(selectedWeapon.clip.rounds + "/" + selectedWeapon.ammo, Main.width
				- Fonts.fontDefault.getWidthByString(selectedWeapon.clip.rounds + "/" + selectedWeapon.ammo, 1) - 4,
				Main.height - Fonts.fontDefault.colH - 4, 1);
		/*
		 * if(!Tutorial.finished) { Tutorial.render(); }
		 */
	}

	public void updateStats() {
		if (cloaked) {
			cloakAnim += Time.anim;
			if (cloakAnim >= 200) {
				loseEnergy(1);
				cloakAnim -= 200;
			}
			if (energy <= 0) {
				cloaked = false;
				cloakColor = 1;
			}
		}
		energyPercent = (float) (energy) / maxEnergy;
		if (!dead && !States.stateGame.paused) {
			timeSinceEnergyLoss += Time.anim;
			if (timeSinceEnergyLoss >= 500) {
				if (energy < maxEnergy) {
					energyAnim += Time.anim;
					if (energyAnim > 50) {
						gainEnergy(1);
						energyAnim -= 50;
					}
				}
			}
		}
		lifePercent = (float) (lives) / maxLives;
		if (!dead && !States.stateGame.paused) {
			timeSinceDamage += Time.anim;
			if (timeSinceDamage >= 3000) {
				if (lives < maxLives) {
					lifeAnim += Time.anim;
					if (lifeAnim > 100) {
						gainLife(1);
						lifeAnim -= 100;
					}
				}
			}
		}
	}

	public void updateControls() {
		boolean r = InputHandler.active(InputHandler.KEY_R);
		boolean w = InputHandler.active(InputHandler.KEY_W);
		boolean a = InputHandler.active(InputHandler.KEY_A);
		boolean s = InputHandler.active(InputHandler.KEY_S);
		boolean d = InputHandler.active(InputHandler.KEY_D);
		boolean eC = InputHandler.clicked(InputHandler.KEY_E);
		boolean fC = InputHandler.clicked(InputHandler.KEY_F);
		boolean fA = InputHandler.active(InputHandler.KEY_F);
		boolean cC = InputHandler.clicked(InputHandler.KEY_C);
		boolean v = InputHandler.clicked(InputHandler.KEY_V);
		boolean spaceC = InputHandler.clicked(InputHandler.KEY_SPACE);
		boolean shiftC = InputHandler.clicked(InputHandler.KEY_LSHIFT);
		boolean controlC = InputHandler.clicked(InputHandler.KEY_LCONTROL);
		boolean tabC = InputHandler.clicked(InputHandler.KEY_TAB);
		boolean altC = InputHandler.clicked(InputHandler.KEY_LALT);
		boolean mouseRA = InputHandler.mbRight.active;
		boolean mouseLC = InputHandler.mbLeft.clicked;
		boolean mouseLA = InputHandler.mbLeft.active;

		controlAim(mouseRA);
		controlShoot(mouseLC, mouseLA);
		controlReload(eC);
		controlMovement(r, w, a, s, d);
		controlJump(spaceC);
		controlCrouch(shiftC);
		controlProne(controlC);
		controlSwitchWeapon(tabC);
		controlActivate(fC, fA);
		controlCloak(v);
		controlPunch(cC);
		controlKnife(altC);
	}

	public void controlMovement(boolean r, boolean w, boolean a, boolean s, boolean d) {// WASD
		if (w || a || s || d) {
			if (r) {
				if (energy > 0) {
					running = true;
					if (w) {
						walkingForward = true;
						walkingBackward = false;
					} else if (s) {
						walkingForward = false;
						walkingBackward = true;
					} else {
						walkingForward = false;
						walkingBackward = false;
					}
				} else {
					running = false;
					if (w) {
						walkingForward = true;
						walkingBackward = false;
					} else if (s) {
						walkingForward = false;
						walkingBackward = true;
					} else {
						walkingForward = false;
						walkingBackward = false;
					}
				}
			} else {
				running = false;
				if (w) {
					walkingForward = true;
					walkingBackward = false;
				} else if (s) {
					walkingForward = false;
					walkingBackward = true;
				} else {
					walkingForward = false;
					walkingBackward = false;
				}
			}
			if (a) {
				strafingLeft = true;
				strafingRight = false;
			} else if (d) {
				strafingLeft = false;
				strafingRight = true;
			} else {
				strafingLeft = false;
				strafingRight = false;
			}
		} else {
			walkingForward = false;
			walkingBackward = false;
			strafingLeft = false;
			strafingRight = false;
		}
		if (!selectedWeapon.reloading) {
			if (!aiming) {
				if (running) {
					if (walkingForward || walkingBackward || strafingLeft || strafingRight) {
						selectedWeapon.gotoPos(WeaponGun.POS_RUN);
					} else {
						selectedWeapon.gotoPos(WeaponGun.POS_DEFAULT);
					}
				} else {
					if (walkingForward || walkingBackward) {
						if (strafingLeft) {
							selectedWeapon.gotoPos(WeaponGun.POS_STRAFELEFT);
						} else if (strafingRight) {
							selectedWeapon.gotoPos(WeaponGun.POS_STRAFERIGHT);
						} else {
							selectedWeapon.gotoPos(WeaponGun.POS_WALK);
						}
					}
					if (strafingLeft && strafingRight) {
						selectedWeapon.gotoPos(WeaponGun.POS_DEFAULT);
					} else if (strafingLeft || strafingRight) {
						if (strafingLeft) {
							selectedWeapon.gotoPos(WeaponGun.POS_STRAFELEFT);
						} else if (strafingRight) {
							selectedWeapon.gotoPos(WeaponGun.POS_STRAFERIGHT);
						}
					}
					if (!(walkingForward || walkingBackward || strafingLeft || strafingRight)) {
						selectedWeapon.gotoPos(WeaponGun.POS_DEFAULT);
					}
				}
			} else {
				selectedWeapon.gotoPos(WeaponGun.POS_AIM);
			}
		}
	}

	public void controlCrouch(boolean shift) {// SHIFT
		if (shift) {
			crouching = !crouching;
			if (crouching) {
				proning = false;
			}
		}
	}

	public void controlProne(boolean ctrl) {// CTRL
		if (ctrl) {
			proning = !proning;
			if (proning) {
				crouching = false;
			}
		}
	}

	public void controlJump(boolean space) {// SPACE
		crouching = false;
		proning = false;
		if (running) {// jump a large distance

		} else if (walkingForward) {// jump a small distance

		} else if (walkingBackward) {

		} else {// jump straight up

		}
	}

	public void controlAim(boolean rclick) {// RIGHT-CLICK
		aiming = rclick;
	}

	public void controlShoot(boolean lclick, boolean lactive) {// LEFT-CLICK
		if (selectedWeapon.automatic) {
			if (lactive) {
				selectedWeapon.pullTrigger();
			}
		} else {
			if (lclick) {
				selectedWeapon.pullTrigger();
			}
		}
	}

	public void controlActivate(boolean fC, boolean fA) {// F

	}

	public void controlCloak(boolean v) {// V
		if (v) {
			if (!cloaked) {
				if (energy > 0) {
					cloaked = true;
					cloakColor = 0;
				} else {
					cloaked = false;
					cloakColor = 1;
				}
			} else {
				cloaked = false;
				cloakColor = 1;
			}
		}
	}

	public void controlReload(boolean e) {// E
		if (!selectedWeapon.reloading) {
			if (e) {
				if (selectedWeapon.clip.rounds < selectedWeapon.clip.maxRounds)
					selectedWeapon.reload();
			}
		}
	}

	public void controlLook() {// MOUSE

	}

	public void controlPunch(boolean c) {// C

	}

	public void controlKnife(boolean alt) {// ALT

	}

	public void controlSwitchWeapon(boolean tab) {// TAB
		if (tab) {
			switch (weaponSelection) {
			case 0:
				weaponSelection = 1;
				selectedWeapon = secondary;
				break;
			case 1:
				weaponSelection = 0;
				selectedWeapon = primary;
				break;
			}
		}
	}

	public void loseLife(int amount) {
		if (amount > 0) {
			timeSinceDamage = 0;
		}
		lives -= amount;
		if (lives <= 0) {
			dead = true;
			lives = 0;
		}
	}

	public void gainLife(int amount) {
		lives += amount;
		if (lives > maxLives) {
			lives = maxLives;
		}
		if (lives > 0) {
			dead = false;
		}
	}

	public void loseEnergy(int amount) {
		if (amount > 0) {
			timeSinceEnergyLoss = 0;
		}
		energy -= amount;
		if (energy < 0) {
			energy = 0;
		}
	}

	public void gainEnergy(int amount) {
		energy += amount;
		if (energy > maxEnergy) {
			energy = maxEnergy;
		}
	}

}
