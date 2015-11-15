package com.bpcs.bpcs_tester.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class BasicTextField extends TextField {
	public BasicTextField() {
		this.setPrefWidth(150);
		this.setMaxWidth(250);
		this.setMinWidth(150);
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("Textfield on focus");
				} else {
					System.out.println("Textfield out focus");
				}

			}
		});
		this.editableProperty().setValue(true);
	}

}
