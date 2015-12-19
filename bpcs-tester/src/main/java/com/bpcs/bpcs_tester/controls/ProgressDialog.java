package com.bpcs.bpcs_tester.controls;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.json.Response;
import com.bpcs.bpcs_tester.services.MyTask;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressDialog {

	private Logger logger = Logger.getLogger(getClass());
	
    private final Stage dialogStage;
    private final ProgressBar pb = new ProgressBar();
    private final ProgressIndicator pin = new ProgressIndicator();
    
    final Label label ;

    public ProgressDialog() {
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setResizable(true);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Service");

        // PROGRESS BAR
        label = new Label();
        label.setText("alerto");
        final VBox vbb = new VBox();
        vbb.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);
        
        pb.setProgress(0.1);
        pin.setProgress(-1F);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pb, pin);
        
        vbb.getChildren().addAll(label, hb);

        Scene scene = new Scene(vbb);
        dialogStage.setScene(scene);
    }

    public void activateProgressBar(final MyTask task)  {
    	ExecutorService executorService = Executors.newSingleThreadExecutor();
       	
        pb.progressProperty().bind(task.progressProperty());
        pin.progressProperty().bind(task.progressProperty());
        dialogStage.show();
        try {
        	executorService.submit(task);
        }
        catch(Exception err) {
        	logger.error(err.getMessage(),err);
        }
        finally {
            executorService.shutdown();
		}
        
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public void setErrorText(final String text)  {
    	label.setText(text);
    }
    
}
