package com.bpcs.bpcs_tester;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.bpcs.bpcs_tester.controls.DateTimePicker;
import com.bpcs.bpcs_tester.controls.InputDateControl;
import com.bpcs.bpcs_tester.controls.URLTextElement;

public class Main extends Application {

	public static void main(String[] args) {
		System.out.println("Start Hello World!");
		Application.launch(args);


	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		primaryStage.setTitle("Title");
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);

		MenuBar menuBar = createMenuBar();

		BorderPane borderPane = createBorderPane(scene, menuBar);
		
		//GridPane centerPane = initUI();

		GridPane centerPane = getCenterPane();
		borderPane.setCenter(centerPane);

		root.getChildren().add(borderPane);

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();	
	}
	
	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		EventHandler<ActionEvent> action = changeTabPlacement();

		Menu menu = new Menu("Direction");
		MenuItem left = new MenuItem("Left");

		left.setOnAction(action);
		menu.getItems().add(left);

		MenuItem right = new MenuItem("Right");
		right.setOnAction(action);
		menu.getItems().add(right);

		MenuItem top = new MenuItem("Top");
		top.setOnAction(action);
		menu.getItems().add(top);

		MenuItem bottom = new MenuItem("Bottom");
		bottom.setOnAction(action);
		menu.getItems().add(bottom);

		menuBar.getMenus().add(menu);
		return menuBar;
	}

	private EventHandler<ActionEvent> changeTabPlacement() {
		return new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String side = mItem.getText();
				if ("left".equalsIgnoreCase(side)) {
					System.out.println("left");
				} else if ("right".equalsIgnoreCase(side)) {
					System.out.println("right");
				} else if ("top".equalsIgnoreCase(side)) {
					System.out.println("top");
				} else if ("bottom".equalsIgnoreCase(side)) {
					System.out.println("bottom");
				}
			}
		};
	}

	private BorderPane createBorderPane(Scene scene, MenuBar menuBar) {
		BorderPane borderPane = new BorderPane();

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setTop(menuBar);
		return borderPane;
	}

	private GridPane getCenterPane() {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setGridLinesVisible(true);
		gridPane.setHgap(8);
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(15));
		
		ColumnConstraints cons1 = new ColumnConstraints();
		cons1.setHgrow(Priority.ALWAYS);
	
		ColumnConstraints cons2 = new ColumnConstraints();
		cons2.setHgrow(Priority.ALWAYS);
		cons2.setFillWidth(true);
		gridPane.getColumnConstraints().addAll(cons1, cons2);
		
		TitledPane pane1 = createCommonPane2();
	
		gridPane.add(pane1, 0, 0);
		
		TitledPane pane2 = createCommonPaneDate();
		
		gridPane.add(pane2, 1, 0);
			
		
		return gridPane;
	}

	private TitledPane createCommonPane2() {
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Common2");
		
					
		URLTextElement urlTextElement = new URLTextElement();
		
		
		OperatorTextElement operatorTextElement = new OperatorTextElement();
		
		VBox vBox = new VBox();
			
		vBox.getChildren().add(urlTextElement.getGridPane());
		vBox.getChildren().add(operatorTextElement.getGridPane());
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}

	private TitledPane createCommonPaneDate() {
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Date");
		
		gridTitlePane.setAlignment(Pos.TOP_LEFT);
		
	    final InputDateControl dateControl = new InputDateControl();
	    final InputDateControl dateControl2 = new InputDateControl();
		
//	    final DateTimePicker dateControl2 = new DateTimePicker();
//	    
//	    dateControl2.valueProperty().addListener(t -> System.out.println(t));
//
//        // Time only
//	    dateControl2.timeValueProperty().addListener(t -> System.out.println(t));
//
//        // DateAndTime
//	    dateControl2.dateTimeValueProperty().addListener(t -> System.out.println(t));
//	    
		VBox vBox = new VBox();
			
		vBox.getChildren().add(dateControl.getGridPane());
		vBox.getChildren().add(dateControl2.getGridPane());
		//vBox.getChildren().add(dateControl2);
		
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}

	private class OperatorTextElement {
		
		private GridPane gridPane;
		private TextField operator;
		
		OperatorTextElement() {
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
			
			
			gridPane.getColumnConstraints().addAll(cons1, cons2);
			
			Label label = new Label("Operator");
			label.setMinWidth(100);
			operator = new TextField();
			operator.setPrefWidth(150);
			operator.setMaxWidth(250);
			operator.setMinWidth(150);
			
						
//			Tooltip tt = new Tooltip();
//			tt.setText("Server URL");
//			tt.setFont(new Font("Arial", 22));
							
			gridPane.add(label, 0, 0);
			//HBox hb = new HBox(urlTextField,b);
			gridPane.add(operator, 1, 0);
			
		}

		public GridPane getGridPane() {
			return gridPane;
		}

		public TextField getUrlTextField() {
			return operator;
		}
		
	}

}
