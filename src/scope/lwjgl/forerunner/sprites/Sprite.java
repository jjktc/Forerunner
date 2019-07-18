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
import static org.lwjgl.opengl.GL11.glVertex3f;

public class Sprite {

	public int ix, iy, width, height;
	public int sheetId;
	public Spritesheet sheet;
	public String location;

	public Sprite(int ix, int iy, int width, int height) {
		this.ix = ix;
		this.iy = iy;
		this.width = width;
		this.height = height;
	}

	public Sprite(Spritesheet sheet, int ix, int iy, int width, int height) {
		if (!Sheets.contains(sheet)) {
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
		location = sheet.location;
	}

	public Sprite(String location, int ix, int iy, int width, int height) {
		if (!Sheets.contains(location)) {
			Sheets.sheets.add(new Spritesheet(location, false));
			sheetId = Sheets.sheets.size() - 1;
		} else {
			sheetId = Sheets.getId(location);
		}
		sheet = Sheets.sheets.get(sheetId);
		this.ix = ix;
		this.iy = iy;
		this.width = width;
		this.height = height;
		this.location = location;
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

	public void render(int x, int y) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
		int y2 = iy + height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex2f(x, y);
		glTexCoord2f(x2, iy);
		glVertex2f(x + width, y);
		glTexCoord2f(x2, y2);
		glVertex2f(x + width, y + height);
		glTexCoord2f(ix, y2);
		glVertex2f(x, y + height);
		glEnd();
		Spritesheet.end();
	}

	public void render(int x, int y, float scale) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
		int y2 = iy + height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex2f(x, y);
		glTexCoord2f(x2, iy);
		glVertex2f(x + width * scale, y);
		glTexCoord2f(x2, y2);
		glVertex2f(x + width * scale, y + height * scale);
		glTexCoord2f(ix, y2);
		glVertex2f(x, y + height * scale);
		glEnd();
		Spritesheet.end();
	}

	public void render(int x, int y, int width, int height) {
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + this.width;
		int y2 = iy + this.height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex2f(x, y);
		glTexCoord2f(x2, iy);
		glVertex2f(x + width, y);
		glTexCoord2f(x2, y2);
		glVertex2f(x + width, y + height);
		glTexCoord2f(ix, y2);
		glVertex2f(x, y + height);
		glEnd();
		Spritesheet.end();
	}

	public void render(int x, int y, float scale, float opacity) {
		glColor4f(1f, 1f, 1f, opacity);
		render(x, y, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void render(int x, int y, float scale, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render(x, y, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void render(int x, int y, int width, int height, float r, float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render(x, y, width, height);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ) {
		glPushMatrix();
		glRotatef(rotX, 1, 0, 0);
		glRotatef(rotY, 0, 1, 0);
		glRotatef(rotZ, 0, 0, 1);
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
		int y2 = iy + height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex3f(x, y, z);
		glTexCoord2f(x2, iy);
		glVertex3f(x + width, y, z);
		glTexCoord2f(x2, y2);
		glVertex3f(x + width, y + height, z);
		glTexCoord2f(ix, y2);
		glVertex3f(x, y + height, z);
		glEnd();
		Spritesheet.end();
		glRotatef(-rotZ, 0, 0, 1);
		glRotatef(-rotY, 0, 1, 0);
		glRotatef(-rotX, 1, 0, 0);
		glPopMatrix();
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ, float scale) {
		glPushMatrix();
		glRotatef(rotX, 1, 0, 0);
		glRotatef(rotY, 0, 1, 0);
		glRotatef(rotZ, 0, 0, 1);
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + width;
		int y2 = iy + height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex3f(x, y, z);
		glTexCoord2f(x2, iy);
		glVertex3f(x + width * scale, y, z);
		glTexCoord2f(x2, y2);
		glVertex3f(x + width * scale, y + height * scale, z);
		glTexCoord2f(ix, y2);
		glVertex3f(x, y + height * scale, z);
		glEnd();
		Spritesheet.end();
		glRotatef(-rotZ, 0, 0, 1);
		glRotatef(-rotY, 0, 1, 0);
		glRotatef(-rotX, 1, 0, 0);
		glPopMatrix();
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ, int width, int height) {
		glPushMatrix();
		glRotatef(rotX, 1, 0, 0);
		glRotatef(rotY, 0, 1, 0);
		glRotatef(rotZ, 0, 0, 1);
		Spritesheet.start(Sheets.sheets.get(sheetId));
		int x2 = ix + this.width;
		int y2 = iy + this.height;

		glBegin(GL_QUADS);
		glTexCoord2f(ix, iy);
		glVertex3f(x, y, z);
		glTexCoord2f(x2, iy);
		glVertex3f(x + width, y, z);
		glTexCoord2f(x2, y2);
		glVertex3f(x + width, y + height, z);
		glTexCoord2f(ix, y2);
		glVertex3f(x, y + height, z);
		glEnd();
		Spritesheet.end();
		glRotatef(-rotZ, 0, 0, 1);
		glRotatef(-rotY, 0, 1, 0);
		glRotatef(-rotX, 1, 0, 0);
		glPopMatrix();
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ, float scale, float opacity) {
		glColor4f(1f, 1f, 1f, opacity);
		render3d(x, y, z, rotX, rotY, rotZ, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ, float scale, float r, float g,
			float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render3d(x, y, z, rotX, rotY, rotZ, scale);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public void render3d(float x, float y, float z, float rotX, float rotY, float rotZ, int width, int height, float r,
			float g, float b, float opacity) {
		glColor4f(r, g, b, opacity);
		render3d(x, y, z, rotX, rotY, rotZ, width, height);
		glColor4f(1f, 1f, 1f, 1f);
	}

	public String toString() {
		String text = "";
		text = "Sprite: " + location + "," + ix + "," + iy + "," + width + "," + height;
		return text;
	}

}
