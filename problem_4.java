import java.util.*;


public class problem_4 {
	public static void main (String[] args) {
		int number = 100, number1 = 100, answer =0;
		while(number<=999) {
			while(number1<=999) {
				if(palindrome(number*number1))
					if(number*number1 > answer)
						answer = number*number1;
				number1++;
			}
			number1 = 100;
			number++;
		}
	System.out.println(answer);
	}				
			
	public static boolean palindrome(int number) {
		String str = Integer.toString(number);
		String reverse="";
		int length = str.length();
 
		for ( int i = length - 1 ; i >= 0 ; i-- )
			reverse = reverse + str.charAt(i);
		if(reverse.equals(str))
			return true;
		else return false;
	} 
}
