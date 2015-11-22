package com.bpcs.bpcs_tester.controls.eventshandler;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;
import com.bpcs.bpcs_tester.model.json.VehicleResponse;
import com.bpcs.bpcs_tester.services.CreateVehicleRequestUtils;
import com.bpcs.bpcs_tester.services.JoiHttpServiceFactory;
import com.bpcs.bpcs_tester.services.VehicleHttpService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GetOfferEventHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		
		int pageSizeInt = (int)ModelProvider.INSTANCE.pageSize;
		
		VehicleRequest request = CreateVehicleRequestUtils.createVehicleRequest();

		JoiHttpServiceFactory serviceFactory = new JoiHttpServiceFactory();
		VehicleHttpService service = serviceFactory.getVehicleJoiService();
		VehicleResponse response = service.getOffers(request, false, pageSizeInt);
		
	}

}
