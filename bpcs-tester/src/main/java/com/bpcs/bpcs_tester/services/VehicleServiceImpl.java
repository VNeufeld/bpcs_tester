package com.bpcs.bpcs_tester.services;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.Request;
import com.bpcs.bpcs_tester.model.json.Response;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;
import com.bpcs.bpcs_tester.model.json.VehicleResponse;

public class VehicleServiceImpl implements IService {
	
	private Logger logger = Logger.getLogger(getClass());


	@Override
	public VehicleResponse execute(Request request) throws Exception {
		
		logger.info(" execute request "+ request);
		
		int pageSizeInt = (int)ModelProvider.INSTANCE.pageSize;
		JoiHttpServiceFactory serviceFactory = new JoiHttpServiceFactory();
		VehicleHttpService service = serviceFactory.getVehicleJoiService();
		VehicleResponse response = service.getOffers((VehicleRequest)request, false, pageSizeInt);
//		int i = 0;
//		while ( true ) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(" Service running " + i);
//			if ( ++i == 5)
//				break;
//		}
		logger.info(" execution finished. response " + response);
		return response;
	}

}
