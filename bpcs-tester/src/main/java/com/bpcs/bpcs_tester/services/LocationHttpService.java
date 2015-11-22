package com.bpcs.bpcs_tester.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.json.HitType;
import com.bpcs.bpcs_tester.model.json.LocationSearchResult;


public class LocationHttpService   extends AbstractJoiService implements ILocationService{
	private static Logger logger = Logger.getLogger(LocationHttpService.class);
	
	

	public LocationHttpService(Long operator, URI uri, long language) {
		super(operator,uri, language);
		
	}

	public LocationSearchResult joiLocationSearch(String searchString, HitType filter, String country) {
		
		GisHttpClient httpClient = new GisHttpClient();;

		try {
			String query = createLocationQuery(searchString, filter, country);
			
			URI locationServiceUri = createUri(query);
			logger.info("locationServiceUri = "+locationServiceUri.toString());

			
			String response =  httpClient.sendGetRequest(locationServiceUri);
			logger.info("response = "+response);
			
			return JsonUtils.createResponseClassFromJson(response, LocationSearchResult.class);
			
		} catch ( IOException e) {
			logger.error(e);
		} catch (URISyntaxException e) {
			logger.error(e);
		}
		return null;
		
	}


}
