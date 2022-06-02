package main_project;

public class saledata {
	private String name;
	private int qty,price,totalamount;
	public saledata(String name, int qty, int price, int totalamount) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.totalamount = totalamount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}
	@Override
	public String toString() {
		return "saledata [name=" + name + ", qty=" + qty + ", price=" + price + ", totalamount=" + totalamount + "]";
	}
	
}
