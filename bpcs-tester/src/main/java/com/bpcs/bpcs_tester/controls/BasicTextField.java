package com.bpcs.bpcs_tester.controls;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.services.JsonUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public abstract class BasicTextField extends TextField {
	protected Logger logger = Logger.getLogger(getClass());

	public BasicTextField() {
		this.setPrefWidth(150);
		this.setMaxWidth(250);
		this.setMinWidth(150);
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					saveLocation(getText());
				}

			}
		});
		this.editableProperty().setValue(true);
	}

	protected abstract void saveLocation(String text);

}
