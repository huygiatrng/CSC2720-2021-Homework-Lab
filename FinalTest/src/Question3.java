public class Question3 {
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
		/* Start with the empty list. */
		LinkedList llist = new LinkedList();
		llist.addNode(2);
		llist.addNode(3);
		llist.addNode(1);
		llist.addNode(7);
		llist.addNode(5);
		llist.addNode(18);
		System.out.print("Input: ");
		llist.printList();
		System.out.print("Output after removing 3rd node: ");
		llist.removeNode(3);
		llist.printList();

	}

}
