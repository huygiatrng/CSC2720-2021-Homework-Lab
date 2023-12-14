
public class BubbleSort {
	public static int[] bubbleSortArray(int[] arr) {
		int temp;
//		Loop with every element of array
		for(int i=0;i<arr.length;i++) {
//			Loop with every element after i element
			for(int j=i;j<arr.length;j++) {
//				Compare them and modify position of them if needed
				if(arr[i]>arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
