package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;

import controllers.HomeViewController.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class HomeViewController {
	
	@FXML
	private BorderPane border;
		
	
	@FXML
    void initialize() {
		
		TableColumn userName = new TableColumn("Usuário");
		userName.setMinWidth(100);
		userName.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
		
		TableColumn ageColumn = new TableColumn("Idade");
		ageColumn.setMinWidth(100);
		ageColumn.setCellValueFactory(new PropertyValueFactory<User,String>("age"));
		
		ObservableList<User> users = FXCollections.observableArrayList();
		users.add(new User("qwe", "12"));
		users.add(new User("qwddfe", "19"));
		users.add(new User("qwdfoklve", "15"));
		users.add(new User("folwqwe", "22"));
		users.add(new User("qdfkowe", "34"));
		
		TableView<User> tableView = new TableView<>();
		tableView.setItems(users);
		tableView.getColumns().addAll(userName, ageColumn);
		
		border.setCenter(tableView);
		System.out.println("wewef");
	}
	
	public static class User{
		private StringProperty userName;
		private StringProperty age;
		
		public User(String userName, String age){
			this.userName = new SimpleStringProperty(userName);
			this.age = new SimpleStringProperty(age);
		}
		
		public String getUserName(){
			return this.userName.get();
		}
		
		public String getAge(){
			return this.age.get();
		}
	}
}
