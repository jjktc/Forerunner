package scope.lwjgl.forerunner.dlc;

import java.util.ArrayList;
import java.util.List;

import scope.lwjgl.forerunner.game.entities.Guns;
import scope.lwjgl.forerunner.game.entity.GunPositions;
import scope.lwjgl.forerunner.game.entity.ItemBullet;
import scope.lwjgl.forerunner.game.entity.ItemMagazine;
import scope.lwjgl.forerunner.game.entity.WeaponGun;
import scope.lwjgl.forerunner.graphics.obj3d.Models;
import scope.lwjgl.forerunner.graphics.obj3d.OBJModel;
import scope.lwjgl.forerunner.processing.FileSystem;
import scope.lwjgl.forerunner.sprites.Sprite;

public class DLCWeapon extends DLCContent {
	
	public List<String> parameters = new ArrayList<String>();
	public int lineStart, lineEnd;
	public String name;
	
	public DLCWeapon() {
		
	}
	
	public void init() {
		if(parameters.size() >= 12) {
			name = parameters.get(0);
			GunPositions gpos = null;
			if(parameters.get(1).equals("defaultGPos")) {
				gpos = Guns.defaultGPos;
			} else {
				String[] gposVars = parameters.get(1).split("_");
				List<Float> gposVarsf = new ArrayList<Float>();
				for(int i = 0; i < gposVars.length; i++) {
					gposVarsf.add(Float.valueOf(gposVars[i]));
				}
				
			}
			ItemMagazine clip = null;
			ItemBullet bullet = null;
			int damage = Integer.parseInt(parameters.get(2));
			if(parameters.get(3).equals("null")) {
				bullet = new ItemBullet(damage, null);
			} else {
				
			}
			int rounds = Integer.parseInt(parameters.get(4));
			if(parameters.get(5).equals("null")) {
				clip = new ItemMagazine(bullet, rounds, null, null);//handle the first null, animmodel
			} else {
				
			}
			Sprite weaponSprite = null;
			if(parameters.get(6).equals("null")) {
				weaponSprite = null;
			} else {
				
			}
			Models.models.add(new OBJModel(FileSystem.home + "/config/dlc/" + parameters.get(7), true));
			Models.models.get(Models.models.size() - 1).init();
			boolean automatic;
			if(parameters.get(8).equals("true")) {
				automatic = true;
			} else {
				automatic = false;
			}
			int rateOfFire = Integer.parseInt(parameters.get(9));
			float range = Float.valueOf(parameters.get(10));
			int defaultAmmo = Integer.parseInt(parameters.get(11));
			int ammo = Integer.parseInt(parameters.get(12));
			WeaponGun gun = new WeaponGun(name, gpos, clip, weaponSprite, Models.models.get(Models.models.size() - 1), automatic, 0, 0, rateOfFire, range, defaultAmmo, ammo);//0 = damage, 0 = accuracy
			Guns.guns.add(gun);
		} else {
			System.out.println("Parameters not passed");
		}
	}

}
