package scope.lwjgl.forerunner.graphics.render3d;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.opengl.GL11;

import scope.lwjgl.forerunner.sprites.Sheets;
import scope.lwjgl.forerunner.sprites.Sprite;
import scope.lwjgl.forerunner.sprites.Spritesheet;

public class TridemDisplay {
	
	public float x, y, z, rotX, rotY, rotZ, width, height;
	public int pixelX, pixelY;
	public float pixelWidth, pixelHeight;
	public Runnable updateDisplay, renderDisplay;
	
	public TridemDisplay(float x, float y, float z, float rotX, float rotY, float rotZ, float width, float height, int pixelX, int pixelY, Runnable updateDisplay, Runnable renderDisplay) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.width = width;
		this.height = height;
		this.pixelX = pixelX;
		this.pixelY = pixelY;
		this.updateDisplay = updateDisplay;
		this.renderDisplay = renderDisplay;
		pixelWidth = width / pixelX;
		pixelHeight = height / pixelY;
	}
	
	public void update() {
		updateDisplay.run();
	}
	
	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(rotX, 1, 0, 0);
		GL11.glRotatef(rotY, 0, 1, 0);
		GL11.glRotatef(rotZ, 0, 0, 1);
		renderDisplay.run();
		GL11.glRotatef(-rotZ, 0, 0, 1);
		GL11.glRotatef(-rotY, 0, 1, 0);
		GL11.glRotatef(-rotX, 1, 0, 0);
		GL11.glTranslatef(-x, -y, -z);
		GL11.glPopMatrix();
	}
	
	public static void renderSprite(Sprite sprite, TridemDisplay display, int x, int y, float scale) {
		Spritesheet.start(Sheets.sheets.get(sprite.sheetId));
		int x2 = sprite.ix + sprite.width;
        int y2 = sprite.iy + sprite.height;
        
        glBegin(GL_QUADS);
        glTexCoord2f(sprite.ix, sprite.iy);
        glVertex3f((x * display.pixelWidth), (y + sprite.height * scale) * display.pixelHeight, 0);
        glTexCoord2f(x2, sprite.iy);
        glVertex3f((x + sprite.width * scale) * display.pixelWidth, (y + sprite.height * scale) * display.pixelHeight, 0);
        glTexCoord2f(x2, y2);
        glVertex3f((x + sprite.width * scale) * display.pixelWidth, (y * display.pixelHeight), 0);
        glTexCoord2f(sprite.ix, y2);
        glVertex3f((x * display.pixelWidth), (y * display.pixelHeight), 0);
        glEnd();
        Spritesheet.end();
	}
	
	public static void renderSprite(Sprite sprite, TridemDisplay display, int x, int y, int width, int height) {
		Spritesheet.start(Sheets.sheets.get(sprite.sheetId));
		int x2 = sprite.ix + sprite.width;
        int y2 = sprite.iy + sprite.height;
        
        glBegin(GL_QUADS);
        glTexCoord2f(sprite.ix, sprite.iy);
        glVertex3f((x * display.pixelWidth), (y + height) * display.pixelHeight, 0);
        glTexCoord2f(x2, sprite.iy);
        glVertex3f((x + width) * display.pixelWidth, (y + height) * display.pixelHeight, 0);
        glTexCoord2f(x2, y2);
        glVertex3f((x + width) * display.pixelWidth, (y * display.pixelHeight), 0);
        glTexCoord2f(sprite.ix, y2);
        glVertex3f((x * display.pixelWidth), (y * display.pixelHeight), 0);
        glEnd();
        Spritesheet.end();
	}
	
	public static void renderSprite(Sprite sprite, TridemDisplay display, int x, int y, float scale, float r, float g, float b, float opacity) {
		GL11.glColor4f(r, g, b, opacity);
		renderSprite(sprite, display, x, y, scale);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	public static void renderSprite(Sprite sprite, TridemDisplay display, int x, int y, int width, int height, float r, float g, float b, float opacity) {
		GL11.glColor4f(r, g, b, opacity);
		renderSprite(sprite, display, x, y, width, height);
		GL11.glColor4f(1, 1, 1, 1);
	}

}
