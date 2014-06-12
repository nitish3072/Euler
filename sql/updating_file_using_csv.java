import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Scanner;


public class testing_csql_connection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
		Connection c = null;

		Class.forName("org.postgresql.Driver");
		c = DriverManager
				.getConnection("jdbc:postgresql://localhost:5432/roads",
						"postgres", "27033072");
		System.out.println("Opened database successfully");
		
		Statement stm = null;
		stm = c.createStatement();
		// Deleting all elements in the table
		String sql = "DELETE FROM accidents;";
		stm.executeUpdate(sql);
		// Processing the file
		File file = new File("datafile.csv");
		Scanner s = new Scanner(file);
		String str;
		str = s.nextLine(); //exclusing the first line as this is the column names
		while(s.hasNext()) { // Now enclosing the string in braces and then processing string to finally put it into the database.
			str = s.nextLine();
			int index =-1;
			index = str.indexOf("NA");
			if (index !=-1) {
				str = str.substring(0, index) + "null" + str.substring(index +2, str.length());
			}
			index = -1;
			str = "'" + str;
			if((index=str.indexOf(','))!=-1) {
				str = str.substring(0, index) + "'," +str.subSequence(index+1, str.length());
			}
			// Now writing sql codes
			sql = "INSERT INTO accidents (states_uts,year_2006,year_2007,year_2008,year_2009,year_2010,year_2011,year_2012)"
					+ " VALUES ("
					+ str + ");";
	        stm.executeUpdate(sql);
		}
        System.out.println("Database Updated successfully with the csv file");
		s.close();
        stm.close();
        c.close();
	}

}
