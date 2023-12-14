import java.util.Stack;

public class Lab8 {
	public static float calculator(String input) {
		Stack<Float> stack = new Stack<Float>();
		boolean validInput = true;
		// If input is empty, return -1 and print("Invalid input")
		if (input == "") {
			validInput = false;
		} else {
			// split the trimmed input string and loop through every element in it
			String[] items = input.trim().split(" ");
			float value1, value2;
			// Separate into multiple cases for calculating
			for (int i = 0; i < items.length; i++) {
				switch (items[i]) {
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
						i = items.length;
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
						i = items.length;
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
						i = items.length;
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
						i = items.length;
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
							i = items.length;
						}
					} else {
						System.out.println("*Not enough operand to calculate*");
						validInput = false;
						i = items.length;
					}
					break;
				case " ":
					break;
				default:
					// If the element is not any of operators above, check if it is numeric and push
					// it to stack as a float, else return -1 and print the error
					if (isNumeric(items[i])) {
						stack.push(Float.parseFloat(items[i]));
					} else {
						validInput = false;
						i = items.length;
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

	public static void showInputAndAnswer(String input) {
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
		String input = "3 5 + 1 -";
		showInputAndAnswer(input);
		System.out.println("\n______TEST_CASES______");
		System.out.println("+ When input is null:");
		showInputAndAnswer("");
		System.out.println("______________________");
		System.out.println("+ When input are only operators:");
		showInputAndAnswer("*");
		System.out.println("");
		showInputAndAnswer("* / +");
		System.out.println("______________________");
		System.out.println("+ When input are only operands (no operators)=> print the last number in input:");
		showInputAndAnswer("82");
		System.out.println("");
		showInputAndAnswer("12 52 7");
		System.out.println("______________________");
		System.out.println("+ When number of operators more than the calculation we need to process:");
		showInputAndAnswer("17 *");
		System.out.println("");
		showInputAndAnswer("15 2 7 * + * - ");
		System.out.println("______________________");
		System.out.println("+ When input have double number instead of integer:");
		showInputAndAnswer("3.8 2.4 +");
		System.out.println("");
		showInputAndAnswer("42 2.5 + 7 8.2 * /");
		System.out.println("______________________");
		System.out.println("+ When there are invalid operators or numbers");
		showInputAndAnswer("3 8 7 +* - 2");
		System.out.println("");
		showInputAndAnswer("15ds 7 + 8*");
		System.out.println("______________________");
		System.out.println("+ When there are number with 1 digit");
		showInputAndAnswer("3 8 7 + - 2 *");
		System.out.println("______________________");
		System.out.println("+ When there are number with 2 digits");
		showInputAndAnswer("31 8 78 + - 2 *");
		System.out.println("______________________");
		System.out.println("+ When there are number with 3 digits");
		showInputAndAnswer("31 872 788 + - 215 *");
		System.out.println("______________________");
		System.out.println("+ Test exponentiation:");
		showInputAndAnswer("3 2 ^");
		System.out.println("");
		showInputAndAnswer("3 2.2 ^");
		
		// Time complexity: O(n) 	Space complexity: O(n)
	}

}
