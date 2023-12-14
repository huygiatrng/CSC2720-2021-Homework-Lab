
public class Lab2Question2 {
//	Print array function
	public static void arrayPrint(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length-1]+"]");
	}
	
	public static int digitsArrToNumber(int[] arr) {
		int num = 0;
//		Loop through index of array
		for (int i = 0; i < arr.length; i++) {
//			take element at i index multiply with 10 power of (length of array - 1 - i index)
//			Example: input [1,0,0], answer is 1*10^(3-1-0) + 0 + 0 = 1* 10^2 = 100
			num+= arr[i]*Math.pow(10,(arr.length-1-i));
		}
		return num;
	}
	
	public static void main(String[] args) {
		int[] origArr = {1,9,8,9};
//		Print original array
		System.out.print("Original array: ");
		arrayPrint(origArr);
		
//		Turn array of digits to number and add 1 into it
		int answer = digitsArrToNumber(origArr)+1;
		
//		Print answer
		System.out.print("Answer: "+answer);
	}

}
