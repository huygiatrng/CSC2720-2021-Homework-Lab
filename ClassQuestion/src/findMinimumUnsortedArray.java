public class findMinimumUnsortedArray {

	public static void main(String[] args) {

//		int[] arr = { 5, 7, 10, 15, 17, 1, 2 };
		int[] arr = { 16, 19, 21, 10, 15, 17, 20, 24, 42, 2, 6, 9, 12 };
		int r, l, m, res;
		r = arr.length - 1;
		l = 0;
		m = (r + l) / 2;
		res = arr[0];

		while (l <= r) {
			if (arr[l] <= arr[r]) {
				res = Math.min(res, arr[m]);
				if (res > arr[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			} else {
				res = Math.min(res, arr[m]);
				if (res > arr[l]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			m = (r + l) / 2;
		}
		System.out.println(res);
	}

}
