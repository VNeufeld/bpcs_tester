package com.bpcs.bpcs_tester.controls;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;

public class DropoffLocationText extends LocationTextElement {

	protected String getLabel() {
		return "Dropoff : ";
	}
	
	protected void saveLocation(String text) {
		System.out.println("dropoff location "+text);
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			ModelProvider.INSTANCE.dropoffAirport = text;
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			ModelProvider.INSTANCE.dropoffCityId = Integer.valueOf(text);
		}
		
		
	}

}