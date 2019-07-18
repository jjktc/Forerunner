package scope.lwjgl.forerunner.game.entity;

import scope.lwjgl.forerunner.graphics.obj3d.AnimModel;
import scope.lwjgl.forerunner.sprites.Sprite;

public class ItemMagazine extends EntityItem {

	public ItemBullet bullet;
	public int rounds;
	public int maxRounds;
	public AnimModel model;
	public Sprite clip;
	public boolean empty;

	public ItemMagazine(ItemBullet bullet, int rounds, AnimModel model, Sprite clip) {
		this.bullet = bullet;
		this.rounds = rounds;
		maxRounds = rounds;
		this.model = model;
		this.clip = clip;
	}

}
