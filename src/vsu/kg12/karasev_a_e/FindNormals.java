package vsu.kg12.karasev_a_e;

import vsu.kg12.karasev_a_e.math.Vector3f;
import vsu.kg12.karasev_a_e.model.Model;
import vsu.kg12.karasev_a_e.model.Polygon;

import java.util.*;

public class FindNormals {
    /*public static ArrayList<Vector3f> findNormals(Model m) {
        List<Polygon> polygons = m.polygons;
        List<Vector3f> vertices = m.vertices;

        ArrayList<Vector3f> temporaryNormals = new ArrayList<>();
        ArrayList<Vector3f> normals = new ArrayList<>();

        for (Polygon p : polygons) {
            temporaryNormals.add(FindNormals.findPolygonsNormals(vertices.get(p.getVertexIndices().get(0)),
                    vertices.get(p.getVertexIndices().get(1)), vertices.get(p.getVertexIndices().get(2))));
        }

        Map<Integer, Set<Vector3f>> vertexPolygonsMap = new HashMap<>();
        for (int j = 0; j < polygons.size(); j++) {
            List<Integer> vertexIndices = polygons.get(j).getVertexIndices();
            Vector3f vec = temporaryNormals.get(j);
            for (Integer index : vertexIndices) {
                vertexPolygonsMap.computeIfAbsent(index, k -> new HashSet<>()).add(vec);
            }
        }

        for (int i = 0; i < vertices.size(); i++) {
            normals.add(findVertexNormals(vertexPolygonsMap.get(i)));
        }

        return normals;
    }*/
    public static void findNormals(Model model) {
        List<Polygon> polygons = model.polygons;
        List<Vector3f> vertices = model.vertices;

        ArrayList<Vector3f> polygonNormals = new ArrayList<>();
        for (Polygon polygon : polygons) {
            Vector3f normal = findPolygonsNormals(
                    vertices.get(polygon.getVertexIndices().get(0)),
                    vertices.get(polygon.getVertexIndices().get(1)),
                    vertices.get(polygon.getVertexIndices().get(2))
            );
            polygonNormals.add(normal);
        }

        Map<Integer, List<Vector3f>> vertexNormalsMap = new HashMap<>();
        for (int i = 0; i < polygons.size(); i++) {
            List<Integer> vertexIndices = polygons.get(i).getVertexIndices();
            Vector3f polygonNormal = polygonNormals.get(i);

            for (Integer index : vertexIndices) {
                vertexNormalsMap.computeIfAbsent(index, k -> new ArrayList<>()).add(polygonNormal);
            }
        }

        ArrayList<Vector3f> vertexNormals = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            List<Vector3f> associatedNormals = vertexNormalsMap.getOrDefault(i, Collections.emptyList());
            vertexNormals.add(findVertexNormal(associatedNormals));
        }

        model.normals = vertexNormals;
        for (Polygon polygon : polygons) {
            List<Integer> vertexIndices = polygon.getVertexIndices();
            ArrayList<Integer> normalIndices = new ArrayList<>(vertexIndices);
            polygon.setNormalIndices(normalIndices);
        }
    }

    public static Vector3f findPolygonsNormals(Vector3f... vs) {
        Vector3f v1 = Vector3f.subtraction(vs[0], vs[1]);
        Vector3f v2 = Vector3f.subtraction(vs[0], vs[2]);

        Vector3f normal = cross(v1, v2);
        if (determinant(v1, v2, normal) < 0) {
            normal = cross(v2, v1);
        }

        return normalize(normal);
    }

    private static Vector3f findVertexNormal(List<Vector3f> normals) {
        if (normals.isEmpty()) {
            return new Vector3f(0, 0, 0);
        }

        Vector3f summedNormal = new Vector3f(0, 0, 0);
        for (Vector3f normal : normals) {
            summedNormal.x += normal.x;
            summedNormal.y += normal.y;
            summedNormal.z += normal.z;
        }
        return normalize(summedNormal);
    }

    public static double determinant(Vector3f a, Vector3f b, Vector3f c) {
        return a.x * (b.y * c.z) - a.y * (b.x * c.z - c.x * b.z) + a.z * (b.x * c.y - c.x * b.y);
    }


    public static float magnitude(Vector3f v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }


    public static Vector3f normalize(Vector3f v) {
        float mag = magnitude(v);
        return new Vector3f(v.x / mag, v.y / mag, v.z / mag);
    }


    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v2.y * v1.z - v2.z * v1.y,
                v2.z * v1.x - v2.x * v1.z,
                v2.x * v1.y - v2.y * v1.x
        );
    }
}
