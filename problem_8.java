import java.util.*;
import java.lang.*;
public class problem_8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
 		String element = in.nextLine();
		int temp =0;
		for(int i = 0;i<=element.length()-5;i++) {
			int x = Character.getNumericValue(element.charAt(i))*Character.getNumericValue(element.charAt(i+1))*Character.getNumericValue(element.charAt(i+2))*Character.getNumericValue(element.charAt(i+3))*Character.getNumericValue(element.charAt(i+4));
			if(temp<x)
				temp = x;
		}
	System.out.println(temp);
	}
}
			
