import java.math.* ;
public class problem_16 {
	public static void main(String[] args) {
		BigInteger multi = new BigInteger("1");
		BigInteger ten = new BigInteger("10");
		BigInteger compare = new BigInteger("0");
		BigInteger sum = new BigInteger("0");
		BigInteger two = new BigInteger("2");
		for(int i =0;i<1000;i++) 
			multi=multi.multiply(two);
	//	System.out.println(multi);
		while(multi.compareTo(compare)>0) {
	//		System. out.println(multiply);
			sum =sum.add(multi.mod(ten));
			multi=multi.divide(ten);
	//		System.out.println(multi);
		}
	System.out.println(sum);
	}
}
