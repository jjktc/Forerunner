package scope.lwjgl.forerunner.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

public class GUI_Icons {
	
	public static BufferedImage frame_icon_image, taskbar_icon_image;
	public static ByteBuffer[] list = new ByteBuffer[2];
	
	public static IntBuffer createIntBuffer(Image img) {

		int len=img.getHeight(null)*img.getWidth(null);
	    ByteBuffer temp=ByteBuffer.allocateDirect(len<<2);;
	    temp.order(ByteOrder.LITTLE_ENDIAN);
	
	    int[] pixels=new int[len];
	
	    PixelGrabber pg=new PixelGrabber(img, 0, 0, img.getWidth(null), img.getHeight(null), pixels, 0, img.getWidth(null));
	
	    try {
	    	pg.grabPixels();
	    } catch (InterruptedException e) {
	    	System.err.println(e.getMessage());
	    }
	
		for (int i=0; i<len; i++) {
			int pos=i<<2;
			int texel=pixels[i];
			if (texel!=0) {
				texel|=0xff000000;
			}
			temp.putInt(pos, texel);
		}
		return temp.asIntBuffer();
	}
	
	public static ByteBuffer convertToByteBuffer(BufferedImage image) {
		byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
		int counter = 0;
		for (int i = 0; i < image.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++)
			{
				int colorSpace = image.getRGB(j, i);
				buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
				buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
				buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
				buffer[counter + 3] = (byte) (colorSpace >> 24);
				counter += 4;
			}
		return ByteBuffer.wrap(buffer);
	}
	
	
	public static void start() {
		try {
			frame_icon_image = ImageIO.read(GUI_Icons.class.getResourceAsStream("/favicon16.png"));
			taskbar_icon_image = ImageIO.read(GUI_Icons.class.getResourceAsStream("/favicon32.png"));
			list[0] = convertToByteBuffer(frame_icon_image);
			list[1] = convertToByteBuffer(taskbar_icon_image);
			Display.setIcon(GUI_Icons.list);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
