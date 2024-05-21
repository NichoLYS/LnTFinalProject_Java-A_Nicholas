package main;

import controller.MenuController;
import controller.UserController;
import database.DatabaseConnection;
import database.DatabaseSingleton;
import javafx.application.Application;
import javafx.stage.Stage;
import view.RegistrationPage;

public class Main extends Application{
	 public DatabaseConnection db = DatabaseSingleton.getInstance();
	 public UserController userController = new UserController();
	 public MenuController menuController = new MenuController();
	public static void main(String[] args) {
		new Main();
		launch(args);
	}
	
	public Main() {
		db.migrateTable();
		userController.createDefaultUsers();
		menuController.createDefaultData();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		RegistrationPage registerPage = new RegistrationPage(arg0);
		arg0.setScene(registerPage.getScene());
		arg0.show();
	}

}

