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
import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.json.Hit;
import com.bpcs.bpcs_tester.model.json.HitGroup;
import com.bpcs.bpcs_tester.model.json.HitType;
import com.bpcs.bpcs_tester.model.json.LocationSearchResult;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static Logger logger = Logger.getLogger(JsonUtils.class);

	
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
	
	public String createDummyResponse(String file) throws IOException {
		String RESOURCE_FOLDER= "json";
		String resource = RESOURCE_FOLDER+ "/"+file;
		return  readResource(resource);
	}

	private String readResource(String file) {
		try {
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(file);
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "utf-8");
			String result = writer.toString();
			logger.info("dummy response "+ file + " " + result);
			return result;
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return null;

	}

	
}
