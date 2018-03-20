package scope.lwjgl.forerunner.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import scope.lwjgl.forerunner.Main;

public class ScreenShot {
	
	public static void takeScreenShot(String name, String format) {
		GL11.glReadBuffer(GL11.GL_FRONT);
		int width = Display.getDisplayMode().getWidth();
		int height= Display.getDisplayMode().getHeight();
		int bpp = 4;
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_BYTE, buffer);
		File file = new File(name);
		BufferedImage image = new BufferedImage(Main.width, Main.height, BufferedImage.TYPE_INT_RGB);
		for(int x = 0; x < Main.width; x++) {
			for(int y = 0; y < Main.height; y++) {
				int i = (x + (Main.width * y)) * bpp;
				int r = buffer.get(i) & 0xFF;
				int g = buffer.get(i + 1) & 0xFF;
				int b = buffer.get(i + 2) & 0xFF;
				image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
			}
		}
		try {
			boolean success = ImageIO.write(image, format, file);
			if(success) {
				System.out.println("Screenshot: " + name + " taken");
			} else {
				System.out.println("Screenshot: " + name + " not taken");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
