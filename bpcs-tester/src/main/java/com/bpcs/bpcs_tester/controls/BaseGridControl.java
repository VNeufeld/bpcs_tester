package com.bpcs.bpcs_tester.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class BaseGridControl extends GridPane {
	
	private final int gap = 8;
	
	private final Insets insets = new Insets(5);

	public BaseGridControl() {
		super();
		setHgap(gap);
		setVgap(gap);
		setPadding(insets);
	}
	
	public GridPane getGridPane() {
		return this;
	}

	
	protected void create() {

		ColumnConstraints cons1 = new ColumnConstraints();
		cons1.setHgrow(Priority.NEVER);

		ColumnConstraints cons2 = new ColumnConstraints();
		cons2.setHgrow(Priority.ALWAYS);

		ColumnConstraints cons3 = new ColumnConstraints();
		cons3.setHgrow(Priority.ALWAYS);
	
		getColumnConstraints().addAll(cons1, cons2, cons3);
	
	}

	protected void create(int columns, int max_width) {

		for ( int i = 0 ; i < columns; i++) {
			ColumnConstraints cons = new ColumnConstraints();
			if ( i == 0 )
				cons.setHgrow(Priority.NEVER);
			else
				cons.setHgrow(Priority.ALWAYS);
			getColumnConstraints().add(cons);
		}
		if ( max_width > 0)
			setMaxWidth(max_width);
		
	
	}

	protected void addElements(Control... controls) {
		int count = controls.length;
		for ( int i = 0; i < count; i++)
			add(controls[i], i, 0);
		
	}
	
	
}
