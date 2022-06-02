package main_project;


import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Menuinfo_control implements Initializable {
	@FXML
	private TableView<Menu> Menu_table;

	@FXML
	private TableColumn<Menu, Integer> col_id;

	@FXML
	private TableColumn<Menu, String> col_name;

	@FXML
	private TableColumn<Menu, String> col_category;

	@FXML
	private TableColumn<Menu, Integer> col_price;

	@FXML
	private TableColumn<Menu, byte[]> col_photo;
	
	   @FXML
	    private TableColumn<Menu, String> col_status;

	@FXML
	private JFXTextField searchitem;

	@FXML
	private Button btnsearch;

	@FXML
	void addmenu(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = new Stage();
			Stage oldstage = (Stage) node.getScene().getWindow();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(oldstage);
			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("AddMenuUi.fxml")));
			stage.setScene(sc);
			stage.setMaximized(false);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void deletemenu(ActionEvent event) {
		Menu m = Menu_table.getSelectionModel().getSelectedItem();
		if (m != null) {
			dbcontrol.delectMenu(m.getId());
			Menu_table.getItems().clear();
			Menu_table.getItems().addAll(dbcontrol.getAllMenu());
			new Alert(AlertType.INFORMATION, "Delect successfully", ButtonType.OK).showAndWait();
		}

		else {
			new Alert(AlertType.WARNING, "Please select row", ButtonType.OK).showAndWait();
		}

	}

	@FXML
	void searchaction(ActionEvent event) {
		String search_item = searchitem.getText();
		Menu_table.getItems().clear();
		Menu_table.getItems().addAll(dbcontrol.searchMenuByName(search_item));
		Menu_table.refresh();
		searchitem.clear();
	}

	@FXML
	void setnormalaction(ActionEvent event) {
		Menu_table.getItems().clear();
		Menu_table.getItems().addAll(dbcontrol.getAllMenu());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		col_name.setCellFactory(TextFieldTableCell.forTableColumn());
		col_category.setCellFactory(TextFieldTableCell.forTableColumn());
		col_status.setCellFactory(TextFieldTableCell.forTableColumn());
		col_price.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

			@Override
			public Integer fromString(String s) {
				return Integer.parseInt(s);

			}

			@Override
			public String toString(Integer i) {
				return String.format("%d", i);
			}
		}));
//		col_photo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<byte[]>() {
//
//			@Override
//			public byte[] fromString(String s) {
//				
//					 byte[] image =  s.getBytes(1, (int) s.length());
//					 return image;		
//			}
//			@Override
//			public String toString(byte[] object) {
//				String s = new String(object);
//				return s;
//			}
//
//			
//		}));

		Menu_table.getItems().addAll(dbcontrol.getAllMenu());

		col_name.setOnEditCommit(e -> {
			Menu m = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {

				new Alert(AlertType.WARNING, "Fill the menu name", ButtonType.OK).showAndWait();
				Menu_table.refresh();
			} else {
				m.setName(newvalue);
				dbcontrol.updateMenu(m.getId(), newvalue, m.getCategory(), m.getPrice(), m.getPhoto(), m.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
			}

		});
		col_category.setOnEditCommit(e -> {
			Menu m = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {
				new Alert(AlertType.WARNING, "Fill the category name", ButtonType.OK).showAndWait();
				Menu_table.refresh();

			} else {
				m.setCategory(newvalue);
				dbcontrol.updateMenu(m.getId(), m.getName(), newvalue, m.getPrice(), m.getPhoto(), m.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
			}
		});
		
		
		
		
		
		col_price.setOnEditCommit(e -> {
			Menu m = e.getRowValue();
			Integer newvalue = e.getNewValue();
			if (newvalue == null) {
				new Alert(AlertType.WARNING, "Fill the price", ButtonType.OK).showAndWait();
				Menu_table.refresh();
			} else {
				m.setPrice(newvalue);
				dbcontrol.updateMenu(m.getId(), m.getName(), m.getCategory(), newvalue, m.getPhoto(),m.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
			}
		});
//		col_status.setOnEditCommit(e -> {
//			Menu m = e.getRowValue();
//			String newvalue = e.getNewValue();
//			if (newvalue.isEmpty()) {
//				new Alert(AlertType.WARNING, "Fill the Status", ButtonType.OK).showAndWait();
//				Menu_table.refresh();
//			} else {
//				m.setStatus(newvalue);
//				dbcontrol.updateMenu(m.getId(), m.getName(), m.getCategory(), m.getPrice(), m.getPhoto(),newvalue);
//				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
//			}
//		});
//		col_photo.setOnEditCommit(e->{
//			
//			Menu m = e.getRowValue();
//			byte[] newvalue = e.getNewValue();
//			if(newvalue==null) {
//				new Alert(AlertType.WARNING,"Choose the file",ButtonType.OK).showAndWait();
//				Menu_table.refresh();
//			}
//			else {
//			m.setPhoto(newvalue);
//			//dbcontrol.updatestaff(s.getId(),s.getUsername(), s.getPassword(), s.getAddress(), newvalue);
//			new Alert(AlertType.INFORMATION,"Update successfully",ButtonType.OK).showAndWait();
//
//			}
//		});
		Menu_table.setEditable(true);
	}

}
