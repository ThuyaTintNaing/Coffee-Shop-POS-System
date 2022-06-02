package main_project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class control {

	@FXML
	private JFXTextField textfield;

	@FXML
	private JFXButton btnlogin;

	@FXML
	void pressaction(MouseEvent event) {
		if (textfield.getText().isEmpty()) {
			new Alert(AlertType.WARNING,"Please fill your name",ButtonType.OK).showAndWait();
			System.out.println("Fill the name");

		} else {
			String sql = "Select * from staff where staff_name = ? and Status = 'active'";
			staff s = null;
			try (Connection con = dbcontrol.createConnection(); PreparedStatement st = con.prepareStatement(sql);) {
				st.setString(1, textfield.getText());
				ResultSet rs = st.executeQuery();
				
				if (rs.next()) {
				
					s = new staff(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7));
					if (!s.getGmail().equals(null)&& s.getUsername().equals(textfield.getText()) && !s.getPassword().equals(pssfield.getText())) {
						System.out.println("Your email exist");
						System.out.println(s.getPassword());
						send("mstzsn01@gmail.com", "qazmlp123!", s.getGmail(),
								"Cafe Villa - Forget Password", s.getPassword());
						
						System.out.println(s);
						new Alert(AlertType.INFORMATION,"Your password already sent to your email,pls check your email",ButtonType.OK).showAndWait();
					} else {
						new Alert(AlertType.WARNING,"Please check the spelling or you clicked wrong button",ButtonType.OK).showAndWait();
						System.out.println("Your name is invalid");
					}

					
				} else {
					new Alert(AlertType.WARNING,"This name doesn't exist in database",ButtonType.OK).showAndWait();
					System.out.println("Your name doesnt exit in database");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	private Label lblerror;

	@FXML
	private JFXPasswordField pssfield;

	@FXML
	void setonaction(ActionEvent event) {

		staff login_staff = dbcontrol.logincheck(textfield.getText(), pssfield.getText());
		if (login_staff != null && login_staff.getPosition().equals("admin")&&login_staff.getUsername().equals(textfield.getText())) {
			new Alert(AlertType.INFORMATION, "login Successful", ButtonType.OK).showAndWait();
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();

				stage.close();
				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("draft1.fxml")));
				stage.setMaximized(false);
				stage.setResizable(false);
				stage.setScene(sc);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (login_staff != null && login_staff.getPosition().equals("staff")&&login_staff.getUsername().equals(textfield.getText())) {
			new Alert(AlertType.INFORMATION, "login Successful", ButtonType.OK).showAndWait();
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();

				stage.close();

				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("staff_ui.fxml")));
				sc.getStylesheets().add(getClass().getResource("staffui.css").toExternalForm());
				stage.setMaximized(false);
				stage.setResizable(true);
				stage.setScene(sc);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			new Alert(AlertType.WARNING, "login fail! Please enter correctly", ButtonType.OK).showAndWait();
//			textfield.clear();
//			pssfield.clear();

		}

	}

	public void send(String from, String password, String to, String sub, String msg) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(sub);
			message.setText(msg);
			System.out.println("Before");
			Transport.send(message);
			System.out.println("message sent successfully");

		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}

	}

}
