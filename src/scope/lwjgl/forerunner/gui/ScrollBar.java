package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprite;

public class ScrollBar {
	
	public int x, y, cx, cy, minx, miny, maxx, maxy, width, height;
	public float scale;
	public int type = 0;
	public Sprite sprite, sprite_hover, sprite_click, sprite_bar;
	public boolean blocked;
	int mouse1, mouse2, mouse3;
	boolean grabbed = false;
	
	public ScrollBar(int x, int y, int minx, int miny, int width, int height, float scale, Sprite sprite, Sprite spriteHover, Sprite spriteClick, Sprite spriteBar, boolean block) {
		this.x  = x;
		this.y = y;
		this.cx = x;
		this.cy = y;
		this.minx = minx;
		this.miny = miny;
		this.maxx = x + width;
		this.maxy = y + height;
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.sprite = sprite;
		this.sprite_hover = spriteHover;
		this.sprite_click = spriteClick;
		this.sprite_bar = spriteBar;
		this.blocked = block;
	}
	
	public void update() {
		mouse1 = (int) InputHandler.mouseYPos;
		mouse3 = mouse1 - mouse2;
		if(hover() && type != 2) {
			type = 1;
		}
		if(click() && type == 1) {
			type = 2;
		}
		if(!hover() && type == 2) {
			type = 5;
		}
		if(!click() && type == 2) {
			type = 3;
		}
		if(!InputHandler.mouseLeftButton && type == 5) {
			type = 0;
		}
		if(!hover() && !click() && (type == 1 || type == 3)) {
			type = 0;
		}
		if(type == 3) {
			type = 0;
		}
		if(blocked) {
			type = 4;
		}
		if(type == 2 || type == 5) {
			grabbed = true;
		} else {
			grabbed = false;
		}
		if(grabbed) {
		int newY = cy + mouse3;
		if(newY <= maxy - sprite.height * scale && newY >= miny) {
			cy = newY;
		}
		}
		mouse2 = mouse1;
	}
	
	public void render() {
		sprite_bar.render(x, y, width, height);
		switch(type) {
			case 0:
				sprite.render(cx, cy, scale);
				break;
			case 1:
				sprite_hover.render(cx, cy, scale);
				break;
			case 2:
				sprite_click.render(cx, cy, scale);
				break;
			case 4:
				sprite_click.render(cx, cy, scale);
				break;
			case 5:
				sprite_click.render(cx, cy, scale);
				break;
		}
	}
	
	public boolean hover() {
		if(InputHandler.mouseXPos >= cx && InputHandler.mouseXPos <= cx + sprite.width * scale && InputHandler.mouseYPos >= cy && InputHandler.mouseYPos <= cy + sprite.height * scale) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean click() {
		if(hover() && InputHandler.mouseLeftButton) {
			return true;
		} else {
			return false;
		}
	}

}
