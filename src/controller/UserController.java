package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DatabaseConnection;
import database.DatabaseSingleton;
import model.User;

public class UserController {
	public static DatabaseConnection db = DatabaseSingleton.getInstance();
	//dummydata
	public static void createDefaultUsers() {
		boolean res1 = insertUser(new User("Jane123", "Jane@email.com", "123", "123"));
		boolean res2 = insertUser(new User("John123", "John@email.com", "123", "123"));
		boolean res3 = insertUser(new User("Jack123", "Jack@email.com", "123", "123"));
		if(res1 && res2 && res3) {
			System.out.println("User inserted!");
		}
	}
	public static boolean insertUser(User user) {
		if(userExist(user.getEmail())) {
			return false;
		}
		String query = "INSERT INTO users (username, email, password, confirmPassword)"
				+ "VALUES(?,?,?,?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getConfirmPassword());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean loginUser(String username, String password) {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);			
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean userExist(String email) {
		String query = "SELECT COUNT(*) FROM users WHERE email = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery(); // mengeksekusi query dan mendapat hasilnya
			if(rs.next()) { // Memeriksa apakah ada hasilnya
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
