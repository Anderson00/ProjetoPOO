package controllers;

import java.awt.Dimension;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SizeWindowController implements ChangeListener<Number> {
	
	private Dimension dimension;
	
	public SizeWindowController(Dimension dimension) {
		this.dimension = dimension;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		// TODO Auto-generated method stub
		
	}
	

}
