import java.util.*;

public class get_time {
	public static void main(String[] args) {
		TimeZone tz = TimeZone.getDefault();
		String copy = args[0];
		if(copy.charAt(0) >='0' && copy.charAt(0) <='9') {		//checking if the first letter in the string is word or a number.
			time_first(copy,tz);					//If First word is a number then directing to time_first
		}
		else {
			region_first(copy,tz);					//Else directing to region_first
		}
	String allZones[] = TimeZone.getAvailableIDs();
	for(int i=0;i<allZones.length;i++)
		System.out.println(allZones[i]);
	System.out.println(copy);
	}

	public static void region_first(String str, TimeZone tz) {
		//
	}

	public static void time_first(String str, TimeZone tz) {
		int store,store_pm; 
		boolean if_pm = false;
		for(int i = 0; ;i++) {
			if(str.compareTo("\"")==0) {
				store = i;	
				break;
			}
			if(str.compareTo("p")==0) {
				if_pm = true;
				store_pm = i;
			}
		}
		String allZones[] = TimeZone.getAvailableIDs();
		for(int i=0;i<allZones.length;i++) {
			
		}						
	}
	
	public static String reqTimezone(String str, int strtPos) {		// Knwing the input string that might contain the timezone
		String result = "";
		result = str.substring(strtPos,str.length());
		return result;
	}

	public static String identifyTimezone(String str, String[] allZones) {	// To know the time zone present in
                for(int i=0;i<allZones.length;i++) {
                        if(allZones[i].indexOf(str)>=0&&allZones[i].indexOf(str)<=allZones[i].length())
				return allZones[i];
		}
		return "nil";
	}
	
	public static String to24Hrs(String str, int endPos) {
			
}
