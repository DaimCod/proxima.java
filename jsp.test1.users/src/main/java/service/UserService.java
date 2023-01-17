package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.DatabaseManagerSingleton;
import pojo.User;

public class UserService {
	
	private static UserService instance;

	private UserService() {
		
	}

	public static UserService getIstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public boolean login (String email, String password) {
		System.out.println("login - START");
		boolean responseValue = false ;
		User userRetrieved = null;
		try {
			userRetrieved = DatabaseManagerSingleton.getInstance().getUser(email, password);
			responseValue = userRetrieved!=null ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseValue ;
	}
	
	public boolean insert(User userToInsert) {
		//insert into DB
		System.out.println("insert");
		boolean response = false;
		
		try {
			
			response = DatabaseManagerSingleton.getInstance().insertUser(userToInsert);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public boolean insert(String email, String password, String firstname, String lastname, String dateofbirth) throws Exception {
		
		Timestamp regdate = new Timestamp(new Date(0).getTime());
		Integer role = 10;
		String image = "image";
		String note = "nota di servizio";
		boolean enabled = false;
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setDateofbirth(Date.valueOf("2023-01-17"));
		user.setRegdate(regdate);
		user.setRole(role);
		user.setImgpath(image);
		user.setNote(note);
		user.setEnabled(enabled);
		
		System.out.println("ora chiamo l'altro insert");
		return insert(user);
		
	}
	
	public ArrayList<User> selectAllUsers(){
		
		ArrayList<User> lista = null;
		
		try {
			lista = DatabaseManagerSingleton.getInstance().getUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

	public boolean deleteUser(int id) {
		
		boolean response = false;
		
		try {
			
			response = DatabaseManagerSingleton.getInstance().deleteUserById(id);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
