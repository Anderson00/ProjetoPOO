package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {	
						
			StackPane root = (StackPane) FXMLLoader.load(getClass().getResource("../layouts/AdminLoginView.fxml"));
			Scene scene = new Scene(root,600,500);			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(oldValue || newValue)
						primaryStage.centerOnScreen();
				}
				
			});
			primaryStage.setTitle("Meus Eventos");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}