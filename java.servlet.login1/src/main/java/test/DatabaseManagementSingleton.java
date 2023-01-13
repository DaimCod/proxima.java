package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManagementSingleton {

	private static DatabaseManagementSingleton instance;
	
	private DatabaseManagementSingleton() {
		
	}
	
	public static DatabaseManagementSingleton getIstance() {
		if(instance == null) {
			instance = new DatabaseManagementSingleton();
		}
		return instance;
	}
	
	public boolean login(String email, String password) throws Exception{

		boolean login = false;
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "select * from users where email='" + email 
						+ "' AND password ='" + password +"';";
		ResultSet res = cmd.executeQuery(query);
		System.out.println("DEBUG::"+query);
		
		if(res.next())
			login = true;
		
		return login;
	}
	
	public String retriveFromDB() throws Exception{

		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "SELECT * FROM users";
		ResultSet res = cmd.executeQuery(query);

		String daRestituire ="";
		
		daRestituire += "<table>"
				+ "<tr> "
				+ "<th> id </th> "
				+ "<th> email </th> "
				+ "<th> password </th> "
				+ "<th> firstName </th> "
				+ "<th> lastName </th> "
				+ "</tr>";
		
		while (res.next()) {
			
			daRestituire += "<tr>";
			daRestituire += "<td>" +(res.getString(1)) + "</td>";
			daRestituire += "<td>" +(res.getString(2)) + "</td>";
			daRestituire += "<td>" +(res.getString(3)) + "</td>";
			daRestituire += "<td>" +(res.getString(4)) + "</td>";
			daRestituire += "<td>" +(res.getString(5)) + "</td>";
			daRestituire += "<td>" +(res.getString(7)) + "</td>";
			daRestituire += "<td>" +(res.getString(8)) + "</td>";
			daRestituire += "<td>" +(res.getString(9)) + "</td>";
			daRestituire += "<td>" +(res.getString(10)) + "</td>";
			daRestituire += "</tr>";
		}
		
		daRestituire += "</table>";
		
		return daRestituire;
	}
	
	public int insertIntoDB(String email, String password, String firstName,String lastName ,String dateofbirth) throws Exception{


		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "INSERT INTO users(email, password, firstname, lastname , dateofbirth) values"
				+ "('" + email + "'," 
				+ "'" + password + "',"
				+ "'" + firstName + "',"
				+ "'" + lastName + "'," 
				+ "'" + dateofbirth + "',"
				+ "');";
		
		int rowsUpdated = cmd.executeUpdate(query);
		
		return rowsUpdated;
	}

	public void deleteFromDB(String id) throws Exception {
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "DELETE FROM users WHERE id = " + "'" + id + "';";
		
		cmd.executeUpdate(query);

	}
}
