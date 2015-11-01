package com.bpcs.bpcs_tester.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class URLTextElement {
	private GridPane gridPane;
	private URLTextField urlTextField;

	public URLTextElement() {
		create();
	}

	private void create() {
		gridPane = new GridPane();
		gridPane.setHgap(8);
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(5));

		ColumnConstraints cons1 = new ColumnConstraints();
		cons1.setHgrow(Priority.NEVER);

		ColumnConstraints cons2 = new ColumnConstraints();
		cons2.setHgrow(Priority.ALWAYS);

		ColumnConstraints cons3 = new ColumnConstraints();
		cons3.setHgrow(Priority.NEVER);

		gridPane.getColumnConstraints().addAll(cons1, cons2, cons3);

		Label labelUrl = new Label("Server URL");
		labelUrl.setMinWidth(100);
		urlTextField = new URLTextField();

		Button b = new Button("Select");
		b.setMinWidth(50);

		// Tooltip tt = new Tooltip();
		// tt.setText("Server URL");
		// tt.setFont(new Font("Arial", 22));

		gridPane.add(labelUrl, 0, 0);
		// HBox hb = new HBox(urlTextField,b);
		gridPane.add(urlTextField, 1, 0);
		gridPane.add(b, 2, 0);
		gridPane.setMaxWidth(600);
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public URLTextField getUrlTextField() {
		return urlTextField;
	}

	public class URLTextField extends TextField {
		public URLTextField() {
			this.setPrefWidth(150);
			this.setMaxWidth(450);
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

	public class NumberTextField extends TextField {

		@Override
		public void replaceText(int start, int end, String text) {
			if (text.matches("[0-9]") || text == "") {
				super.replaceText(start, end, text);
			}
		}

		@Override
		public void replaceSelection(String text) {
			if (text.matches("[0-9]") || text == "") {
				super.replaceSelection(text);
			}
		}

	}

}
