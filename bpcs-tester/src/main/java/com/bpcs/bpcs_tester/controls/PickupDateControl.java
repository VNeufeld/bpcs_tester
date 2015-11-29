package com.bpcs.bpcs_tester.controls;

import java.time.LocalDate;

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
	protected EventHandler<ActionEvent> getEventAction() {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate date = datePicker.getValue();
				ApplicationProperties.getInstance().setPickupDate(date);
				System.err.println("Selected Pickup Date: " + date);

			}
		};
		return event;
	}

	@Override
	protected EventHandler<ActionEvent> getTimeEventAction() {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//ApplicationProperties.getInstance().setPickupDate(date);
				System.out.println(" PickupTime : "+event.toString());
			}
		};
		return event;
	}
	
}