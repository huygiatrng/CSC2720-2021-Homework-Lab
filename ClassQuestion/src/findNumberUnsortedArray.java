
public class findNumberUnsortedArray {

	public static void main(String[] args) {
		int r, l, m, target;
		int[] arr = { 5, 7, 10, 15, 17, 1, 2 };
//		for (int element : arr) {
//
//			r = arr.length - 1;
//			l = 0;
//			m = (r + 1) / 2;
//			target = element;
//
//			while ((arr[m] != target) && (l <= r)) {
//				if (arr[l] <= arr[m]) {
//					if ((target > arr[m]) || (target < arr[l])) {
//						l = m + 1;
//					} else {
//						r = m - 1;
//					}
//				} else {
//					if ((target < arr[m]) || (target > arr[r])) {
//						r = m - 1;
//					} else {
//						l = m + 1;
//					}
//				}
//				m = (r + l) / 2;
//			}
//			if (l > r) {
//				System.out.println("FAILED TO FIND TARGET IN ARRAY");
//			} else {
//				System.out.println(m);
//			}
//		}
		r = arr.length - 1;
		l = 0;
		m = (r + 1) / 2;
		target = 17;

		while ((arr[m] != target) && (l <= r)) {
			if (arr[l] <= arr[m]) {
				if ((target > arr[m]) || (target < arr[l])) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			} else {
				if ((target < arr[m]) || (target > arr[r])) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			m = (r + l) / 2;
		}
		if (l > r) {
			System.out.println("FAILED TO FIND TARGET IN ARRAY");
		} else {
			System.out.println(m);
		}

	}

}
