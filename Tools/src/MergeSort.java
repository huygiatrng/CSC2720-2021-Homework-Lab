
public class MergeSort {
	public static void Merge_Sort(int arr[], int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			
			Merge_Sort(arr, l, m);
			Merge_Sort(arr, m + 1, r);
			int n1 = m - l + 1;
			int n2 = r - m;

			int leftArr[] = new int[n1];
			int rightArr[] = new int[n2];

			for (int i = 0; i < n1; ++i)
				leftArr[i] = arr[l + i];
			for (int j = 0; j < n2; ++j)
				rightArr[j] = arr[m + 1 + j];

			int i = 0, j = 0, k = l;

			while (i < n1 && j < n2) {
				if (leftArr[i] <= rightArr[j]) {
					arr[k] = leftArr[i];
					i++;
				} else {
					arr[k] = rightArr[j];
					j++;
				}
				k++;
			}

			while (i < n1) {
				arr[k] = leftArr[i];
				i++;
				k++;
			}
			while (j < n2) {
				arr[k] = rightArr[j];
				j++;
				k++;
			}
		}
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
		
		int[] arr = { 50, 11, 33, 21, 40, 50, 40, 40, 21 };
		arrayPrint(arr);
		Merge_Sort(arr,0,arr.length-1);
		arrayPrint(arr);
		
	}

}
