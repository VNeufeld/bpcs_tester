package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PickupDateControl extends InputDateControl {

	public PickupDateControl() {
		super("PickupDate");
	}

	@Override
	protected LocalDate getPropertyDateTime() {
		LocalDate ldt = ApplicationProperties.getInstance().getPickupDate();
		if (ldt == null) {
			ldt = LocalDate.now();
		}
		return ldt;
	}
	@Override
	protected String getPropertyTime() {
		return ApplicationProperties.getInstance().getPickupTime();
	}


	@Override
	protected EventHandler<ActionEvent> getEventAction() {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate date = datePicker.getValue();
				ApplicationProperties.getInstance().setPickupDate(date);
				System.out.println("Selected Pickup Date: " + date);
				
				ModelProvider.INSTANCE.pickupDateTimex = 
						LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),ModelProvider.INSTANCE.pickupDateTimex.getHour(), ModelProvider.INSTANCE.pickupDateTimex.getMinute());

			}
		};
		return event;
	}

	@Override
	protected void saveTime(String text) {
		System.out.println("Selected Pickup Time: " + text);
		ApplicationProperties.getInstance().setPickupTime(text);
		LocalTime lt = LocalTime.parse(text);
		ModelProvider.INSTANCE.pickupDateTimex = 
				LocalDateTime.of(ModelProvider.INSTANCE.pickupDateTimex.getYear(), 
						ModelProvider.INSTANCE.pickupDateTimex.getMonth(), 
						ModelProvider.INSTANCE.pickupDateTimex.getDayOfMonth(),
						lt.getHour(), 
						lt.getMinute());
		
	}

	
}
