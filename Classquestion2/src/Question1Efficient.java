
public class Question1Efficient {
	static void arrayPrint(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length-1]+"]");
	}
	
	static int getMaxArea(int[] arr,int n) {
		int l = 0;
		int r = arr.length-1;
		int area = 0;
		
		while(l<r) {
			area = Math.max(area,Math.min(arr[l], arr[r]) * (r - l));
			if(arr[l]<arr[r])
				l+=1;
			else 
				r-=1;
		}
		return area;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,8,6,2,5,4,8,3,7};
		arrayPrint(arr);
		System.out.print("Answer: "+getMaxArea(arr, arr.length));
		
		
	}

}
