package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprite;

public class CheckBox {
	
	public int x, y, width, height;
	public float scale;
	public boolean def, selected;
	public String text;
	public Sprite checkbox, checkbox_hover, check;
	public int type = 0;
	public Runnable method;
	
	public CheckBox(int x, int y, boolean def, String text, Sprite checkbox, Sprite checkbox_hover, Sprite check, float scale, Runnable method) {
		this.x = x;
		this.y = y;
		width = (int) (Fonts.fontDefault.getWidthByString(text, scale) + checkbox.width * scale + scale);
		height = (int) (checkbox.height * scale);
		this.def = def;
		selected = def;
		this.text = text;
		this.checkbox = checkbox;
		this.checkbox_hover = checkbox_hover;
		this.check = check;
		this.scale = scale;
		this.method = method;
	}
	
	public void update() {
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
			selected = !selected;
			method.run();
			type = 0;
		}
	}
	
	public void render() {
		switch(type) {
			case 0:
				checkbox.render(x, y, scale);
				break;
			case 1:
				checkbox_hover.render(x, y, scale);
				break;
			case 2:
				checkbox_hover.render(x, y, scale);
				break;
			case 3:
				checkbox.render(x, y, scale);
				break;
		}
		if(selected) {
			check.render(x, y, scale);
		}
		Fonts.fontDefault.drawString(text, (int) (x + scale + checkbox.width * scale), (int) (y + (checkbox.height * scale - Fonts.fontDefault.colH * scale) / 2), scale);
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
