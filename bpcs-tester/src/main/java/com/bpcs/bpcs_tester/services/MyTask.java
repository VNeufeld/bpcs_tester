package com.bpcs.bpcs_tester.services;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressIndicator;

import com.bpcs.bpcs_tester.controls.ProgressDialog;
import com.bpcs.bpcs_tester.model.json.Request;
import com.bpcs.bpcs_tester.model.json.Response;

public class MyTask implements Callable<Response> {
	private Logger logger = Logger.getLogger(getClass());

	private Timer timer = new Timer();
	private IService service;
	private Request request;
	private final ProgressDialog pdx;
	public DoubleProperty progressProperty = new SimpleDoubleProperty();

	public MyTask(IService service, Request request, ProgressDialog pdx ) {
		super();
		this.service = service;
		this.request = request;
		this.pdx  = pdx;
	}
	
	public void execute() {
		logger.info("start service "+service.getClass());
		pdx.activateProgressBar(this);
		logger.info("service "+service.getClass() + " is running...");
		
	}

	@Override
	public Response call() throws Exception {
		try {
			logger.info("execute service call "+service.getClass());
			
			timer.schedule(new XTask(progressProperty), 500, 500);
			Response r = service.execute(request);
			
			logger.info("execution of service "+service.getClass() + " is finished ..");
			
			
			return r;
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e);

			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error Dialog");
					alert.setContentText(" Error : " +e.getMessage());
					alert.showAndWait();
				}
				});
			
			
			
			return null;
		}
		finally {
			timer.cancel();
			logger.info("cancel timer");
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					logger.info("close pd dialog and update GUI ");
					pdx.getDialogStage().close();
				}
				});
		
		}
	}

	public DoubleProperty progressProperty() {
		return progressProperty;
	}

	static class XTask extends TimerTask {
		private Logger logger = Logger.getLogger(getClass());
		private DoubleProperty dp;

		XTask(DoubleProperty d) {
			dp = d;
		}

		@Override
		public void run() {

			dp.set(dp.doubleValue() + 0.05);
			logger.info(" Task running " + dp.doubleValue());
			
		}
	}
	
//	static class TestTask extends Task<Void> {
//
//	    private final int waitTime; // milliseconds
//	    private final int pauseTime; // milliseconds
//
//	    public static final int NUM_ITERATIONS = 100;
//
//	    TestTask(int waitTime, int pauseTime) {
//	      this.waitTime = waitTime;
//	      this.pauseTime = pauseTime;
//	    }
//
//	    @Override
//	    protected Void call() throws Exception {
//	      this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
//	      this.updateMessage("Waiting...");
//	      Thread.sleep(waitTime);
//	      this.updateMessage("Running...");
//	      for (int i = 0; i < NUM_ITERATIONS; i++) {
//	        updateProgress((1.0 * i) / NUM_ITERATIONS, 1);
//	        Thread.sleep(pauseTime);
//	      }
//	      this.updateMessage("Done");
//	      this.updateProgress(1, 1);
//	      return null;
//	    }
//
//	  }

}
