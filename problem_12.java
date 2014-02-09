public class problem_12 {
	public static void main (String[] args) {
                long divisor = 2, old_divi=2, original_number;
                long counter = 0, multiply = 1, number=6, dummy=4;
		original_number = number;
		for(int i = 4 ; multiply<=500 ;i++) {
			multiply = 1;
                	while(number >1) {
                        	if (number%divisor == 0) {
                                	number = number / divisor;
					if(old_divi==divisor)
						counter++;
				}
                        	else {
					divisor = divisor + 1;
					multiply = multiply*(counter+1); 
					old_divi = divisor;
					counter = 0;
				}

               		 }
		number = original_number + i;
		original_number = number;
		multiply = multiply*(counter +1);
		divisor = 2;
		old_divi = 2;
		counter = 0;
		dummy = i;
		}
		System.out.println(number-dummy);
	}
}
