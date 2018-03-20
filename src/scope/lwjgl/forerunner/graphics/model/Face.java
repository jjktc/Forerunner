package scope.lwjgl.forerunner.graphics.model;

/**
 * A single face defined on a model. This is a sreies of points
 * each with a position, normal and texture coordinate
 * 
 * @author Kevin Glass
 */
class Face {
	public Tuple3[] verts;
	public Tuple3[] norms;
	public Tuple2[] texs;
	public int points;
	
	public Face(int points) {
		verts = new Tuple3[points];
		norms = new Tuple3[points];
		texs = new Tuple2[points];
	}
	
	public void addPoint(Tuple3 vert, Tuple2 tex, Tuple3 norm) {
		verts[points] = vert;
		texs[points] = tex;
		norms[points] = norm;
		
		points++;
	}
	
	public Tuple3 getVertex(int p) {
		return verts[p];
	}

	public Tuple2 getTexCoord(int p) {
		return texs[p];
	}

	public Tuple3 getNormal(int p) {
		return norms[p];
	}
}