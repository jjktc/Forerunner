package scope.lwjgl.forerunner.game.entities;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.game.entity.GunPosition;
import scope.lwjgl.forerunner.game.entity.GunPositions;
import scope.lwjgl.forerunner.game.entity.ItemBullet;
import scope.lwjgl.forerunner.game.entity.ItemMagazine;
import scope.lwjgl.forerunner.game.entity.WeaponGun;
import scope.lwjgl.forerunner.graphics.obj3d.AnimModel;
import scope.lwjgl.forerunner.graphics.obj3d.Animation;
import scope.lwjgl.forerunner.graphics.obj3d.KeyFrame;
import scope.lwjgl.forerunner.graphics.obj3d.Models;
import scope.lwjgl.forerunner.sprites.Sprites;

public class Guns {

	public static int GUN_STANDARDASSAULTRIFLE = 0;
	public static int GUN_STANDARDPISTOL = 1;
	public static List<WeaponGun> guns = new ArrayList<WeaponGun>();

	public static GunPositions defaultGPos = new GunPositions(
			new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 125, false, 0, 0), // default
			new GunPosition(0.5f, -3, -15, 0f, -70, -5f, 1, 125, false, 0, 0), // strafe left
			new GunPosition(0.5f, -3, -15, 0f, -90f, -5f, 1, 125, false, 0, 0), // strafe right
			// new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 125, false, 0, 0),//strafe
			// left
			// new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 125, false, 0, 0),//strafe
			// right
			new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 125, true, 0.125f, 0), // walk
			new GunPosition(0.5f, -4.5f, -12, 5f, 5f, 5f, 1, 125, true, 0.25f, 0), // run
			new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 0, false, 0, 0), // jump
			new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 0, true, 0.075f, 0), // crouch
			new GunPosition(0.5f, -3, -15, 0f, -75f, -5f, 1, 0, true, 0.0375f, 0), // prone
			new GunPosition(-2.15f, -2.2f, -15, 0f, -89.1f, -5.7f, 1, 75, false, 0, 0), // aim
			new GunPosition(0.5f, -1, -17, 0f, -75f, -65f, 1, 125, false, 0, 0), // reload
			2, 1.5f);
	public static GunPositions gposStandardPistol = new GunPositions(
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 1, 125, false, 0, 0), // default
			new GunPosition(3.25f, -1.75f, -17.5f, 15f, -75f, 10f, 1, 125, false, 0, 0), // strafe left
			new GunPosition(3.25f, -1.75f, -17.5f, -15f, -75f, -20f, 1, 125, false, 0, 0), // strafe right
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 1, 125, true, 0.125f, 0), // walk
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -35f, 1, 125, true, 0.25f, 30), // run
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 1, 0, false, 0, 0), // jump
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 1, 0, true, 0.075f, 0), // crouch
			new GunPosition(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 1, 0, true, 0.0375f, 0), // prone
			new GunPosition(-0.35f, -1.4f, -17.5f, 0f, -90f, 0f, 1, 75, false, 0, 0), // aim
			new GunPosition(5.5f, 2f, -25f, -45f, -75f, -45f, 1, 125, false, 0, 0), // reload
			1, 20);
	public static Animation[] animStandardAssaultRifle = {
			new Animation("reload", new KeyFrame(0.5f, -1, -17, 0f, -75f, -65f, 0),
					new KeyFrame[] { new KeyFrame(0.5f, -1, -17, 0, -75, -65, 0),
							new KeyFrame(0.5f, -1, -17, 0, -75, -65, 0),
							new KeyFrame(0.5f, -3f, -25, 0f, -75f, -65f, 125),
							new KeyFrame(0.5f, -8f, -32, 0f, -75f, -65f, 125),
							new KeyFrame(-3f, -8f, -5, -10f, -75f, -65f, 250),
							new KeyFrame(0.5f, -4.5f, -31, -10f, -75f, -65f, 250),
							new KeyFrame(0.5f, -4.5f, -31, 0f, -75f, -65f, 125),
							new KeyFrame(0.5f, -1, -17, 0f, -75f, -65f, 125) }),
			new Animation("reload_reset", new KeyFrame(0.5f, -1, -17, 0f, -75f, -65f, 0), new KeyFrame[] {
					new KeyFrame(0.5f, -1, -17, 0, -75, -65, 0), new KeyFrame(0.5f, -3, -15, 0f, -75f, -5f, 125) }) };
	public static Animation[] animStandardPistol = {
			new Animation("reload", new KeyFrame(5.5f, 2f, -25f, -45f, -75f, -45f, 0),
					new KeyFrame[] { new KeyFrame(5.5f, 2f, -25f, -45f, -75f, -45f, 0),
							new KeyFrame(4f, -15f, -25f, -45f, -75f, -45f, 375),
							new KeyFrame(4f, -15f, -25f, -45f, -75f, -45f, 125),
							new KeyFrame(5.5f, 2f, -25f, -45f, -75f, -45f, 375) }),
			new Animation("reload_reset", new KeyFrame(5.5f, 2f, -25f, -45f, -75f, -45f, 0),
					new KeyFrame[] { new KeyFrame(5.5f, 2f, -25f, -45f, -75f, -45f, 0),
							new KeyFrame(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 125) }) };

	public static void init() {
		guns.add(new WeaponGun("Standard Assault Rifle", defaultGPos,
				new ItemMagazine(new ItemBullet(1, null), 30,
						new AnimModel(Models.models.get(Models.MODEL_STANDARDASSAULTRIFLECLIP),
								new KeyFrame(0.5f, -3, -15, 0f, -75f, -5f, 0), animStandardAssaultRifle),
						null),
				Sprites.gi_standardassaultrifle, Models.models.get(Models.MODEL_STANDARDASSAULTRIFLE), true, 16, 0.75f,
				10, 300, 150, 180));
		guns.add(new WeaponGun("Standard Pistol", gposStandardPistol,
				new ItemMagazine(new ItemBullet(1, null), 8,
						new AnimModel(Models.models.get(Models.MODEL_STANDARDPISTOLCLIP),
								new KeyFrame(3.25f, -1.75f, -17.5f, 0f, -75f, -5f, 0), animStandardPistol),
						null),
				Sprites.gi_standardpistol, Models.models.get(Models.MODEL_STANDARDPISTOL), false, 13, 0.85f, 1, 50, 80,
				88));
	}

}
