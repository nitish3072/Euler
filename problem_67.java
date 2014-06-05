import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class problem_67 {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("triangle.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		//System.out.println(line.length());
		int[] sum = new int[100];
		int temp,k=0,p=0;
		int[] sum1 = new int[100];
		// Scanner in = new Scanner(System.in);
		int[][] intArray=new int[100][100];
		for(int i =0; i<100;i++) {
			sum[i]=0;
			sum1[i]=0;
		}
		while((line = in.readLine()) != null)
		{	
					for(int j=0;j<line.length()-1;j=j+3) {
						// s= in.nextLine();
						//System.out.print(Integer.parseInt(String.valueOf(line.charAt(j)) + String.valueOf(line.charAt(j+1)))+" ");
						//System.out.println(k);
						intArray[k][p] = Integer.parseInt(String.valueOf(line.charAt(j)) + String.valueOf(line.charAt(j+1)));
						p++;
					}
					//System.out.println();
					k++;
					p=0;
		}		
				
				
			for(int i=0 ;i<100;i++) {
				for(int j=0 ;j<=i;j++) {
					if (i == 0) {
						temp = intArray[i][j];
					}
					else if(j==0) 
						temp = sum[0] + intArray[i][j];
					else if (j==i)
							temp = sum[j-1] + intArray[i][j];
					else {
						if(sum[j] > sum[j-1])
							temp = sum[j] + intArray[i][j];
						else temp = sum[j-1] + intArray[i][j];
					}
					sum1[j] = temp;
				}
				System.arraycopy( sum1, 0, sum, 0, sum1.length );
			}
			
			int max=0;
			for(int i=0;i<100;i++) {
				if(max<sum1[i])
					max = sum1[i];
			}
			
			
			System.out.println(max);
	}
}
