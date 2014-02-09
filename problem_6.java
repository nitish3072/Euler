public class problem_6 {
	public static void main(String[] args) {
		int square_sum = 0, sum =0;
		for(int i = 1;i<=100;i++)
			sum = sum + i;
		for(int j = 1; j<=100;j++)
			square_sum = square_sum + j*j;
		System.out.println(sum*sum - square_sum);
	}
}
