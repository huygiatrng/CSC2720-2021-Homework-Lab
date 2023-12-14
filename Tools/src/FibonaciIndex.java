
public class FibonaciIndex {
	public static int Fibonaci(int n) {		
		if(n<=2) {
			if(n==0) {
				return 0;
			}
			return 1;
		}else {
			return Fibonaci(n-1)+Fibonaci(n-2);
		}
	}
	public static void main(String[] args) {
       for (int i = 0; i < 100; i++) {
            System.out.print(Fibonaci(i) + " ");
        }
	}
}
