package scope.lwjgl.forerunner.graphics.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ObjData {
	
	@SuppressWarnings("rawtypes") public ArrayList verts = new ArrayList();
	@SuppressWarnings("rawtypes") public ArrayList normals = new ArrayList();
	@SuppressWarnings("rawtypes") public ArrayList texCoords = new ArrayList();
	@SuppressWarnings("rawtypes") public ArrayList faces = new ArrayList();
	
	@SuppressWarnings("unchecked")
	public ObjData(String loc) {
		try {
			InputStream in = ObjData.class.getResourceAsStream(loc);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			while (reader.ready()) {
				String line = reader.readLine();
				
				if (line == null) {
					break;
				}
				
				if (line.startsWith("vn")) {
					Tuple3 normal = readTuple3(line);
					normals.add(normal);
				} else if (line.startsWith("vt")) {
					Tuple2 tex = readTuple2(line);
					texCoords.add(tex);
				} else if (line.startsWith("v")) {
					Tuple3 vert = readTuple3(line);
					verts.add(vert);
				} else if (line.startsWith("f")) {
					Face face = readFace(line);
					faces.add(face);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Tuple2 readTuple2(String line) throws IOException {
		StringTokenizer tokens = new StringTokenizer(line, " ");
		
		tokens.nextToken();
		
		try {
			float x = Float.parseFloat(tokens.nextToken());
			float y = Float.parseFloat(tokens.nextToken());
			
			return new Tuple2(x,y);
		} catch (NumberFormatException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	public static Tuple3 readTuple3(String line) throws IOException {
		StringTokenizer tokens = new StringTokenizer(line, " ");
		
		tokens.nextToken();
		
		try {
			float x = Float.parseFloat(tokens.nextToken());
			float y = Float.parseFloat(tokens.nextToken());
			float z = Float.parseFloat(tokens.nextToken());
			
			return new Tuple3(x,y,z);
		} catch (NumberFormatException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	public int getFaceCount() {
		return faces.size();
	}
	
	public Face getFace(int index) {
		return (Face) faces.get(index);
	}
	
	public Face readFace(String line) throws IOException {
		StringTokenizer points = new StringTokenizer(line, " ");
		
		points.nextToken();
		int faceCount = points.countTokens();
		
		if (faceCount != 3) {
			throw new RuntimeException("Only triangles are supported. Face count:" + faceCount);
		}
		
		Face face = new Face(faceCount);
		
		try {
			for (int i=0;i<faceCount;i++) {
				StringTokenizer parts = new StringTokenizer(points.nextToken(), "/");
				
				int v = Integer.parseInt(parts.nextToken());
				int t = Integer.parseInt(parts.nextToken());
				int n = Integer.parseInt(parts.nextToken());
				
				face.addPoint((Tuple3) verts.get(v-1), (Tuple2) texCoords.get(t-1), (Tuple3) normals.get(n-1));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return face;
	}
}
