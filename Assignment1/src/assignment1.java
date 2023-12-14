public class assignment1 {
	public static int[] intersectionLoopJoin(int[] arr1, int[] arr2) {
		int n1 = arr1.length;
		int n2 = arr2.length;
		int[] res = new int[0];

		// if length of them are greater than 0, else return empty array
		if ((n1 < 1) || (n2 < 1)) {
			return res;
		}
		// If input array is not sorted, start sorting using quick sort function
		if (!IsArraySorted(arr1, n1)) {
			System.out.println("(First array is unsorted. It will be sorted right now.)");
			quickSortArray(arr1, 0, n1 - 1);
		}
		if (!IsArraySorted(arr2, n2)) {
			System.out.println("(Second array is unsorted. It will be sorted right now.)");
			quickSortArray(arr2, 0, n2 - 1);
		}

		int count = 0;
		// Loop through each pair of elements in arrays
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				if (arr1[i] == arr2[j]) {
					if (res.length == 0) {
						res = addOneMoreElementToArray(res, arr1[i]);
						count += 1;
					} else {
						if (res[count - 1] != arr1[i]) {
							res = addOneMoreElementToArray(res, arr1[i]);
							count += 1;
						}
					}
				}
			}
		}
		return res;
	}

	public static int[] intersectBinarySearch(int[] arr1, int[] arr2) {
		int n1 = arr1.length;
		int n2 = arr2.length;
		int[] res = new int[0];
		int mark = 0;
		int ifFound;

		// if length of them are greater than 0, else return empty array
		if ((n1 < 1) || (n2 < 1)) {
			return res;
		}
		// If input array is not sorted, start sorting using quick sort function
		if (!IsArraySorted(arr1, n1)) {
			System.out.println("(First array is unsorted. It will be sorted right now.)");
			quickSortArray(arr1, 0, n1 - 1);
		}
		if (!IsArraySorted(arr2, n2)) {
			System.out.println("(Second array is unsorted. It will be sorted right now.)");
			quickSortArray(arr2, 0, n2 - 1);
		}
		// For loop on first array and find it in second array in second array
		for (int i = 0; i < arr1.length; i++) {
			// Avoid checking same value in first array which cause duplicated value in
			// result
			if (i == 0 || (i > 0 && arr1[i] != arr1[i - 1])) {
				ifFound = binarySearch(arr2, mark, arr2.length - 1, arr1[i]);
				if (ifFound > -1) {
					mark = ifFound;
					res = addOneMoreElementToArray(res, arr1[i]);
				}
			}
		}
		return res;
	}
	
	
	public static int[] intersectTwoPointers(int[] arr1, int[] arr2) {
	    int p1=0, p2=0;
	    int[] res = new int[0];
	    while(p1<arr1.length && p2<arr2.length){
	        if(arr1[p1]<arr2[p2]){
	            p1+=1;
	        }else if(arr1[p1]>arr2[p2]){
	            p2+=1;
	        }else{
	        	if (res.length==0||res[res.length-1] != arr1[p1]) {
	        		res = addOneMoreElementToArray(res, arr1[p1]);
	        	}
	            p1+=1;
	            p2+=1;
	        }
	    }
	    return res;
	}

	// Binary search function
	public static int binarySearch(int[] arr, int l, int r, int x) {
		if (r >= l) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == x)
				return mid;
			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);
			return binarySearch(arr, mid + 1, r, x);
		}
		return -1;
	}

	// Function to add one element to array
	public static int[] addOneMoreElementToArray(int[] arr, int valueToAdd) {
		int[] temp = arr;
		arr = new int[arr.length + 1];
		for (int i = 0; i < temp.length; i++) {
			arr[i] = temp[i];
		}
		arr[arr.length - 1] = valueToAdd;
		return arr;
	}

	// Check if array is sorted
	public static boolean IsArraySorted(int arr[], int n) {
		if (n == 0 || n == 1)
			return true;
		for (int i = 1; i < n; i++) {
			if (arr[i - 1] > arr[i])
				return false;
		}
		return true;
	}

	public static int[] quickSortArray(int[] arr, int l, int r) {
		// Only run if left boundary is smaller than right boundary
		if (l < r) {
			int temp;
			// Choose pivot and start compare it with each element until reach element that
			// is lesser than pivot
			int pivot = arr[r];
			int index = (l - 1);

			for (int i = l; i <= r - 1; i++) {
				if (arr[i] < pivot) {
					index++;
					// Swap
					temp = arr[index];
					arr[index] = arr[i];
					arr[i] = temp;
				}
			}
			temp = arr[index + 1];
			arr[index + 1] = arr[r];
			arr[r] = temp;
			// Create an partition index of the current array and keep sorting two sides of
			// it
			int partitionIndex = index + 1;
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
			System.out.println("  NULL ARRAY  ]");
		}
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 5, 6, 6, 9, 9, 9, 11, 11, 21 };
		int[] arr2 = { 6, 6, 9, 11, 21, 21, 21 };
		System.out.print("Join Loop:\nArray1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectionLoopJoin(arr1, arr2));

		arr1 = new int[] { 1, 5, 6, 6, 9, 9, 9, 11, 11, 21 };
		arr2 = new int[] { 6, 6, 9, 11, 21, 21, 21 };
		System.out.print("_________________________________\nBinary search:\nArray1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));

		// Test cases
		System.out.println("\nTEST CASES");
		System.out.println("=================================\nIf any in 2 input arrays is empty, return null array.");
		arr1 = new int[0];
		arr2 = new int[] { 6, 6, 9, 11, 21, 21, 21 };
		System.out.print("Array1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));
		arr1 = new int[] { 1, 5, 6, 6, 9, 9, 9, 11, 11, 21 };
		arr2 = new int[0];
		System.out.print("_________________________________\nArray1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));
		arr1 = new int[0];
		arr2 = new int[0];
		System.out.print("=================================\nArray1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));

		System.out.println("\n=================================\nIf any in 2 input arrays is unsorted, sorted it and keep going.");
		arr1 = new int[] { 1, 5, 9, 21, 11, 17, 62, 6, 9, 6, 6, 9, 9, 9, 11, 11, 21 };
		arr2 = new int[] { 6, 6, 9, 72, 11, 9, 9, 6, 21, 11, 21 };
		System.out.print("Array1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));
		
		System.out.println("\n=================================\nAlso work if any of input arrays have size is 1.");
		arr1 = new int[] { 21};
		arr2 = new int[] { 6, 6, 9, 11, 21, 21, 21 };
		System.out.print("Array1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectBinarySearch(arr1, arr2));

		/* We can use 2 pointer run through 2 array input. Let it p1 and p2 and an empty array (int[] result = int[0])
		 * if arr1[p1] < arr2[p2], p1+=1, else if arr1[p1] > arr2[p2], p2+=2
		 * if arr1[p1] == arr2[p2], add arr1[p1] into result array if last element of result array is not arr1[p1] or size of result==0
		 * then p1+=1 and p2+=2. Run until one of 2 pointer are out of index in 2 array.(p1>=arr1.length||p2>=arr2.length)
		 * In the worst case number of loop we have to run is sum of length of 2 arrays ( O(m+n))
		 */
		System.out.println("\n=================================\nTest solution 3: 2 Pointers only work with 2 SORTED ARRAYS");
		arr1 = new int[] { 1, 5, 6, 6, 9, 9, 9, 11, 11, 21 };
		arr2 = new int[] { 6, 6, 9, 11, 21, 21, 21 };
		System.out.print("Array1: ");
		arrayPrint(arr1);
		System.out.print("Array2: ");
		arrayPrint(arr2);
		System.out.print("Result: ");
		arrayPrint(intersectTwoPointers(arr1, arr2));

		
	}

}
