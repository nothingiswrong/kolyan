package com.cgvsu;

import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class TriangulatedPolygon extends Polygon {
   public List<Triangle> triangles;
   public TriangulatedPolygon(List<Triangle> triangles, ArrayList<Integer> verticeIndexes) {
      this.triangles = triangles;
      this.vertexIndices = verticeIndexes;
   }
}
