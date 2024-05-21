package database;

public class DatabaseSingleton {
	private static DatabaseConnection instance;
	public static DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

}
