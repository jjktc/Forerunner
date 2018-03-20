package scope.lwjgl.forerunner.game.entity;

import scope.lwjgl.forerunner.sprites.Sprite;

public class ItemBullet extends EntityItem {
	
	public int damage;
	public Sprite casing, bulletHole;
	
	public ItemBullet(int damage, Sprite casing) {
		this.damage = damage;
		this.casing = casing;
	}

}
