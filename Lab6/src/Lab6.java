
public class Lab6 {
//	Print array
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

//	Get a copy of array
	public static int[] getCopyOf(int[] arr) {
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}
	
//	Sort array with quick sort
	public static void mergeSortArray(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSortArray(arr, l, m);
			mergeSortArray(arr, m + 1, r);

			//define length and left-right arrays.
			int n1 = m - l + 1;
			int n2 = r - m;
			int leftArr[] = new int[n1];
			int rightArr[] = new int[n2];

			//copy elements from original array to left and right arrays
			for (int i = 0; i < n1; ++i)
				leftArr[i] = arr[l + i];
			for (int j = 0; j < n2; ++j)
				rightArr[j] = arr[m + 1 + j];

			int i = 0, j = 0,k=l;

			// loop 2 pointers through two arrays and sort them until one of the pointer reach the end of its array
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

			// Copy left and right array to original
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

// De-duplicate Function
	public static int[] deDuplicate_v1(int[] arr) {
		// Start de-duplicate array
		// Catch null input array
		try {
			int n = arr.length;
			if (n == 0) {
				System.out.print("*You cannot deduplicate a empty array* ");
				return getCopyOf(arr);
			} else if (n < 2) {
				return getCopyOf(arr);
			}
			mergeSortArray(arr,0,n-1);
			int lenOfRes = 1;
			int pointer = arr[0];
			for (int i = 1; i < n; i++) {
				if (pointer != arr[i]) {
					lenOfRes += 1;
					pointer = arr[i];
				}
			}
			int[] res = new int[lenOfRes];
			res[0] = arr[0];
			// Create 2 pointers, one goes on original array, one goes on de-duplicated
			// array(on original array)
			int resIndex = 0;
			int origIndex = 1;
			while (origIndex < n) {
				// If we see new element that is different from last element de-duplicated area
				// on array, we will plus 1 in result size and add that element to that position
				if (arr[origIndex] != res[resIndex]) {
					resIndex += 1;
					res[resIndex] = arr[origIndex];
				}
				origIndex += 1;
			}
			// return the size of area that we has done de-duplication
			return res;
		} catch (NullPointerException e) {
			System.out.println("You cannot deduplicate a null array");
			int[] res = {};
			return res;
		}
	}

	public static int deDuplicate_v2(int[] arr) {
		// Start de-duplicate array
		// Catch null input array
		try {
			int n = arr.length;
			if (n == 0) {
				System.out.print("*You cannot deduplicate a empty array* ");
				return -1;
			} else if (n < 2) {
				return 1;
			}
			mergeSortArray(arr,0,n-1);
			// Create 2 pointers, one goes on original array, one goes on de-duplicated
			// array(on original array)
			int resIndex = 0;
			int origIndex = 1;
			while (origIndex < n) {
				// If we see new element that is different from last element de-duplicated area
				// on array, we will plus 1 in result size and add that element to that position
				if (arr[origIndex] != arr[resIndex]) {
					resIndex += 1;
					arr[resIndex] = arr[origIndex];
				}
				origIndex += 1;
			}
			// return the size of area that we has done de-duplication
			return resIndex + 1;
		} catch (NullPointerException e) {
			System.out.println("You cannot deduplicate a null array");
			return -1;
		}
	}

	public static void main(String[] args) {
//		TESTING
		int[] arr = { 50, 11, 33, 21, 40, 50, 40, 40, 21 };
		System.out.print("Original Array: ");
		arrayPrint(arr);

		System.out.println("===============================================");

		System.out.print("*Function return array: \nDeduplicated Array: ");
		arrayPrint(deDuplicate_v1(arr));

		System.out.println("===============================================");

		System.out.print(
				"*Function deduplicate right on original array then return length of result: \nDeduplicated Array: [");

		int sizeAfterDeduplicate = deDuplicate_v2(arr);

//		If size of result is -1, don't print the array
		if (sizeAfterDeduplicate >= 0) {
//			Loop through the result index in original array and print them
			for (int i = 0; i < sizeAfterDeduplicate - 1; i++) {
				System.out.print(arr[i] + ",");
			}
			System.out.println(arr[arr.length - 1] + "]");
		}

		System.out.println("\nTEST=SPECIAL=CASES=========================");

		System.out.println("When array is null");
		System.out.print("Original Array: ");
		int[] arr2 = {};
		arrayPrint(arr2);
		System.out.print("Deduplicated Array: ");
		int[] res2 = deDuplicate_v1(arr2);
		arrayPrint(res2);

		System.out.println("\nWhen array length is 1");
		System.out.print("Original Array: ");
		int[] arr3 = { 75 };
		arrayPrint(arr3);
		System.out.print("Deduplicated Array: ");
		int[] res3 = deDuplicate_v1(arr3);
		arrayPrint(res3);

		System.out.println("\nWhen array length is 2 and they are the same");
		System.out.print("Original Array: ");
		int[] arr4 = { 75, 75 };
		arrayPrint(arr4);
		System.out.print("Deduplicated Array: ");
		int[] res4 = deDuplicate_v1(arr4);
		arrayPrint(res4);

		System.out.println("\nWhen array length is 2 and they are not the same");
		System.out.print("Original Array: ");
		int[] arr5 = { 75, 90 };
		arrayPrint(arr5);
		System.out.print("Deduplicated Array: ");
		int[] res5 = deDuplicate_v1(arr5);
		arrayPrint(res5);
	}
}
