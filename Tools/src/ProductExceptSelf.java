
public class ProductExceptSelf {
	public static void arrayPrint(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length - 1] + "]");
	}

	public static int[] productExceptSelf(int[] arr) {
		int n = arr.length;
		int[] res = new int[n];
		int multiply = 1;
		for (int i = 0; i < res.length; i++) {
			res[i] = 1;
		}
		for (int i = 0; i < n - 1; i++) {
			multiply = multiply * arr[i];
			res[i + 1] = multiply;
		}
		multiply = 1;
		for (int i = n - 2; i >= 0; i--) {
			multiply = multiply * arr[i + 1];
			res[i] = multiply * res[i];
		}
		return res;
	}

	public static int[] productExceptSelfv2(int[] arr) {

		int n = arr.length;
		int[] prefix = new int[n];
		int[] suffix = new int[n];
		int[] res = new int[n];

		prefix[0] = 1;
		for (int i = 1; i < n; i++) {
			prefix[i] = arr[i - 1] * prefix[i - 1];
		}

		suffix[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			suffix[i] = arr[i + 1] * suffix[i + 1];
		}

		for (int i = 0; i < n; i++) {
			res[i] = prefix[i] * suffix[i];
		}

		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		System.out.print("Given array: ");
		arrayPrint(arr);
		System.out.println("======================");
		System.out.print("Answer: ");
		arrayPrint(productExceptSelf(arr));
		System.out.println("* Time Complexity: 0(n)");
		System.out.println("* Space Complexity: 0(1)");

		System.out.println("======================");
		arr = new int[] { 1, 2, 3, 4 };
		System.out.print("Answer: ");
		arrayPrint(productExceptSelfv2(arr));
		System.out.println("* Time Complexity: 0(n)");
		System.out.println("* Space Complexity: 0(n)");

	}
}
