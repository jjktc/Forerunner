package scope.lwjgl.forerunner.font;

import scope.lwjgl.forerunner.sprites.Sprite;
import scope.lwjgl.forerunner.sprites.Spritesheet;


public class Char {
	
	public int x, y, width, height;
	public Sprite sprite;
	public Spritesheet sheet;
	
	public Char(Spritesheet sheet, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		sprite = new Sprite(sheet, x, y, width, height);
	}
	
	public void render(int x, int y, float scale) {
		sprite.render(x, y, scale);
	}

}
