package com.bpcs.bpcs_tester.services;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.Request;
import com.bpcs.bpcs_tester.model.json.Response;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;
import com.bpcs.bpcs_tester.model.json.VehicleResponse;

public class VehicleServiceImpl implements IService {

	@Override
	public VehicleResponse execute(Request request) {
//		int pageSizeInt = (int)ModelProvider.INSTANCE.pageSize;
//		VehicleRequest request1 = CreateVehicleRequestUtils.createVehicleRequest();
//		JoiHttpServiceFactory serviceFactory = new JoiHttpServiceFactory();
//		VehicleHttpService service = serviceFactory.getVehicleJoiService();
//		VehicleResponse response = service.getOffers(request1, false, pageSizeInt);
		VehicleResponse response = new VehicleResponse();
		int i = 0;
		while ( true ) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" Service running " + i);
			if ( ++i == 5)
				break;
		}
		System.out.println(" Service finisched ");
		return response;
	}

}
