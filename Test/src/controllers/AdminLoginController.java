package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl.ReaderWriter;

import application.NavigationView;
import application.RoundImageView;
import application.RoundImageViewSkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminLoginController {

	@FXML
	private StackPane stackPane;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField authField;

    @FXML
    private JFXPasswordField pwdField;

    @FXML
    private JFXButton loginBtn;
    
    @FXML
    private StackPane fotoIcon;
    
    @FXML
    private Text avisoIncorreto;
    
    private RoundImageView im;
    private Socket sock;
    private StackPane modal;

    @FXML
    void loginCheck(ActionEvent event) { 
    	if(authField.getText().equals("") || pwdField.getText().equals("")){
    		authField.validate();
    		pwdField.validate();
    	}else{    	
    		Socket server;
			try {				
				modal = new StackPane(new JFXSpinner());
				modal.setStyle("-fx-background-color: rgba(0,0,0,.5)");
				stackPane.getChildren().add(modal);
				server = new Socket(Constantes.SERVER, Constantes.PORT_SERVER);
				
				LoginThread login = new LoginThread(this,server, authField.getText(), pwdField.getText(),avisoIncorreto);
	    		new Thread(login).start();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    }
    
    void removeSpinner(){
    	stackPane.getChildren().remove(modal);
    }
    
    @FXML
    void keyCheck(KeyEvent event){
    	if(event.getCode() == KeyCode.ENTER){
			loginBtn.fire();
		}
    }
    
    public void setVista(Node node){
    	stackPane.getScene().setRoot(new StackPane(node));
    	//stackPane.getChildren().setAll(node);
    }

    @FXML
    void recuperarSenhaBtn(ActionEvent event) {
    	DropShadow shadow = new DropShadow();
    	JFXDialogLayout layout = new JFXDialogLayout();
    	layout.setHeading(new Text("Recuperar senha"));
    	JFXTextField field = new JFXTextField();
    	field.setPromptText("Email");
    	field.setFocusColor(Color.rgb(114,159,207));
    	field.setFont(new Font(15));
    	layout.setBody(field);
    	layout.setActions(new JFXButton("Ok"));
    	JFXDialog dialog = new JFXDialog(stackPane, layout, DialogTransition.TOP);
    	dialog.setEffect(shadow);
    	dialog.show();
    }
    
    @FXML
    void cadastrarBtn(ActionEvent event){
    	DropShadow shadow = new DropShadow();
    	JFXDialogLayout layout = new JFXDialogLayout(); 
    	JFXDialog dialog = new JFXDialog(stackPane, layout, DialogTransition.TOP);
    	layout.setHeading(new Text("Cadastrar"));
    	try {
			layout.setBody((VBox)FXMLLoader.load(getClass().getResource("../layouts/CadastrarView.fxml")));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	layout.setActions(new JFXButton("Ok"),new JFXButton("Cancelar"));
    	((JFXButton)layout.getActions().get(0)).setOnAction(e -> {
    		
    	});
    	((JFXButton)layout.getActions().get(1)).setOnAction(e -> {
    		dialog.close();
    	});
    	
    	dialog.setEffect(shadow);
    	dialog.show();
    }
    
    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'AdminLoginView.fxml'.";
        assert authField != null : "fx:id=\"authField\" was not injected: check your FXML file 'AdminLoginView.fxml'.";
        assert pwdField != null : "fx:id=\"pwdField\" was not injected: check your FXML file 'AdminLoginView.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'AdminLoginView.fxml'.";
            
        DropShadow shadow = new DropShadow();
        im = new RoundImageView();        
        im.setImage(new Image("file:///home/anderson/workspace/Test/src/application/photo.jpg"));
        im.setEffect(shadow);        
        
        RoundImageViewSkin skin = new RoundImageViewSkin(im);
        skin.getSkinnable().setMaxWidth(200);
        skin.getSkinnable().setMinWidth(200);
        skin.getSkinnable().setMaxHeight(150.0);
        skin.getSkinnable().setMinHeight(150.0);
        
        
        
        fotoIcon.getChildren().add(im);
        
        RequiredFieldValidator required = new RequiredFieldValidator();        
        required.setIcon(new Icon(AwesomeIcon.WARNING,"0.6em",";","error"));
        required.setMessage("Usuário obrigatório");   

        RequiredFieldValidator requiredPass = new RequiredFieldValidator();        
        requiredPass.setIcon(new Icon(AwesomeIcon.WARNING,"0.6em",";","error"));
        requiredPass.setMessage("Senha obrigatória");   
                
        authField.getValidators().add(required);
        pwdField.getValidators().add(requiredPass);
        
       
       NavigationView.setLoginController(this);
       
    }
}
