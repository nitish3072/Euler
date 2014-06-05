public class problem_67 {

	public static void main(String args[]) {
		String s;
		Scanner in = new Scanner(System.in);
		s = in.nextLine();
		int[] intArray=new int[100];
		int[][] sol=new int[100][200];
			for(int i=0;i<100;i++) {
				for(j=0;j<i;j++)
					intArray[j] = Integer.parseInt(String.valueOf(s.charAt(j)));
				if(i=0) {
					sol[0][0] = intArray[0];
					break;
				}
				
				for(int j=0;j<2*i;i++) {
					sol[i][j] = 
