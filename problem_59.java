import java.util.Scanner;

public class problem_59 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String text;
		//System.out.println(s.delimiter());
		text = s.nextLine();
		s.close();
		
		int num1=0,num2 = 0,num3 = 0; // 3 numbers will be needed as the encrypt is using alphabets
		String[] ans = text.split(",");	// Splitting the strings around comma
		System.out.println(ans[3]);
		int[] key = {97,97,97};
		//System.out.println(ans.length);
		for(int i=0;i<ans.length;i=i+3) {
			num1 = Integer.parseInt(ans[i]);
			key[0] = operation(num1,key[0]);
			if((i+1)<ans.length) {
				num2 = Integer.parseInt(ans[i+1]);
				key[1] = operation(num2,key[1]);
			}
			if((i+2)<ans.length) { 
				num3 = Integer.parseInt(ans[i+2]);
				key[2] = operation(num3,key[2]);
			}
		}
		
		int sum = 0, num2Xor =0, num3Xor= 0;
		for(int i=0;i<ans.length;i=i+3) {
			num1 = Integer.parseInt(ans[i]);
			if((i+1)<ans.length) {
				num2 = Integer.parseInt(ans[i+1]);
				num2Xor = xor(num2,key[1]);
			}
			else num2Xor = 0;
			if((i+2)<ans.length) {
				num3 = Integer.parseInt(ans[i+2]);
				num3Xor = xor(num3,key[2]);
			}
			else num3Xor = 0;
			
			sum = sum + xor(num1,key[0]) + num2Xor + num3Xor;
		}
		System.out.println(sum);
	}
	
	public static int operation(int num, int key) {
		while(key<=122) {
			if((xor(num,key)>=65) && (xor(num,key)<=90) || (xor(num,key)>=48) && (xor(num,key)<=57)) {
				return key;
			}
			else if(xor(num,key)>=97&&xor(num,key)<=122){
				return key;
			}
			else if(xor(num,key)==32||xor(num,key)==33||xor(num,key)==40||xor(num,key)==41||xor(num,key)==44||xor(num,key)==59||xor(num,key)==46||xor(num,key)==39){
				return key;
			}
			else {
				key++;
			}
		}
		//System.out.println(key);
		return key-1;
	}
	
	public static int xor(int number1, int number2) {
		int result = number1^number2;
		return result;		
	}
}
