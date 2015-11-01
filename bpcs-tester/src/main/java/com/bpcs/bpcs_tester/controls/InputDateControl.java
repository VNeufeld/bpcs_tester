package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;

import com.bpcs.bpcs_tester.util.Time24HoursValidator;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class InputDateControl {
	
	public InputDateControl() {
		super();
		create();
	}

	private GridPane gridPane;
	private DatePicker pickupDate;
	private DatePicker dropOffDate;
	
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
		cons3.setHgrow(Priority.ALWAYS);
	
		gridPane.getColumnConstraints().addAll(cons1, cons2, cons3);

		Label labelUrl = new Label("Pickup :");
		labelUrl.setMinWidth(60);
		
		gridPane.add(labelUrl, 0, 0);
		
		pickupDate = new DatePicker();
		LocalDate localDate = LocalDate.of(2015,  11,  20);
						
		pickupDate.setValue(localDate);
		pickupDate.setMinWidth(150);
		
		TimeTextField timeText = new TimeTextField();
		timeText.setText("10:00");
		timeText.setMinWidth(60);
		timeText.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println(" event "+event.toString());
				
				
			}
		});
			
		
		gridPane.add(pickupDate, 1, 0);
		gridPane.add(timeText, 2, 0);
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public DatePicker getPickupDate() {
		return pickupDate;
	}
	
	public class TimeTextField extends TextField {
		
		public TimeTextField() {
			super();
			this.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent  ke) {
					  System.out.println("Key Pressed: " + ke.getText());
					    String text = ke.getCharacter();
					    if ( text.length() > 0) {
							char c = text.charAt(0);
							System.out.println("c="+c+ " ci = "+(int)c);
							System.out.println("Answer:"+Integer.toHexString(c));
					    }
				}
				
			});
		}

		Time24HoursValidator thvalHoursValidator = new Time24HoursValidator();

		@Override
		public void replaceText(int start, int end, String text) {
			if ( text.length() > 0) {
				char c = text.charAt(0);
				System.out.println("c="+c+ " ci = "+(int)c);
				System.out.println("Answer:"+Integer.toHexString(c));
			}
			if ((text.matches("[0-9]*") || text.equals(":") ) ) {
				super.replaceText(start, end, text);
			}
		}

		@Override
		public void replaceSelection(String text) {
			System.out.println(" selection : " + text + " v: "+this.getText());
//			if (thvalHoursValidator.validate(text)) {
//				super.replaceSelection(text);
//			}
			super.replaceSelection(text);
		}

	}

		
}
