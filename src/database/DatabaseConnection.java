package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import controller.UserController;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/puddingdb", "root", "");
			statement = connection.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void migrateTable() {
		createUsersTable();
		createMenuTable();
	}
	
	public void createUsersTable() {
		String query = "CREATE TABLE IF NOT EXISTS users("
				+ "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "username VARCHAR(50) NOT NULL,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "password VARCHAR(50) NOT NULL,"
				+ "confirmPassword VARCHAR(50) NOT NULL)";
		exec(query);
	}
	
	public void createMenuTable() {
		String query = "CREATE TABLE IF NOT EXISTS menu("
				+ "KodeMenu CHAR(6) PRIMARY KEY,"
				+ "NamaMenu VARCHAR(50) NOT NULL,"
				+ "HargaMenu INT NOT NULL,"
				+ "StokMenu INT NOT NULL)";
		exec(query);
	}
	
	
	public void exec(String query) {
		try {
			statement.execute(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
