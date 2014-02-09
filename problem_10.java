public class problem_10 {
	public static void main(String[] args) {
		long number = 4, sum = 5, count = 2;
		while(number<2000000) {
			if(prime(number)) {
				count++;
				sum = sum + number;
			}
		number++;
		}	
	System.out.println(sum);
	}
        public static boolean prime(long number) {
                for(int i=2;i*i<=number;i++) {
                        if(number%i==0)
                                return false;
                }
                return true;
        }
}
