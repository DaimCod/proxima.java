package service;

import java.io.IOException;
import java.sql.Date;
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
		return response;
	}
	
	public ArrayList<User> selectAllUsers(){
		ArrayList<User> lista = new ArrayList<User>();
		
		Date dateofbirth = Date.valueOf("2000-01-03");
		Timestamp sqlTimestamp = new Timestamp(new Date(0).getTime());
		
		User utente = new User(1,"a@b.c", "aaa", "d", "s", dateofbirth, sqlTimestamp, 1, "image", "note", true);
		
		User utente1 = new User(2,"a@d.d", "aaa", "da", "sh", dateofbirth, sqlTimestamp, 1, "image", "note", true);
		
		lista.add(utente);
		lista.add(utente1);
		
		return lista;
	}
}
