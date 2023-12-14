import java.util.Scanner;

public class lab1 {
	
	int getGuessedNumber(Scanner input, int l, int r, int previousGuess ) {
		int mid = (l + r) / 2;
		
		if(mid != previousGuess) {
            System.out.print("Is your number: "+mid+"?\n");
            System.out.print("Please enter C for correct, H for too high, or L for too low.\r\n"
            		+ "Enter your response (H/L/C): ");
            char userAnswer = input.nextLine().toUpperCase().charAt(0);
            while (userAnswer!='H'&&userAnswer!='L'&&userAnswer!='C') {
            	System.out.print("Enter your response (H/L/C): ");
            	userAnswer = input.nextLine().charAt(0);
            }
            
            if (userAnswer=='H') {
            	return getGuessedNumber(input,l, mid, mid);
            }else if(userAnswer=='L'){
            	return getGuessedNumber(input, mid, r, mid);
            }else{
            	return 0;
            }
        }
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		lab1 thisClass = new lab1();		
		int n=1;
		
		System.out.print("Enter n: ");
		while (true){
			if 	(n<1) {
				System.out.println("Enter a positive integer for n: ");
			}else {
				try {
					n = Integer.parseInt(input.nextLine());
					break;
				}catch(NumberFormatException ignore) {
					System.out.println("Enter a positive integer for n: ");
				}
			}
		}
		System.out.println("Welcome to Guess My Number!");
		System.out.println("Please think of a number between 0 and "+(n-1)+".");
		
		int result = thisClass.getGuessedNumber(input,0,n,-1);
		
		if (result==-1) {
			System.out.println("Your number should not between from 0 and "+(n-1)+".");
			System.out.println("Please try again!");
			System.out.println("Thank you for playing Guess My Number!");
		}else if(result==0){
			System.out.println("Thank you for playing Guess My Number!");
		}
		

	}

}
