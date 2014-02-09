public class problem_7 {
	
	public static void main (String[] args) {
		int start = 1, count = 2, answer=0;
		while(true) {
			if(prime(start*6 - 1)) {
				count ++;
				answer = start*6 - 1;
			}
			if(count == 10001)
			break;
			if(prime(start*6 + 1)) {       
                                count ++;
				answer = start*6 + 1;
			}
                        if(count == 10001)
                        break;
			start++;
		}
	System.out.println(answer);
	}

	public static boolean prime(int number) {
		for(int i=2;i*i<=number;i++) {
			if(number%i==0)
				return false;
		}
		return true;
	}
}
