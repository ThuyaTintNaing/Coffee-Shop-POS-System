package main_project;

import java.sql.*;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbcontrol {
	private static final String url = "jdbc:mysql://localhost/pos_system";
	private static final String username = "root";
	private static final String password = "";

	public static Connection createConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, username, password);
	}

	public static List<staff> getAllItem() {
		String sql = "SELECT staff_id,staff_name,gmail,address,position,Status FROM staff WHERE Status = 'active' ";
		ArrayList<staff> nstaff = new ArrayList<>();
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {
			while (rs.next()) {

				staff s = new staff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				nstaff.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nstaff;
	}
	
	public static List<saledetail> getReport() {
		String sql = "SELECT * FROM vi_salereport";
		ArrayList<saledetail> saledetail = new ArrayList<>();
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {
			while (rs.next()) {

				saledetail sd = new saledetail(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
				saledetail.add(sd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saledetail;
	}
	
	public static List<Menu> getAllMenu() {
		String sql = "SELECT * FROM item WHERE Status = 'active'";
		ArrayList<Menu> allmenu = new ArrayList<>();
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {
			while (rs.next()) {
				Blob b = rs.getBlob(5);
				byte[] image = b.getBytes(1, (int) b.length());
				Menu m = new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), image, rs.getString(6));
				allmenu.add(m);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allmenu;
	}
	public static Menu getByid(int id) {
		String sql = "SELECT * FROM item WHERE Status = 'active' AND item_id = ?";
//		ArrayList<Menu> allmenu = new ArrayList<>();
		Menu m = null;
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Blob b = rs.getBlob(5);
				byte[] image = b.getBytes(1, (int) b.length());
				 m = new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), image, rs.getString(6));
//				allmenu.add(m);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public static List<Menu> getbycategory(String category) {
		String sql = "SELECT * FROM item Where category = ? AND Status = 'active'";
		ArrayList<Menu> allmenu = new ArrayList<>();
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);){
			st.setString(1, category);
				ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Blob b = rs.getBlob(5);
				byte[] image = b.getBytes(1, (int) b.length());
				Menu m = new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), image, rs.getString(6));
				allmenu.add(m);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allmenu;
	}

	public static boolean insertStaff(String name,String gmail, String password, String address, String position, String status) {
		String sql = "INSERT INTO staff (staff_name,gmail, password, address, position, Status) values (?,?,?,?,?,?)";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setString(2, gmail);
			st.setString(3, password);
			st.setString(4, address);
			st.setString(5, position);
			st.setString(6, status);
			int r = st.executeUpdate();
			System.out.println(r + " row inserted...");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void insertMenu(String name, String category, int price, byte[] image, String status) {
		String sql = "INSERT INTO item (item_name, category, unit_price, item_photo,Status) values (?,?,?,?,?)";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setString(2, category);
			st.setInt(3, price);
			st.setBlob(4, new ByteArrayInputStream(image));
			st.setString(5, status);
			int r = st.executeUpdate();
			System.out.println(r + " row insert");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static List<staff> searchItemByName(String name) {
		String sql = "SELECT staff_id,staff_name,gmail,address,position,Status FROM staff WHERE staff_name like ?";
		ArrayList<staff> newstaff = new ArrayList<>();
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + name + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				staff s = new staff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				newstaff.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newstaff;
	}

	public static List<Menu> searchMenuByName(String name) {
		String sql = "SELECT * FROM item WHERE item_name like ?";
		ArrayList<Menu> SearchMenu = new ArrayList<>();
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + name + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Blob b = rs.getBlob(5);
				byte[] image = b.getBytes(1, (int) b.length());
				Menu s = new Menu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), image, rs.getString(6));
				SearchMenu.add(s);
			}

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SearchMenu;
	}

	public static staff logincheck(String username, String password) {
		String sql = "SELECT staff_id,staff_name,gmail,address,position,Status FROM staff WHERE staff_name = ? and password = ? and Status = 'active'";
		staff s = null;
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String dbusername = rs.getString(2);
				String gmail = rs.getString(3);
				String dbaddress = rs.getString(4);
				String dbpositon = rs.getString(5);
				String dbstatus = rs.getString(6);
				s = new staff(id, dbusername,gmail, dbaddress, dbpositon,dbstatus);

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return s;
	}

	public static void delectstaff(int id) {
		String sql = "UPDATE staff SET Status = 'not active' WHERE staff_id = ?";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void delectMenu(int id) {
		String sql = "UPDATE item SET Status = 'not sale' WHERE item_id = ?";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void updatestaff(int id, String name, String address, String position,String status) {
		String sql = "UPDATE staff SET staff_name = ?, address = ?, position = ?, Status = ? WHERE staff_id = ?";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, position);
			st.setString(4, status);
			st.setInt(5, id);
			int r = st.executeUpdate();
			System.out.println(r + " row update");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void updateMenu(int id, String name, String category, int price, byte[] image, String status) {
		String sql = "UPDATE item SET item_name = ?, category = ?, unit_price = ?, item_photo = ?, Status = ? WHERE item_id = ?";
		try (Connection con = createConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setString(2, category);
			st.setInt(3, price);
			st.setBlob(4, new ByteArrayInputStream(image));
			st.setString(5, status);
			st.setInt(6, id);
			
			int r = st.executeUpdate();
			System.out.println(r + " row update");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public static List<saledetail> getbetweendata(Date startdate,Date enddate) {
		String sql = "SELECT * FROM vi_salereport WHERE sale_date BETWEEN ? AND ?";
		ArrayList<saledetail> saledetail = new ArrayList<>();
		try (Connection con = createConnection();
				PreparedStatement st = con.prepareStatement(sql);){
				st.setDate(1,startdate);
				st.setDate(2,enddate);
				ResultSet rs = st.executeQuery();
			while (rs.next()) {

				saledetail sd = new saledetail(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
				saledetail.add(sd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saledetail;
	}
}
