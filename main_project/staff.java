package main_project;

public class staff {
	private int id;
	private String username,gmail,password,address,position,status;
	
	public staff(int id, String username, String gmail, String address, String position, String status) {
		super();
		this.id = id;
		this.username = username;
		this.gmail = gmail;
		
		this.address = address;
		this.position = position;
		this.status = status;
	}
	
	public staff(int id, String username,String gmail, String password, String address, String position, String status) {
		super();
		this.id = id;
		this.username = username;
		this.gmail = gmail;
		this.password = password;
		this.address = address;
		this.position = position;
		this.status = status;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "staff [id=" + id + ", username=" + username + ", gmail=" + gmail + ", password=" + password
				+ ", address=" + address + ", position=" + position + ", status=" + status + "]";
	}

	
	
	
	
}
	
