
public class Lab2Question1 {
//	Print array function
	public static void arrayPrint(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println(arr[arr.length-1]+"]");
	}
	
//	Sort array with quick sort
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
	
//	Sort array with bubble sort
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
		int[] origArr = {0,1,1,0,1,2,1,2,0,0,0,1};
		System.out.print("Original array: ");
		arrayPrint(origArr);
		
//		Sort array with pubble sort
		origArr = bubbleSortArray(origArr);
		
//		Print sorted array
		System.out.print("Sorted array with bubble sort: ");
		arrayPrint(origArr);
		
		System.out.println("========================================================");
		
//		Redifine original array
		origArr = new int[]{0,1,1,0,1,2,1,2,0,0,0,1};
		System.out.print("Original array: ");
		arrayPrint(origArr);
		
//		Sort array with quick sort
		origArr = quickSortArray(origArr,0,origArr.length-1);
		
//		Print sorted array
		System.out.print("Sorted array with quick sort: ");
		arrayPrint(origArr);
	}

}
