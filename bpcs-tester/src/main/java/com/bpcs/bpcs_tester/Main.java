package com.bpcs.bpcs_tester;

import java.util.List;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.controls.InputDateControl;
import com.bpcs.bpcs_tester.controls.LocationTextElement;
import com.bpcs.bpcs_tester.controls.OperatorSelectComboElement;
import com.bpcs.bpcs_tester.controls.URLTextElement;
import com.bpcs.bpcs_tester.controls.eventshandler.GetOfferEventHandler;
import com.bpcs.bpcs_tester.model.Operator;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private static Logger logger = Logger.getLogger(Main.class);	

	public static void main(String[] args) {
		System.out.println("Start Hello World!");
		logger.info(" Start ");
		
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
		
		TitledPane pane2 = createPaneDate();
		
		gridPane.add(pane2, 1, 0);
		
		TitledPane paneLocation = createPaneLocations();
		gridPane.add(paneLocation, 0, 2, 2, 1 );
		
		gridPane.add(createStartButtons(), 0, 3, 2, 1 );
		
		
		gridPane.add(createResultTable(), 0,  4, 2, 1);
		
		
		return gridPane;
	}

	private Node createStartButtons() {
		ToggleButton toggle = new ToggleButton("Toggle color");
		HBox hb = new HBox();
		// hb.setAlignment(Pos.CENTER);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(8);
		gridPane.setVgap(8);
		gridPane.setPadding(new Insets(5));
		
		Label labelUrl = new Label("Size");
		//labelUrl.setMinWidth(100);
		TextField pageSizeField = new TextField();

		Button b = new Button("Start");
		b.setMinWidth(50);
		b.setOnAction(new GetOfferEventHandler() );
		
		gridPane.add(labelUrl, 0, 0);
		gridPane.add(pageSizeField, 1, 0);
		gridPane.add(b, 2, 0);
		
		hb.getChildren().add(gridPane);
		
		Background bb = new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
//	     hb.backgroundProperty().bind(Bindings.when(toggle.selectedProperty())
//	                .then(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)))
//	                .otherwise(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));

		
		hb.setBackground(bb);
		
		return hb;
	}

	private TitledPane createCommonPane2() {
		
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Common2");
		
					
		URLTextElement urlTextElement = new URLTextElement();
		
		
		OperatorSelectComboElement operatorTextElement = new OperatorSelectComboElement();
		
		VBox vBox = new VBox();
			
		vBox.getChildren().add(urlTextElement.getGridPane());
		vBox.getChildren().add(operatorTextElement.getGridPane());
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}

	private TitledPane createPaneDate() {
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Date");
		
		gridTitlePane.setAlignment(Pos.TOP_LEFT);
		
	    final InputDateControl dateControl = new InputDateControl("pickupDate");
	    final InputDateControl dateControl2 = new InputDateControl("dropoffDate");

		VBox vBox = new VBox();
			
		vBox.getChildren().add(dateControl.getGridPane());
		vBox.getChildren().add(dateControl2.getGridPane());
			
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}
	
	private TitledPane createResultTable() {
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Result");
		
		gridTitlePane.setAlignment(Pos.TOP_LEFT);

		VBox vBox = new VBox();
		
			
			
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}

	
	private TitledPane createPaneLocations() {
		TitledPane gridTitlePane = new TitledPane();
		gridTitlePane.setText("Locations");
		
		gridTitlePane.setAlignment(Pos.TOP_LEFT);
		
		ToggleGroup group = new ToggleGroup();
	    RadioButton button1 = new RadioButton("City");
	    button1.setToggleGroup(group);
	    button1.setSelected(true);
	    RadioButton button2 = new RadioButton("Airport");
	    button2.setToggleGroup(group);
	    RadioButton button3 = new RadioButton("Filter");
	    button3.setToggleGroup(group);
		
	    final LocationTextElement dateControl = new LocationTextElement();
	    final LocationTextElement dateControl2 = new LocationTextElement();
	    LocationTypeSelectElement locationTypeSelectElement = new LocationTypeSelectElement();
	    TravelTextElement travelTextElement = new TravelTextElement();

		VBox vBox = new VBox();
		
		vBox.getChildren().add(locationTypeSelectElement.getGridPane());
		vBox.getChildren().add(dateControl.getGridPane());
		vBox.getChildren().add(dateControl2.getGridPane());
		vBox.getChildren().add(travelTextElement.getGridPane());
			
		gridTitlePane.setContent(vBox);
		
		return gridTitlePane;
	}
	
	private class LocationTypeSelectElement {
		private GridPane gridPane;
		private RadioButton buttonCity ;
		private RadioButton buttonApt ;
		private RadioButton buttonFilter ;
	  
		
		LocationTypeSelectElement() {
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
			cons2.setHgrow(Priority.NEVER);
			
			
			ColumnConstraints cons3 = new ColumnConstraints();
			cons3.setHgrow(Priority.NEVER);
			
			
			gridPane.getColumnConstraints().addAll(cons1, cons2, cons3);
			
			buttonCity = new RadioButton("City");
			buttonApt = new RadioButton("Airport");
			buttonFilter = new RadioButton("Filter");
			ToggleGroup group = new ToggleGroup();
			buttonCity.setToggleGroup(group);
			buttonCity.setSelected(true);
		    buttonApt.setToggleGroup(group);
		    buttonFilter.setToggleGroup(group);
									
			gridPane.add(buttonCity, 0, 0);
			gridPane.add(buttonApt, 1, 0);
			gridPane.add(buttonFilter, 2, 0);
		}

		public GridPane getGridPane() {
			return gridPane;
		}

	
	}


	
	private class TravelTextElement {
		
		private GridPane gridPane;
		private TextField supplier;
		private TextField stations;
		private TextField servcats;
		
		TravelTextElement() {
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


}
