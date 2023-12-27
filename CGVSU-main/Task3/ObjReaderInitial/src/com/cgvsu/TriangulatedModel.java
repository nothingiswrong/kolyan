package com.cgvsu;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;

import java.util.ArrayList;
import java.util.List;

public class TriangulatedModel extends Model {
    private List<TriangulatedPolygon> triangulatedPolygons;
    public List<Vector3f> vertices;
    public TriangulatedModel(List<Vector3f> vertices, List<TriangulatedPolygon> triangulatedPolygons) {
        this.vertices = vertices;
        this.triangulatedPolygons = triangulatedPolygons;
    }

    public TriangulatedModel(Model model, List<TriangulatedPolygon> triangulatedPolygons) {
            vertices = model.vertices;
            textureVertices = model.textureVertices;
            normals = model.normals;
            this.triangulatedPolygons = triangulatedPolygons;
    }

    public List<TriangulatedPolygon> getTriangulatedPolygons() {
        return triangulatedPolygons;
    }


}
