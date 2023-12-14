import java.util.ArrayList;

public class Assignment3 {
	public static class Node{
		int data;
	    Node left, right;
	    public Node(int item)
	    {
	        data = item;
	        left = null;
	        right = null;
	    }
	}
	public static class BinaryTree {
	    Node root;
	    
	    public void add(int data) {
	    	this.root = addRecursion(this.root, data);
	    }
	    
	    static Node addRecursion(Node node, int data)
	    {
	        if (node == null)
	            return new Node(data);
	        if (data < node.data)
	            node.left = addRecursion(node.left, data);
	        else
	            node.right = addRecursion(node.right, data);
	        return node;
	    }

	    void printTree(Node root) {
	    	if(root==null) {
	    		System.out.println("*Null binary search tree cannot be printed*");
	    		return;
	    	}   	
	    	int maxLevel = maxLevel(root);
	    	ArrayList<Node> nodes = new ArrayList<Node>();
	    	nodes.add(root);
	    	printNodeInternal(nodes, 1, maxLevel);
	    }

	    static void printNodeInternal(ArrayList<Node> nodes, int level, int maxLevel) {
	    	if(nodes.isEmpty()||isAllNull(nodes)) {
	    		return;
	    	}
	    	int floor = maxLevel - level;
	    	int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
	        printWhitespaces(firstSpaces);
	        ArrayList<Node> newNodes  = new ArrayList<Node>();
	        for(Node node : nodes) {
	        	if (node != null) {
	                System.out.print(node.data);
	                newNodes.add(node.left);
	                newNodes.add(node.right);
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }
	        	printWhitespaces(betweenSpaces);
	        }
	        System.out.println("");
	        for (int i = 1; i <= endgeLines; i++) {
	            for (int j = 0; j < nodes.size(); j++) {
	                printWhitespaces(firstSpaces - i);
	                if (nodes.get(j) == null) {
	                    printWhitespaces(endgeLines + endgeLines + i + 1);
	                    continue;
	                }

	                if (nodes.get(j).left != null)
	                    System.out.print("/");
	                else
	                    printWhitespaces(1);

	                printWhitespaces(i + i - 1);

	                if (nodes.get(j).right != null)
	                    System.out.print("\\");
	                else
	                    printWhitespaces(1);

	                printWhitespaces(endgeLines + endgeLines - i);
	            }
	            System.out.println("");
	        }
	        printNodeInternal(newNodes, level + 1, maxLevel);
	    }
	    
	    static boolean isAllNull(ArrayList<Node> nodes) {
	        for (Node node : nodes) {
	            if (node != null)
	                return false;
	        }
	        return true;
	    }
	    
	    static int maxLevel(Node root) {
	    	if (root == null)
	            return 0;
	    	return Math.max(BinaryTree.maxLevel(root.left), BinaryTree.maxLevel(root.right)) + 1;
	    }
	    
	    static void printWhitespaces(int count) {
	        for (int i = 0; i < count; i++)
	            System.out.print(" ");
	    }
	    
	    public void deleteNode(int target) {
	    	if(root==null) {
	    		System.out.println("*Null binary search tree doesn't need to use removing*");
	    		return;
	    	}
	    	this.root = deleteNodeRec(this.root,target);
	    	// Below lines of code for delete all the target in binary search tree, not just one.
	    	while(this.root!=null&& this.root.data==target) {
	    		this.root = deleteNodeRec(this.root,target);
	    	}
	    }
	    
	    Node deleteNodeRec(Node root, int target)
	    {
	        // Base
	        if (root == null)
	            return root;
	      
	        // Using recursion to find the target
	        if (root.data > target)
	        {
	            root.left = deleteNodeRec(root.left, target);
	            return root;
	        }
	        else if (root.data < target) 
	        {
	            root.right = deleteNodeRec(root.right, target);
	            return root;
	        }
	      
	        //When found the target
	        
	        // If left of target is null, replace target with its right node
	        if (root.left == null) 
	        {
	            Node temp = root.right;
	            return temp;
	        }
	        // If right of target is null, replace target with its left node
	        else if (root.right == null) 
	        {
	            Node temp = root.left;
	            return temp;
	        }
	        // if right and left nodes are not null
	        else 
	        {
	        	// Assign pair of replacement and its parent running through
	        	// left side of right branch of root to find minimum
	            Node parentOfReplacement = root;
	            Node replacement = root.right;
	            
	            //Stop running when left of replacement reach null
	            while (replacement.left != null) 
	            {
	                parentOfReplacement = replacement;
	                replacement = replacement.left;
	            }
	            
	            // At this point replacement.right will be the child of replacement but hold higher data
	            // We want to assign the left of the parent of replacement into it, then replace the data of deleted data by replacement data
	            // If the parent of replacement is root, it means the running step above only run 1 time. So those pairs didn't
	            // turn left yet.
	            // That why we have assign the right of parent into the right of replacement
	            
	            if (parentOfReplacement != root)
	            	parentOfReplacement.left = replacement.right;
	            else
	            	parentOfReplacement.right = replacement.right;
	      
	            // Change data of target by replacement data
	            root.data = replacement.data;
	            return root;
	        }
	    }
	}
	
	public static void main(String[] args) {
		System.out.println("Original Case:");
		BinaryTree tree_level = new BinaryTree();
		tree_level.root = new Node(5);
		tree_level.add(2);
		tree_level.add(6);
		tree_level.add(1);
		tree_level.add(3);
		tree_level.add(4);
		tree_level.add(10);
		tree_level.add(8);
		tree_level.add(7);
		tree_level.add(9);
		tree_level.add(12);
		tree_level.add(13);
		tree_level.add(11);
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 7 __________________________");
		tree_level.deleteNode(7);
		tree_level.printTree(tree_level.root);
		
		System.out.println("Remove 12 _________________________");

		tree_level.deleteNode(12);
		tree_level.printTree(tree_level.root);
		
		System.out.println("Remove 10 _________________________");
		tree_level.deleteNode(10);
		tree_level.printTree(tree_level.root);
		
		System.out.println("Remove 5 _________________________");
		tree_level.deleteNode(5);
		tree_level.printTree(tree_level.root);
		
		System.out.println("TEST CASES: ==========================================================");
		System.out.println("When the binary search tree is null\n");
		tree_level = new BinaryTree();
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 10 _________________________");
		tree_level.deleteNode(10);
		tree_level.printTree(tree_level.root);
		
		System.out.println("\n====================================================================\nWhen the binary search tree has only one node\nand target to delete is not that node:\n");
		tree_level.add(6);
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 10 ________________________");
		tree_level.deleteNode(10);
		tree_level.printTree(tree_level.root);
		
		System.out.println("\n====================================================================\nWhen the binary search tree has only one node\nand target to delete is that node:\n");
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 6 _________________________");
		tree_level.deleteNode(6);
		tree_level.printTree(tree_level.root);
		
		System.out.println("\n====================================================================\nWhen the binary search tree has different 2 nodes");
		tree_level.add(10);
		tree_level.add(15);
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 10 ________________________");
		tree_level.deleteNode(10);
		tree_level.printTree(tree_level.root);
		
		System.out.println("\n====================================================================\nWhen the binary search tree has multiple same nodes, it will remove all nodes that match target");
		tree_level = new BinaryTree();
		tree_level.add(10);
		tree_level.add(10);
		tree_level.add(10);
		tree_level.printTree(tree_level.root);
		System.out.println("Remove 10 ________________________");
		tree_level.deleteNode(10);
		tree_level.printTree(tree_level.root);
		
	}

}
