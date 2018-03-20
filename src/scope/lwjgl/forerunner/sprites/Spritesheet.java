package scope.lwjgl.forerunner.sprites;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import utility.ImagingTools;

public class Spritesheet {
	
	public int sheet;
	public String location;
	
	public Spritesheet(String location, boolean external) {
		if(external) {
			sheet = ImagingTools.glLoadTextureLinearExternal(location);
		} else {
			sheet = ImagingTools.glLoadTextureLinear(location);
		}
		this.location = location;
	}
	
	public static void start(Spritesheet sheet) {
		glEnable(GL_TEXTURE_RECTANGLE_ARB);
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, sheet.sheet);
	}
	
	public static void end() {
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
		glDisable(GL_TEXTURE_RECTANGLE_ARB);
	}
	
	public void start() {
		glEnable(GL_TEXTURE_RECTANGLE_ARB);
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, sheet);
	}

}
