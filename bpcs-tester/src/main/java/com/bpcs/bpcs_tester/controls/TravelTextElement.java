package com.bpcs.bpcs_tester.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TravelTextElement {
	private GridPane gridPane;
	private TextField supplier;
	private TextField stations;
	private TextField servcats;
	
	public TravelTextElement() {
		create();	
	}
	
	public String getSupplier() {
		return supplier.getText();
	}
	public String getStations() {
		return stations.getText();
	}
	public String getServcat() {
		return servcats.getText();
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
		
		
		//gridPane.getColumnConstraints().addAll(cons1, cons2);
		
		Label label = new Label("Supplier");
		label.setMinWidth(100);
		supplier = new TextField();
		supplier.setPrefWidth(150);
		supplier.setMaxWidth(250);
		supplier.setMinWidth(150);

		Label label2 = new Label("Stations");
		label2.setMinWidth(80);
		stations = new TextField();
		stations.setMinWidth(200);


		Label label3 = new Label("Servcat");
		label3.setMinWidth(80);
		servcats = new TextField();
		servcats.setMinWidth(100);

		
		gridPane.add(label, 0, 0);
		gridPane.add(supplier, 1, 0);
		gridPane.add(label2, 2, 0);
		gridPane.add(stations, 3, 0);
		gridPane.add(label3, 4, 0);
		gridPane.add(servcats, 5, 0);
		
	}

	public GridPane getGridPane() {
		return gridPane;
	}

}
