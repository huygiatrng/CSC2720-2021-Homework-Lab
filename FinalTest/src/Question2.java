import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Question2 {
	public static float calculator(ArrayList<String> input) {
		Stack<Float> stack = new Stack<Float>();
		boolean validInput = true;
		// If input is empty, return -1 and print("Invalid input")
		if (input.size()==0) {
			validInput = false;
		} else {
			// split the trimmed input string and loop through every element in it
			float value1, value2;
			// Separate into multiple cases for calculating
			for (int i = 0; i < input.size(); i++) {
				switch (input.get(i)) {
				case "+":
					// Check if size of stack allows us to pop 2 items from it, if not return -1 and
					// print the error
					if (validSizeOfStack(stack, 2)) {
						value1 = stack.pop();
						value2 = stack.pop();
						stack.push(value2 + value1);
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = input.size();
					}
					break;
				case "-":
					// Check if size of stack allows us to pop 2 items from it, if not return -1 and
					// print the error
					if (validSizeOfStack(stack, 2)) {
						value1 = stack.pop();
						value2 = stack.pop();
						stack.push(value2 - value1);
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = input.size();
					}
					break;
				case "*":
					// Check if size of stack allows us to pop 2 items from it, if not return -1 and
					// print the error
					if (validSizeOfStack(stack, 2)) {
						value1 = stack.pop();
						value2 = stack.pop();
						stack.push(value2 * value1);
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = input.size();
					}
					break;
				case "/":
					// Check if size of stack allows us to pop 2 items from it, if not return -1 and
					// print the error
					if (validSizeOfStack(stack, 2)) {
						value1 = stack.pop();
						value2 = stack.pop();
						stack.push(value2 / value1);
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = input.size();
					}
					break;
				case "^":
					// Check if size of stack allows us to pop 2 items from it, if not return -1 and
					// print the error
					if (validSizeOfStack(stack, 2)) {
						value1 = stack.pop();
						value2 = stack.pop();
						float result = 1;
						// the power have to be integer, not decimal.
						if (value1 % 1 == 0) {
							for (int j = (int) value1; j != 0; --j) {
								result *= value2;
							}
							stack.push(result);
							break;
						} else {
							validInput = false;
							System.out.println("*Power have to be integer.*");
							i = input.size();
						}
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = input.size();
					}
					break;
				case " ":
					break;
				default:
					// If the element is not any of operators above, check if it is numeric and push
					// it to stack as a float, else return -1 and print the error
					if (isNumeric(input.get(i))) {
						stack.push(Float.parseFloat(input.get(i)));
					} else {
						validInput = false;
						i = input.size();
					}
					break;
				}
			}
		}
		// Check if validInput flag has ever turned false or not, then print the error
		// as well as return -1, else pop the last item from stack
		if (validInput == false) {
			System.out.println("*Invalid input* ");
			return -1;
		} else {
			return stack.pop();
		}
	}

	// Check if size of the stack is satisfied the requirement of size
	public static boolean validSizeOfStack(Stack<Float> a, int size) {
		return (a.size() >= size);
	}

	// Check if a string is a number
	public static boolean isNumeric(String input) {
		return input.matches("[-+]?\\d*\\.?\\d+");
	}

	public static void PostFixCalculator(ArrayList<String> input) {
		System.out.println("Input: \"" + input + "\"");
		float result = calculator(input);
		// if result can be turn into integer, print it as integer, else round it 2
		// decimals and print it
		if (result % 1 == 0) {
			System.out.format("Answer: %.0f\n", result);
		} else {
			System.out.format("Answer: %.2f\n", result);
		}
	}

	public static void main(String[] args) {
		ArrayList<String> input = new ArrayList<String>(Arrays.asList(new String[] {"2","1","+","4","*"}));
		PostFixCalculator(input);
		
		// Time complexity: O(n) 	Space complexity: O(n)
	}

}
