import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BritishMuseumSearch {

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

    public static void britishMuseumSearch(int start, int goal) {
        List<List<Integer>> allPaths = new ArrayList<>();
        LinkedList<Integer> currentPath = new LinkedList<>();
        boolean[] visited = new boolean[NUM_NODES];
        
        System.out.println("\nBritish Museum Search Algorithm");
        dfsExplore(start, goal, visited, currentPath, allPaths);

        System.out.println("\nAll Possible Paths:");
        for (List<Integer> path : allPaths) {
            printPath(path);
        }
    }

    public static void dfsExplore(int node, int goal, boolean[] visited, LinkedList<Integer> currentPath, List<List<Integer>> allPaths) {
        visited[node] = true;
        currentPath.add(node);

        if (node == goal) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (int i = 0; i < NUM_NODES; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    dfsExplore(i, goal, visited, currentPath, allPaths);
                }
            }
        }

        currentPath.removeLast();
        visited[node] = false;
    }

    public static void printPath(List<Integer> path) {
        List<String> nodePath = new ArrayList<>();
        for (int node : path) {
            nodePath.add(nodeNames[node]);
        }
        System.out.println(String.join(" -> ", nodePath));
    }

    public static void main(String[] args) {
        int start = 0;
        int goal = 6;

        printAdjacencyMatrix();
        britishMuseumSearch(start, goal);
    }
}