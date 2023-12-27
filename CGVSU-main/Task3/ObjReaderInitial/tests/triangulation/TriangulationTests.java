package triangulation;

import com.cgvsu.Triangle;
import com.cgvsu.TriangulatedPolygon;
import com.cgvsu.TriangulationModule;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TriangulationTests {
    @Test
    public void test1() {
        var model = new Model();
        var vertices = new ArrayList<Vector3f>();
        vertices.add(new Vector3f(1, 0, 0));
        vertices.add(new Vector3f(1, 1, 0));
        vertices.add(new Vector3f(0, 0, 0));
        vertices.add(new Vector3f(0, 1, 0));
        model.vertices = vertices;

        var p1 = new Polygon();
        var pv = new ArrayList<Integer>();
        pv.add(0);
        pv.add(1);
        pv.add(2);
        pv.add(3);
        p1.setVertexIndices(pv);
        model.polygons.add(p1);
        var triangles = new ArrayList<Triangle>();
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
        var vi = new ArrayList<Integer>();
        vi.add(0);
        vi.add(1);
        var tm = TriangulationModule.triangulate(model);
        var requiredTriangulatedPolygon = new TriangulatedPolygon(triangles, vi);
        var isEqual = true;
        for (int i = 0; i < requiredTriangulatedPolygon.triangles.size(); i++) {
            var t = tm.getTriangulatedPolygons().get(0).triangles.get(i);
            if (t.vertexIndice1 != triangles.get(i).vertexIndice1
                    || t.vertexIndice2 != triangles.get(i).vertexIndice2
                    || t.vertexIndice3 != triangles.get(i).vertexIndice3) {
                isEqual = false;
                break;
            }
        }

        Assertions.assertTrue(isEqual);
    }

    @Test
    public void test2() {
        var model = new Model();
        var vertices = new ArrayList<Vector3f>();
        vertices.add(new Vector3f(1, 0, 0));
        vertices.add(new Vector3f(3, 1, 0));
        vertices.add(new Vector3f(4, 2, 0));
        vertices.add(new Vector3f(3, 4, 0));
        vertices.add(new Vector3f(1, 3, 0));
        model.vertices = vertices;
        var p1 = new Polygon();
        var pv = new ArrayList<Integer>();

        pv.add(0);
        pv.add(1);
        pv.add(2);
        pv.add(3);
        pv.add(4);

        p1.setVertexIndices(pv);
        model.polygons.add(p1);

        var tm = TriangulationModule.triangulate(model);
        var triangles = new ArrayList<Triangle>();
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
        triangles.add(new Triangle(0, 3, 4));

        var vi = new ArrayList<Integer>();
        vi.add(0);
        vi.add(1);
        vi.add(2);
        vi.add(3);
        vi.add(4);
        var requiredTriangulatedPolygon = new TriangulatedPolygon(triangles, vi);
        var isEqual = true;
        for (int i = 0; i < requiredTriangulatedPolygon.triangles.size(); i++) {
            var t = tm.getTriangulatedPolygons().get(0).triangles.get(i);
            if (t.vertexIndice1 != triangles.get(i).vertexIndice1
                    || t.vertexIndice2 != triangles.get(i).vertexIndice2
                    || t.vertexIndice3 != triangles.get(i).vertexIndice3) {
                isEqual = false;
                break;
            }
        }
        Assertions.assertTrue(isEqual);
    }
}
