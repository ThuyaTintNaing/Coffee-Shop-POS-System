package main_project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class load extends Application {

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage st) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("login_ui.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		
		
		st.setScene(sc);
		st.setTitle("Cafe Villa");
		st.centerOnScreen();
		st.setResizable(false);
		st.show();
		
	}

}
