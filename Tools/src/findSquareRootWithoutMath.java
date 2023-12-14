
public class findSquareRootWithoutMath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int l, mid, r, target;
		target = 1024;

		l = 0;
		r = target / 2;
		mid = (l + r) / 2;
		while (l <= r) {
			if (mid * mid > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
			mid = (l + r) / 2;

		}
		System.out.println("Target: " + target);
		System.out.print(mid + " and " + -1 * mid);

	}

}
