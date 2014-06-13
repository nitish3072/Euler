import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Scanner;


public class updating_file_using_csv {

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
		str = s.nextLine(); //excluding the first line as this is the column names
		while(s.hasNext()) { // Now enclosing the string in braces and then processing string to finally put it into the database.
			str = s.nextLine();
			int index =-1;
			index = str.indexOf("NA");
			if (index !=-1) {
				str = str.substring(0, index) + "-1" + str.substring(index +2, str.length());
			}
			index = -1;
			index = str.indexOf("&");
			if (index !=-1) {
				str = str.substring(0, index) + "and " + str.substring(index +2, str.length());
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
        System.out.println("Table accidents Updated successfully with the csv file");
        finding_max_columns(c);
        population (c);
        per_capita(c);
        vehicle_counts(c);
        accident_per_vehicle(c);
		s.close();
        stm.close();
        c.close();
	}
	
	// Finding out top 3 most accident prone states
	public static void finding_max_columns (Connection c) throws ClassNotFoundException, SQLException {
		Statement stm = null;
		stm = c.createStatement();
		for(int i = 2006;i<=2012;i++) {
			String sql = "SELECT states_uts FROM accidents ORDER BY year_"
					+ i +" DESC NULLS LAST limit 4";
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			rs.next();			
			System.out.print("Top 3 most accident prone states in  " + i + " are: "+ rs.getString("states_uts")+", ");
			rs.next();
			System.out.print(rs.getString("states_uts")+" and ");
			rs.next();
			System.out.println(rs.getString("states_uts"));
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	//Creating new table population with the given csv file
	public static void population (Connection c) throws SQLException, FileNotFoundException{
		Statement stm = null;
		stm = c.createStatement();
		// Deleting all elements in the table
		String sql = "DELETE FROM population;";
		stm.executeUpdate(sql);
		File file = new File("population.csv");
		Scanner s = new Scanner(file);
		String str;
		str = s.nextLine();
		while(s.hasNext()) { // Now enclosing the string in braces and then processing string to finally put it into the database.
			str = s.nextLine();
			int index =-1;
			index = str.indexOf("NA");
			if (index !=-1) {
				str = str.substring(0, index) + "-1" + str.substring(index +2, str.length());
			}
			index = -1;
			str = "'" + str;
			if((index=str.indexOf(','))!=-1) {
				str = str.substring(0, index) + "'," +str.subSequence(index+1, str.length());
			}
			// Now writing sql codes
			sql = "INSERT INTO population (state_uts, population)"
					+ " VALUES ("
					+ str + ");";
	        	stm.executeUpdate(sql);
		}
        	System.out.println("Table population Updated successfully with the csv file");
	}
	
	// Finding most accidents prone states per capita
	public static void per_capita (Connection c) throws SQLException {
		Statement stm = null;
		stm = c.createStatement();
		for(int i = 2006;i<=2012;i++) {
			String sql = "SELECT population.state_uts , accidents.states_uts FROM population, accidents "
					+ "WHERE population.state_uts = accidents.states_uts ORDER BY CAST(NULLIF(accidents.year_"
					+ i +",0) AS FLOAT) / population.population DESC NULLS LAST limit 3";
			ResultSet rs = stm.executeQuery(sql);
			rs.next();		
			System.out.print("Top 3 most accident prone states per capita in  " + i + " are: "+ rs.getString("states_uts")+", ");
			rs.next();
			System.out.print(rs.getString("states_uts")+" and ");
			rs.next();
			System.out.println(rs.getString("states_uts"));
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	//  Creating table for vehicles number
	public static void vehicle_counts (Connection c) throws SQLException, FileNotFoundException{
		Statement stm = null;
		stm = c.createStatement();
		// Deleting all elements in the table
		String sql = "DELETE FROM vehicle_counts;";
		stm.executeUpdate(sql);
		File file = new File("vehicles.csv");
		Scanner s = new Scanner(file);
		String str;
		str = s.nextLine();
		while(s.hasNext()) { // Now enclosing the string in braces and then processing string to finally put it into the database.
			str = s.nextLine();
			int index =-1;
			index = str.indexOf("NA");
			if (index !=-1) {
				str = str.substring(0, index) + "-1" + str.substring(index +2, str.length());
			}
			index = -1;
			str = "'" + str;
			if((index=str.indexOf(','))!=-1) {
				str = str.substring(0, index) + "'," +str.subSequence(index+1, str.length());
			}
			// Now writing sql codes
			sql = "INSERT INTO vehicle_counts (state_ut, number)"
					+ " VALUES ("
					+ str + "*1000);";
	        	stm.executeUpdate(sql);
		}
        	System.out.println("Table vehicle_counts Updated successfully with the csv file");
	}
	
	// Finding which state has most accidents / number of vehicles
	public static void accident_per_vehicle (Connection c) throws SQLException {
		Statement stm = null;
		stm = c.createStatement();
		for(int i = 2006;i<=2012;i++) {
			String sql = "SELECT vehicle_counts.state_ut , accidents.states_uts FROM vehicle_counts, accidents "
					+ "WHERE vehicle_counts.state_ut = accidents.states_uts ORDER BY CAST(NULLIF(accidents.year_"
					+ i +",0) AS FLOAT) / vehicle_counts.number DESC NULLS LAST limit 1";
			ResultSet rs = stm.executeQuery(sql);
			rs.next();		
			System.out.println("Top most most accidents / number of vehicles in  " + i + " are: "+ rs.getString("states_uts"));
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
