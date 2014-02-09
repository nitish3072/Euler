//600851475143
public class problem_3 {
	public static void main (String[] args) {
		long number = 600851475143L;
		long divisor = 2, answer;
		int counter = 0;
		while(divisor*divisor < number) {
			if (number%divisor == 0) {
				number = number / divisor;
				answer = number;
			}
			else 
				divisor = divisor + 1;
		}
		System.out.println(number);
	}
}
