import java.util.*;

class Prims {

    private int len;
    private int[] parent;
    private boolean[] visited;
    private int[] distance;
    private int[][] graph;

    public Prims(int[][] graph) {
        this.len = graph.length;
        this.parent = new int[len];
        this.visited = new boolean[len];
        this.distance = new int[len];
        this.graph = graph;
    }

    public void primsAlgo() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        distance[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < len - 1; i++) {
            int u = minDistance();
            visited[u] = true;

            for (int v = 0; v < len; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < distance[v]) {
                    parent[v] = u;
                    distance[v] = graph[u][v];
                }
            }
        }

        display();
    }

    private void display() {
        System.out.println("ORDER");
        for (int i = 1; i < len; i++) {
            System.out.println(parent[i]+1 + " - " + (i+1));
        }
    }

    private int minDistance() {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < len; v++) {
            if (!visited[v] && distance[v] < min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 5, 6, 0},
                {5, 0, 9, 0},
                {6, 9, 0, 4},
                {0, 0, 0, 4},
        };

        Prims mst = new Prims(graph);
        mst.primsAlgo();
    }
}
