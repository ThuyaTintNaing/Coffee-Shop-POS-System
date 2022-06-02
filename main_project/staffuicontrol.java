package main_project;

import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.text.TabExpander;

import org.apache.derby.impl.sql.execute.CurrentDatetime;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.sun.javafx.css.CalculatedValue;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class staffuicontrol implements Initializable {
	@FXML
	private TableView<saledata> sale_table;

	@FXML
	private TableColumn<saledata, String> col_name;

	@FXML
	private TableColumn<saledata, Integer> col_qty;

	@FXML
	private TableColumn<saledata, Integer> col_unitprice;

	@FXML
	private TableColumn<saledata, Integer> col_total;
	
	 @FXML
	    private Text date_time;
	
	  @FXML
	    private JFXButton all_butt;

	    @FXML
	    private JFXButton drink_butt;

	    @FXML
	    private JFXButton dess_butt;

	    @FXML
	    private JFXButton main_butt;

	@FXML
	private JFXButton btninvoice;
	@FXML
	private Label lbltotal;

	@FXML
	private TextField txfpaid;

	@FXML
	private TextField txftax;

	@FXML
	private TextField txfdiscount;

	@FXML
	private Label lblrefund;
	@FXML
	private ImageView logout;
	@FXML
	public JFXTextField txfprice;

	@FXML
	public JFXTextField txfqty;

	@FXML
	public JFXTextField txfname;
	@FXML
	private TableColumn<saledata, Void> buttcol;
	@FXML
	private Label subtotal;

	private saledata updatesale = null;

	@FXML
	private ScrollPane scroll;
	int sub = 0;

	@FXML
	void allmenu(ActionEvent event) {
		all_butt.setStyle("-fx-background-color : #134c4f");
		drink_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setStyle("-fx-background-color :  #ffd083");
		dess_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setTextFill(Color.BLACK);
		all_butt.setTextFill(Color.WHITE);
		drink_butt.setTextFill(Color.BLACK);
		dess_butt.setTextFill(Color.BLACK);
		menulist.clear();
		menulist.addAll(dbcontrol.getAllMenu());
		scroll.setContent(null);
		GridPane p = new GridPane();

		System.out.println(menulist.size());

		System.out.print(menulist.size());

		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < menulist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("menuitem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				itemcontrol itemController = fxmlLoader.getController();
				itemController.setdata(menulist.get(i));
				itemController.butt.setUserData(menulist.get(i).getId());
				itemController.initialize(this);

				if (column == 3) {
					column = 0;
					row++;
				}

				p.add(anchorPane, column++, row); // (child,column,row)

				GridPane.setMargin(anchorPane, new Insets(0, 0, 20, 20));
			}
			scroll.setContent(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void dessertmenu(ActionEvent event) {
		main_butt.setTextFill(Color.BLACK);
		all_butt.setTextFill(Color.BLACK);
		drink_butt.setTextFill(Color.BLACK);
		dess_butt.setTextFill(Color.WHITE);
		all_butt.setStyle("-fx-background-color :  #ffd083");
		drink_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setStyle("-fx-background-color :  #ffd083");
		dess_butt.setStyle("-fx-background-color : #134c4f");
		scroll.setContent(null);
		GridPane p = new GridPane();

		menulist.clear();

		System.out.println(menulist.size());

		menulist.addAll(dbcontrol.getbycategory("Dessert"));
		System.out.print(menulist.size());

		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < menulist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("menuitem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				itemcontrol itemController = fxmlLoader.getController();
				itemController.setdata(menulist.get(i));
				itemController.butt.setUserData(menulist.get(i).getId());
				itemController.initialize(this);
				if (column == 3) {
					column = 0;
					row++;
				}

				p.add(anchorPane, column++, row); // (child,column,row)

				GridPane.setMargin(anchorPane, new Insets(0, 0, 20, 20));
			}
			scroll.setContent(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void drinkmenu(ActionEvent event) {
		all_butt.setStyle("-fx-background-color :  #ffd083");
		drink_butt.setStyle("-fx-background-color : #134c4f");
		main_butt.setStyle("-fx-background-color :  #ffd083");
		dess_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setTextFill(Color.BLACK);
		all_butt.setTextFill(Color.BLACK);
		drink_butt.setTextFill(Color.WHITE);
		dess_butt.setTextFill(Color.BLACK);
		// scroll.setContent(null);
		GridPane p = new GridPane();

		menulist.clear();

		System.out.println(menulist.size());

		menulist.addAll(dbcontrol.getbycategory("Drinks"));
		System.out.print(menulist.size());

		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < menulist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("menuitem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				itemcontrol itemController = fxmlLoader.getController();
				itemController.setdata(menulist.get(i));
				itemController.butt.setUserData(menulist.get(i).getId());
				itemController.initialize(this);
				if (column == 3) {
					column = 0;
					row++;
				}

				p.add(anchorPane, column++, row); // (child,column,row)

				GridPane.setMargin(anchorPane, new Insets(0, 0, 20, 20));
			}
			scroll.setContent(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void mainmenu(ActionEvent event) {
		all_butt.setStyle("-fx-background-color :  #ffd083");
		drink_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setStyle("-fx-background-color : #134c4f");
		dess_butt.setStyle("-fx-background-color :  #ffd083");
		main_butt.setTextFill(Color.WHITE);
		all_butt.setTextFill(Color.BLACK);
		drink_butt.setTextFill(Color.BLACK);
		dess_butt.setTextFill(Color.BLACK);
		scroll.setContent(null);
		GridPane p = new GridPane();

		menulist.clear();

		System.out.println(menulist.size());

		menulist.addAll(dbcontrol.getbycategory("Main Course"));
		System.out.print(menulist.size());

		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < menulist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("menuitem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				itemcontrol itemController = fxmlLoader.getController();
				itemController.setdata(menulist.get(i));
				itemController.butt.setUserData(menulist.get(i).getId());
				itemController.initialize(this);
				if (column == 3) {
					column = 0;
					row++;
				}

				p.add(anchorPane, column++, row);

				GridPane.setMargin(anchorPane, new Insets(0, 0, 20, 20));
			}
			scroll.setContent(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void invoiceaction(ActionEvent event) {
		calculate();
		try {
			Node node = (Node) event.getSource();
			Stage stage = new Stage();
			Stage oldstage = (Stage) node.getScene().getWindow();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(oldstage);
			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("invoice.fxml")));
			stage.setScene(sc);

			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void actionlogout(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			// stage.close();

			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("login_ui.fxml")));
			sc.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(sc);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@FXML
//	void updateaction(ActionEvent event) {
//		updatesale = sale_table.getSelectionModel().getSelectedItem();
//		if (updatesale != null) {
//			txfname.setText(updatesale.getName());
//			txfprice.setText(String.valueOf(updatesale.getPrice()));
//			txfqty.setText(String.valueOf(updatesale.getQty()));
//			calculate();
//			// sale_table.getItems().remove(updatesale);
//		} else {
//
//			// new Alert(AlertType.WARNING,"Select the
//			// row",javafx.scene.control.ButtonType.OK).showAndWait();
//			JFXDialogLayout layout = new JFXDialogLayout();
//			Label lbl = new Label("Warring");
//			lbl.setFont(new Font("Arial", 30));
//			layout.setHeading(lbl);
//			layout.setBody(new Label("Please select the row that you want to update!!"));
//			JFXButton btn = new JFXButton("Okay");
//			btn.setStyle("-fx-background-color: #3b7069");
//			btn.setTextFill(Color.WHITE);
//			btn.setPrefSize(60, 30);
//			JFXDialog dialog = new JFXDialog(stack, layout, JFXDialog.DialogTransition.RIGHT);
//			btn.setOnAction(e -> {
//				dialog.close();
//				scroll.requestFocus();
//			});
//			layout.setActions(btn);
//			dialog.show();
//
//		}
//
//	}

	@FXML
	void addtotable(ActionEvent event) {
//		updatesale = sale_table.getSelectionModel().getSelectedItem();

		if (updatesale != null && !txfname.getText().isEmpty() && !txfqty.getText().isEmpty()
				&& !txfqty.getText().isEmpty()) {
			for (saledata item : sale_table.getItems()) {
				if (item.getName().equals(updatesale.getName()))
					System.out.println("insert the ewe");
			}
			sub = sub - updatesale.getTotalamount();
			sale_table.getItems().remove(updatesale);
			String name = txfname.getText();
			int price = Integer.parseInt(txfprice.getText());
			int qty = Integer.parseInt(txfqty.getText());
			int total = qty * price;

			saledata newsale = new saledata(name, qty, price, total);
			sale_table.getItems().add(newsale);
			sub = total + sub;
			subtotal.setText(String.valueOf(sub) + " kyats");
			txfname.clear();
			txfprice.clear();
			txfqty.clear();
			System.out.println("Hello2");
			calculate();
			scroll.requestFocus();
		} else if (!txfname.getText().isEmpty() && !txfqty.getText().isEmpty() && !txfqty.getText().isEmpty()) {
			String name = txfname.getText();
			int price = Integer.parseInt(txfprice.getText());
			int qty = Integer.parseInt(txfqty.getText());
			int total = qty * price;
			saledata newsale = new saledata(name, qty, price, total);
			sale_table.getItems().add(newsale);

			sub = total + sub;
			subtotal.setText(String.valueOf(sub) + " kyats");
			txfname.clear();
			txfprice.clear();
			txfqty.clear();
			calculate();
			scroll.requestFocus();
			List<saledata> sd = new ArrayList<saledata>();

			for (saledata sw : sd) {
				if (sw.getName().equals(newsale.getName())) {
					System.out.println("Equal " + name);
				}

			}
			for (saledata saledata1 : sale_table.getItems()) {

				sd.add(saledata1);

			}
			System.out.println(sd);

		}

		else {
			JFXDialogLayout layout = new JFXDialogLayout();
			Label lbl = new Label("Warring");
			lbl.setFont(new Font("Arial", 30));
			layout.setHeading(lbl);
			layout.setBody(new Label("Please select one menu!!"));
			JFXButton btn = new JFXButton("Okay");

			btn.setStyle("-fx-background-color: #3b7069");
			btn.setTextFill(Color.WHITE);
			btn.setPrefSize(60, 30);

			JFXDialog dialog = new JFXDialog(stack, layout, JFXDialog.DialogTransition.RIGHT);
			btn.setOnAction(e -> {
				dialog.close();
				scroll.requestFocus();

			});
			layout.setActions(btn);
			dialog.show();
			System.out.println("Hello3");

		}
		
	}

	@FXML
	void cancelaction(ActionEvent event) {
		sale_table.getItems().clear();

	}

	@FXML
	void taxenter(ActionEvent event) {
//		txfdiscount.requestFocus();
//		System.out.println("Hello");
	}

	@FXML
	private StackPane stack;

	private List<Menu> menulist = new ArrayList<>();
	FXMLLoader fxmlLoader = new FXMLLoader();
	private saledata sale;
	public itemcontrol itemController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		date_time.setText("hello this is current date_time");	
		// sale_table.setEditable(true);
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col_unitprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		col_total.setCellValueFactory(new PropertyValueFactory<>("totalamount"));
		buttcol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
		// addButtonToTable();
		showdata();
		Callback<TableColumn<saledata, Void>, TableCell<saledata, Void>> cellFactory = new Callback<TableColumn<saledata, Void>, TableCell<saledata, Void>>() {
			@Override
			public TableCell<saledata, Void> call(final TableColumn<saledata, Void> param) {
				final TableCell<saledata, Void> cell = new TableCell<saledata, Void>() {

					private final Button btn = new Button("Cancel");

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction(e -> {
								saledata data = getTableView().getItems().get(getIndex());
								sub = sub - data.getTotalamount();
								subtotal.setText(String.valueOf(sub) + " kyats");

								sale_table.getItems().remove(data);
								txfname.clear();txfprice.clear();txfqty.clear();
								System.out.println("selectedData: " + data);
								// txfdiscount.clear();
								// txfpaid.clear();
								// txftax.clear();
								calculate();
//                                if(sale_table.getItems().size()==0) {
//                                 	System.out.println("hello world");
//                                 	txfpaid.setText("0 Kyat");
//                                 	lblrefund.setOpacity(0);
//            					 }

								System.out.println(lbltotal.getText());

							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		buttcol.setCellFactory(cellFactory);
		// calculate();

	}

	public void calculate() {
		lblrefund.setOpacity(1);
		saledata newsale;

		System.out.println(sub + "From calculate");

		SimpleIntegerProperty tax = new SimpleIntegerProperty(0);
		SimpleIntegerProperty discount = new SimpleIntegerProperty(0);
		SimpleIntegerProperty paid = new SimpleIntegerProperty(0);
		// SimpleIntegerProperty refund = new SimpleIntegerProperty(0);
		NumberBinding total = tax.add(sub);
		NumberBinding totalamount1 = discount.multiply(sub);
		NumberBinding totalamount2 = totalamount1.divide(100);
		NumberBinding totalamount = total.subtract(totalamount2);
		NumberBinding refund = paid.subtract(totalamount);
//		if(txftax.getText().isEmpty()&&txfdiscount.getText().isEmpty()&&txfpaid.getText().isEmpty()) {
//			new Alert(AlertType.WARNING,"fill the textfield",javafx.scene.control.ButtonType.OK).showAndWait();
//		}
//		else {
		lbltotal.textProperty().bind(totalamount.asString("%d Kyat"));
		if (txfpaid.getText().isEmpty()) {
			System.out.println("tip");
		} else {
			if (sale_table.getItems().size() == 0) {
				paid.set(0);
				lblrefund.textProperty().bind(refund.asString("%d Kyat"));
				txfpaid.setText("");

			} else {
				paid.set(Integer.parseInt(txfpaid.getText()));
				lblrefund.textProperty().bind(refund.asString("%d Kyat"));
			}
		}
		if (txftax.getText().isEmpty()) {
			System.out.println("tip2");
		} else {

			if (sale_table.getItems().size() == 0) {
				tax.set(0);
				txftax.setText("");
			} else {
				tax.set(Integer.parseInt(txftax.getText()));

			}
		}
		if (txfdiscount.getText().isEmpty()) {
			System.out.println("tip3");
		} else {

			if (sale_table.getItems().size() == 0) {
				discount.set(0);
				txfdiscount.setText("");
			} else {
				discount.set(Integer.parseInt(txfdiscount.getText()));

			}
		}
		
//		    lblrefund.textProperty().bind(refund.asString("%d Kyat"));
//			lblrefund.textProperty().bind(totalamount.asString("%d Kyat"));
//			lblrefund.textProperty().bind(refund.asString("%d Kyat"));
		System.out.println(lbltotal.getText());
		txftax.setOnAction(e -> {
			if (txftax.getText().isEmpty()) {
				tax.set(0);
				txfdiscount.requestFocus();
				txftax.setPromptText("No tax");
			} else {

				txfdiscount.requestFocus();
				tax.set(Integer.parseInt(txftax.getText()));
			}
		});
		txfdiscount.setOnAction(e -> {
			if (txfdiscount.getText().isEmpty()) {
				discount.set(0);
				txfpaid.requestFocus();
				txfdiscount.setPromptText("No discount");
			} else {
				txfpaid.requestFocus();
				discount.set(Integer.parseInt(txfdiscount.getText()));
			}
		});
		txfpaid.setOnAction(e -> {
			if (txfpaid.getText().isEmpty()) {
				new Alert(AlertType.WARNING, "Enter customer payment", javafx.scene.control.ButtonType.OK)
						.showAndWait();
			} else {

				paid.set(Integer.parseInt(txfpaid.getText()));
				lblrefund.textProperty().bind(refund.asString("%d Kyat"));
			}

		});

//		int tax = Integer.parseInt(txftax.getText());
//		int discount = Integer.parseInt(txfdiscount.getText());
//		int paid = Integer.parseInt(txfpaid.getText());
//	
//		int totalamount = tax+sub-(sub*discount/100);
//		int refund = paid - totalamount;
//		lbltotal.setText(String.valueOf(totalamount));
//		lblrefund.setText(String.valueOf(refund));
	}

	// }
//	Print Demo
//	public static void printNode(Node nodeToPrint) {
//		PrinterJob job = PrinterJob.createPrinterJob();
//		if (job != null) {
//			job.showPrintDialog(null);
//			Printer printer = Printer.getDefaultPrinter();
//			PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MarginType.DEFAULT);
//
//			if (job.printPage(nodeToPrint)) {
//				job.endJob();
//			}
//		}
//	}

	public void showdata() {
		menulist.addAll(dbcontrol.getAllMenu());
		scroll.setContent(null);
		GridPane p = new GridPane();

		System.out.println(menulist.size());

		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < menulist.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("menuitem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				itemcontrol itemController = fxmlLoader.getController();
				itemController.setdata(menulist.get(i));
				itemController.butt.setUserData(menulist.get(i).getId());
				itemController.initialize(this);

				if (column == 3) {
					column = 0;
					row++;
				}

				p.add(anchorPane, column++, row);

				GridPane.setMargin(anchorPane, new Insets(0, 0, 20, 20));
			}

			scroll.setContent(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void settabledata(saledata sale) {

		this.sale = sale;

		System.out.println(this.sale.toString());
		txfname.setText(this.sale.getName());
		txfprice.setText(String.valueOf(this.sale.getPrice()));
		txfqty.setText(String.valueOf(this.sale.getQty()));

	}
}
