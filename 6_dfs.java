import java.util.ArrayList;
import java.util.List;

public class dfs {

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

    public static void dfsSearch(int start, int goal) {
        boolean[] visited = new boolean[NUM_NODES];
        int[] parent = new int[NUM_NODES];

        for (int i = 0; i < NUM_NODES; i++) {
            parent[i] = -1;
        }

        System.out.println("\nDepth-First Search:");

        if (dfsExplore(start, goal, visited, parent)) {
            System.out.println("Goal found at node " + nodeNames[goal]);
            printPath(parent, start, goal);
        } else {
            System.out.println("Goal not found");
        }
    }

    public static boolean dfsExplore(int node, int goal, boolean[] visited, int[] parent) {
        visited[node] = true;

        if (node == goal) {
            return true;
        }

        for (int i = 0; i < NUM_NODES; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                parent[i] = node;
                if (dfsExplore(i, goal, visited, parent)) {
                    return true;
                }
            }
        }

        return false;
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
        dfsSearch(start, goal);
    }
}