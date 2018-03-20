package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.graphics.ColorRGB;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprites;

public class ClickerText {
	
	public String text;
	public int x, y, width, height;
	public float scale;
	public ColorRGB color, color2;
	public Runnable method;
	public int type = 0;
	public float opacity;
	public boolean blocked;
	
	public ClickerText(String text, int x, int y, float scale, ColorRGB color, Runnable method, boolean blocked) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.color = color;
		color2 = color;
		this.method = method;
		width = Fonts.fontDefault.getWidthByString(text, scale);
		height = Fonts.fontDefault.getHeightByString(text, scale);
		this.blocked = blocked;
	}
	
	public ClickerText(String text, int x, int y, float scale, ColorRGB color, ColorRGB color2, Runnable method, boolean blocked) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.color = color;
		this.color2 = color2;
		this.method = method;
		width = Fonts.fontDefault.getWidthByString(text, scale);
		height = Fonts.fontDefault.getHeightByString(text, scale);
		this.blocked = blocked;
	}
	
	public void update() {
		if(blocked) {
			type = 4;
			opacity = color.a;
		}
		if(type == 0 && !hover()) {
			opacity = color.a;
		}
		if(type == 0 && hover()) {
			opacity = color.a + ((1 - color.a) / 2);
			if(opacity > 1) opacity = 1;
			type = 1;
		}
		if(type == 1 && touched()) {
			opacity = 1;
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
			type = 0;
			method.run();
		}
	}
	
	public void render() {
		switch(type) {
			case 0:
				Fonts.fontDefault.drawString(text, x, y, scale, color.r, color.g, color.b, opacity);
				break;
			case 1:
				Fonts.fontDefault.drawString(text, x, y, scale, color2.r, color2.g, color2.b, opacity);
				Sprites.selectorL.render((int) (x - Sprites.selectorL.width * scale / 2) - 2, (int) ((y + height / 2) - (Sprites.selectorL.height * scale / 2) / 2), scale / 2, 1, 1, 1, opacity);
				Sprites.selectorR.render((int) (x + width + 2), (int) ((y + height / 2) - (Sprites.selectorR.height * scale / 2) / 2), scale / 2, 1, 1, 1, opacity);
				break;
			case 2:
				Fonts.fontDefault.drawString(text, x, y, scale, color2.r, color2.g, color2.b, opacity);
				Sprites.selectorL.render((int) (x - Sprites.selectorL.width * scale / 2) - 2, (int) ((y + height / 2) - (Sprites.selectorL.height * scale / 2) / 2), scale / 2, 1, 1, 1, opacity);
				Sprites.selectorR.render((int) (x + width + 2), (int) ((y + height / 2) - (Sprites.selectorR.height * scale / 2) / 2), scale / 2, 1, 1, 1, opacity);
				break;
			case 4:
				Fonts.fontDefault.drawString(text, x, y, scale, color.r / 8 * 7, color.g / 8 * 2, color.b / 8 * 2, opacity / 8 * 7);
				break;
		}
	}
	
	public void renderSelector() {
		Fonts.fontDefault.drawString(text, x, y, scale, color2.r, color2.g, color2.b, 0.9f);
		Sprites.selectorL.render((int) (x - Sprites.selectorL.width * scale / 2) - 2, (int) ((y + height / 2) - (Sprites.selectorL.height * scale / 2) / 2), scale / 2, 1, 1, 1, 0.9f);
		Sprites.selectorR.render((int) (x + width + 2), (int) ((y + height / 2) - (Sprites.selectorR.height * scale / 2) / 2), scale / 2, 1, 1, 1, 0.9f);
	}
	
	public boolean touched() {
		int x2 = x + width;
		int y2 = y + height;
		if(InputHandler.mouseXPos >= x && InputHandler.mouseYPos >= y && InputHandler.mouseXPos <= x2 && InputHandler.mouseYPos <= y2) {
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
