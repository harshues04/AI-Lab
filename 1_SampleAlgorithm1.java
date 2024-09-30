/* In this Sample Algorithm 1, we print the adjacency matrix and then implement the algorithm. 
The algorithm consists of an open list to which the nodes that are visited are appended.

Problems: It lacks a mechanism to track which nodes have been visited. 
Nodes are revisited multiple times, leading to inefficiency and potential infinite loops.

Soltion: Introduce an open list which will keep track of the nodes being visited.
 */
import java.util.Arrays;

public class SampleAlgorithm1 {

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

    static String[] nodeNames = {"S","A", "B", "C", "D", "E", "G"};

    public static void moveGen(int node, int[] neighbors) {
        for (int i = 0; i < NUM_NODES; i++) {
            if (graph[node][i] == 1) {
                neighbors[i] = 1;
            } else {
                neighbors[i] = 0;
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

    public static void printState(int[] open, int current) {
        System.out.println("Current Node: " + nodeNames[current]);
        System.out.print("Open List: ");
        for (int i = 0; i < NUM_NODES; i++) {
            if (open[i] == 1) System.out.print(nodeNames[i] + " ");
        }
        System.out.println();
    }

    public static int sampleAlgorithm1(int start, int goal) {
        int[] open = new int[NUM_NODES];
        int[] neighbors = new int[NUM_NODES];

        moveGen(start, open);
        open[start] = 1;

        for (int i = 0; i < NUM_NODES; i++) {
            if (open[i] == 1) {
                if (i == goal) {
                    System.out.println("Sample Algorithm 1: Goal has been reached");
                    return i;
                } else {
                    moveGen(i, neighbors);
                    for (int j = 0; j < NUM_NODES; j++) {
                        if (neighbors[j] == 1 && open[j] == 0) {
                            open[j] = 1;
                        }
                    }
                }
                printState(open, i);
                System.out.println();
            }
        }
        System.out.println("Sample Algorithm 1: Goal not found");
        return -1;
    }

    public static void main(String[] args) {
        int start = 0; // Starting node
        int goal = 6;  // Goal node

        printAdjacencyMatrix();

        System.out.println("\nRunning Sample Algorithm 1:");
        sampleAlgorithm1(start, goal);
    }
}