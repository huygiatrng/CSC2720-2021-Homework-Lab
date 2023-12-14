import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch {
	static class Graph {
		int V; // Number of Vertices
		LinkedList<Integer>[] adj; // adjacency lists

		// Constructor
		Graph(int V) {
			this.V = V;
			adj = new LinkedList[V];
			for (int i = 0; i < adj.length; i++)
				adj[i] = new LinkedList<Integer>();
		}

		void addEdge(int v, int w) {
			adj[v].add(w); // Add w to the list of v.
		}

		public String DFS(int root) {
			if (root >= adj.length) {
				return "*Invalid root*";
			}

			ArrayList<Integer> visited = new ArrayList<Integer>();
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(root);
			while (!stack.isEmpty()) {
				int vertex = stack.pop();
				if (!visited.contains(vertex)) {
					visited.add(vertex);
					for (int v : adj[vertex]) {
						stack.push(v);
					}
				}
			}
			return visited.toString();
		}

		public String DFSRecursion(int root) {
			if (root >= adj.length) {
				return "*Invalid root*";
			}
			ArrayList<Integer> visited = new ArrayList<Integer>();
			return DFSRecursionUtil(root, visited).toString();
		}

		public ArrayList<Integer> DFSRecursionUtil(int root, ArrayList<Integer> visited) {
			int n = adj[root].size();
			if (!visited.contains(root)) {
				visited.add(root);
				if ((n > 0)) {
					for (int i = n - 1; i >= 0; i--) {
						visited = DFSRecursionUtil(adj[root].get(i), visited);
					}
				}
			}
			return visited;
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(4, 5);
		g.addEdge(1, 3);
		g.addEdge(3, 5);
		System.out.println("Depth-First Traversal with Stack:\n" + g.DFS(0));
		System.out.println("Depth-First Traversal with Recursion:\n" + g.DFSRecursion(0));
		System.out.println("\nTEST CASES\n\nIf graph is empty (null):");

		g = new Graph(0);
		System.out.println("Depth-First Traversal with Stack:\n" + g.DFS(0));
		System.out.println("Depth-First Traversal with Recursion:\n" + g.DFSRecursion(0));
		System.out.println("Because the root is not available in adjacency list, so it returns invalid root.");

		System.out.println("\nIf the graph likes a single linked list and has disconnected node.");
		g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		// Without edge between 2 and 3
		g.addEdge(2, 4);
		g.addEdge(4, 5);
		System.out.println("Depth-First Traversal with Stack:\n" + g.DFS(0));
		System.out.println("Depth-First Traversal with Recursion:\n" + g.DFSRecursion(0));

		System.out.println("\nIf the graph is a binary tree.");
		g = new Graph(7);
		g.addEdge(4, 5);
		g.addEdge(4, 2);
		// Without edge between 2 and 3
		g.addEdge(2, 3);
		g.addEdge(2, 1);
		g.addEdge(5, 6);
		System.out.println("Depth-First Traversal with Stack:\n" + g.DFS(4));
		System.out.println("Depth-First Traversal with Recursion:\n" + g.DFSRecursion(4));

		System.out.println("\nIf we try to print a disconnected node.");
		System.out.println("Depth-First Traversal with Stack:\n" + g.DFS(0));
		System.out.println("Depth-First Traversal with Recursion:\n" + g.DFSRecursion(0));

	}

}