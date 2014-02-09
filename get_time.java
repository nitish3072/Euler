import java.util.*;

public class get_time {
	public static void main(String[] args) {
		TimeZone tz = TimeZone.getDefault();
		if(args[0].charAt(0) >='0' && args[0].charAt(0) <='9') {	//checking if the first letter in the string is word or a number.
			time_first(args,tz);					//If it is a number then directing to time_first
		}
		else {
			region_first(args,tz);					//Else directing to region_first
		}
	System.out.println(tz.getDisplayName());
	}

	public static void region_first(String[] args, TimeZone tz) {
		//
	}

	public static void time_first(String[] args, TimeZone tz) {
		int store,store_pm; 
		boolean if_pm = false;
		for(int i = 0; ;i++) {
			if(args[i].compareTo("\"")==0) {
				store = i;	
				break;
			}
			if(args[i].compareTo("p")==0) {
				if_pm = true;
				store_pm = i;
			}
		}
		//
	}
}
