package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;

import com.bpcs.bpcs_tester.util.Time24HoursValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class InputDateControl extends BaseGridControl{
	private final String label;
	private final int labelWidth = 90; 
	private final int timeTextWidth = 60; 
	private final int datePickerWidth = 150; 
	protected DatePicker datePicker;
	protected TimeTextField timeText;
	
	public InputDateControl(final String label) {
		super();
		this.label = label;
		createDateControl();
	}
	
	private void createDateControl() {
		createLabel(0,0); 

		datePicker = createDatePicker(datePickerWidth,1, 0);
		LocalDate saved = getPropertyDateTime();
		datePicker.setValue(saved);
		
		timeText = createTimeTextField(timeTextWidth, 2,0);
		
//		DateTimeFormatter LOCAL_TIME = new DateTimeFormatterBuilder()
//                .appendValue(HOUR_OF_DAY, 2)
//                .appendLiteral(':')
//                .appendValue(MINUTE_OF_HOUR, 2).toFormatter();
		
		String s = getPropertyTime();
		timeText.setText(s);
	}

	protected String getPropertyTime() {
		return "10:15";
	}

	private TimeTextField createTimeTextField(int width, int row, int col) {
		TimeTextField ttf = new TimeTextField();
		ttf.setMinWidth(width);
		add(ttf, row, col);
		return ttf;
	}

	private DatePicker createDatePicker(int datePickerWidth,int row, int col) {
		DatePicker dp = new DatePicker();
		dp.setMinWidth(datePickerWidth);
		EventHandler<ActionEvent> eventHandler = getEventAction();
		if ( eventHandler != null)
			dp.setOnAction(eventHandler);
		add(dp, row, col);
		return dp;
	}

	protected EventHandler<ActionEvent> getEventAction() {
		return null;
	}

	protected void createLabel(int row, int col) {
		Label labelUrl = new Label(label);
		labelUrl.setMinWidth(labelWidth);
		add(labelUrl, row, col);
	}

	protected LocalDate getPropertyDateTime() {
		return LocalDate.now(); 
	}
	protected void saveTime(String text) {
		
	}

	public class TimeTextField extends TextField {
		
		public TimeTextField() {
			super();
			this.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent  ke) {
					    String text = ke.getCharacter();
				}
				
			});
			this.focusedProperty().addListener(new ChangeListener<Boolean>() {

				public void changed(ObservableValue<? extends Boolean> arg0,
						Boolean oldPropertyValue, Boolean newPropertyValue) {
					if (!newPropertyValue) {
						saveTime(getText());
					}
				}

	
			});
			
		}

		Time24HoursValidator thvalHoursValidator = new Time24HoursValidator();

//		@Override
//		public void replaceText(int start, int end, String text) {
//			if ( text.length() > 0) {
//				char c = text.charAt(0);
//				System.out.println("c="+c+ " ci = "+(int)c);
//				System.out.println("Answer:"+Integer.toHexString(c));
//			}
//			if ((text.matches("[0-9]*") || text.equals(":") ) ) {
//				super.replaceText(start, end, text);
//			}
//		}
//
//		@Override
//		public void replaceSelection(String text) {
//			System.out.println(" selection : " + text + " v: "+this.getText());
////			if (thvalHoursValidator.validate(text)) {
////				super.replaceSelection(text);
////			}
//			super.replaceSelection(text);
//		}

	}

		
}
