package vsu.kg12.karasev_a_e.model;

import vsu.kg12.karasev_a_e.Vector2f;
import vsu.kg12.karasev_a_e.Vector3f;

import java.util.ArrayList;

public class Model {
	public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
	public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
	public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
	public ArrayList<Polygon> polygons = new ArrayList<Polygon>();
}