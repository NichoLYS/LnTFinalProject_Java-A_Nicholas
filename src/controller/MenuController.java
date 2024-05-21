package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import database.DatabaseSingleton;
import model.Menu;

public class MenuController {
	public static DatabaseConnection db = DatabaseSingleton.getInstance();
	//dummy data
	public void createDefaultData() {
		insertMenu(new Menu("PD-123", "Telur", 3000, 10));
	}
	public static List<Menu> getAllMenu() {
		List<Menu> menu = new ArrayList<>();
		String query = "SELECT * FROM menu";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kodeMenu = rs.getString("KodeMenu");
				String namaMenu = rs.getString("NamaMenu");
				int hargaMenu = rs.getInt("HargaMenu");
				int stokMenu = rs.getInt("StokMenu");
				Menu newMenu = new Menu(kodeMenu, namaMenu, hargaMenu, stokMenu);
				menu.add(newMenu);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return menu;
	}
	public static boolean insertMenu(Menu menu) {
		if(menuIsExist(menu.getNamaMenu())) {
			return false;
		}
	
		String query = "INSERT INTO menu (KodeMenu, NamaMenu, HargaMenu, StokMenu) "
				+ "VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, menu.getKodeMenu());
			stmt.setString(2, menu.getNamaMenu());
			stmt.setInt(3, menu.getHargaMenu());
			stmt.setInt(4, menu.getStokMenu());
			
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean menuIsExist(String namaMenu) {
		String query = "SELECT COUNT(*) FROM menu WHERE NamaMenu = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, namaMenu);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean kodeIsUnique(String kodeMenu) {
		String query = "SELECT COUNT(*) FROM menu WHERE KodeMenu = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, kodeMenu);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kodeMenuVal = rs.getString("KodeMenu");
				if(kodeMenuVal.equals(kodeMenu)) {
					return false;
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<String> getNamaMenu() {
		String query = "SELECT NamaMenu FROM menu";
		List<String> namaMenu = new ArrayList<String>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String namaMenuVal = rs.getString("NamaMenu");
				namaMenu.add(namaMenuVal);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return namaMenu;
	}
	
	public static boolean updateMenu(String namaMenu, int hargaMenu, int stokMenu) {
		if(menuIsExist(namaMenu)) {
			String query = "UPDATE menu SET hargaMenu = ?, stokMenu = ? WHERE namaMenu = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setInt(1, hargaMenu);
				stmt.setInt(2, stokMenu);
				stmt.setString(3, namaMenu);
				int rowsUpdated = stmt.executeUpdate();
				return rowsUpdated > 0;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean deleteMenu(String namaMenu) {
		if(menuIsExist(namaMenu)) {
			String query = "DELETE FROM menu WHERE namaMenu = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, namaMenu);
				int rowsAffected = stmt.executeUpdate();
				return rowsAffected > 0;
			} catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return false;
	}
}
