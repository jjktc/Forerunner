package scope.lwjgl.forerunner.graphics.model;

import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import scope.lwjgl.forerunner.graphics.Graphics;

public class ObjModel {
	
	public int modelID;
	public ObjData data;
	public Texture texture;
	public static FloatBuffer material;
	
	public ObjModel(String loc) {
		
		Graphics.defineLight();
		
		data = new ObjData(loc);
		try {
			String loc2 = loc.substring(1);
			texture = new TextureLoader().getTexture(loc2.replace(".obj", ".jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		modelID = glGenLists(1);
		glNewList(modelID, GL11.GL_COMPILE);
		GL11.glBegin(GL11.GL_TRIANGLES);
		//int faceCount = data.getFaceCount();
		
		for (int i = 0; i < data.getFaceCount(); i++) {
			for (int v=0;v<3;v++) {
				Tuple3 vert = data.getFace(i).getVertex(v);
				Tuple3 norm = data.getFace(i).getNormal(v);
				Tuple2 tex = data.getFace(i).getTexCoord(v);
				
				glNormal3f(norm.x, norm.y, norm.z);
				glTexCoord2f(tex.x, tex.y);
				glVertex3f(vert.x, vert.y, vert.z);
			}
		}
		
		glEnd();
		glEndList();
	}
	
	public void render(float x, float y, float z, float scale, float xRot, float yRot, float zRot) {
		glScalef(scale, scale, scale);
		if(xRot != 0) glRotatef(xRot, 1, 0, 0);
		if(yRot != 0) glRotatef(yRot, 0, 1, 0);
		if(zRot != 0) glRotatef(zRot, 0, 0, 1);
		glTranslatef(x, y, z);
		texture.bind();
		glCallList(modelID);
	}
	
}
