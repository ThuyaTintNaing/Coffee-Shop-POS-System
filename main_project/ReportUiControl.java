package main_project;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class ReportUiControl implements Initializable{
	
    @FXML
    private TableView<saledetail> SaleDetail_table;

    @FXML
    private TableColumn<saledetail, Integer> invoice_id;

    @FXML
    private TableColumn<saledetail, Date> saledate;

    @FXML
    private TableColumn<saledetail, String> menuname;

    @FXML
    private TableColumn<saledetail, String> category;

    @FXML
    private TableColumn<saledetail, Integer> unitprice;

    @FXML
    private TableColumn<saledetail, Integer> qty;

    @FXML
    private TableColumn<saledetail, Integer> totalamount;

    @FXML
    private JFXTextField searchitem;
    
    @FXML
    private JFXDatePicker enddate;

    @FXML
    private JFXDatePicker startdate;

    @FXML
    private Button btnsearch;

    @FXML
    void printaction(ActionEvent event) {

    }

    @FXML
    void searchaction(ActionEvent event) {
    	if(startdate.getValue()!=null && enddate.getValue()!=null) {
    	Date startDate = Date.valueOf(startdate.getValue());
    	Date endDate = Date.valueOf(enddate.getValue());
    	SaleDetail_table.getItems().clear();
    	SaleDetail_table.getItems().addAll(dbcontrol.getbetweendata(startDate, endDate));   	
    	startdate.setValue(null);enddate.setValue(null);
    	}
    	else {
    		new Alert(AlertType.WARNING,"Choose correctly",ButtonType.OK).showAndWait();
    	}
    }

    @FXML
    void setnormalaction(ActionEvent event) {
    	SaleDetail_table.getItems().clear();
    	SaleDetail_table.getItems().addAll(dbcontrol.getReport());
    }
    StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter =
                  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
                
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		startdate.setConverter(converter);
		enddate.setConverter(converter);
		//invoiceid, menuid, saleprice, quanttity, amount;
		//invoiceid, Date saledate, String itemname, String category, int saleprice, int quantity
		invoice_id.setCellValueFactory(new PropertyValueFactory<>("invoiceid"));
		saledate.setCellValueFactory(new PropertyValueFactory<>("saledate"));
		menuname.setCellValueFactory(new PropertyValueFactory<>("itemname"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		unitprice.setCellValueFactory(new PropertyValueFactory<>("saleprice"));
		qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		totalamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		SaleDetail_table.getItems().addAll(dbcontrol.getReport());
		
	}

}
