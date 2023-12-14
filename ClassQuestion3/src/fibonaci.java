
public class fibonaci {
	public static int Fibonaci(int n) {
		if(n<=2) {
			return 1;
		}else {
			return Fibonaci(n-1)+Fibonaci(n-2);
		}
	}
	public static void main(String[] args) {
		int numberIndex = 4;
		System.out.print(Fibonaci(numberIndex));
	}
}