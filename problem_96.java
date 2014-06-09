import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class problem_96 {
	public static void main(String[] args) throws FileNotFoundException {
		int[][] sudoku = new int[9][9];
		File file = new File("sudoku.txt");
		Scanner s = new Scanner(file);
		String line;
		int sum = 0;
		//line = s.nextLine();
		while(s.hasNext()) {
			for(int i=0;i<9;i++) {
				line = s.nextLine();
				//System.out.println(line);
				if(line.charAt(0) != 'G') {
					sudoku = getSudoku(line,sudoku,i);
				}
				else i--;
			}
			sudoku = preparingSudoku(sudoku);
			StringBuilder sb = new StringBuilder();
			sb.append(sudoku[0][0]).append(sudoku[0][1]).append(sudoku[0][2]);
			String strI = sb.toString();
			sum = sum + Integer.parseInt(strI);
		}
		System.out.println(sum);
	}
	
	public static int[][] getSudoku(String line, int[][] sudoku, int i) {
			for(int j=0;j<9;j++) {
				sudoku[i][j] = Character.getNumericValue(line.charAt(j));	
			}
		return sudoku;
	}
	
	public static int[][] preparingSudoku(int[][] sudoku) {
		int[][] valuesSudoku = new int[81][9];
		int[][] columns = new int[9][9];				// making columns and get the numbers that are already present in them
		columns = initialize(columns);
		int[][] rows = new int[9][9];				// making rows and get the numbers that are already present in columns
		rows = initialize(rows);
		int[][] boxes = new int[9][9];			// making boxes(that are total 9) and get the numbers that are already present in columns
		boxes = initialize(boxes);
		
		for(int i=0;i<9;i++) {					// loops to remove the numbers already present in columns from columns arrays
			for(int j=0;j<9;j++) {
				if(sudoku[i][j]>0)
					rows[i][sudoku[i][j] - 1] = 0;
			}
		}
		for(int i=0;i<9;i++) {					// loops to remove the numbers already present in rows from rows arrays
			for(int j=0;j<9;j++) {
				if(sudoku[j][i]>0)
					columns[i][sudoku[j][i] - 1] = 0;
			}
		}
		
		int[][] boxesIndex = {{0,0}, {0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {			
				int num=i/3;
				int num1=j/3;
				int boxNumber = num*3 + num1;
				if(sudoku[i][j]>0) {
					boxes[boxNumber][sudoku[i][j]-1]=0;
				}
			}
		}
		int[] term = new int[1];
		term[0] = 0;
		solvingSudoku(sudoku, boxes, rows, columns, 0, 0,term);
		/*while(ifsolved(sudoku)==false) {
			for(int i=0;i<9;i++) {					// Now solving sudoku
				for(int j=0;j<9;j++) {
					int num=i/3;
					int num1=j/3;
					int boxNumber = num * 3 +  num1;
					//System.out.println(boxNumber);
					int common=0;
					int value=0,rowPos=0,columnPos=0;
					if(sudoku[i][j]==0) {
						for(int k=0;k<9;k++) {
							if(boxes[boxNumber][k]!=0 && rows[i][k]!=0 && columns[j][k]!=0 && boxes[boxNumber][k]==rows[i][k] && boxes[boxNumber][k]==columns[j][k]) {
								value = boxes[boxNumber][k];
								rowPos = i;
								columnPos = j;
								common++;
							}
						}
						if(common == 1){
							sudoku[i][j]=value;
							rows[i][value-1] =0;
							columns[j][value-1] =0;
							boxes[boxNumber][value-1]=0;
						}
					}
				}
			}
		}*/
		return sudoku;
	}
	
	public static int[][] initialize(int [][] array) {
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				array[i][j] = j+1;
		return array;
		
	}
	public static boolean ifsolved(int[][] array) {
		for(int i=0;i<9;i++) {					// Checking if sudoku solved
			for(int j=0;j<9;j++) {
				if(array[i][j]==0)
					return false;
			}
		}
		return true;		
	}
/*	public static int solvingSudoku(int[][] sudoku, int[][] boxes, int[][] rows, int[][] columns, int i, int j) {
		for(int pos=0;pos<9;pos++) {
			if(rows[i][pos]!=0 && sudoku[i][j] == 0) {
				if(columns[j][rows[i][pos] - 1] == rows[i][pos] && boxes[(i/3)*3 +j/3][rows[i][pos]-1] == rows[i][pos]) {
					return solvingSudoku(rows[i][pos], boxes, rows, columns, i, j);
				}
				else return 
			}
		}
	}
}*/

	public static void solvingSudoku(int[][] sudoku, int[][] boxes, int[][] rows, int[][] columns, int i, int j,int[] term) {
		int tempsudoku,temprows,tempcolumns,tempbox,temp,num,num1,boxNumber,posI,posJ ;
		if(i>=9)
			return ;
		for(int pos=0;pos<9;pos++) {
			if(rows[i][pos]!=0 && sudoku[i][j] == 0) {
				if(columns[j][rows[i][pos] - 1] == rows[i][pos] && boxes[(i/3)*3 +j/3][rows[i][pos]-1] == rows[i][pos]) {
					num=i/3;
					num1=j/3;
					boxNumber = num*3 + num1;
					temp= rows[i][pos];
					tempsudoku = sudoku[i][j]; temprows =rows[i][rows[i][pos]-1]; tempcolumns = columns[j][rows[i][pos]-1]; tempbox =boxes[boxNumber][rows[i][pos]-1];
					sudoku[i][j] = rows [i][pos];
					columns[j][sudoku[i][j]-1] = 0;
					rows[i][sudoku[i][j]-1] = 0;
					boxes[boxNumber][sudoku[i][j]-1] = 0;
					posI = i;posJ = j;
					if (j == 8) {									// If first row is completed then go to next row by j=0 and i++
						posJ = 0;
						posI++;
					}
					else posJ++;
					//System.out.println(i);
					solvingSudoku(sudoku, boxes, rows, columns, posI, posJ, term);
					if(i >= 8)
						term[0] = 1;
					if (term[0] == 1) {
						return;
					}
					else {
						sudoku[i][j] = tempsudoku;
						rows[i][temp-1] = temprows;
						columns[j][temp-1] = tempcolumns;
						boxes[boxNumber][temp-1] = tempbox;
					}
				}
			}
		}
		if( sudoku[i][j] != 0) {
			if (j == 8) {									// If first row is completed then go to next row by j=0 and i++
				j = 0;
				i++;
			}
			else j++;
			solvingSudoku(sudoku, boxes, rows, columns, i, j, term);
			
		}
	}
}
			
			
			
			
			
			
			
	/*	if(columns[i][sudoku[j][i] - 1] != sudoku[i][j] && boxes[(i/3)*3 +j/3][sudoku[i][j]-1] != sudoku[i][j]) {	// If the element is not satisfied then go to previous state
								// Put some allowed value from destination row array
			pos++;
			sudoku[i][j] = temp;
			solvingSudoku(sudoku, boxes, rows, columns, i, j, pos);
		}
		else {
			
		}
		/*if(i>=9&&j>=9) 										// To end recurrsion
			return;
		else if(sudoku[i][j]==0) {
			while(i!=10) {									// Put some allowed value from destination row array
				pos++;
				if(rows[i][pos] !=0){						// Dont change the sudoku yet, just check if the row element can be put or not
					break;
				}
			}
			if(columns[i][sudoku[j][i] - 1] == sudoku[i][j] && boxes[(i/3)*3 +j/3][sudoku[i][j]-1] == sudoku[i][j]){	// If the element is satisfied then go to next state.
				sudoku[i][j] = rows[i][pos];
				columns[i][sudoku[j][i] - 1] = 0;
				rows[i][sudoku[i][j] - 1] = 0;
				boxes[(i/3)*3 +j/3][sudoku[i][j]-1] = 0;
				if (j == 9) {									// If first row is completed then go to next row by j=0 and i++
					j = 0;
					i++;
					pos = 0;
				}
				else j++;
				solvingSudoku(sudoku, boxes, rows, columns, i, j, 0);	
				if(i>=9&&j>=9) 										// To end recurrsion
					return;
				
			}
			else {												// If the element is not satisfied then go to previous state
				
			}
			
		}
		else {
			if (j == 9) {									// If first row is completed then go to next row by j=0 and i++
				j = 0;
				i++;
				pos = 0;
			}
			else j++;
		}*/
		/*if (j==0) {
			pos = 0;			
		}
		prePos = pos;
		while(i<9) {
			if(rows[i][pos]!=0) {
				sudoku[i][j] =rows[i][j];
				pos++;
				columns[i][sudoku[j][i] - 1] = 0;
				rows[i][sudoku[i][j] - 1] = 0;
				boxes[(i/3)*3 +j/3][sudoku[i][j]-1] = 0;
				break;
			}
		}
 		if(boxes[(i/3)*3 +j/3][sudoku[i][j]-1]!=sudoku[i][j] && rows[i][sudoku[i][j] - 1]!=sudoku[i][j] && columns[j][sudoku[i][j] - 1]!=sudoku[i][j]) {
			
		}*/
