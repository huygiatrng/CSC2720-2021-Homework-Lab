import java.util.Stack;

public class Calculator {

	public static String[] OPERATORLIST = { "+", "-", "/", "*", "^" };

	// Function for in fix calculating, input a string, output will be a double
	// array with size is 2,
	// FIrst element is the answer, and the second element is the flagged error
	public static double[] calculator(String inputString) {
		//Create two stacks to store operand and operator
		Stack<Double> operandStack = new Stack<Double>();
		Stack<String> operatorStack = new Stack<String>();
		boolean validInput = true;
		double[] resultAfterCalculate;
		double[] output = new double[] { 0, 0 };
		
		
		// return syntax error if input is null
		if (inputString.trim() == ""||(inputString.chars().filter(ch -> ch == '(').count()!=inputString.chars().filter(ch -> ch == ')').count())) {
			validInput = false;
			output[1] = -1;
			return output;
		} else {
			//Trim the string, split it by space and store them in an array
			String[] inputList = inputString.trim().split(" ");
			int len = inputList.length;
			// Check if the last element is operator, return syntax error
			if (contain(inputList[inputList.length - 1], OPERATORLIST)) {
				validInput = false;
				output[1] = -1;
				return output;
			}
			// always store previous string, it helps calculator to process when receive a minus symbol later
			String previousString = inputList[0];
			// loop through every element
			for (int i = 0; i < len; i++) {
				// if element is number (allow with both integer and double)
				if (isNumeric(inputList[i])) {
					operandStack.push(Double.parseDouble(inputList[i]));
					//Save the previous String 
					if (i > 0) {
						previousString = inputList[i];
					}
				} else if (inputList[i].equals("(")) {
					// if get open bracket, save to operator stack
					operatorStack.push(inputList[i]);
//					(((inputList[i].equals("-") && operandStack.isEmpty() && operatorStack.isEmpty())||(inputList[i].equals("-") && !operatorStack.isEmpty() && (previousString.equals("^") || previousString.equals("*") || previousString.equals("+")
//							|| previousString.equals("/"))))) {
				} else if ((inputList[i].equals("-"))&&((i==0)||(!operatorStack.isEmpty() && (previousString.equals("^") || previousString.equals("*") || previousString.equals("+")
						|| previousString.equals("/"))))) {
					// When minus symbol at the beginning, or previous of minus symbol is a symbol
					// instead of save minus symbol, check if next element is number then add -1*numberElement to the operand stack
					// This case is really important to help calculator to understand something likes
					// 18*-2 3^-2 or 7+-2
					// The calculator with know it as
					// 18*(-2) 3^(-2) or 7+(-2)
					if (isNumeric(inputList[i] + inputList[i + 1])) {
						operandStack.push(-1 * Double.parseDouble(inputList[i + 1]));
						i += 1;
					} else {
						//if the next element is not number, return error
						validInput = false;
						output[1] = -1;
						return output;
					}
				} else if (inputList[i].equals(")")) {
					//When close bracket, start calculate all operation inside the brackets.
					
					if (validSizeOfStack(operandStack, 2)) {
						while (!operatorStack.peek().equals("(")) {
							resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(),
									operandStack.pop());
							if (resultAfterCalculate[1] == 0) {
								operandStack.push(resultAfterCalculate[0]);
							} else {
								output[1] = checkErrorInResult(resultAfterCalculate);
								validInput = false;
								return output;
							}
						}
					} else if (validSizeOfStack(operandStack, 1)) {
						continue;
					} else {
						validInput = false;
						output[1] = -1;
						return output;
					}
					operatorStack.pop();
					
				} else if (contain(inputList[i], OPERATORLIST)) {
					// If receive the symbol, check if the calculation takes precedence over the previous one					
					while (!operatorStack.empty() && hasPrecedence(inputList[i], operatorStack.peek())) {
						if (validSizeOfStack(operandStack, 2)) {
							resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(),
									operandStack.pop());
							if (resultAfterCalculate[1] == 0) {
								operandStack.push(resultAfterCalculate[0]);
							} else {
								output[1] = checkErrorInResult(resultAfterCalculate);
								validInput = false;
								return output;
							}
						} else {
							validInput = false;
							output[1] = -1;
							return output;
						}
					}
					operatorStack.push(inputList[i]);
				}
				// Update previous string everyloop
				if (i > 0) {
					previousString = inputList[i];
				}
			}
			// Again, until it is no more operator in operator stack, calculate by pop out every 2 items from operand and 1 item from operator
			while (!operatorStack.empty()) {
				if (validSizeOfStack(operandStack, 2)) {
					resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(), operandStack.pop());
					if (resultAfterCalculate[1] == 0) {
						operandStack.push(resultAfterCalculate[0]);
					} else {
						output[1] = checkErrorInResult(resultAfterCalculate);
						validInput = false;
						return output;
					}
				} else if (validSizeOfStack(operandStack, 1)) {
					output[1] = 0;
					output[0] = operandStack.pop();
					return output;
				} else {
					validInput = false;
					output[1] = -1;
					return output;
				}
			}
			// For some special cases, I added a flag checker here for debug or update
			if (validInput == false) {
				output[1] = -1;
				return output;
			}
			// return error if the operand stack is empty
			if (operandStack.empty()) {
				output[1] = -1;
				return output;
			}
			// pop the last item from operand
			output[0] = operandStack.pop();
			return output;
		}
	}

	// Function to check which the operator has precedence
	public static boolean hasPrecedence(String a, String b) {
		// Prioritize the calculations in brackets first. Then exponentiation =>
		// multiplication and division => and finally addition and subtraction.
		if (b.equals("(") || b.equals(")"))
			return false;
		if ((a.equals("^")) && (b.equals("*") || b.equals("/") || b.equals("+") || b.equals("-")))
			return false;
		if ((a.equals("*") || a.equals("/")) && (b.equals("+") || b.equals("-")))
			return false;
		else
			return true;
	}

	// Function to check if a array contains the key data in it
	public static boolean contain(String key, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(key)) {
				return true;
			}
		}
		return false;
	}

	// Function to flag error depend on the result
	public static double checkErrorInResult(double[] result) {
		if (result[1] == -1) {
			System.out.println("Syntax Error");
			return result[1];
		} else if (result[1] == -2) {
			System.out.println("Math Error");
			return result[1];
		} else {
			System.out.println("Power has to be an integer.");
			return result[1];
		}
	}

	// Function to check if the string is numeric (double or integer)
	public static boolean isNumeric(String input) {
		return input.matches("[-+]?\\d*\\.?\\d+");
	}

	// Function to check if size of stack is greater than the parameter
	public static boolean validSizeOfStack(Stack<Double> a, int size) {
		return (a.size() >= size);
	}

	// Function to calculation with given 2 operands with the operator
	public static double[] useOperator(String op, double a, double b) {
		double[] result = new double[] { 0, 0 };
		switch (op) {
		case "+":
			result[0] = b + a;
			return result;
		case "-":
			result[0] = b - a;
			return result;
		case "*":
			result[0] = b * a;
			return result;
		case "/":
			if (a == 0) {
				result[1] = -2;
			} else {
				result[0] = b / a;
			}
			return result;
		case "^":
			double c = 1;
			// the power have to be integer, not decimal.
			if (a % 1 == 0) {
				int j = (int) a;
				while (j != 0) {
					if (a < 0) {
						j += 1;
					} else {
						j -= 1;
					}
					c *= b;
				}
				if (a < 0) {
					c = 1 / c;
				}
				result[0] = c;
			} else {
				result[1] = -3;
			}
			return result;
		}
		return result;
	}

	// Function to return string depend on the flagged error. Return error or return
	// result
	public static String processOperation(String input) {

		double[] output = calculator(input);
		if (output[1] == -1) {
			return "SYNTAX ERROR";
		} else if (output[1] == -2) {
			return "MATH ERROR";
		} else if (output[1] == -3) {
			return "PLEASE USE INTEGER FOR POWER OF EXPONENTIATION";
		} else {
			double res = output[0];
			if (res % 1.0 == 0) {
				return String.format("%.0f", res);
			} else {
				return String.format("%.2f", res);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Original question:");
		String input = "10 * 2 - 15";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("\n______TEST_CASES______");
		System.out.println("+ When input is null:");
		input = "";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When input are only operators:");
		input = "*";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("");
		input = "* / +";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When input are only operands (no operators)=> print the last number in input:");
		input = "82";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When number of operators more than the calculation we need to process:");
		input = "17 *";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("");
		input = "15 * 27 + *";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When input have double number instead of integer:");
		input = "3.8 + 2.4";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("");
		input = "42 + 2.5 * 7 / 8.2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When there are invalid operators or numbers");
		input = "35 *+ 15";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("");
		input = "35ds + 15";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When there are number with 1 digit");
		input = "3 + 8 / 2 - 5 * 5";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When there are number with 2 digits");
		input = "31 + 15 - 25 - 15";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ When there are number with 3 digits");
		input = "315 + 159 - 145 * 151 / 101";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ Test exponentiation:");
		input = "3 ^ 2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println(processOperation(""));
		System.out.println("");
		input = "3 ^ 2.2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("______________________");
		System.out.println("+ Test Precedence:");
		input = "42 + 2.5 * 7 / 8.2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("You can see that it calculates *,/ and ^ before + and - ");
		input = "2 * 8 ^ 2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("You can see that it calculates ^ before * and / ");
		input = "( 2 * 8 ) ^ 2";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("You can see that it calculates operation in brackets before *, /  and ^ ");
		System.out.println("______________________");
		System.out.println("+ Test the humanness of the computer when performing calculations with a negative number but the - sign is not with the number.:");
		input = "2 + - 4";
		System.out.println(input + " = " + processOperation(input));
		input = "2 * - 4";
		System.out.println(input + " = " + processOperation(input));
		input = "2 ^ - 4";
		System.out.println(input + " = " + processOperation(input));
		input = "- 5 + 6";
		System.out.println(input + " = " + processOperation(input));
		System.out.println("+ When some of number is negative:");
		input = "30 + 8 + -15";
		System.out.println(input + " = " + processOperation(input));
		input = "19 * -2";
		System.out.println(input + " = " + processOperation(input));
		input = "19 * -2 + 8 - 15 + 18 / -2";
		System.out.println(input + " = " + processOperation(input));
		input = "19 * -2 )";
		System.out.println(input + " = " + processOperation(input));
		
		//Time complexity: O(n)
		//Time complexity: O(n)

	}

}
