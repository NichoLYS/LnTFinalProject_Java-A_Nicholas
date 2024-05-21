package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeletePage {
	Stage stage;
	Scene scene;
	BorderPane rootNode;
	VBox vbox, buttonBox;
	ComboBox<String> comboBox;
	Label namaMenuTxt;
	Button submitBtn, backBtn;
	
	public void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		
		namaMenuTxt = new Label("Nama Menu: ");
		
		submitBtn = new Button("Submit");
		backBtn = new Button("Back");
		
		comboBox = new ComboBox<>();
		
		List<String> namaMenu = MenuController.getNamaMenu();
		comboBox.setItems(FXCollections.observableArrayList(namaMenu));
	}
	
	 protected void showAlert(String title, String message) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();
		}
	 
	 public void setLayout() {
			
			buttonBox = new VBox(10);
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.getChildren().addAll(submitBtn, backBtn);
			
			vbox = new VBox(8);
			vbox.setAlignment(Pos.CENTER);
			vbox.getChildren().addAll(namaMenuTxt, comboBox, buttonBox);
			vbox.setPadding(new Insets(50));
			rootNode.setCenter(vbox);
			
			BorderPane.setMargin(rootNode, new Insets(40));
			
		}
	 
	 public void buttonHandler() {
		 backBtn.setOnAction(event -> {
			 HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show(); 
		 });
		 
			 submitBtn.setOnAction(event -> {
				 String namaMenu;

				 try {
					 namaMenu = comboBox.getValue();
				 } catch(Exception e) {
					return;
				 }
				 boolean response = MenuController.deleteMenu(namaMenu);
				 if(response) {
					 HomePage homePage = new HomePage(stage);
						stage.setScene(homePage.getScene());
						stage.show(); 
				 } 
				 else {
					 showAlert("Failed Delete", "The Menu is not exist or the menu you input is not valid");
				 }
			 });
		 
	 }
	 
	public Scene getScene() {
		return this.scene;
	}
	public DeletePage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Update Page");
		init();
		setLayout();
		buttonHandler();
	}

}
