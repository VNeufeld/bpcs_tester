package com.bpcs.bpcs_tester.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.util.ApplicationProperties;


public class JoiHttpServiceFactory {

	private static Logger logger = Logger.getLogger(JoiHttpServiceFactory.class);

	private static GisHttpClient httpClient = null;
	

	public JoiHttpServiceFactory() {
	}
	

	private static GisHttpClient getGisHttpClientInstance() {
		if ( httpClient == null)
			httpClient = new GisHttpClient();
		
		return httpClient;
		
	}
	private URI getServerURI() throws URISyntaxException {
		
		URI uri = ModelProvider.INSTANCE.serverUrl;

		logger.info("VehicleHttpService URI : = "+uri.toString());
		return uri;
		
	}

	public ILocationService   getLocationJoiService(long operator) {
		try {
			URI uri = getServerURI();
			
			long language = ModelProvider.INSTANCE.languageId;
			
			boolean dummy = ApplicationProperties.getInstance().isDummy();
			ILocationService locationHttpService = null;
			if ( dummy )
			{
				locationHttpService = new DummyLocationService(operator, uri, language); 
			}

			else
				locationHttpService = new LocationHttpService(operator,uri, language);
			
			return locationHttpService;
		}
		catch(URISyntaxException ex) {
			
		}
		return null;

	}

	public VehicleHttpService getVehicleJoiService() {
		
		return  new  VehicleHttpService(getGisHttpClientInstance());
		
	}
}
