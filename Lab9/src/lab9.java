public class lab9 {
	public static class Queue{
		Node head;
		Node tail;
		Queue maxQueue = new Queue();

		static class Node {
			int data;
			Node next;
			Node(int d) {
				data = d;
				next = null;
			}
		}
		void push(int data) {
			Node newNode = new Node(data);
			Node newNodeforMax = new Node(data);
			if (head == null) {
				this.head = newNode;
				this.tail = newNode;
				this.maxQueue.head = newNodeforMax;
				this.maxQueue.tail = newNodeforMax;
			} else {
				tail.next = newNode;
				tail = tail.next;
				while(data>this.maxQueue.tail.data) {
					this.maxQueue.deleteLastNode();
				}
				this.maxQueue.tail.next = newNodeforMax;
				this.maxQueue.tail=this.maxQueue.tail.next;
			}	
		}

		int peek() {
			return this.head.data;
		}
		
		int pop() {
			Node n = head;
			if(this.head.data==this.maxQueue.head.data) {
				this.maxQueue.head= this.maxQueue.head.next;
			}
			this.head = this.head.next;
			return n.data;
		}
		
		void deleteLastNode() {
			Node n = head;
			if(n.next==null) {
				this.head = null;
			}else {
				while(n.next.next!=null) {
					n.next = null;
				}
			}
		}
		
		int maxValue() {
			return this.maxQueue.head.data;
		}
		
		void printQueue() {
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
		
		
	}
	
	public static void main(String[] args) {
		Queue inputQueue = new Queue();
		inputQueue.push(15);
		inputQueue.push(4);
		inputQueue.push(8);
		inputQueue.push(3);
		inputQueue.push(2);
		inputQueue.push(3);
		inputQueue.push(12);
		System.out.print("Current Queue: ");
		inputQueue.printQueue();
		System.out.print("Max value of Queue: "+ inputQueue.maxValue());
		
		
		

	}

}
