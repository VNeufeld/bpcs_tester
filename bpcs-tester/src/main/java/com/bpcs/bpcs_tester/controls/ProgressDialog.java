package com.bpcs.bpcs_tester.controls;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.bpcs.bpcs_tester.model.json.Response;
import com.bpcs.bpcs_tester.services.MyTask;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressDialog {
    private final Stage dialogStage;
    private final ProgressBar pb = new ProgressBar();
    private final ProgressIndicator pin = new ProgressIndicator();

    public ProgressDialog() {
        dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        // PROGRESS BAR
        final Label label = new Label();
        label.setText("alerto");

        
        pb.setProgress(0.1);
        pin.setProgress(-1F);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pb, pin);

        Scene scene = new Scene(hb);
        dialogStage.setScene(scene);
    }

    public void activateProgressBar(final MyTask task)  {
    	ExecutorService executorService = Executors.newSingleThreadExecutor();
       	
        pb.progressProperty().bind(task.progressProperty());
        pin.progressProperty().bind(task.progressProperty());
        dialogStage.show();
        Future<Response> future = executorService.submit(task);

        //dialogStage.close();
        
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
}