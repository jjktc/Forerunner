package scope.lwjgl.forerunner.graphics.obj3d;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import utility.Face;
import utility.Model;
import utility.OBJLoader;

public class OBJModel {

	public String fileLocation;
	public int displayList;
	public boolean external;
	public float width, height, depth;
	public float fXP, fXN, fYP, fYN, fZP, fZN;

	public OBJModel(String fileLocation, boolean external) {
		this.fileLocation = fileLocation;
		this.external = external;
	}

	public void init() {
		displayList = glGenLists(1);
		Model m = null;
		if (external) {
			m = OBJLoader.loadModelExt(fileLocation);
		} else {
			m = OBJLoader.loadModel(fileLocation);
		}
		for (Face face : m.faces) {
			Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
			Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
			Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
			if (v1.x > fXP)
				fXP = v1.x;
			else if (v1.x < fXN)
				fXN = v1.x;
			if (v2.x > fXP)
				fXP = v2.x;
			else if (v2.x < fXN)
				fXN = v2.x;
			if (v3.x > fXP)
				fXP = v3.x;
			else if (v3.x < fXN)
				fXN = v3.x;
			if (v1.y > fYP)
				fYP = v1.y;
			else if (v1.y < fYN)
				fYN = v1.y;
			if (v2.y > fYP)
				fYP = v2.y;
			else if (v2.y < fYN)
				fYN = v2.y;
			if (v3.y > fYP)
				fYP = v3.y;
			else if (v3.y < fYN)
				fYN = v3.y;
			if (v1.z > fZP)
				fZP = v1.z;
			else if (v1.z < fZN)
				fZN = v1.z;
			if (v2.z > fZP)
				fZP = v2.z;
			else if (v2.z < fZN)
				fZN = v2.z;
			if (v3.z > fZP)
				fZP = v3.z;
			else if (v3.z < fZN)
				fZN = v3.z;
		}
		width = fXP - fXN;
		height = fYP - fYN;
		depth = fZP - fZN;
		glNewList(displayList, GL_COMPILE);
		{
			glBegin(GL_TRIANGLES);
			for (Face face : m.faces) {
				Vector3f n1 = m.normals.get((int) face.normal.x - 1);
				glNormal3f(n1.x, n1.y, n1.z);
				Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
				glVertex3f(v1.x, v1.y, v1.z);
				Vector3f n2 = m.normals.get((int) face.normal.y - 1);
				glNormal3f(n2.x, n2.y, n2.z);
				Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
				glVertex3f(v2.x, v2.y, v2.z);
				Vector3f n3 = m.normals.get((int) face.normal.z - 1);
				glNormal3f(n3.x, n3.y, n3.z);
				Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
				glVertex3f(v3.x, v3.y, v3.z);
			}
			glEnd();
		}
		glEndList();
	}

	public void render() {
		glCallList(displayList);
	}

	public void render(float x, float y, float z) {
		glTranslatef(x, y, z);
		render();
		glTranslatef(-x, -y, -z);
	}

	public void render(float x, float y, float z, float rotX, float rotY, float rotZ) {
		glTranslatef(x, y, z);
		glRotatef(rotX, 1, 0, 0);
		glRotatef(rotY, 0, 1, 0);
		glRotatef(rotZ, 0, 0, 1);
		render();
		glRotatef(-rotZ, 0, 0, 1);
		glRotatef(-rotY, 0, 1, 0);
		glRotatef(-rotX, 1, 0, 0);
		glTranslatef(-x, -y, -z);
	}

	public void clean() {
		glDeleteLists(displayList, 1);
	}

}
