package com.bpcs.bpcs_tester.controls;

import java.util.List;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.Operator;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class OperatorSelectComboElement {
	private static Logger logger = Logger.getLogger(OperatorSelectComboElement.class);
	private GridPane gridPane;
	private ComboBox<ComboOperator> operator;

	private String operatorText;

	public OperatorSelectComboElement() {
		create();
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

		ColumnConstraints cons3 = new ColumnConstraints();
		cons3.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(cons1, cons2, cons3);

		Label label = new Label("Operator");
		label.setMinWidth(100);
		operator = createOperatorComboBox();

		// Tooltip tt = new Tooltip();
		// tt.setText("Server URL");
		// tt.setFont(new Font("Arial", 22));

		gridPane.add(label, 0, 0);
		// HBox hb = new HBox(urlTextField,b);
		gridPane.add(operator, 1, 0);

		// gridPane.add(button, 2, 0);

	}

	private ComboBox<ComboOperator> createOperatorComboBox() {

		final ComboBox<ComboOperator> cb = new ComboBox<ComboOperator>();
		cb.setEditable(true);
		initOperator(cb);
		cb.valueProperty().addListener(new ChangeListener<ComboOperator>() {
			@Override
			public void changed(ObservableValue<? extends ComboOperator> ov, ComboOperator t, ComboOperator t1) {
				operatorText = t1.getName();
				logger.info("operatorText = " + operatorText);
				ApplicationProperties.getInstance().setSelectedOperators(t1.getId());
				ModelProvider.INSTANCE.selectedOperator = t1;

			}
		});
		return cb;
	}

	private void initOperator(ComboBox<ComboOperator> cb) {
		List<Operator> ops = ApplicationProperties.getInstance().getOperators();
		Long selOp = ApplicationProperties.getInstance().getSelectedOperators();
		ComboOperator selected_operator = null;
		for (Operator op : ops) {
			ComboOperator opc = new ComboOperator(op);
			cb.getItems().add(opc);
			if (selOp != null && op.getId() == selOp)
				selected_operator = opc;
		}
		if (selected_operator != null) {
			cb.setValue(selected_operator);
			ModelProvider.INSTANCE.selectedOperator = selected_operator;
		}

	}

	public GridPane getGridPane() {
		return gridPane;
	}

	private static class ComboOperator extends Operator {

		public ComboOperator(long id, String name) {
			super(id, name);
		}

		public ComboOperator(Operator op) {
			super(op.getId(), op.getName());
		}

		@Override
		public String toString() {
			return "" + id + " : " + name;
		}

	}

}
