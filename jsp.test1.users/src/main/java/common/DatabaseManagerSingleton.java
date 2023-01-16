/**
 *
 */
package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.User;

/**
 * Singleton manager class that provides to retrieve properties
 * 
 * @author maurizio.franco@ymail.com
 *
 */
public class DatabaseManagerSingleton {

	
	
	private static DatabaseManagerSingleton instance;

	private DatabaseManagerSingleton() throws IOException {
	}

	public static DatabaseManagerSingleton getInstance() throws IOException {
		if (instance == null) {
			instance = new DatabaseManagerSingleton();
			
		}
		return instance;
	}
	
	public String executeSelectFromDB () throws SQLException, ClassNotFoundException, IOException {
		System.out.println("executeSelectFromDB - START");
		StringBuilder sb = new StringBuilder () ;
//		String returnValue = "" ;
		//String driver = "org.mariadb.jdbc.Driver";
		String driver = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.driver");
		System.out.println("cerco classe " + driver + " e' nel cp");
		Class.forName(driver);

		// stringa di connessione
		String host = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.host");
		String port = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.port");
		String dbName = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.db.name");
		String url = "jdbc:mariadb://" + host + ":" + port + "/" + dbName;
		// connessione con username e password
		String username = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.db.username");
		String password = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.db.password");
		Connection con = DriverManager.getConnection(url, username, password);

		System.out.println("sono riuscito a connettermi al database urs: " + url);

		Statement cmd = con.createStatement();
		String query = "select * from messages;";
		ResultSet res = cmd.executeQuery(query);
        System.out.println("DEBUG result set fetch size: " + res.getFetchSize());
        sb.append("<table>");
		while (res.next()) {
			sb.append("<tr>");
			
			sb.append("<td>");
			sb.append(res.getString(1));
			sb.append("</td>");
			sb.append("<td>");			
			sb.append(res.getString(2));
			sb.append("</td>");
			sb.append("<td>");			
			sb.append(res.getString(3));
			sb.append("</td>");
			
			sb.append("</tr>");			
		}
		sb.append("</table></body></html>");
		res.close();
		cmd.close();
		System.out.println("doGet - END: " + sb.toString ());
		return sb.toString () ;
	}

	public User getUser (String email, String password) {
		System.out.println("getUser - START - email: " + email + " - password: " + password);
		User userToReturn = null ;
		try {
			String driver = "org.mariadb.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
			// connessione con email e password
			Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
			Statement cmd = con.createStatement();
			String query = "select * from users where email='"+email+"' AND password ='" + password + "';";
			System.out.println("DEBUG - query: " + query); 
			ResultSet res = cmd.executeQuery(query);
	        System.out.println("DEBUG user with password found?: " + res != null);
			if (res.next()) {
				userToReturn = new User () ;
				userToReturn.setId(res.getInt(1));
				userToReturn.setEmail(res.getString(2));
				userToReturn.setPassword(res.getString(3));
				//
				//
				//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userToReturn ;		
	}
}
