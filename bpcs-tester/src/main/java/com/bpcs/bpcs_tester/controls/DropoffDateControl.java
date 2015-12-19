package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.bpcs.bpcs_tester.model.ModelProvider;
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
				ModelProvider.INSTANCE.dropoffDateTime = 
						LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),ModelProvider.INSTANCE.dropoffDateTime.getHour(), ModelProvider.INSTANCE.dropoffDateTime.getMinute());
				

			}
		};
		return event;
	}

	@Override
	protected void saveTime(String text) {
		System.out.println("Selected Dropoff Time: " + text);
		ApplicationProperties.getInstance().setDropoffTime(text);
		LocalTime lt = LocalTime.parse(text);
		ModelProvider.INSTANCE.dropoffDateTime = 
				LocalDateTime.of(ModelProvider.INSTANCE.dropoffDateTime.getYear(), 
						ModelProvider.INSTANCE.dropoffDateTime.getMonth(), 
						ModelProvider.INSTANCE.dropoffDateTime.getDayOfMonth(),
						lt.getHour(), 
						lt.getMinute());
		
		
	}

}
