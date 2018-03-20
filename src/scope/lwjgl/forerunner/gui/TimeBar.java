package scope.lwjgl.forerunner.gui;

import scope.lwjgl.forerunner.Time;
import scope.lwjgl.forerunner.sprites.Sprite;

public class TimeBar {
	
	public Sprite bar, progressSection;
	public int x, y, xS, yS, mWidth, width, height, time, sTime, cTime;
	public float scale, scaleS, rate;
	public boolean active;
	public Runnable method;
	
	public TimeBar(Sprite bar, Sprite progressSection, int x, int y, float scale, int xS, int yS, float scaleS, int width, int time, int startTime, float rate, boolean active, Runnable method) {
		this.bar = bar;
		this.progressSection = progressSection;
		this.x = x;
		this.y = y;
		this.xS = xS;
		this.yS = yS;
		this.scale = scale;
		this.scaleS =  scaleS;
		this.time = time;
		sTime = startTime;
		cTime = sTime;
		this.rate = rate;
		this.active = active;
		this.method = method;
		mWidth = width;
		height = (int) (progressSection.height * scaleS);
	}
	
	public void update() {
		if(active) {
			int anim = Time.anim;
			cTime += (int) (anim * rate);
			width = (cTime / time) * mWidth;
			if(cTime >= time) {
				method.run();
			}
		}
	}
	
	public void render() {
		if(active) {
			bar.render(x, y, scale);
			progressSection.render(xS, yS, width, height);
		}
	}

}
