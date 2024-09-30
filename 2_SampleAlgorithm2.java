/* In this Sample Algorithm 2, we use a open list to solve the problem faced by algorithm 1
We print the open list to keep track of the visited nodes.

Problems: We dont know the path taken by the algorithm to reach the goal, therefore we can't backtrack and find out how we have reached the goal

Solution: Implement backtracking by using node pairs where the nodes present in pairs.
Child and parent together in the open list which makes backtracking possible. 
 */

import java.util.Arrays;

public class SampleAlgorithm2 {

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

    public static void moveGen(int node, int[] neighbors) {
        Arrays.fill(neighbors, 0);  // Reset neighbors
        for (int i = 0; i < NUM_NODES; i++) {
            if (graph[node][i] == 1) {
                neighbors[i] = 1;
            }
        }
    }

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

    public static void printState(int[] open, int[] close, int current) {
        System.out.println("Current Node: " + nodeNames[current]);
        System.out.print("Open List: ");
        for (int i = 0; i < NUM_NODES; i++) {
            if (open[i] == 1 || i == current) {  // Include current node in open list
                System.out.print(nodeNames[i] + " ");
            }
        }
        System.out.println();

        System.out.println("After visiting node " + nodeNames[current] + ", the close list is updated as follows:");
        System.out.print("Close List: ");
        for (int i = 0; i < NUM_NODES; i++) {
            if (close[i] == 1) System.out.print(nodeNames[i] + " ");
        }
        System.out.println();
    }

    public static int sampleAlgorithm2(int start, int goal) {
        int[] open = new int[NUM_NODES];
        int[] close = new int[NUM_NODES];
        int[] neighbors = new int[NUM_NODES];

        // Initialize the open list with the starting node
        open[start] = 1;

        while (true) {
            // Find the next node from the open list that is not in the close list
            int currentNode = -1;
            for (int i = 0; i < NUM_NODES; i++) {
                if (open[i] == 1 && close[i] == 0) {
                    currentNode = i;
                    break;
                }
            }

            if (currentNode == -1) {
                System.out.println("Sample Algorithm 2: Goal not found");
                return -1;
            }

            // Check if the goal node is found
            if (currentNode == goal) {
                System.out.println("Sample Algorithm 2: Goal found at node " + nodeNames[currentNode]);
                return currentNode;
            }

            // Add the current node to the close list and remove it from the open list
            close[currentNode] = 1;
            open[currentNode] = 0;

            // Generate neighbors of the current node and add them to the open list if not in close
            moveGen(currentNode, neighbors);
            for (int i = 0; i < NUM_NODES; i++) {
                if (neighbors[i] == 1 && close[i] == 0) {
                    open[i] = 1;
                }
            }

            // Print the state after visiting the current node
            printState(open, close, currentNode);
        }
    }

    public static void main(String[] args) {
        int start = 0; // Starting node (S)
        int goal = 6;  // Goal node (G)

        printAdjacencyMatrix();

        System.out.println("\nRunning Sample Algorithm 2:");
        sampleAlgorithm2(start, goal);
    }
}