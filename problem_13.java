import java.util.*;

public class problem_13 {
	public static void main(String[] args) {
		int multiply = 1;
		String[] element = new String[50];
			BufferedReader in = new BufferedReader(new FileReader(input_13.txt));
			while (in.ready()) {
			String s = in.readLine();
			}
			in.close();

		for(int i = 0;i<100;i++) 
			for(int j = 0;j<50;j++)
				multiply = multiply * Character.getNumericValue(element[i].charAt(j));
		System.out.println(multiply);
	}
}
