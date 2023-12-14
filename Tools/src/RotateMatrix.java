
public class RotateMatrix {

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
		// TODO Auto-generated method stub

	}

}
