
public class Question1 {
	public static void arrayPrint(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length-1]+"]");
	}
	
	public static int getLargestArea(int[] arr) {
		int area;
		int res = 0;
		for(int i = 0; i<arr.length-1;i++) {
			for(int j = i + 1; j<arr.length;j++) {
				area = (j-i)*(Math.min(arr[i], arr[j]));
				res = Math.max(area, res);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = {1,8,6,2,5,4,8,3,7};
		arrayPrint(arr);
		System.out.print("Answer: "+ getLargestArea(arr));
	}

}
