package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;

public class HomePage {
	protected Stage stage;
	protected Scene scene;
	
	protected BorderPane rootNode;
	protected VBox vbox;
	protected HBox hbox;
	protected Button updateBtn, deleteBtn, insertBtn;
	protected TableView<Menu> tableView;
	protected ObservableList<Menu> menuList;
	
	protected void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		
		//Mengambil seluruh list menu dan membuatnya ke observablelist
		List<Menu> allMenu = MenuController.getAllMenu();
		menuList = FXCollections.observableArrayList(allMenu);
		
		// Create table columns
		TableColumn<Menu, String> kodeMenuColumn = new TableColumn<>("Kode Menu");
		kodeMenuColumn.setCellValueFactory(cellData -> cellData.getValue().kodeMenuProperty());
		
		TableColumn<Menu, String> namaMenuColumn = new TableColumn<>("Nama Menu");
		namaMenuColumn.setCellValueFactory(cellData -> cellData.getValue().namaMenuProperty());
		
		TableColumn<Menu, String> hargaMenuColumn = new TableColumn<>("Harga Menu");
		hargaMenuColumn.setCellValueFactory(cellData -> cellData.getValue().hargaMenuProperty());
		
		TableColumn<Menu, String> stokMenuColumn = new TableColumn<>("Stok Menu");
		stokMenuColumn.setCellValueFactory(cellData -> cellData.getValue().stokMenuProperty());
		
		tableView = new TableView();
		tableView.getColumns().addAll(kodeMenuColumn, namaMenuColumn, hargaMenuColumn, stokMenuColumn);
		tableView.setItems(menuList);
		
		 updateBtn = new Button("Update");
	        updateBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
	                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
	        
	        deleteBtn = new Button("Delete");
	        deleteBtn.setStyle("-fx-background-color: red; -fx-border-color: white; "
	                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
	        
	        insertBtn = new Button("Insert");
	        insertBtn.setStyle("-fx-background-color: lightgreen; -fx-border-color: white; "
	                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
	}
	
	public void setLayout() {
		vbox = new VBox(8);
		vbox.getChildren().addAll(tableView);
		
		hbox = new HBox(8);
		hbox.getChildren().addAll(updateBtn, insertBtn, deleteBtn);
		
		rootNode.setCenter(vbox);
		rootNode.setBottom(hbox);
	}
	
	public void buttonHandler() {
		EventHandler<MouseEvent> insertEvent = event -> {
			InsertPage insertPage = new InsertPage(stage);
			stage.setScene(insertPage.getScene());
			stage.show();
		};
		insertBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, insertEvent);
		
		EventHandler<MouseEvent> updateEvent = event -> {
			UpdatePage updatePage = new UpdatePage(stage);
			stage.setScene(updatePage.getScene());
			stage.show();
		};
		updateBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, updateEvent);
		
		EventHandler<MouseEvent> deleteEvent = event -> {
			DeletePage deletePage = new DeletePage(stage);
			stage.setScene(deletePage.getScene());
			stage.show();
		};
		deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteEvent);
	}
	
	public HomePage(Stage stage) {
		init();
		setLayout();
		buttonHandler();
		this.stage = stage;
		this.stage.setTitle("Home Page");
	}

	public Scene getScene() {
		return this.scene;
	}

}
