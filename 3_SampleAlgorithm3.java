/* In this Sample Algorithm 3, we solve the problems faced in the second one. 
We implement child- parent pairs to make backtracking possible and backtrack to reconstruct the path to reach the goal.
It consists of an open list of node pairs. It prints the path taken to reach the goal at the end. 
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SampleAlgorithm3 {

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

    public static void printState(List<int[]> open, int[] close, int current) {
        System.out.println("Current Node: " + nodeNames[current]);
        System.out.print("Open List: ");
        for (int[] pair : open) {
            System.out.print("(" + nodeNames[pair[0]] + ", " + nodeNames[pair[1]] + ") ");
        }
        System.out.println();

        System.out.println("After visiting node " + nodeNames[current] + ", the close list is updated as follows:");
        System.out.print("Close List: ");
        for (int i = 0; i < NUM_NODES; i++) {
            if (close[i] == 1) System.out.print(nodeNames[i] + " ");
        }
        System.out.println();
    }

    public static void printPath(List<int[]> makePair, int goal) {
        System.out.println("Backtracking to reconstruct the path:");
        LinkedList<String> path = new LinkedList<>();
        int currentNode = goal;

        // Backtrack from the goal node using the makePair list
        while (currentNode != -1) {
            for (int[] pair : makePair) {
                if (pair[0] == currentNode) {
                    path.addFirst(nodeNames[currentNode]);
                    currentNode = pair[1];
                    break;
                }
            }
        }

        // Print the path from start to goal
        System.out.println("Path: " + String.join(" -> ", path));
    }

    public static int sampleAlgorithm3(int start, int goal) {
        List<int[]> open = new LinkedList<>();  // List of node pairs (current, parent)
        int[] close = new int[NUM_NODES];
        int[] neighbors = new int[NUM_NODES];
        List<int[]> makePair = new LinkedList<>();  // For backtracking

        // Initialize the open list with the starting node and no parent (-1)
        open.add(new int[]{start, -1});

        while (!open.isEmpty()) {
            // Get the next node from the open list (current, parent)
            int[] currentNodePair = open.remove(0);
            int currentNode = currentNodePair[0];
            int parentNode = currentNodePair[1];

            // Check if the goal node is found
            if (currentNode == goal) {
                System.out.println("Sample Algorithm 3: Goal found at node " + nodeNames[currentNode]);
                makePair.add(new int[]{currentNode, parentNode});  // Add the goal to the pair list
                printPath(makePair, goal);
                return currentNode;
            }

            // Add the current node to the close list
            close[currentNode] = 1;
            makePair.add(new int[]{currentNode, parentNode});  // Track the node and its parent

            // Generate neighbors of the current node
            moveGen(currentNode, neighbors);
            for (int i = 0; i < NUM_NODES; i++) {
                if (neighbors[i] == 1 && close[i] == 0) {
                    open.add(new int[]{i, currentNode});  // Add the neighbor with the current node as the parent
                }
            }

            // Print the state after visiting the current node
            printState(open, close, currentNode);
        }

        System.out.println("Sample Algorithm 3: Goal not found");
        return -1;
    }

    public static void main(String[] args) {
        int start = 0; // Starting node (S)
        int goal = 6;  // Goal node (G)

        printAdjacencyMatrix();

        System.out.println("\nRunning Sample Algorithm 3:");
        sampleAlgorithm3(start, goal);
    }
}