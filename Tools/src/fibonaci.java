
public class fibonaci {
	public static int Fibonaci(int n) {
		if(n<=1) {
			return n;
		}else {
			return Fibonaci(n-1)+Fibonaci(n-2);
		}
	}
	public static void main(String[] args) {
		int numberOfStair = 0;
		System.out.print(Fibonaci(numberOfStair));
	}
}