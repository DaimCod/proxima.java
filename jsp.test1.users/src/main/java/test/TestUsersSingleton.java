package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import pojo.User;

public class TestUsersSingleton {
	private static TestUsersSingleton instance;

	private TestUsersSingleton() {
		
	}

	public static TestUsersSingleton getIstance() {
		if (instance == null) {
			instance = new TestUsersSingleton();
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

	public int signup(String email, String password, String firstname, String lastname, String dateofbirth) throws Exception {
		
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		Statement cmd = con.createStatement();
		String query = "select * from users where email='" + email + "';";
		ResultSet res = cmd.executeQuery(query);
		
		while(res.next()) {
			if(res.getString("email").equals(email))
				return 0;
		}
		
		Timestamp regdate = new Timestamp(new Date(0).getTime());
		int role = 10;
		String image = "image";
		String note = "nota di servizio";
		int enabled = 0;
		
		System.out.println(email+ password+firstname+ lastname+ dateofbirth);
		query = null;
		query = "INSERT INTO users(email, password, firstname, lastname, dateofbirth, regdate, role, imgpath, note, enabled) values"
				+ "('" + email + "'," 
				+ "'" + password + "',"
				+ "'" + firstname + "',"
				+ "'" + lastname + "'," 
				+ "'" + dateofbirth + "',"
				+ "'" + regdate + "',"
				+ "'" + role + "',"
				+ "'" + image + "',"
				+ "'" + note + "',"
				+ "'" + enabled
				+ "');";
		
		int rowsUpdated = cmd.executeUpdate(query);
		return rowsUpdated;
	}
}
