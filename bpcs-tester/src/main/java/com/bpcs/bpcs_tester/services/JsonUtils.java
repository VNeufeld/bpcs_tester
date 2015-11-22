package com.bpcs.bpcs_tester.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.bpcs.bpcs_tester.model.json.Hit;
import com.bpcs.bpcs_tester.model.json.HitGroup;
import com.bpcs.bpcs_tester.model.json.HitType;
import com.bpcs.bpcs_tester.model.json.LocationSearchResult;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	public static <T> T createResponseClassFromJson(String jsonString, Class<T> claszz)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		T result = mapper.readValue(jsonString, claszz);
		return result;
	}
	
	public static String convertRequestToJsonString(Object request)
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();

	    //mapper.configure( SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false );

		//String result = mapper.writerWithDefaultPrettyPrinter(  ).writeValueAsString( request );
		String result = mapper.writeValueAsString( request );
		
		System.out.println("result " + result);
		
		return result;
	}
	
	public static String saveDummyResultAsJson() {
		
		LocationSearchResult result = new LocationSearchResult();
		HitGroup group = new HitGroup();
		group.setType(HitType.CITY);
		
		Hit hit = new Hit();
		hit.setId(Long.valueOf(12345l));
		hit.setIdentifier("München");
		hit.setType(HitType.CITY);
		group.addHit(hit);
		
		result.addGroup(group);
		
		try {
			String jsonString = JsonUtils.convertRequestToJsonString(result);

			File file = new File("C:/temp/locSearch.json");
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    	new FileOutputStream(file), "UTF-8"));
			out.append(jsonString);
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String createDummyResponse(String file) throws IOException {
		String RESOURCE_FOLDER= "/resource/json";
		String resource = RESOURCE_FOLDER+ "/"+file;
		return  new XmlUtils().readResource(resource);
	}

	
}
