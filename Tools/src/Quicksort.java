
public class Quicksort {
	public static int[] quickSortArray(int[] arr, int l, int r) {
//		Only run if left boundary is smaller than right boundary
		if (l<r){
			int temp;
//			Choose pivot and start compare it with each element until reach element that is lesser than pivot
			int pivot = arr[r];
			int index = (l - 1);
			
			for(int i = l;i<=r-1; i++) {
				if (arr[i] < pivot){
		            index++;
//		            Swap
		            temp = arr[index];
		            arr[index] = arr[i];
		            arr[i] = temp;
		        }
			}
			
	        temp = arr[index+1];
	        arr[index+1] = arr[r];
	        arr[r] = temp;
//	        Create an partition index of the current array and keep sorting two sides of it
	        int partitionIndex = index+1;
	        quickSortArray(arr, l, partitionIndex - 1);
	        quickSortArray(arr, partitionIndex + 1, r);
		}
        return arr;
	}
	public static void arrayPrint(int[] arr) {
		// Catch null array error when printing array
		try {
			System.out.print("[");
			for (int i = 0; i < arr.length - 1; i++) {
				System.out.print(arr[i] + ",");
			}
			System.out.println(arr[arr.length - 1] + "]");
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			System.out.println("  Cannot print null array  ]");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
