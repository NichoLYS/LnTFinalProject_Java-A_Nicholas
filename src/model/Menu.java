package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {

	public String kodeMenu;
	public String namaMenu;
	public int hargaMenu;
	public int stokMenu;
	
	public String getKodeMenu() {
		return kodeMenu;
	}

	public void setKodeMenu(String kodeMenu) {
		this.kodeMenu = kodeMenu;
	}

	public String getNamaMenu() {
		return namaMenu;
	}

	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}

	public int getHargaMenu() {
		return hargaMenu;
	}

	public void setHargaMenu(int hargaMenu) {
		this.hargaMenu = hargaMenu;
	}

	public int getStokMenu() {
		return stokMenu;
	}

	public void setStokMenu(int stokMenu) {
		this.stokMenu = stokMenu;
	}

	public Menu(String kodeMenu, String namaMenu, int hargaMenu, int stokMenu) {
		super();
		this.kodeMenu = kodeMenu;
		this.namaMenu = namaMenu;
		this.hargaMenu = hargaMenu;
		this.stokMenu = stokMenu;
	}
	
	public StringProperty kodeMenuProperty() {
		return new SimpleStringProperty(kodeMenu);
	}
	
	public StringProperty namaMenuProperty() {
		return new SimpleStringProperty(namaMenu);
	}
	
	public StringProperty hargaMenuProperty() {
		return new SimpleStringProperty(Integer.toString(hargaMenu));
	}
	
	public StringProperty stokMenuProperty() {
		return new SimpleStringProperty(Integer.toString(stokMenu));
	}

}
