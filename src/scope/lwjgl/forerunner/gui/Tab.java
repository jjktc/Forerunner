package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.font.Fonts;
import scope.lwjgl.forerunner.input.InputHandler;
import scope.lwjgl.forerunner.sprites.Sprite;

public class Tab {
	
	public boolean open = false;
	public String title;
	public Sprite icon, sprite, sprite_hover, sprite_clicked;
	public Runnable rupdate, rrender;
	public int x, y;
	public float scale;
	public int type = 0;
	int textX, textY, iconX, iconY;
	
	public Tab(boolean open, String title, Sprite icon, Sprite sprite, Sprite sprite_hover, Sprite sprite_clicked, Runnable rupdate, Runnable rrender) {
		this.open = open;
		this.title = title;
		this.icon = icon;
		this.sprite = sprite;
		this.sprite_hover = sprite_hover;
		this.sprite_clicked = sprite_clicked;
		this.rupdate = rupdate;
		this.rrender = rrender;
	}
	
	public void update(Tab[] tabs) {
		if(!open && type == 4) type = 0;
		if(open) type = 4;
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
			for(int i = 0; i < tabs.length; i++) {
				tabs[i].open = false;
			}
			open = true;
			type = 4;
		}
		rupdate.run();
	}
	
	public void renderTab() {
		switch(type) {
			case 0:
				sprite.render(x, y, scale);
				break;
			case 1:
				sprite_hover.render(x, y, scale);
				break;
			case 2:
				sprite_clicked.render(x, y, scale);
				break;
			case 3:
				sprite.render(x, y, scale);
				break;
			case 4:
				sprite_clicked.render(x, y, scale);
				break;
		}
		if(icon != null) {
			icon.render(iconX, iconY, 32, 32);
		} else {
			Fonts.fontDefault.drawString(title, textX, textY, scale);
		}
	}
	
	public void renderContent() {
		rrender.run();
	}
	
	public boolean touched() {
		if(hover()) {
			return InputHandler.mouseLeftButton;
		}
		return false;
	}
	
	public boolean hover() {
		int x2 = (int) (x + sprite.width * scale);
		int y2 = (int) (y + sprite.height * scale);
		if(InputHandler.mouseXPos >= x && InputHandler.mouseYPos >= y && InputHandler.mouseXPos <= x2 && InputHandler.mouseYPos <= y2) {
			return true;
		}
		return false;
	}

}
