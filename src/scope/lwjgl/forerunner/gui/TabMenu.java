package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.Main;
import scope.lwjgl.forerunner.font.Fonts;

public class TabMenu {
	
	public int tabSX, tabSY;
	public float tabScale;
	public Tab[] tabs;
	public Runnable rupdate, rrender;
	public boolean center;
	
	public TabMenu(int tabSX, int tabSY, float tabScale, Tab[] tabs, Runnable rupdate, Runnable rrender, boolean center) {
		this.tabSX = tabSX;
		this.tabSY = tabSY;
		this.tabScale = tabScale;
		this.tabs = tabs;
		for(int i = 0; i < tabs.length; i++) {
			int nx = (int) (tabs[i].sprite.width * tabScale) + tabSX;
			int ny = tabSY;
			if(center) {
				nx = (int) ((Main.width - (tabs[i].sprite.width * tabScale * (tabs.length - (i * 2)))) / 2);
			}
			this.tabs[i].x = nx;
			this.tabs[i].y = ny;
			this.tabs[i].scale = tabScale;
			int tx = (int) (nx + (tabs[i].sprite.width - Fonts.fontDefault.getWidthByString(tabs[i].title, tabScale)) / 2);
			int ty = (int) (ny + (tabs[i].sprite.height - Fonts.fontDefault.colH * tabScale) / 2);
			this.tabs[i].textX = tx;
			this.tabs[i].textY = ty;
			int ix = (int) (nx + (tabs[i].sprite.width - 32) / 2);
			int iy = (int) (ny + (tabs[i].sprite.height - 32) / 2);
			this.tabs[i].iconX = ix;
			this.tabs[i].iconY = iy;
		}
		this.rupdate = rupdate;
		this.rrender = rrender;
		this.center = center;
	}
	
	public void update() {
		rupdate.run();
		for(int i = 0; i < tabs.length; i++) {
			tabs[i].update(tabs);
		}
	}
	
	public void render() {
		for(int i = 0; i < tabs.length; i++) {
			tabs[i].renderTab();
		}
		rrender.run();
		for(int i = 0; i < tabs.length; i++) {
			if(tabs[i].open) tabs[i].renderContent();
		}
	}
	
}
