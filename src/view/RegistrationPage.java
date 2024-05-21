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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import request.RegisterRequest;

public class RegistrationPage {
	protected Stage stage;
	protected Scene scene;
	
	protected BorderPane rootNode;
	protected GridPane gridPane;
	
	protected VBox vbox;
	
	protected Label titleLabel, usernameLabel, emailLabel, passLabel, confirmPassLabel;
	protected TextField usernameTF, emailTF;
	protected PasswordField passTF, confirmPassTF;
	protected Button registerBtn, loginBtn;
	
	protected void init() {
		rootNode = new BorderPane();
		gridPane = new GridPane();
		
		scene = new Scene(rootNode, 500, 500);
		
		titleLabel = new Label("Please Register an Account!");
		titleLabel.setStyle("-fx-font-size: 24; -fx-font-family: 'Impact'; -fx-text-fill: black;");
		
		usernameLabel = new Label("Username: ");
		usernameLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		emailLabel = new Label("Email: ");
		emailLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		passLabel = new Label("Password: ");
		passLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		confirmPassLabel = new Label("Confirm Password: ");
		confirmPassLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		usernameTF = new TextField();
		emailTF = new TextField();
		passTF = new PasswordField();
		confirmPassTF = new PasswordField();
		
		registerBtn = new Button("Register");
		
		EventHandler<MouseEvent> RegisterEvent = event -> {
			RegisterRequest req = new RegisterRequest();
			req.setUsername(usernameTF.getText());
			req.setEmail(emailTF.getText());
			req.setPassword(passTF.getText());
			req.setConfirmPassword(confirmPassTF.getText());
			if(!req.getUsername().isEmpty() && !req.getEmail().isEmpty() && !req.getPassword().isEmpty() && !req.getConfirmPassword().isEmpty()) {
				if(req.getPassword().equals(req.getConfirmPassword())) {
					User newUser = new User(req.getUsername(), req.getEmail(), req.getPassword(), req.getConfirmPassword());
					Boolean response = UserController.insertUser(newUser);
					if(response) {
						// redirect ke login page
						LoginPage newPage = new LoginPage(stage);
						stage.setScene(newPage.getScene());
						stage.show();
					} else {
						showAlert("Registration Error", "User already exists");
					}					
				} else {
					showAlert("Registration Error", "Password Doesn't match");
				}
			} else {
				showAlert("Registration Error", "Please fill in the fields");
			}
		};
		registerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, RegisterEvent);
		
		loginBtn = new Button("Login");
		
		EventHandler<MouseEvent> redirectLoginEvent = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				LoginPage loginPage = new LoginPage(stage);
				stage.setScene(loginPage.getScene());
				stage.show();
			}
		};
		loginBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, redirectLoginEvent);
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
		vbox.getChildren().addAll(registerBtn, loginBtn);
		
		gridPane.add(titleLabel, 0, 0);
		gridPane.add(usernameLabel, 0, 1);
		gridPane.add(usernameTF, 0, 2);
		gridPane.add(emailLabel, 0, 3);
		gridPane.add(emailTF, 0, 4);
		gridPane.add(passLabel, 0, 5);
		gridPane.add(passTF, 0, 6);
		gridPane.add(confirmPassLabel, 0, 7);
		gridPane.add(confirmPassTF, 0, 8);
		gridPane.add(vbox, 0, 9);
		gridPane.setVgap(10);
		rootNode.setCenter(gridPane);
		
		
		registerBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
				+ "-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-text-fill: black");
		
		loginBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
				+ "-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-text-fill: black");
		
		BorderPane.setMargin(gridPane, new Insets(50, 100, 100, 120));
		
		
	
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public RegistrationPage(Stage stage) {
		// TODO Auto-generated constructor stub
		init();
		setLayout();
		this.stage = stage;
		this.stage.setTitle("Registration Page");
	}
	
	

}
