package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprite;

public class Btn {

	public Sprite regular, hover, clicked;
	public int x, y, width, height;
	public float scale, fontScale;
	public Runnable method;
	public boolean blocked;
	public int type = 0;
	public String text;

	public Btn(Sprite regular, Sprite hover, Sprite clicked, String text, int x, int y, float scale, float fontScale,
			Runnable method, boolean blocked) {
		this.regular = regular;
		this.hover = hover;
		this.clicked = clicked;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.fontScale = fontScale;
		this.method = method;
		this.blocked = blocked;
		width = (int) (regular.width * scale);
		height = (int) (regular.height * scale);
		this.text = text;
	}

	public void update() {
		if (blocked) {
			type = 4;
		}
		if (type == 0 && hover()) {
			type = 1;
		}
		if (type == 1 && touched()) {
			type = 2;
		}
		if (type == 1 && !touched() && !hover()) {
			type = 0;
		}
		if (type == 2 && !touched() && hover()) {
			type = 3;
		}
		if (type == 2 && !touched() && !hover()) {
			type = 0;
		}
		if (type == 3) {
			method.run();
			type = 0;
		}
	}

	public void render() {
		switch (type) {
		case 0:
			regular.render(x, y, scale);
			if (!text.equals(""))
				Fonts.fontDefault.drawString(text,
						(int) (x + (regular.width / 2) * scale)
								- Fonts.fontDefault.getWidthByString(text, fontScale) / 2,
						(int) (y + (regular.height / 2) * scale)
								- Fonts.fontDefault.getHeightByString(text, fontScale) / 2,
						fontScale);
			break;
		case 1:
			hover.render(x, y, scale);
			if (!text.equals(""))
				Fonts.fontDefault.drawString(text,
						(int) (x + (regular.width / 2) * scale)
								- Fonts.fontDefault.getWidthByString(text, fontScale) / 2,
						(int) (y + (regular.height / 2) * scale)
								- Fonts.fontDefault.getHeightByString(text, fontScale) / 2,
						fontScale);
			break;
		case 2:
			clicked.render(x, y, scale);
			if (!text.equals(""))
				Fonts.fontDefault.drawString(text,
						(int) (x + (regular.width / 2) * scale)
								- Fonts.fontDefault.getWidthByString(text, fontScale) / 2,
						(int) (y + (regular.height / 2) * scale)
								- Fonts.fontDefault.getHeightByString(text, fontScale) / 2,
						fontScale);
			break;
		case 3:
			regular.render(x, y, scale);
			if (!text.equals(""))
				Fonts.fontDefault.drawString(text,
						(int) (x + (regular.width / 2) * scale)
								- Fonts.fontDefault.getWidthByString(text, fontScale) / 2,
						(int) (y + (regular.height / 2) * scale)
								- Fonts.fontDefault.getHeightByString(text, fontScale) / 2,
						fontScale);
			break;
		case 4:
			break;
		}
	}

	public boolean touched() {
		if (hover()) {
			return InputHandler.mouseLeftButton;
		}
		return false;
	}

	public boolean hover() {
		int x2 = x + width;
		int y2 = y + height;
		if (InputHandler.mouseXPos >= x && InputHandler.mouseYPos >= y && InputHandler.mouseXPos <= x2
				&& InputHandler.mouseYPos <= y2) {
			return true;
		}
		return false;
	}

}
