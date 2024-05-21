package view;

import java.util.List;
import java.util.Random;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdatePage {
	Stage stage;
	Scene scene;
	
	BorderPane rootNode;
	VBox vbox, buttonBox;
	ComboBox<String> comboBox;
	
	Label namaMenuTxt, hargaMenuTxt, stokMenuTxt;
	TextField hargaMenuTF, stokMenuTF ;
	
	Button submitBtn, backBtn;
	
	public void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		
		namaMenuTxt = new Label("Nama Menu: ");
		
		hargaMenuTxt = new Label("Harga Menu");
		stokMenuTxt = new Label("Stok Menu");

		
		hargaMenuTF = new TextField(); 
		stokMenuTF = new TextField(); 
		
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
			vbox.getChildren().addAll(namaMenuTxt, comboBox, hargaMenuTxt, hargaMenuTF, stokMenuTxt, stokMenuTF, buttonBox);
			vbox.setPadding(new Insets(50));
			rootNode.setCenter(vbox);
			
			BorderPane.setMargin(rootNode, new Insets(50));
			
		}
	 
	 public void buttonHandler() {
		 backBtn.setOnAction(event -> {
			 HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show(); 
		 });
		 
		 submitBtn.setOnAction(event -> {
			 String namaMenu = comboBox.getValue();
			 String hargaMenuText = hargaMenuTF.getText();
			    String stokMenuText = stokMenuTF.getText();
			    int hargaMenu;
			    int stokMenu;

			    // Validate and parse hargaMenu
			    try {
			        hargaMenu = Integer.parseInt(hargaMenuText);
			    } catch (NumberFormatException e) {
			        showAlert("Invalid type", "Harga Menu must be an integer");
			        hargaMenuTF.setText("0");
			        return;
			    }

			    // Validate and parse stokMenu
			    try {
			        stokMenu = Integer.parseInt(stokMenuText);
			    } catch (NumberFormatException e) {
			        showAlert("Invalid type", "Stock Menu must be an integer");
			        stokMenuTF.setText("0");
			        return;
			    }
			    
			    try {
					 namaMenu = comboBox.getValue();
				 } catch(Exception e) {
					return;
				 }
			 
			 if(namaMenu == null || namaMenu.isEmpty() || hargaMenuText.isEmpty() || stokMenuText.isEmpty()) {
					showAlert("Empty Fields", "Please fill all fields");
					return;
				}
			 boolean response = MenuController.updateMenu(namaMenu, hargaMenu, stokMenu);
			 if(response) {
				 HomePage homePage = new HomePage(stage);
					stage.setScene(homePage.getScene());
					stage.show(); 
			 } 
			 else {
				 showAlert("Failed Update", "The Menu is not exist");
			 }
		 });
	 }
	 
	public Scene getScene() {
		return this.scene;
	}
	public UpdatePage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Update Page");
		init();
		setLayout();
		buttonHandler();
	}

}
