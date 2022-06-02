package main_project;

import java.util.Arrays;

public class Menu {
	private int id;
	private String name, category;
	int price;
	private byte[] photo;
	private String status;

	public Menu(int id, String name, String category, int price, byte[] photo, String status) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.photo = photo;
		this.status = status;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", photo="
				+ Arrays.toString(photo) + ", status=" + status + "]";
	}

	
}
