public class Lab3 {
//	Function to print matrix

	public static void printMatrix(int[][] mat) {
		for (int i =0; i< mat.length;i++) {
			System.out.print("[");
			for (int j =0; j< mat[i].length-1;j++) {
				System.out.printf("%3d,",mat[i][j]);
			}
			System.out.printf("%3d",mat[i][mat[i].length-1]);
			System.out.println("]");
		}
	}
	

//	Function to get a copy of a matrix without reference
	public static int[][] getCopyOf(int[][] mat) {
		int[][] result = new int[mat.length][mat[0].length];
		for (int i =0; i< mat.length;i++) {
			for (int j =0; j< mat[i].length;j++) {
				result[i][j]=mat[i][j];
			}
		}
		return result;
	}

//	Function to rotate matrix clockwise in question 1
	public static int[][] rotateMatrix1(int mat[][]) {
		int n = mat.length;
		int a[][] = new int[n][n];
//		save the matrix with rotated position on an extra array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][(n - 1) - j] = mat[j][i];
			}
		}
		
//		replace matrix elements with elements of extra array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = a[i][j];
			}
		}
		return mat;
	}

//	Function to rotate matrix clockwise in question 2
	public static void rotateMatrix2(int mat[][]) {
		int n = mat.length;
//		Transpose matrix
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}
//		Flip matrix horizontally
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (n / 2); j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[i][n - j - 1];
				mat[i][n - j - 1] = temp;
			}
		}
	}

//	Function to rotate matrix clockwise in question 2 (method2):
	public static void rotateMatrix2ver2(int mat[][]) {
		int left = 0;
		int right = mat.length - 1;
//		Switch left and right with top and bottom on matrix(which makes matrix rotate 90 degrees clockwise.
		while (left < right) {
			int top = left;
			int bottom = right;
//			loop for every element in row or column with = n (matrix size n^2)
			for (int i = 0; i < (right - left); i++) {
//				save top row as temporary
				int temp = mat[top][left + i];
//				replace top row with left column
				mat[top][left + i] = mat[bottom - i][left];
//				replace left column with bottom row
				mat[bottom - i][left] = mat[bottom][right - i];
//				replace bottom row with right column
				mat[bottom][right - i] = mat[top + i][right];
//				replace right column with temporary (top row)
				mat[top + i][right] = temp;
			}
			left++;
			right--;
		}
	}

	public static void main(String[] args) {
		int[][] origMatrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

		System.out.println("Original matrix:");
//		printMatrix(origMatrix);
		printMatrix(origMatrix);
		System.out.println("============================================");
//		QUESTION I:
//		Get copy of original matrix
		int[][] rotatedMat1 = getCopyOf(origMatrix);
		System.out.println("Rotated matrix in question 1:");
//		Rotate matrix and print it
		rotatedMat1 = rotateMatrix1(rotatedMat1);
		printMatrix(rotatedMat1);
		
		System.out.println("============================================");
//		QUESTION II:
//		Get copy of original matrix
		int[][] rotatedMat2 = getCopyOf(origMatrix);
//		Rotate matrix and print it
		System.out.println("(method1)Rotated matrix in question 2:");
		rotateMatrix2(rotatedMat2);
		printMatrix(rotatedMat2);

//		Get copy of original matrix
		int[][] rotatedMat3 = getCopyOf(origMatrix);
//		Rotate matrix and print it
		System.out.println("(method2)Rotated matrix in question 2:");
		rotateMatrix2ver2(rotatedMat3);
		printMatrix(rotatedMat3);

		/*
		 * Both 2 methods in question 2 has Time complexity = 0(n^2) and space complexity = 0(1) because it rotated the matrix on itself without creating any extra matrix. 
		 * In method 1, we transposed the matrix and flip it horizontally, which also rotated the matrix clockwise at 90 degree. 
		 * In method 2, we created one temporary variable to help us rotate the matrix  from top to right, left to top,etc... 
		 * I prefer method 1 because it doesn't require to remember or calculate so much about where should we put or save temporary variable. It also looks more mathematical.
		 */

	}
}
