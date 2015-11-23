package com.bpcs.bpcs_tester.services;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import com.bpcs.bpcs_tester.controls.ProgressDialog;
import com.bpcs.bpcs_tester.model.json.Request;
import com.bpcs.bpcs_tester.model.json.Response;

public class MyTask implements Callable<Response> {

	private Timer timer = new Timer();
	private IService service;
	private Request request;
	private ProgressDialog pdx;
	public DoubleProperty progressProperty = new SimpleDoubleProperty();

	public MyTask(IService service, Request request, ProgressDialog pd ) {
		super();
		this.service = service;
		this.request = request;
		this.pdx  = pd;
	}
	
	public void execute() {
		pdx.activateProgressBar(this);
		
	}

	@Override
	public Response call() throws Exception {
		timer.schedule(new XTask(progressProperty), 500, 500);
		Response r = service.execute(request);
		timer.cancel();
	
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
			// Update your GUI here.
				System.out.println(" Cancel Timer r = " +r );
				pdx.getDialogStage().close();
			}
			});
		return r;
	}

	public DoubleProperty progressProperty() {
		return progressProperty;
	}

	static class XTask extends TimerTask {
		private DoubleProperty dp;

		XTask(DoubleProperty d) {
			dp = d;
		}

		@Override
		public void run() {

			dp.set(dp.doubleValue() + 0.05);
			System.out.println(" Task running " + dp.doubleValue());
		}
	}
	
	static class TestTask extends Task<Void> {

	    private final int waitTime; // milliseconds
	    private final int pauseTime; // milliseconds

	    public static final int NUM_ITERATIONS = 100;

	    TestTask(int waitTime, int pauseTime) {
	      this.waitTime = waitTime;
	      this.pauseTime = pauseTime;
	    }

	    @Override
	    protected Void call() throws Exception {
	      this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
	      this.updateMessage("Waiting...");
	      Thread.sleep(waitTime);
	      this.updateMessage("Running...");
	      for (int i = 0; i < NUM_ITERATIONS; i++) {
	        updateProgress((1.0 * i) / NUM_ITERATIONS, 1);
	        Thread.sleep(pauseTime);
	      }
	      this.updateMessage("Done");
	      this.updateProgress(1, 1);
	      return null;
	    }

	  }

}
