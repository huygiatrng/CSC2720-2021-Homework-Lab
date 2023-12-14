public class Lab6LinkedList {
	public static class LinkedList {
		Node head;
		Node tail;

		static class Node {
			int data;
			Node next;

			Node(int d) {
				data = d;
				next = null;
			}
		}

		void reverse() {
			Node prev = null;
			Node current = this.head;
			Node next = null;
			while (current != null) {
				next = current.next;
				current.next = prev;
				prev = current;
				current = next;
			}
			this.head = prev;
		}
		
		Node mergeSort(Node head) {
			if (head != null && head.next != null) {
		        // get the middle of the list

		        Node middle = head; 
		        Node fast = head;
		 
		        while (fast.next != null && fast.next.next != null) {
		        	middle = middle.next;
		            fast = fast.next.next;
		        }

		        Node nextofmiddle = middle.next;
		 
		        middle.next = null;
		 
		        Node left = mergeSort(head);
		 
		        Node right = mergeSort(nextofmiddle);
		 
		        Node sortedlist = mergeList(left, right);
		        
		        return sortedlist;
			}else {
				return head;
			}
		}
		
	    Node mergeList(Node a, Node b)
	    {
	    	Node result = null;
	        if (a == null)
	            return b;
	        if (b == null)
	            return a;
	 
	        if (a.data <= b.data) {
	            result = a;
	            result.next = mergeList(a.next, b);
	        }
	        else {
	            result = b;
	            result.next = mergeList(a, b.next);
	        }
	        return result;
	    }
	    
	    Node deDuplicate(Node head) {
	    	if(head==null||head.next==null) {
	    		return head;
	    	}else {
	    		head = mergeSort(head);
	    		Node result = head;
	    		Node n = result;
	    		Node curr = head;
	    		while(curr.next!=null) {
	    			if(curr.next.data!=curr.data) {
	    				n.next = curr.next;
	    				n = n.next;
	    			}
	    			curr = curr.next;
	    		}
	    		n.next = null;
	    		return result;
	    	}	    	
	    }

		void addNode(int data) {
			Node newNode = new Node(data);
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
		}

		void insertNode(int pos, int data) {
			Node n = head;
			Node newNode = new Node(data);
			if (pos == 0) {
				newNode.next = head;
				head = newNode;
			}
			for (int i = 1; n != null; i++) {
				if (i == pos) {
					if (n.next == null) {
						this.addNode(data);
					} else {
						newNode.next = n.next;
						n.next = newNode;
					}
					return;
				}
				n = n.next;
			}
			System.out.println("*The position you want to add data is out of range.*");
		}

		void printList() {
			Node n = head;
			System.out.print("[");
			while (n != null) {
				if (n.next == null) {
					System.out.print(n.data);
				} else {
					System.out.print(n.data + ",");
				}
				n = n.next;
			}
			System.out.println("]");
		}

		void updateNode(int pos, int data) {
			Node n = head;
			if (pos < 0) {
				System.out.println("*The position you want to update is out of range.*");
				return;
			}
			for (int i = 0; i < pos; i++) {
				n = n.next;
				if (n == null) {
					System.out.println("*The position you want to update is out of range.*");
					return;
				}
			}
			n.data = data;
		}

		void removeNode(int pos) {
			Node n = head;
			if (pos == 0) {
				head = n.next;
			}
			for (int i = 1; i < pos; i++) {
				n = n.next;
				if (n.next == null) {
					System.out.println("*The position you want to remove is out of range.*");
					return;
				}
			}
			if ((n.next).next == null) {
				n.next = null;
				tail = n;
			} else {
				n.next = (n.next).next;
			}
		}
	}
	
	public static void main(String[] args) {
//		TESTING
		LinkedList llist = new LinkedList();
		llist.addNode(50);
		llist.addNode(11);
		llist.addNode(33);
		llist.addNode(21);
		llist.addNode(40);
		llist.addNode(50);
		llist.addNode(40);
		llist.addNode(40);
		llist.addNode(21);
		System.out.print("Original Linked List: ");
		llist.printList();

		System.out.println("===============================================");

		System.out.print("\nDeduplicated Linked List: ");

        llist.head = llist.deDuplicate(llist.head);
        llist.printList();

		System.out.println("\nTEST=SPECIAL=CASES=========================");

		System.out.println("When array is null");
		System.out.print("Original Linked List: ");
		llist = new LinkedList();
		llist.printList();
		llist.head = llist.deDuplicate(llist.head);
		System.out.print("Deduplicated Linked List: ");
        llist.printList();

		System.out.println("\nWhen Linked List length is 1");
		System.out.print("Original Linked List: ");
		llist = new LinkedList();
		llist.addNode(21);
		llist.printList();
		llist.head = llist.deDuplicate(llist.head);
		System.out.print("Deduplicated Linked List: ");
        llist.printList();

		System.out.println("\nWhen Linked List length is 2 and they are the same");
		System.out.print("Original Linked List: ");
		llist = new LinkedList();
		llist.addNode(75);
		llist.addNode(75);
		llist.printList();
		llist.head = llist.deDuplicate(llist.head);
		System.out.print("Deduplicated Linked List: ");
        llist.printList();

		System.out.println("\nWhen Linked List length is 2 and they are not the same");
		System.out.print("Original Linked List: ");
		llist = new LinkedList();
		llist.addNode(75);
		llist.addNode(25);
		llist.printList();
		llist.head = llist.deDuplicate(llist.head);
		System.out.print("Deduplicated Linked List: ");
        llist.printList();
		
		
	}
}
