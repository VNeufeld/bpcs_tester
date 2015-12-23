package com.bpcs.bpcs_tester.controls;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class URLGridControl  extends BaseGridControl{
	
	private static String LABEL = " Server URL";
	private static String BUTTON_LABEL = "Select";
	private static int LABEL_MIN_WIDTH = 100;
	private static int BUTTON_MIN_WIDTH = 50;
	private static int GRID_CONTROL_MAX_WIDTH = 500;
	private static int EDIT_TEXT_WIDTH = 200;
	
	private BasicTextField urlTextField;

	public URLGridControl(  ) {
		create(3,GRID_CONTROL_MAX_WIDTH);
		
		Label labelUrl = new Label(LABEL);
		labelUrl.setMinWidth(LABEL_MIN_WIDTH);
		
		urlTextField = new URLTextField();
		
		Button b = new Button(BUTTON_LABEL);
		b.setMinWidth(BUTTON_MIN_WIDTH);
		
		// Location in grid
		addElements(labelUrl,urlTextField,b );
		
	}


	public class URLTextField extends BasicTextField {

		public URLTextField() {
			super(EDIT_TEXT_WIDTH);
		}

		@Override
		protected void saveLocation(String text) {
			try {
				ModelProvider.INSTANCE.serverUrl = new URI(text);
			} catch (URISyntaxException e) {
				logger.error(e.getMessage());
			}
			ApplicationProperties.getInstance().setServerUrl(text);
			logger.info(" set server url : " + text);

		}

		@Override
		protected String getDefaultValue() {
			String url = ApplicationProperties.getInstance().getServerUrl();
			if ( StringUtils.isNotEmpty(url))
				saveLocation(url);
			else
				logger.info(" nor default server url is defined ");
			return url;
			
		}
		
	}

}
