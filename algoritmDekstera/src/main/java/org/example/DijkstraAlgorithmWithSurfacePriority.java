package org.example;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

import static java.lang.System.*;

public class DijkstraAlgorithmWithSurfacePriority {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};


    public static int shortestPath(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distances = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Node[][] prev = new Node[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(distances[i], INF);
        }

        distances[startRow][startCol] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new Node(startRow, startCol, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int row = node.row;
            int col = node.col;

            if (row == endRow && col == endCol) {
                printPath(prev, endRow, endCol);
                return distances[row][col];
            }

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]) {
                    int surfaceDiff = Math.abs(grid[newRow][newCol] - grid[row][col]);
                    int newDistance = distances[row][col] + surfaceDiff;

                    if (newDistance < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newDistance;
                        pq.offer(new Node(newRow, newCol, newDistance));
                        prev[newRow][newCol] = node;
                    }
                }
            }
        }
        return -1;
    }

    public static void printPath(Node[][] prev, int endRow, int endCol) {
        Node node = prev[endRow][endCol];
        Stack<Node> stack = new Stack<>();

        while (node != null) {
            stack.push(node);
            node = prev[node.row][node.col];
        }

        out.print("Path: ");

        while (!stack.isEmpty()) {
            Node pathNode = stack.pop();
            out.print("(" + pathNode.row + ", " + pathNode.col + ") ");
        }

        out.println();
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,  3,  4, 13},
                {99, 99, 99, 1},
                {99, 99, 99, 3},
                {2,  1,  3, 2}
        };

        int startRow = 0;
        int startCol = 0;
        int endRow = 3;
        int endCol = 3;

        int shortestPath = shortestPath(grid, startRow, startCol, endRow, endCol);

        Node[][] prev = new Node[grid.length][grid[0].length];

        out.println("Из начальных координат (" + startRow + ", " + startCol + ") в конечные (" + endRow + ", " + endCol + ") расстояние " + shortestPath);

    }


}

