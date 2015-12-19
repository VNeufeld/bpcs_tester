package com.bpcs.bpcs_tester.controls;

import java.net.URI;
import java.net.URISyntaxException;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class OperatorSelectElement {
	
	private GridPane gridPane;
	private BasicTextField operator;
	
	public OperatorSelectElement() {
		create();	
	}
	
	void create() {
		gridPane = new GridPane();
		gridPane.setHgap(8);
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(5));
		
		ColumnConstraints cons1 = new ColumnConstraints();
		cons1.setHgrow(Priority.NEVER);
		
		ColumnConstraints cons2 = new ColumnConstraints();
		cons2.setHgrow(Priority.ALWAYS);

		ColumnConstraints cons3 = new ColumnConstraints();
		cons3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(cons1, cons2, cons3);
		
		Label label = new Label("Operator");
		label.setMinWidth(100);
		operator = new OperatorTextField();
		

		Button button = new Button("Select");
		button.setMinWidth(50);
		
		HBox hb = new HBox(8);
		hb.getChildren().add(operator);
		hb.getChildren().add(button);
					
//		Tooltip tt = new Tooltip();
//		tt.setText("Server URL");
//		tt.setFont(new Font("Arial", 22));
						
		gridPane.add(label, 0, 0);
		//HBox hb = new HBox(urlTextField,b);
		gridPane.add(hb, 1, 0);

		//gridPane.add(button, 2, 0);
		
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public TextField getUrlTextField() {
		return operator;
	}
	public class OperatorTextField extends BasicTextField {

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
