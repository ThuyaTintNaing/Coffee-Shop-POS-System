package main_project;

import java.io.File;
import java.nio.file.Files;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class AddMenucontrol {
	private byte[] image;
	@FXML
	private JFXTextField txfname;

	@FXML
	private JFXTextField txfcategory;

	@FXML
	private JFXTextField txfprice;

	@FXML
	private Label lblfile;

	@FXML
	void addaction(ActionEvent event) {
		String status = "active";
		String name;
		String category;
		String price;

		name = txfname.getText();
		price = txfprice.getText();
		category = txfcategory.getText();
		if (!name.matches("^\\w+( +\\w+)*$")) {

			new Alert(AlertType.WARNING, "Please enter the name", ButtonType.OK).showAndWait();
			txfname.requestFocus();

		}

		else if (!category.matches("^\\w+( +\\w+)*$")) {
			txfcategory.requestFocus();
			new Alert(AlertType.WARNING, "Please enter the category", ButtonType.OK).showAndWait();
		}

		else if (!price.matches("[0-9]+")) {
			new Alert(AlertType.WARNING, "Please enter the number", ButtonType.OK).showAndWait();
			txfprice.requestFocus();
		} else if (image == null) {
			new Alert(AlertType.WARNING, "Please choose file", ButtonType.OK).showAndWait();

		} else {
			dbcontrol.insertMenu(txfname.getText(), txfcategory.getText(), Integer.parseInt(txfprice.getText()), image, status);
			new Alert(AlertType.INFORMATION, "insert successfully", ButtonType.OK).showAndWait();
			txfname.clear();
			txfcategory.clear();
			txfprice.clear();
			lblfile.setText("Choose file");

		}
	}

	@FXML
	void browseaction(ActionEvent event) {
		Node node = (Node) event.getSource();
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(node.getScene().getWindow());
		if (f != null) {
			fc.setInitialDirectory(f.getParentFile());
			lblfile.setText(f.getAbsolutePath());
			try {
				image = Files.readAllBytes(f.toPath());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
