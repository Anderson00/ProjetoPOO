package controllers;

import java.io.File;
import java.io.IOException;

import com.jfoenix.validation.RequiredFieldValidator;

import application.RoundImageView;
import application.RoundImageViewSkin;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CadastrarDialogController {
	@FXML
	private VBox vbox;
	
	@FXML
	private StackPane fotoPane;
	
	@FXML
    void initialize() {
		
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Carregue uma imagem");
		chooser.getExtensionFilters().addAll(		
				new FileChooser.ExtensionFilter("All Images", "*.jpg","*.jpeg","*.png"),
			    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
			    new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
			    new FileChooser.ExtensionFilter("PNG", "*.png")			    
			);
		
		DropShadow shadow = new DropShadow();
        RoundImageView im = new RoundImageView();
        im.setCursor(Cursor.HAND);
        im.setOnMouseClicked(e -> {        	
			File file = chooser.showOpenDialog(vbox.getScene().getWindow());
			if(file != null)
				im.setImage(new Image("file://"+file.getAbsolutePath()));
        });
        im.setImage(new Image("file:///home/anderson/workspace/Test/src/application/photo.jpg"));
        im.setEffect(shadow);
        
        RoundImageViewSkin skin = new RoundImageViewSkin(im);
        skin.getSkinnable().setMaxWidth(100);
        skin.getSkinnable().setMinWidth(100);
        skin.getSkinnable().setMaxHeight(100);
        skin.getSkinnable().setMinHeight(100);
        
        fotoPane.getChildren().add(im);
                
    }
}
