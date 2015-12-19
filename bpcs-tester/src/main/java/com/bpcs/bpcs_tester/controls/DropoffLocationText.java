package com.bpcs.bpcs_tester.controls;

import org.apache.commons.lang.StringUtils;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;
import com.bpcs.bpcs_tester.util.ApplicationProperties;

public class DropoffLocationText extends LocationTextElement {

	protected String getLabel() {
		return "Dropoff : ";
	}
	
	protected void saveLocation(String text) {
		System.out.println("dropoff location "+text);
		if ( StringUtils.isEmpty(text))
			return;
		
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			ModelProvider.INSTANCE.dropoffAirport = text;
			ApplicationProperties.getInstance().setDropoffApt(text);
			
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			if ( StringUtils.isNumeric(text))  {
				ModelProvider.INSTANCE.dropoffCityId = Integer.valueOf(text);
				ApplicationProperties.getInstance().setDropoffCity(text);;
			}				
		}
		
		
	}
	protected String getDefaultValue() {
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			return ApplicationProperties.getInstance().getDropoffApt();
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			return ApplicationProperties.getInstance().getDropoffCity();
		}
		return "00";
	}

}
