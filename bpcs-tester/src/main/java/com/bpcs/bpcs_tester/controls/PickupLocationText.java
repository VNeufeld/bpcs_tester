package com.bpcs.bpcs_tester.controls;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;

public class PickupLocationText extends LocationTextElement {

	protected String getLabel() {
		return "Pickup : ";
	}
	
	protected void saveLocation(String text) {
		System.out.println("pickup location "+text);
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			ModelProvider.INSTANCE.airport = text;
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			ModelProvider.INSTANCE.cityId = Integer.valueOf(text);
		}
		
		
	}
}
