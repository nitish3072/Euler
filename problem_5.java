public class problem_5 {
	public static void main(String[] args) {
		int number = 1;
		int count = 1;
		for(;;) {
			while(count <= 20) {
				if (number%count!=0)
					break;
				count++;
			}
			if(count<=20) {
				number++;
				count = 1;
			}
		else break;
		}
		System.out.println(number);
	}
}
