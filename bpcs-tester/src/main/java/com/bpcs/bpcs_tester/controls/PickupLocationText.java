package com.bpcs.bpcs_tester.controls;

import org.apache.commons.lang.StringUtils;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;

public class PickupLocationText extends LocationTextElement {

	protected String getLabel() {
		return "Pickup : ";
	}
	
	protected void saveLocation(String text) {
		System.out.println("pickup location "+text);
		if ( StringUtils.isEmpty(text))
			return;
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			ModelProvider.INSTANCE.airport = text;
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			if ( StringUtils.isNumeric(text))
				ModelProvider.INSTANCE.cityId = Integer.valueOf(text);
		}
		
		
	}
}
