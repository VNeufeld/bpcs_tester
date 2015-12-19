package com.bpcs.bpcs_tester.controls;

import java.net.URI;
import java.net.URISyntaxException;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class URLGridControl  extends BaseGridControl{
	private BasicTextField urlTextField;

	public URLGridControl() {
		create();
		
		Label labelUrl = new Label("Server URL");
		labelUrl.setMinWidth(100);
		
		urlTextField = new URLTextField();
		
		urlTextField.setText(getDefaultValue());
		
		Button b = new Button("Select");
		b.setMinWidth(50);
		
		add(labelUrl, 0, 0);
		add(urlTextField, 1, 0);
		add(b, 2, 0);
		setMaxWidth(600);
		
	}

	private String getDefaultValue() {
		return ApplicationProperties.getInstance().getServerUrl();
	}

	public class URLTextField extends BasicTextField {

		@Override
		protected void saveLocation(String text) {
			try {
				ModelProvider.INSTANCE.serverUrl = new URI(text);
			} catch (URISyntaxException e) {
				logger.error(e.getMessage());
			}
			ApplicationProperties.getInstance().setServerUrl(text);

		}
		
	}

}
