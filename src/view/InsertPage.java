package view;

import java.util.Random;

import controller.MenuController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;

public class InsertPage {
	Stage stage;
	Scene scene;
	
	BorderPane rootNode;
	VBox vbox, buttonBox;
	HBox hbox;
	
	Label kodeMenuTxt, namaMenuTxt, hargaMenuTxt, stokMenuTxt;
	TextField kodeMenuTF, namaMenuTF, hargaMenuTF, stokMenuTF;
	
	Button submitBtn, backBtn;
	Random rand;
	
	public void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		rand = new Random();
		
		kodeMenuTxt = new Label("Kode Menu");
		namaMenuTxt = new Label("Nama Menu");
		hargaMenuTxt = new Label("Harga Menu");
		stokMenuTxt = new Label("Stok Menu");
		
		kodeMenuTF = new TextField();
		namaMenuTF = new TextField(); 
		hargaMenuTF = new TextField(); 
		stokMenuTF = new TextField(); 
		
		submitBtn = new Button("Submit");
		backBtn = new Button("Back");
	}
	
	public void submitHandler() {
		EventHandler<MouseEvent> submitEvent = event -> {
			String kodeMenu = kodeMenuTF.getText();
			String namaMenu = namaMenuTF.getText();
			String hargaMenuText = hargaMenuTF.getText();
		    String stokMenuText = stokMenuTF.getText();
		    Integer hargaMenu;
		    Integer stokMenu;
			
			
			try {
				hargaMenu = Integer.parseInt(hargaMenuTF.getText());
			} catch (Exception e) {
				showAlert("Invalid type", "Harga Menu must be an integer");
				hargaMenuTF.setText("0");
				return;
				
			}
			try {
				stokMenu = Integer.parseInt(stokMenuTF.getText());
			} catch (Exception e) { 
				showAlert("Invalid type", "Stock Menu must be an integer");
				stokMenuTF.setText("0");
				return;
			}
			
			if(kodeMenu.isEmpty() || namaMenu.isEmpty() || hargaMenuText.isEmpty() || stokMenuText.isEmpty()) {
				showAlert("Empty Fields", "Please fill all fields");
				return;
			} 
			
				if (isValidKodeMenu(kodeMenu)) {
					Menu newMenu = new Menu(kodeMenu, namaMenu, hargaMenu, stokMenu);
					boolean inserted = MenuController.insertMenu(newMenu);
					boolean isUnique = MenuController.kodeIsUnique(newMenu.getKodeMenu());
					if(inserted && isUnique) {
						HomePage homePage = new HomePage(stage);
						stage.setScene(homePage.getScene());
						stage.show();
					}
					else {
						showAlert("Failed Insert", "Failed to Insert, Menu already exists");
						return;
					}
				}
				else {
					showAlert("Failed Insert", "Failed to Insert, Kode Menu must start with PD- and contain random number");
					return;
				}   
		};
		submitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, submitEvent);
		
		 backBtn.setOnAction(event -> {
			 HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show(); 
		 });
	}
	
	
	private static boolean isValidKodeMenu(String kodeMenu) {
		if(kodeMenu.length() == 6 && kodeMenu.startsWith("PD-") ) {
			for(int i = 3; i < 6; i++) {
				if(!Character.isDigit(kodeMenu.charAt(i))) {
					return false;
				}
				return true;
			}
		}
		return false;
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
		vbox.getChildren().addAll(kodeMenuTxt, kodeMenuTF, namaMenuTxt, namaMenuTF,
				hargaMenuTxt, hargaMenuTF, stokMenuTxt, stokMenuTF, buttonBox);
		vbox.setPadding(new Insets(50));
		rootNode.setCenter(vbox);
		
		BorderPane.setMargin(rootNode, new Insets(50));
		
	}
	
	public InsertPage(Stage stage) {
		init();
		submitHandler();
		setLayout();
		this.stage = stage;
		this.stage.setTitle("Insert Page");
	}

	public Scene getScene() {
		return this.scene;
	}

}
