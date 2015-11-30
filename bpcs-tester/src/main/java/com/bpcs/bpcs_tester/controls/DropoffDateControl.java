package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;

import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DropoffDateControl extends InputDateControl {

	public DropoffDateControl() {
		super("Dropoff Date");
	}

	@Override
	protected LocalDate getPropertyDateTime() {
		LocalDate ldt = ApplicationProperties.getInstance().getDropoffDate();
		if (ldt == null) {
			ldt = LocalDate.now();
		}
		return ldt;
	}
	@Override
	protected String getPropertyTime() {
		return ApplicationProperties.getInstance().getDropoffTime();
	}

	@Override
	protected EventHandler<ActionEvent> getEventAction() {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate date = datePicker.getValue();
				ApplicationProperties.getInstance().setDropoffDate(date);
				System.out.println("Selected Dropoff Date: " + date);

			}
		};
		return event;
	}

	@Override
	protected void saveTime(String text) {
		System.out.println("Selected Dropoff Time: " + text);
		ApplicationProperties.getInstance().setDropoffTime(text);
		
	}

}
