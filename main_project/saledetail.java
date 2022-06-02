package main_project;

import java.sql.Date;




public class saledetail {
	private int invoiceid;
	private Date saledate;
	private String itemname,category;
	private int saleprice,quantity,amount;
	public saledetail(int invoiceid, Date saledate, String itemname, String category, int saleprice, int quantity,
			int amount) {
		super();
		this.invoiceid = invoiceid;
		this.saledate = saledate;
		this.itemname = itemname;
		this.category = category;
		this.saleprice = saleprice;
		this.quantity = quantity;
		this.amount = amount;
	}
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	public Date getSaledate() {
		return saledate;
	}
	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "saledetail [invoiceid=" + invoiceid + ", saledate=" + saledate + ", itemname=" + itemname
				+ ", category=" + category + ", saleprice=" + saleprice + ", quantity=" + quantity + ", amount="
				+ amount + "]";
	}
	
}
