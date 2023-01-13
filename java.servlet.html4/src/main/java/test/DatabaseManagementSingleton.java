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
	
	public String retriveFromDB() throws Exception{

		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "SELECT * FROM users_dayaimkazmi";
		ResultSet res = cmd.executeQuery(query);

		String daRestituire ="";
		
		daRestituire += "<table>"
				+ "<tr> "
				+ "<th> id </th> "
				+ "<th> email </th> "
				+ "<th> firstName </th> "
				+ "<th> lastName </th> "
				+ "<th> birthday </th>"
				+ "<th> image path </th>"
				+ "<th> role </th>"
				+ "<th> hr note </th>"
				+ "<th> technical note </th>"
				+ "<th> is enabled </th>"
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
	
	public int insertIntoDB(String email,String firstName,String lastName ,String birthday,String imgpath,String role,String hrnote,String technicalnote,String enabled) throws Exception{


		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "INSERT INTO users_dayaimkazmi(email, firstname, lastname , birthday, imgpath, role, hrnote, technicalnote, enabled) values"
				+ "('" + email + "'," 
				+ "'" + firstName + "',"
				+ "'" + lastName + "'," 
				+ "'" + birthday + "'," 
				+ "'" + imgpath + "'," 
				+ "'" + role + "'," 
				+ "'" + hrnote + "'," 
				+ "'" + technicalnote + "'," 
				+ "'" + enabled 
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
		String query = "DELETE FROM users_dayaimkazmi WHERE id = " + "'" + id + "';";
		
		cmd.executeUpdate(query);

	}
}
