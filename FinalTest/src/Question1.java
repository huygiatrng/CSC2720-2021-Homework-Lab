import java.util.ArrayList;
import java.util.HashSet;


public class Question1 {
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

	public static int[] getCopyOf(int[] arr) {
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		return result;
	}
	
	public static HashSet<Integer> deDuplicate(int[] arr) {
		HashSet<Integer> map = new HashSet<Integer>();
		int n = arr.length;
		for (int i = 0; i < n; ++i)
        {
            // there in the hash map
            if (!map.contains(arr[i])) {
            	map.add(arr[i]);
            }            
        }
		return map;
	}
	
	public static ArrayList<Integer> deDuplicateV2(int[] arr) {
		HashSet<Integer> map = new HashSet<Integer>();
		ArrayList<Integer> output = new ArrayList<Integer>();
		int n = arr.length;
		for (int i = 0; i < n; ++i)
        {
            if (!map.contains(arr[i])) {
            	map.add(arr[i]);
            	output.add(arr[i]);
            }            
        }
		return output;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,2,3,4,4,6,6,6};
		System.out.print("input:");
		arrayPrint(arr);
		System.out.print("output return hashset:");
		System.out.print(deDuplicate(arr));
		
		System.out.print("\noutput return arraylist:");
		System.out.print(deDuplicateV2(arr));
	}

}
