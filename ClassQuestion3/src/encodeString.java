
public class encodeString {

	public static int stringToint(String str) {
		int i = 0, number = 0;
		boolean isNegative = false;
		int len = str.length();
		if (str.charAt(0) == '-') {
			isNegative = true;
			i = 1;
		}
		while (i < len) {
			number *= 10;
			number += (str.charAt(i++) - '0');
		}
		if (isNegative) {
			number = -number;
		}
		return number;
	}

	public static int encodeStr(String input) {
		int len = input.length();
		int res = 0;

		if (len == 0)
			return 1;

		while (input.charAt(0) == '0') {
			input = input.substring(1);
			len = input.length();
		}

		if (len == 1)
			return 1;

		if (isValid(input.substring(len - 1, len))) {
			res += encodeStr(input.substring(0, len - 1));
		}
		if (isValid(input.substring(len - 2, len))) {
			res += encodeStr(input.substring(0, len - 2));
		}
		return res;
	}

	public static boolean isValid(String s) {
		if (s.length() == 0)
			return false;
		int i = stringToint(s);
		if (i >= 1 && i <= 26)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// Test
		String input = "121";
		System.out.print("ANSWER: " + encodeStr(input));
	}

}
