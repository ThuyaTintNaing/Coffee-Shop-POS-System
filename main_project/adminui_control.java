package main_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class adminui_control implements Initializable {
	@FXML
	private AnchorPane mainpane;
	@FXML
	private JFXButton buttmenu;
	@FXML
    private JFXButton mostsale;


	@FXML
	private JFXButton buttreport;

	@FXML
	private JFXButton buttsatff;
	
	@FXML
	void menuaction(ActionEvent event) {
		buttmenu.requestFocus();
		buttmenu.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
		buttreport.setStyle(null);
		buttsatff.setStyle(null);
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("Menu_info.fxml"));
			pane.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	@FXML
	void saleaction(ActionEvent event) {
		buttreport.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
		buttmenu.setStyle(null);
		
		buttsatff.setStyle(null);
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("reportui.fxml"));
			pane.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void staffaction(ActionEvent event) {
		buttsatff.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
		buttmenu.setStyle(null);
		buttreport.setStyle(null);

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("staff_info.fxml"));
			pane.getStylesheets().add(getClass().getResource("table.css").toExternalForm());

			mainpane.getChildren().setAll(pane);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void logoutaction(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			stage.close();

			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("login_ui.fxml")));
			sc.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(sc);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	 @FXML
//	    void mostsaleaction(ActionEvent event) {
//		 mostsale.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
//			buttmenu.setStyle(null);
//			buttsatff.setStyle(null);
//			buttreport.setStyle(null);
//			
//		 try {
//				Node node = (Node) event.getSource();
//				Stage stage = (Stage) node.getScene().getWindow();
//
//				//stage.close();
//				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("draft1.fxml")));
//				stage.setMaximized(false);
//				stage.setResizable(false);
//				stage.setScene(sc);
//				stage.show();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
//		        	mostsale.requestFocus();
//		        	mostsale.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
					buttmenu.requestFocus();;
					buttmenu.setStyle("-fx-border-width: 0 0 0 6;" + "-fx-border-color:  #ffd083");
					buttsatff.setStyle(null);
					buttreport.setStyle(null);
					try {
						AnchorPane pane = FXMLLoader.load(getClass().getResource("Menu_info.fxml"));
						pane.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
						mainpane.getChildren().setAll(pane);
					} catch (IOException e) {

						e.printStackTrace();
					}

					
		        }
		    });
	}
}
