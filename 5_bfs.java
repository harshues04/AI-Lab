import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class bfs {

    static final int NUM_NODES = 7;
    static int[][] graph = {
        {0, 1, 1, 0, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 0},
        {1, 1, 0, 1, 0, 0, 0},
        {0, 0, 1, 0, 0, 1, 0},
        {0, 1, 0, 0, 0, 0, 1},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0}
    };

    static String[] nodeNames = {"S", "A", "B", "C", "D", "E", "G"};

    public static void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        System.out.print("  ");
        for (String nodeName : nodeNames) {
            System.out.print(nodeName + " ");
        }
        System.out.println();

        for (int i = 0; i < NUM_NODES; i++) {
            System.out.print(nodeNames[i] + " ");
            for (int j = 0; j < NUM_NODES; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfsSearch(int start, int goal) {
        boolean[] visited = new boolean[NUM_NODES];
        int[] parent = new int[NUM_NODES];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < NUM_NODES; i++) {
            parent[i] = -1;
        }

        visited[start] = true;
        queue.add(start);

        System.out.println("\nBreadth-First Search:");

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == goal) {
                System.out.println("Goal found at node " + nodeNames[goal]);
                printPath(parent, start, goal);
                return;
            }

            for (int i = 0; i < NUM_NODES; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    parent[i] = node;
                }
            }
        }

        System.out.println("Goal not found");
    }

    public static void printPath(int[] parent, int start, int goal) {
        List<String> path = new ArrayList<>();
        int currentNode = goal;

        while (currentNode != -1) {
            path.add(0, nodeNames[currentNode]);
            currentNode = parent[currentNode];
        }

        System.out.println("Path: " + String.join(" -> ", path));
    }

    public static void main(String[] args) {
        int start = 0;  // Starting node (S)
        int goal = 6;   // Goal node (G)

        printAdjacencyMatrix();
        bfsSearch(start, goal);
    }
}