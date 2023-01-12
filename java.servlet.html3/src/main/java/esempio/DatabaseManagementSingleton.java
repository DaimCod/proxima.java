package esempio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

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
	
	public String retriveFromServer() throws Exception{

		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "SELECT * FROM messages";
		ResultSet res = cmd.executeQuery(query);

		String daRestituire ="";
		
		daRestituire += "<table>";
		
		while (res.next()) {
			daRestituire += "<tr>";
			daRestituire += "<td>" +(res.getString(2)) + "</td>" ;
			daRestituire += "<td>" +(res.getString(3)) + "</td>";
			daRestituire += "<td>" +(res.getString(4)) + "</td>";
			daRestituire += "<td>" +(res.getString(5)) + "</td>";
			daRestituire += "</tr>";
		}
		
		daRestituire += "</table>";
		
		return daRestituire;
	}
	
	public int insertIntoDB(String username, String textMessage, String ldt) throws Exception{

		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "INSERT INTO messages(userName, textMessage, userInsertedTime ,serverReceivedTime) values"
				+ "('" + username + "'," 
				+ "'" + textMessage + "',"
				+ "'" + ldt + "'," 
				+ "'" + ldt + "');";
		
		int rowsUpdated = cmd.executeUpdate(query);
		
		return rowsUpdated;
	}
}
