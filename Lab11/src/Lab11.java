import java.util.ArrayList;
import java.util.Stack;

public class Lab11 {
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
	    
	    boolean isBST() {
	    	//Create empty stack and pass through recursion
	    	Stack<Integer> inOrderTravel = new Stack<Integer>();
	    	return isBSTRecursion(this.root,inOrderTravel);
	    }
	    
	    boolean isBSTRecursion(Node root,Stack<Integer> inOrderTravel) {
	    	//return true if root is null
	    	if (root != null) {
	    		//Run recursion on left side
		    	if (!isBSTRecursion(root.left,inOrderTravel))
	                return false;
		    	//Peek last data from stack to make sure the root is higher than than that data
		    	if (!inOrderTravel.isEmpty() && root.data <= inOrderTravel.peek())
			    		return false;
		    	//Push data of root into stack
		    	inOrderTravel.push(root.data);
		    	//Run recursion of right side
		    	return isBSTRecursion(root.right,inOrderTravel);
	    	}
	    	return true;
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
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(4);
		tree.root.left = new Node(2);
		tree.root.right = new Node(6);
		tree.root.left.left = new Node(1);
		tree.root.left.right = new Node(3);
		tree.root.right.left = new Node(5);
		tree.root.right.right = new Node(7);
		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
		System.out.println("___________________________\nTEST CASES:");
		
		System.out.println("___________________________\nIf tree holds duplicate values (4):");
		tree.root.right.left.left = new Node(4);
		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
		System.out.println("Because all keys in left subtree of a key must be smaller and all keys in right subtree must be greater.\nSo if there are duplicated value in binary tree, return false.\n");
		
		System.out.println("___________________________\nIf tree is null:");
		tree = new BinaryTree();
		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
		
		System.out.println("___________________________\nIf tree has 1 node only:");
		tree.root = new Node(6);
		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
		
		System.out.println("___________________________\nIf tree has 2 nodes:");
		tree.root.left = new Node(5);
		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
		
		System.out.println("___________________________\nIf binary tree is unbalanced:");
		tree.root.right = new Node(9);
		tree.root.left.left = new Node(1);
		tree.root.left.left.left = new Node(0);
		tree.root.left.left.right = new Node(2);
		tree.root.left.left.right.right = new Node(4);
		tree.root.left.left.right.right.left = new Node(3);

		tree.printTree(tree.root);
		System.out.println("Is this tree binary search tree: "+tree.isBST()+"\n");
	}

}
