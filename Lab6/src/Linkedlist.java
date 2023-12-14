public class Linkedlist {
	// singular linked list
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
		/* Start with the empty list. */
		LinkedList llist = new LinkedList();
		// Question 1
		llist.addNode(1);
		llist.addNode(3);
		llist.addNode(5);
		llist.addNode(7);
		llist.addNode(9);
		llist.printList();

		// Question 2
		// Third node's index is 0, because it begins at 0
		System.out.print("TEST update node (element at index 2 to 11): ");
		llist.updateNode(2, 11);
		llist.printList();

		// Question 3
		System.out.print("TEST insert node: ");
		llist.insertNode(3, 0);
		llist.printList();

		// Question 4
		System.out.print("TEST reverse linkedlist: ");
		llist.reverse();
		llist.printList();
		llist.reverse();
		llist.printList();

		LinkedList llist2 = new LinkedList();
		llist2.printList();
		llist2.reverse();
		llist2.printList();
		llist2.addNode(3);
		llist2.addNode(7);
		llist2.reverse();
		llist2.printList();

	}
}
