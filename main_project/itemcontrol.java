package main_project;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class itemcontrol implements Initializable {

	
	@FXML
	private Label name;
	@FXML
    private AnchorPane ap;
	@FXML
	private Label price;

	private Menu menu;
	@FXML
	private ImageView imgview;

	@FXML
	private JFXButton btnadd;
	 
	public JFXButton butt;
	 TextInputDialog td = new TextInputDialog("number only");
	 
	
	 private staffuicontrol staffui;
	
	 @FXML
	    void btnadd(ActionEvent event) {
		 
		    FXMLLoader fxmlLoader = new FXMLLoader();			
			staffuicontrol staffuicontroller = fxmlLoader.getController();
		     int id = (int) butt.getUserData();
		     Menu selectedmeu = dbcontrol.getByid(id);
		     
		 
		 td.setHeaderText("enter quantity"); 
		 td.showAndWait();
		 if(!td.getEditor().getText().matches("[0-9]+")){
			 new Alert(AlertType.WARNING,"Enter the number only 1-10",ButtonType.OK).showAndWait();
			 
		 }
		
		 
		 else {
			String name = selectedmeu.getName();
			int qty = Integer.parseInt(td.getEditor().getText());
			int price = selectedmeu.getPrice();
			int total = qty*price;
			staffui.settabledata(new saledata(name, qty, price, total));
			//System.out.println(selectedmeu.getName()  +  selectedmeu.getPrice()  + td.getEditor().getText());
		 }
		 
	    }
	 

	public void setdata(Menu menu) {

		this.menu = menu;
		name.setText(menu.getName());
		imgview.setImage(convertTomage(menu.getPhoto()));
		price.setText(String.valueOf(menu.getPrice()));

	}
//	public saledata reuturndata() {
//		int id = (int) butt.getUserData();
//		Menu selectedmeu = dbcontrol.getByid(id);
//		String name = selectedmeu.getName();
//		int qty = Integer.parseInt(td.getEditor().getText());
//		int price = selectedmeu.getPrice();
//		
//		
//		return new saledata(selectedmeu.getName(), qty, price, qty*price);
//	}
	private static Image convertTomage(byte[] raw) {
		WritableImage image = new WritableImage(180, 98);
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(raw);
			BufferedImage read = ImageIO.read(bis);
			image = SwingFXUtils.toFXImage(read, null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return image;
	}

	
	public void initialize(staffuicontrol staffui) {
        this.staffui = staffui;
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 

	}
}
