import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class DFS_Super {
	public static class Graph<T> {

		
		private Map<T, List<T> > map = new HashMap<>();
		
	    public void addVertex(T s)
	    {
	    	if(!map.containsKey(s)) {
	    		map.put(s, new LinkedList<T>());
	    	}else {
	    		System.out.println(s+" already exists in the chart.");
	    	}
	    }
	 
	    public void addEdge(T source,
	                        T destination,
	                        boolean bidirectional)
	    {
	        if (!map.containsKey(source))
	            addVertex(source);
	 
	        if (!map.containsKey(destination))
	            addVertex(destination);
	 
	        map.get(source).add(destination);
	        if (bidirectional == true) {
	            map.get(destination).add(source);
	        }
	    }
	 
	    // This function gives the count of vertices
	    public int getVertexCount()
	    {
	        return map.keySet().size();
	    }
	 
	    // This function gives the count of edges
	    public int getEdgesCount(boolean bidirection)
	    {
	        int count = 0;
	        for (T v : map.keySet()) {
	            count += map.get(v).size();
	        }
	        if (bidirection == true) {
	            count = count / 2;
	        }
	        return count;
	    }
	 
	    // This function gives whether
	    // a vertex is present or not.
	    public boolean hasVertex(T s)
	    {
	        return map.containsKey(s);
	    }
	 
	    // This function gives whether an edge is present or not.
	    public boolean hasEdge(T s, T d)
	    {
	    	return (map.get(s).contains(d));
	    }
	 
	    // Prints the adjacency list of each vertex.
	    @Override
	    public String toString()
	    {
	        StringBuilder builder = new StringBuilder();
	 
	        for (T v : map.keySet()) {
	            builder.append(v.toString() + ": ");
	            for (T w : map.get(v)) {
	                builder.append(w.toString() + " ");
	            }
	            builder.append("\n");
	        }
	 
	        return (builder.toString());
	    }
	    
	    public String toDftString(T root) {
	    	ArrayList<T> visited = new ArrayList<T>();
	        Stack<T> stack = new Stack<T>();
	        stack.push(root);
	        while (!stack.isEmpty()) {
	            T vertex = stack.pop();
	            if (!visited.contains(vertex)) {
	                visited.add(vertex);
	                for (T w : map.get(vertex)) {              
	                    stack.push(w);
	                }
	            }
	        }
	        return visited.toString();
	    }
	    
	    public String toBftString(T root) {
	    	ArrayList<T> visited = new ArrayList<T>();
	    	Queue<T> queue = new LinkedList<T>();
	    	queue.add(root);
	    	visited.add(root);
	        while (!queue.isEmpty()) {
	            T vertex = queue.poll();
	            for (T v : map.get(vertex)) {
	                if (!visited.contains(v)) {
	                    visited.add(v);
	                    queue.add(v);
	                }
	            }
	        }
	        return visited.toString();
	    }
	    public boolean hasNoCycle(T start) {
	    	HashSet<T> visited = new HashSet<T>();
	    	T prev = null;
	        return !dfsRecursive(start,prev, visited);
	    }
	    
	    public boolean dfsRecursive(T current,T prev, HashSet<T> visited) {
	        if(current==null) {
	        	return true;
	        }
	        if(visited.contains(current)&&(prev!=current||prev==null)) {
	        	return false;
	        }
	        visited.add(current);
		    for(T next : map.get(current)) {
		    	// To check the previous and next are the same
		    	if(next!=prev) {
		        	if(!dfsRecursive(next,current,visited)) {
		        		return false;
		        	}
		    	}
		    }
		    return true;
	    }
	    
	    public boolean isTree(T root){
	    	// Number of key map
	    	int n = map.size();
	    	
	    	// create empty hash set for running dfs
	    	HashSet<T> visited = new HashSet<T>();
	    	
	    	// check if the graph has cycle or not
	    	if(!dfsRecursive(root,null,visited)) {
	    		return false;
	    	}else {
	    		// return true if size of key map equals to size of visited hash set that we ran in dfs
	    		return (visited.size()==n);
	    	}
	    }
	}
	
	public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice",false);
        graph.addEdge("Bob", "Rob",false);
        graph.addEdge("Alice", "Mark",false);
        graph.addEdge("Rob", "Mark",false);
        graph.addEdge("Alice", "Maria",false);
        graph.addEdge("Rob", "Maria",false);
        // Printing the graph
        System.out.println("Graph:\n"
                           + graph.toString());
 
        // Gives the no of vertices in the graph.
        System.out.println("Number of vertices:\n"+graph.getVertexCount());
 
        // Gives the no of edges in the graph.
        System.out.println("Number of edges:\n"+graph.getEdgesCount(false));
 
        // Tells whether the edge is present or not.
        String a = "Bob";
        String b = "Mark";
        System.out.println("\nIf graph have an edge between "+a+" and "+b+": \n"+graph.hasEdge(a, b));
 
        // Tells whether vertex is present or not
        System.out.println("\nIf graph have "+b+" vertex: \n"+graph.hasVertex(b));
        
        System.out.println("\nPrint graph with Depth-First Traversal:  \n"+graph.toDftString("Bob"));
        
        System.out.println("\nPrint graph with Breadth-First Traversal:  \n"+graph.toBftString("Bob"));
        
        System.out.println("\nGraph has cycle:  \n"+graph.hasNoCycle("Bob"));
        
        System.out.println("\nGraph is tree:  \n"+graph.isTree("Bob"));
	}

}
