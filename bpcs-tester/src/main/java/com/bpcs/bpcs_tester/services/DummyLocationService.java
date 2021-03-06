package com.bpcs.bpcs_tester.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.json.HitType;
import com.bpcs.bpcs_tester.model.json.LocationSearchResult;


public class DummyLocationService extends AbstractJoiService implements ILocationService{
	private static Logger logger = Logger.getLogger(DummyLocationService.class);

	public DummyLocationService(Long operator, URI uri, long language) {
		super(operator,uri, language);
	}

	public LocationSearchResult joiLocationSearch(String searchString, HitType filter, String country) {
		
		logger.info("executy dummy location service. search string "+searchString+ " filter = "+filter );

		try {
			
			String query = createLocationQuery(searchString, filter, country);
			
			URI locationServiceUri = createUri(query);
			logger.info("locationServiceUri = "+locationServiceUri.toString());
			String response = null;
			// dummy
			if ( filter == HitType.COUNTRY) {
				response = new JsonUtils().createDummyResponse("DummyLocationSearchCountries.json");
			}
			else
				response = new JsonUtils().createDummyResponse("DummyLocationSearchResult.json");
			logger.info("dummy response = "+response);
			
			return JsonUtils.createResponseClassFromJson(response, LocationSearchResult.class);
			
		} catch ( IOException e) {
			logger.error(e);
		} catch (URISyntaxException e) {
			logger.error(e);
		}
		return null;
		
	}


}
