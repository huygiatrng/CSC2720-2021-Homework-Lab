import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxDequeueFastAccess {

	public static class deQueue {
		// Create a queue and a max dequeue
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayDeque<Integer> maxDequeue = new ArrayDeque<Integer>();

		void enqueue(int data) {
			// remove all last element of maxDequeue if they < input data
			while (!maxDequeue.isEmpty() && maxDequeue.getLast() < data) {
				maxDequeue.removeLast();
			}
			// add the element at the end
			maxDequeue.addLast(data);
			queue.add(data);
		}

		// Function to dequeue a value
		void dequeue() {
			// return the last element of the dequeue
			if (maxDequeue.getFirst() == queue.peek()) {
				maxDequeue.removeFirst();
			}
			queue.remove();
		}

		// Function return get first element of maxDeQueue
		int getMax() throws Exception {
			if (queue.isEmpty())
				throw new Exception("Empty Queue");
			else
				return maxDequeue.getFirst();
		}

		String asString() {
			if (queue.isEmpty()) {
				return "[Empty]";
			}
			String res = queue.toString();
			return res;
		}
	}

	// test method
	public static void main(String[] args) throws Exception {
		deQueue inputDequeue = new deQueue();

		// ENQUEUEING
		inputDequeue.enqueue(16);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(13);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(25);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(6);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(15);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(35);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(35);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(17);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(26);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.enqueue(18);
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());

		// DEQUEUEING
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());
		inputDequeue.dequeue();
		System.out.println("Current dequeue: " + inputDequeue.asString() + "\n - Max value: " + inputDequeue.getMax());

		// Time Complexity: O(1)
		// Space Complexity: O(n)
	}
}
