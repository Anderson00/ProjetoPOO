package application;

import java.io.IOException;

import controllers.AdminLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NavigationView{
	public static String LOGIN = "../layouts/AdminLoginView.fxml";
	public static String HOME = "../layouts/HomeView.fxml"; 
	public static String EVENTOS_LISTA = "../layouts/AdminLoginView.fxml";
	
	private static AdminLoginController controller;
	
	public static void setLoginController(AdminLoginController controller){
		NavigationView.controller = controller;
	}
	
	
	public static void loadView(String fxml){
		try {
			controller.setVista(FXMLLoader.load(NavigationView.class.getResource(fxml)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
