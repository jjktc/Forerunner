package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprite;
import scope.lwjgl.forerunner.sprites.Sprites;

public class CubeBtn extends Btn {
	
	public int anim;
	public int type = 0;
	public int type2 = 0;

	public CubeBtn(String text, int x, int y, float scale) {
		super(Sprites.cube_btn, Sprites.cube_btn, Sprites.cube_btn, text, x, y, scale, 2 * scale, new Runnable() {
			public void run() {
				
			}
		}, false);
		width = (int) (Sprites.cube_btn.width * scale);
		height = (int) (Sprites.cube_btn.height * scale);
		type2 = 0;
	}
	
	public CubeBtn(Sprite sprite, int x, int y, float scale, float fontScale, Runnable method) {
		super(sprite, Sprites.cube_btn, Sprites.cube_btn, "", x, y, scale, fontScale, method, false);
		width = (int) (Sprites.cube_btn.width * scale);
		height = (int) (Sprites.cube_btn.height * scale);
		type2 = 1;
		text = "";
	}
	
	public CubeBtn(Sprite sprite, Sprite sprite2, int x, int y, float scale) {
		super(sprite, sprite2, sprite, " ", x, y, scale, scale, new Runnable() {
			public void run() {
				
			}
		}, false);
		type2 = 2;
	}
	
	public void update() {
		if(type2 == 0) {
			anim += Time.anim;
			if(anim >= 500) {
				if(type >= 2) {
					type = 0;
				} else {
					type++;
				}
				anim -= 500;
			}
		} else if(type2 == 2) {
			anim += Time.anim;
			if(anim >= 500) {
				if(type >= 1) {
					type = 0;
				} else {
					type++;
				}
				anim -= 500;
			}
		} else {
			if(type == 0 && hover()) {
				type = 1;
			}
			if(type == 1 && touched()) {
				type = 2;
			}
			if(type == 1 && !touched() && !hover()) {
				type = 0;
			}
			if(type == 2 && !touched() && hover()) {
				type = 3;
			}
			if(type == 2 && !touched() && !hover()) {
				type = 0;
			}
			if(type == 3) {
				method.run();
				type = 0;
			}
		}
	}
	
	public void render() {
		if(type2 == 0) {
			switch(type) {
				case 0:
					Sprites.cube_btn.render(x, y, scale);
					Fonts.fontDefault.drawString(text, (int) (x + (48 / 2) * scale) - Fonts.fontDefault.getWidthByString(text, fontScale) / 2, (int) (y + (48 / 2) * scale) - Fonts.fontDefault.getHeightByString(text, fontScale) / 2, fontScale, 0, 0, 0, 1f);
					break;
				case 1:
					Sprites.cube_btn2.render(x, y, scale);
					Fonts.fontDefault.drawString(text, (int) (8 * scale + (x + (48 / 2) * scale) - Fonts.fontDefault.getWidthByString(text, fontScale) / 2), (int) (8 * scale + (y + (48 / 2) * scale) - Fonts.fontDefault.getHeightByString(text, fontScale) / 2), fontScale, 0, 0, 0, 1f);
					break;
				case 2:
					Sprites.cube_btn3.render(x, y, scale);
					Fonts.fontDefault.drawString(text, (int) (12 * scale +  (x + (48 / 2) * scale) - Fonts.fontDefault.getWidthByString(text, fontScale) / 2), (int) (12 * scale + (y + (48 / 2) * scale) - Fonts.fontDefault.getHeightByString(text, fontScale) / 2), fontScale, 0, 0, 0, 1f);
					break;
			}
		} else if(type2 == 2) {
			switch(type) {
				case 0:
					regular.render(x, y, scale);
					break;
				case 1:
					hover.render(x, y, scale);
					break;
			}
		} else {
			switch(type) {
				case 0:
					Sprites.cube_btn.render(x, y, scale);
					regular.render((int) ((x + (48 / 2) * scale) - regular.width * fontScale / 2), (int) ((y + (48 / 2) * scale) - regular.height * fontScale / 2), fontScale);
					break;
				case 1:
					Sprites.cube_btn2.render(x, y, scale);
					regular.render((int) (8 * scale + (x + (48 / 2) * scale) - regular.width * fontScale / 2), (int) (8 * scale + (y + (48 / 2) * scale) - regular.height * fontScale / 2), fontScale);
					break;
				case 2:
					Sprites.cube_btn3.render(x, y, scale);
					regular.render((int) (12 * scale + (x + (48 / 2) * scale) - regular.width * fontScale / 2), (int) (12 * scale + (y + (48 / 2) * scale) - regular.height * fontScale / 2), fontScale);
					break;
			}
		}
	}
	
	public boolean touched() {
		if(hover()) {
			return InputHandler.mouseLeftButton;
		}
		return false;
	}
	
	public boolean hover() {
		int x2 = x + width;
		int y2 = y + height;
		if(InputHandler.mouseXPos >= x && InputHandler.mouseYPos >= y && InputHandler.mouseXPos <= x2 && InputHandler.mouseYPos <= y2) {
			return true;
		}
		return false;
	}

}
