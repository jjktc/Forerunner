package scope.lwjgl.forerunner.sprites;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import scope.lwjgl.forerunner.graphics.ColorRGB;

public class ExtSprite {
	
	public int ix, iy, width, height;
	public int sheetId;
	public Spritesheet sheet;
	
	public ExtSprite(Spritesheet sheet, int ix, int iy, int width, int height) {
		if(!Sheets.contains(sheet)) {
			Sheets.sheets.add(sheet);
			sheetId = Sheets.sheets.size() - 1;
		} else {
			sheetId = Sheets.getId(sheet);
		}
		this.sheet = sheet;
		this.ix = ix;
		this.iy = iy;
		this.width = width;
		this.height = height;
	}
	
	public ExtSprite(String location, int ix, int iy, int width, int height) {
		if(!Sheets.contains(location)) {
			Sheets.sheets.add(new Spritesheet(location, true));
			sheetId = Sheets.sheets.size() - 1;
		} else {
			sheetId = Sheets.getId(location);
		}
		sheet = Sheets.sheets.get(sheetId);
		this.ix = ix;
		this.iy = iy;
		this.width = width;
		this.height = height;
	}
	
	public static void startRotate(float a, float b, float c, float rotation) {
		glPushMatrix();
		glTranslatef(a, b, c);
		glRotatef(rotation, 0f, 0f, 1f);
		glTranslatef(-a, -b, -c);
	}
	
	public static void endRotate() {
		glPopMatrix();
	}
	
	public static void startMatrix() {
		glPushMatrix();
	}
	
	public static void endMatrix() {
		glPushMatrix();
	}
	
	public void render(int xa, int ya) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
        int y2 = iy + height;

        glBegin(GL_QUADS);
        glTexCoord2f(ix, iy);
        glVertex2f(xa, ya);
        glTexCoord2f(x2, iy);
        glVertex2f(xa + width, ya);
        glTexCoord2f(x2, y2);
        glVertex2f(xa + width, ya + height);
        glTexCoord2f(ix, y2);
        glVertex2f(xa, ya + height);
        glEnd();
        Spritesheet.end();
	}
	
	public void render(int xa, int ya, float scale) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
        int y2 = iy + height;

        glBegin(GL_QUADS);
        glTexCoord2f(ix, iy);
        glVertex2f(xa, ya);
        glTexCoord2f(x2, iy);
        glVertex2f(xa + width * scale, ya);
        glTexCoord2f(x2, y2);
        glVertex2f(xa + width * scale, ya + height * scale);
        glTexCoord2f(ix, y2);
        glVertex2f(xa, ya + height * scale);
        glEnd();
        Spritesheet.end();
	}
	
	public void render(int xa, int ya, float scale, int width, int height) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
        int y2 = iy + height;

        glBegin(GL_QUADS);
        glTexCoord2f(ix, iy);
        glVertex2f(xa, ya);
        glTexCoord2f(x2, iy);
        glVertex2f(xa + width * scale, ya);
        glTexCoord2f(x2, y2);
        glVertex2f(xa + width * scale, ya + height * scale);
        glTexCoord2f(ix, y2);
        glVertex2f(xa, ya + height * scale);
        glEnd();
        Spritesheet.end();
	}
	
	public void render(int xa, int ya, int width, int height) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + this.width;
        int y2 = iy + this.height;

        glBegin(GL_QUADS);
        glTexCoord2f(ix, iy);
        glVertex2f(xa, ya);
        glTexCoord2f(x2, iy);
        glVertex2f(xa + width, ya);
        glTexCoord2f(x2, y2);
        glVertex2f(xa + width, ya + height);
        glTexCoord2f(ix, y2);
        glVertex2f(xa, ya + height);
        glEnd();
        Spritesheet.end();
	}
	
	public void render(int xa, int ya, float scale, ColorRGB color) {
		glColor4f(color.r, color.g, color.b, color.a);
		render(xa, ya, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void render(int xa, int ya, float scale, float opacity) {
		glColor4f(1f, 1f, 1f, opacity);
		render(xa, ya, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void render(int xa, int ya, float scale, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render(xa, ya, scale);
		glColor4f(1f, 1f ,1f, 1f);
	}
	
	public void render(int xa, int ya, int width, int height, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render(xa, ya, width, height);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public void render(int xa, int ya, float scale, int width, int height, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render(xa, ya, scale, width, height);
		glColor4f(1f, 1f, 1f, 1f);
	}
	
	public String toString() {
		String text = "";
		text = "Sprite: " + ix + "," + iy + "," + width + "," + height;
		return text;
	}

}
