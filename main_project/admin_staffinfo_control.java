package main_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class admin_staffinfo_control implements Initializable {
	@FXML
	public TableView<staff> staff_table;

	@FXML
	private TableColumn<staff, Integer> col_id;

	@FXML
	private TableColumn<staff, String> col_name;
	@FXML
    private TableColumn<staff, String> col_gmail;

	@FXML
	private TableColumn<staff, String> col_address;

	@FXML
	private TableColumn<staff, String> col_pos;
	
	@FXML
    private TableColumn<staff, String> col_status;

	@FXML
	private JFXTextField searchitem;

	@FXML
	private Button btnsearch;

	ObservableList<staff> obs = FXCollections.observableArrayList();

	@FXML
	void addstaff(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = new Stage();
			Stage oldstage = (Stage) node.getScene().getWindow();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(oldstage);
			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("AddStaffUi.fxml")));
			stage.setScene(sc);
			stage.setMaximized(false);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void searchaction(ActionEvent event) {
		String search_item = searchitem.getText();
		staff_table.getItems().clear();
		staff_table.getItems().addAll(dbcontrol.searchItemByName(search_item));
		staff_table.refresh();
		searchitem.clear();
	}

	@FXML
	void setnormalaction(ActionEvent event) {
		staff_table.getItems().clear();
		staff_table.getItems().addAll(dbcontrol.getAllItem());
	}

	@FXML
	void deleteaction(ActionEvent event) {
		staff s = staff_table.getSelectionModel().getSelectedItem();
		if (s != null) {
			dbcontrol.delectstaff(s.getId());
					
			new Alert(AlertType.INFORMATION, "Delect successfully", ButtonType.OK).showAndWait();
			staff_table.getItems().clear();
			staff_table.getItems().addAll(dbcontrol.getAllItem());
		}

		else {
			new Alert(AlertType.WARNING, "Please select row", ButtonType.OK).showAndWait();
		}

	}

	@FXML
	void updateaction(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		col_name.setCellFactory(TextFieldTableCell.forTableColumn());
		col_address.setCellFactory(TextFieldTableCell.forTableColumn());
		col_pos.setCellFactory(TextFieldTableCell.forTableColumn());
		col_status.setCellFactory(TextFieldTableCell.forTableColumn());
		

		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("username"));
		col_gmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		col_pos.setCellValueFactory(new PropertyValueFactory<>("position"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		staff_table.getItems().addAll(dbcontrol.getAllItem());

		col_name.setOnEditCommit(e -> {
			staff s = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {

				new Alert(AlertType.WARNING, "Fill the name", ButtonType.OK).showAndWait();
				staff_table.refresh();
			} else {
				s.setUsername(newvalue);
				dbcontrol.updatestaff(s.getId(), newvalue,s.getAddress(), s.getPosition(),s.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
			}

		});
//		col_password.setOnEditCommit(e -> {
//			staff s = e.getRowValue();
//			String newvalue = e.getNewValue();
//			if (newvalue.isEmpty()) {
//				new Alert(AlertType.WARNING, "Fill the password", ButtonType.OK).showAndWait();
//				staff_table.refresh();
//
//			} else {
//				s.setPassword(newvalue);
//				dbcontrol.updatestaff(s.getId(), s.getUsername(), newvalue, s.getAddress(), s.getPosition());
//				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
//			}
//		});
		col_address.setOnEditCommit(e -> {
			staff s = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {
				new Alert(AlertType.WARNING, "Fill the address", ButtonType.OK).showAndWait();
				staff_table.refresh();
			} else {
				s.setAddress(newvalue);
				dbcontrol.updatestaff(s.getId(), s.getUsername(), newvalue, s.getPosition(),s.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
			}
		});
		col_pos.setOnEditCommit(e -> {
			staff s = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {
				new Alert(AlertType.WARNING, "Fill the position", ButtonType.OK).showAndWait();
				staff_table.refresh();
			} else {
				s.setPosition(newvalue);
				dbcontrol.updatestaff(s.getId(), s.getUsername(), s.getAddress(), newvalue,s.getStatus());
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();

			}
		});
		col_status.setOnEditCommit(e -> {
			staff s = e.getRowValue();
			String newvalue = e.getNewValue();
			if (newvalue.isEmpty()) {
				new Alert(AlertType.WARNING, "Fill the position", ButtonType.OK).showAndWait();
				staff_table.refresh();
			} else {
				s.setStatus(newvalue);
				dbcontrol.updatestaff(s.getId(), s.getUsername(), s.getAddress(),s.getPosition(),  newvalue);
				new Alert(AlertType.INFORMATION, "Update successfully", ButtonType.OK).showAndWait();
				

			}
		});
		
		staff_table.setEditable(true);
	}

}
