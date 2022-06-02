package main_project;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class quantitycontrol implements Initializable{
	    @FXML
	    private TextField txfqty;

	    @FXML
	    private JFXButton btnadd;
	    
	    public int getqty() {
	    	return Integer.parseInt(txfqty.getText());
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
		            btnadd.requestFocus();
		        }
		    });
			
			
		}

	    
}
