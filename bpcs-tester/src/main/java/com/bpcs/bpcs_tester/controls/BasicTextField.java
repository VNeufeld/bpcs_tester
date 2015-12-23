package com.bpcs.bpcs_tester.controls;

import org.apache.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public abstract class BasicTextField extends TextField {
	protected Logger logger = Logger.getLogger(getClass());

	public BasicTextField(int width) {
		this.setPrefWidth(width);
		this.setMinWidth(width);
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					saveLocation(getText());
				}

			}
		});
		this.editableProperty().setValue(true);
		this.setText(getDefaultValue());

	}

	protected abstract String getDefaultValue();

	protected abstract void saveLocation(String text);

}
