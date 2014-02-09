public class problem_2 {
	public static void main(String[] args) {
		int fibo = 2, sum = 2, previous = 1, temp;
		while(fibo<=4000000) {
			temp = previous;
			previous = fibo;
			fibo = fibo + temp;
			if (fibo%2 == 0)
				sum = sum + fibo;
		}
	System.out.println(sum);
	}
}
