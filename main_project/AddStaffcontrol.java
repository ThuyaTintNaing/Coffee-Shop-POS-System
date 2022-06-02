package main_project;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class AddStaffcontrol implements Initializable {
	@FXML
	private JFXTextField txfname;

	@FXML
	private JFXTextArea txfaddress;

	@FXML
	private JFXTextField txfpassword;

	@FXML
	private JFXRadioButton radstaff;

	@FXML
	private ToggleGroup tggroup;

	@FXML
	private JFXRadioButton radadmin;
	@FXML
    private JFXTextField txfemail;

	@FXML
	void addaction(ActionEvent event) {
		String status = "active";
		String name;
		String password;
		String address;
		String gmail;
		radstaff.setToggleGroup(tggroup);
		radadmin.setToggleGroup(tggroup);
		name = txfname.getText();
		password = txfpassword.getText();
		address = txfaddress.getText();
		gmail = txfemail.getText();
		if (!name.matches("^\\w+( +\\w+)*$")) {

			new Alert(AlertType.WARNING, "Please enter the name", ButtonType.OK).showAndWait();
			txfname.requestFocus();
			return;

		}
		else if(!gmail.matches( "^[\\w. +\\-]+@gmail\\.com$")) {
			new Alert(AlertType.WARNING, "Please enter the gmail correctly", ButtonType.OK).showAndWait();
		}
		
		else if (!password.matches("^\\w+( +\\w+)*$")) {

			new Alert(AlertType.WARNING, "Please enter the password", ButtonType.OK).showAndWait();
		}
		
		else if (!address.matches("^\\w+( +\\w+)*$")) {
			new Alert(AlertType.WARNING, "Please enter the address", ButtonType.OK).showAndWait();
			return;
		} else {
			if (radstaff.isSelected()) {
				if (dbcontrol.insertStaff(name, gmail, password, address, radstaff.getText(),status)) {
					new Alert(AlertType.INFORMATION, "add new record Successfully", ButtonType.OK).showAndWait();
					txfname.clear();
					txfaddress.clear();
					txfpassword.clear();

				} else {
					new Alert(AlertType.WARNING, "Fail to add", ButtonType.OK).showAndWait();

				}

			} else {
				if (dbcontrol.insertStaff(name, gmail, password, address, radadmin.getText(),status)) {
					new Alert(AlertType.INFORMATION, "add new record Successful", ButtonType.OK).showAndWait();
					txfname.clear();
					txfaddress.clear();
					txfpassword.clear();
				} else {
					new Alert(AlertType.WARNING, "Fail to add", ButtonType.OK).showAndWait();
				}
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
