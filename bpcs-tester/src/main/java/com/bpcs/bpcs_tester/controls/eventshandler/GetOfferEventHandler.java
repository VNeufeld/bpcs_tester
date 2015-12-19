package com.bpcs.bpcs_tester.controls.eventshandler;

import com.bpcs.bpcs_tester.controls.ProgressDialog;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;
import com.bpcs.bpcs_tester.services.CreateVehicleRequestUtils;
import com.bpcs.bpcs_tester.services.IService;
import com.bpcs.bpcs_tester.services.MyTask;
import com.bpcs.bpcs_tester.services.VehicleServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GetOfferEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		VehicleRequest request1 = CreateVehicleRequestUtils.createVehicleRequest();

		IService service = new VehicleServiceImpl();
		
		final ProgressDialog pdx = new ProgressDialog();;

		MyTask task = new MyTask(service, request1, pdx);
		task.execute();
		System.out.println(" handle OK ");
				
	}

}
