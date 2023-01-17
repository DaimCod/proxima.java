/**
 *
 */
package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

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

	// maybe da eliminare
	public String executeSelectFromDB() throws SQLException, ClassNotFoundException, IOException {
		System.out.println("executeSelectFromDB - START");
		StringBuilder sb = new StringBuilder();
		Connection con = null;
		try {
			con = getConnection();
		} catch (Exception e) {

			e.printStackTrace();
		}

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
		System.out.println("doGet - END: " + sb.toString());
		return sb.toString();
	}

	public User getUser(String email, String password) {

		User userToReturn = null;
		try {
			
			Connection con = getConnection();
			Statement cmd = con.createStatement();
			String query = "select * from users where email='" + email + "' AND password ='" + password + "';";
			ResultSet res = cmd.executeQuery(query);
			
			if (res.next()) {
				
				userToReturn = new User();
				userToReturn.setId(res.getInt(1));
				userToReturn.setEmail(res.getString(2));
				userToReturn.setPassword(res.getString(3));
				userToReturn.setFirstname(res.getString(4));
				userToReturn.setLastname(res.getString(5));
				userToReturn.setDateofbirth(Date.valueOf(res.getString(6)));
				userToReturn.setRegdate(res.getTimestamp(7));
				userToReturn.setRole(res.getInt(8));
				userToReturn.setImgpath(res.getString(9));
				userToReturn.setNote(res.getString(10));
				userToReturn.setEnabled(res.getBoolean(11));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userToReturn;
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> listUsers = new ArrayList<User>();

		try {

			Connection con = getConnection();
			Statement cmd = con.createStatement();
			String query = "SELECT * FROM users;";
			ResultSet res = cmd.executeQuery(query);

			while (res.next()) {

				User user = new User();
				user.setId(res.getInt(1));
				user.setEmail(res.getString(2));
				user.setPassword(res.getString(3));

				listUsers.add(user);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return listUsers;
	}

	public boolean deleteUserById(int id) {

		try {

			Connection con = getConnection();
			Statement cmd = con.createStatement();
			String query = "DELETE FROM users WHERE id = " + id + ";";
			int rowsUpdated = cmd.executeUpdate(query);
			if (rowsUpdated > 0) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean insertUser(User userToInsert) {

		try {

			Connection con = getConnection();
			Statement cmd = con.createStatement();

			String query = "select * from users where email='" + userToInsert.getEmail() + "';";
			ResultSet res = cmd.executeQuery(query);

			while (res.next()) {
				if (res.getString("email").equals(userToInsert.getEmail())) {
					System.out.println("Ho trovato un utente esistente - ritorno FALSE");
					return false;
				}
			}

			int enable = 0;
			System.out.println("enable è " + userToInsert.isEnabled());
			if(userToInsert.isEnabled()) {
				enable = 1;
			}
			
			query = "INSERT INTO users(email, password, firstname, lastname, dateofbirth, regdate, role, imgpath, note, enabled) values"
					+ "('" + userToInsert.getEmail() + "'," + "'" + userToInsert.getPassword() + "'," + "'"
					+ userToInsert.getFirstname() + "'," + "'" + userToInsert.getLastname() + "'," + "'"
					+ userToInsert.getDateofbirth() + "'," + "'" + userToInsert.getRegdate() + "'," + "'"
					+ userToInsert.getRole() + "'," + "'" + userToInsert.getImgpath() + "'," + "'"
					+ userToInsert.getNote() + "'," + "'" + enable + "');";

			int rowsUpdated = cmd.executeUpdate(query);
			System.out.println("rowsUpdated: " + rowsUpdated);
			if (rowsUpdated > 0) {
				System.out.println("DBSing ritorno TRUE");
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("DBSing ritorno TRUE");
		return false;
	}

	public boolean updateUserById(int id, User userToUpdate) {
		// TODO Auto-generated catch block
		return false;
	}

	private Connection getConnection() throws Exception {
		String driver = PropertiesManagerSingleton.getInstance().getProperty("database.mysql.driver");
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

		return con;
	}
}
