import java.util.Stack;

public class Calculator2 {

	public static char[] OPERATORLIST = { '+', '-', '/', '*', '^' };

	public static double[] calculator(String inputString) {
		Stack<Double> operandStack = new Stack<Double>();
		Stack<Character> operatorStack = new Stack<Character>();
		boolean validInput = true;
		double[] resultAfterCalculate;
		double[] output = new double[] { 0, 0 };

		if (inputString == "") {
			validInput = false;
			output[1] = -1;
			return output;
		} else {
			char[] inputArr = inputString.toCharArray();
			for (int i = 0; i < inputArr.length; i++) {
				String num = "";
				if (inputArr[i] == ' ') {
					continue;
				} else if (inputArr[i] >= '0' && inputArr[i] <= '9') {
					num += inputArr[i];
					while (i + 1 < inputArr.length
							&& ((inputArr[i + 1] >= '0' && inputArr[i + 1] <= '9') || (inputArr[i + 1] == '.'))) {
						num += inputArr[i + 1];
						i++;
					}
					if (isNumeric(num)) {
						if(operandStack.empty()&&!operatorStack.empty()&&operatorStack.peek()=='-') {
							operatorStack.pop();
							operandStack.push(-1*Double.parseDouble(num));
						}else if(!operatorStack.empty()&&operatorStack.peek()=='-'){
							operatorStack.pop();
							if (!operatorStack.empty()&&(operatorStack.peek()=='(')) {
								operandStack.push(-1*Double.parseDouble(num));
								System.out.print("Dit1");
							}else {
								operatorStack.push('-');
								operandStack.push(Double.parseDouble(num));
								System.out.print("Dit3");
							}
						}else{
							operandStack.push(Double.parseDouble(num));
						}
						
					} else {
						validInput = false;
						System.out.println("Syntax Error");
						output[1] = -1;
						return output;
					}

//					i -= 1;
				} else if (inputArr[i] == '(') {
					operatorStack.push(inputArr[i]);
				} else if (inputArr[i] == ')') {
					if (validSizeOfStack(operandStack, 2)) {
						while (operatorStack.peek() != '(') {
							resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(),
									operandStack.pop());
							if (resultAfterCalculate[1] == 0) {
								operandStack.push(resultAfterCalculate[0]);
							} else if (resultAfterCalculate[1] == -1) {
								validInput = false;
								System.out.println("Syntax Error");
								output[1] = -1;
								return output;
							}else if (resultAfterCalculate[1] == -2) {
								validInput = false;
								System.out.println("Math Error");
								output[1] = -2;
								return output;
							} else {
								validInput = false;
								System.out.println("Power has to be an integer.");
								output[1] = -3;
								return output;
							}
						}
					}else if (validSizeOfStack(operandStack, 1)){
						continue;
					}else {
						validInput = false;
						output[1] = -1;
						return output;
					}
					operatorStack.pop();
				} else if (contain(inputArr[i], OPERATORLIST)) {
					while (!operatorStack.empty() && hasPrecedence(inputArr[i], operatorStack.peek())) {
						if (validSizeOfStack(operandStack, 2)) {
							resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(),
									operandStack.pop());
							if (resultAfterCalculate[1] == 0) {
								operandStack.push(resultAfterCalculate[0]);
							} else if (resultAfterCalculate[1] == -1) {
								validInput = false;
								System.out.println("Syntax Error");
								output[1] = -1;
								return output;
							}else if (resultAfterCalculate[1] == -2) {
								validInput = false;
								System.out.println("Math Error");
								output[1] = -2;
								return output;
							} else {
								validInput = false;
								System.out.println("Power has to be an integer.");
								output[1] = -3;
								return output;
							}
						} else {
							validInput = false;
							output[1] = -1;
							return output;
						}
					}
					operatorStack.push(inputArr[i]);
				}
			}
			while (!operatorStack.empty()) {
				if (validSizeOfStack(operandStack, 2)) {
					resultAfterCalculate = useOperator(operatorStack.pop(), operandStack.pop(), operandStack.pop());
					if (resultAfterCalculate[1] == 0) {
						operandStack.push(resultAfterCalculate[0]);
					} else if (resultAfterCalculate[1] == -1) {
						validInput = false;
						System.out.println("Syntax Error");
						output[1] = -1;
						return output;
					}else if (resultAfterCalculate[1] == -2) {
						validInput = false;
						System.out.println("Math Error");
						output[1] = -2;
						return output;
					} else {
						validInput = false;
						System.out.println("Power has to be an integer.");
						output[1] = -3;
						return output;
					}
				} else if (validSizeOfStack(operandStack, 1)){
					output[1] = 0;
					output[0]=operandStack.pop();
					return output;
				}else {
					validInput = false;
					output[1] = -1;
					return output;
				}
			}
			if (validInput == false) {
				output[1] = -1;
				return output;
			}

			if (operandStack.empty()) {
				output[1] = -1;
				return output;
			}
			output[0] = operandStack.pop();
			return output;
		}
	}

	public static boolean hasPrecedence(char a, char b) {
		// Prioritize the calculations in brackets first. Then exponentiation =>
		// multiplication and division => and finally addition and subtraction.
		if (b == '(' || b == ')')
			return false;
		if ((a == '^') && (b == '*' || b == '/' || b == '+' || b == '-'))
			return false;
		if ((a == '*' || a == '/') && (b == '+' || b == '-'))
			return false;
		else
			return true;
	}

	public static boolean contain(char key, char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumeric(String input) {
		return input.matches("[-+]?\\d*\\.?\\d+");
	}

	public static boolean validSizeOfStack(Stack<Double> a, int size) {
		return (a.size() >= size);
	}

	public static double[] useOperator(char op, double a, double b) {
		double[] result = new double[] { 0, 0 };		
		switch (op) {
		case '+':
			result[0] = b + a;
			return result;
		case '-':
			result[0] = b - a;
			return result;
		case '*':
			result[0] = b * a;
			return result;
		case '/':
			if (a == 0) {
				result[1] = -2;
			} else {
				result[0] = b / a;
			}
			return result;
		case '^':
			double c = 1;
			// the power have to be integer, not decimal.
			if (a % 1 == 0) {
				int j = (int)a;
				while(j!=0) {
					if(a<0) {
						j+=1;
					}else {
						j-=1;
					}
					c *= b;
				}
				if(a<0) {
					c = 1/c;
				}
				System.out.println(c);
				result[0] = c;
			} else {
				System.out.println("Please use integer for power of exponentiation.");
				result[1] = -3;
			}
			return result;
		}
		return result;
	}

	public static String processOperation(String input) {

		double[] output = calculator(input);
		if (output[1] == -1) {
			return "SYNTAX ERROR";
		} else if (output[1] == -2) {
			return "MATH ERROR";
		}else if (output[1] == -3) {
			return "PLEASE USE INTEGER FOR POWER OF EXPONENTIATION";
		}
		else {
			return String.valueOf(output[0]);
		}
	}
	public static void main(String[] args) {
		String input = "18+2+(-18)";
		System.out.println("="+processOperation(input));

	}

}
