package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.NavigationView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class LoginThread extends Task<Boolean>{
	private Socket server;
	private String user, pass;
	private Text msg;
	private AdminLoginController controll;
	
	public LoginThread(AdminLoginController controll,Socket server, String user, String pass, Text msg){
		this.controll = controll;
		this.server = server;
		this.user = user;
		this.pass = pass;
		this.msg = msg;
	}

	@Override
	protected Boolean call() throws Exception {
		// TODO Auto-generated method stub
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		boolean estado = false;
		try {		  			
			input = new ObjectInputStream(server.getInputStream());
			output = new ObjectOutputStream(server.getOutputStream());
			output.flush();
			    			
			output.writeObject(user+";"+pass);
			
			estado = input.readBoolean();
				  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
    		/*try {
    			input.close();
        		output.close(); 
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
		return estado;
	}
	
	@Override
	protected void updateValue(Boolean value) {
		// TODO Auto-generated method stub
		super.updateValue(value);
		if(value){
			NavigationView.loadView(NavigationView.HOME);
		}else{
			msg.setText("Usuário ou senha incorretos");
		}
		this.controll.removeSpinner();
	}

}
