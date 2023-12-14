public class Question4 {
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
	    static int count = 0;
	    
	    public Node KthSmallestUtil(Node root, int k)
	    {
	        // Base
	        if (root == null)
	            return null;
	        
	        //In order traversal
	        // left
	        Node left = KthSmallestUtil(root.left, k);
	        count++;
	        //root
	        if (left != null)
	            return left;	        
	        if (count == k)
	            return root;
	        //right
	        return KthSmallestUtil(root.right, k);
	    }
	    public void KthSmallest(Node root, int k)
	    {
	        Node output = KthSmallestUtil(root, k);
	        if (output == null)
	            System.out.println("Your input is invalid");
	        else
	            System.out.println("k-th smallest node is " + output.data);
	    }
	    
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(4);
		tree.root.left = new Node(2);
		tree.root.right = new Node(6);
		tree.root.left.left = new Node(1);
		tree.root.left.right = new Node(3);
		tree.root.right.left = new Node(5);
		tree.root.right.right = new Node(7);
		
		tree.KthSmallest(tree.root, 3);
		
	}

}
