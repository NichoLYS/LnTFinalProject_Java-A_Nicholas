package view;

import controller.UserController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class LoginPage {
	
	protected Stage stage;
	protected Scene scene;
	
	protected BorderPane rootNode;
	protected GridPane gridPane;
	protected VBox vbox;
	
	protected Label loginText, usernameText, passwordText;
	protected TextField usernameTF;
	protected PasswordField passwordTF;
	protected Button loginBtn;
	
	protected void init() {
		rootNode = new BorderPane();
		gridPane = new GridPane();
		scene = new Scene(rootNode, 500, 500);
		
		loginText = new Label("Login Here!");
		loginText.setStyle("-fx-font-family: 'Impact'; -fx-font-size: 24; -fx-text-fill: black");
		
		usernameText = new Label("Username: ");
		usernameText.setStyle("-fx-font-family: 'Lucida Bright'; -fx-font-size: 16; -fx-text-fill: black");
		
		passwordText = new Label("Password: ");
		passwordText.setStyle("-fx-font-family: 'Lucida Bright'; -fx-font-size: 16; -fx-text-fill: black");
		
		usernameTF = new TextField();
		passwordTF = new PasswordField();
		
		loginBtn = new Button("Login");
	}
	
	protected void loginFunction() {
		EventHandler<MouseEvent> LoginEvent = event -> {
			String username = usernameTF.getText();
			String password = passwordTF.getText();
			if(!username.isEmpty() && !password.isEmpty()) {
				Boolean response = UserController.loginUser(username, password);
				if(response) {
					// redirect ke homepage
					HomePage homePage = new HomePage(stage);
					stage.setScene(homePage.getScene());
					stage.show();
				} else {
					showAlert("Login Error", "Password mismatch / user doesn't exist");
				}
			}
			else {
				showAlert("Login Error", "Please fill the field");
			}
		};
		loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, LoginEvent);
	}
	protected void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	protected void setLayout() {
		vbox = new VBox(8);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(loginBtn);
		
		gridPane.add(loginText, 1, 0);
		gridPane.add(usernameText, 1, 1);
		gridPane.add(usernameTF, 1, 2);
		gridPane.add(passwordText, 1, 3);
		gridPane.add(passwordTF, 1, 4);
		gridPane.add(vbox, 1, 5);
		gridPane.setVgap(10);
		rootNode.setCenter(gridPane);
		
		BorderPane.setMargin(gridPane, new Insets(120, 100, 120, 180));
		
		loginBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
				+ "-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-text-fill: black");
	}
	
	protected Scene getScene() {
		return this.scene;
	}
	
	protected LoginPage(Stage stage) {
		init();
		setLayout();
		loginFunction();
		this.stage = stage;
		this.stage.setTitle("Login Page");
		
	}

}
