package com.cgvsu;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class TriangulationModule {
    public static TriangulatedModel triangulate(Model model) {
        var triangulatedPolygons = new ArrayList<TriangulatedPolygon>();

        for (int i = 0; i < model.polygons.size(); i++) {

            var triangles = new ArrayList<Triangle>();

            var vertices = model.polygons.get(i).getVertexIndices();

            if (vertices.size() < 3) throw new IllegalArgumentException("Необходимо минимум 3 точки для триангуляции");

            for (int j = 1; j < vertices.size() - 1; j++) {
                triangles.add(new Triangle(vertices.get(0), j, j+1));
            }
            triangulatedPolygons.add(new TriangulatedPolygon(triangles, vertices));
        }

        return new TriangulatedModel(model, triangulatedPolygons);

    }
}
