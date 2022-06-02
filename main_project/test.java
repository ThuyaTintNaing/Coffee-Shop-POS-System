package main_project;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

	
	import javafx.application.Application;
	import javafx.beans.binding.DoubleBinding;
	import javafx.beans.property.SimpleDoubleProperty;
	import javafx.scene.Scene;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;

	public class test extends Application{
		private TextField txfX = new TextField();
		private TextField txfY = new TextField();
		private Label lblAnswer = new Label();
		private SimpleDoubleProperty x = new SimpleDoubleProperty(12.5);
		private SimpleDoubleProperty y = new SimpleDoubleProperty(.5);
		private DoubleBinding answer = x.add(y);
		
		public static void main(String[] args) {
			//send("mstzsn01@gmail.com","qazmlp123!","thuyatintnaing12@gmail.com","Heading","password");
			launch(args);
		}
		@Override
		public void start(Stage st) throws Exception {
			VBox root = new VBox(txfX, txfY, lblAnswer);
			root.setStyle("-fx-font: bold 20 Arial;");
			
			Scene sc = new Scene(root);
			st.setScene(sc);
			st.setTitle("Adding");
			st.show();
			
			lblAnswer.textProperty().bind(answer.asString("x + y= %.02f"));
			
			txfX.setOnAction(e->{
				x.set(Double.parseDouble(txfX.getText()));						
			});
			txfY.setOnAction(e->{
				y.set(Double.parseDouble(txfY.getText()));			
			});
		}
		
	
	
	
	
	
	
	
	
	
	
	
	public static void send(String from, String password, String to, String sub, String msg) {

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
