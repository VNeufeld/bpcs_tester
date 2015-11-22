package com.bpcs.bpcs_tester.controls;

import com.bpcs.bpcs_tester.model.OfferDO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OfferResultTable {
	
	private final TableView<OfferDO> table = new TableView<>();
	
	@SuppressWarnings("unchecked")
	private void create() {
		table.setEditable(true);
		 
        TableColumn<OfferDO,String> firstNameCol = new TableColumn<OfferDO,String>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
 
        TableColumn<OfferDO,String> lastNameCol = new TableColumn<OfferDO,String>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        
        table.setItems(getData());

        table.getColumns().addAll(firstNameCol, lastNameCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
	}

	private ObservableList<OfferDO> getData() {
		 final ObservableList<OfferDO> data =  FXCollections.observableArrayList();
		 OfferDO offer = new OfferDO();
		 data.add(offer);
		 return data;
		 
	}

}
