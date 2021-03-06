package com.bpcs.bpcs_tester.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.io.IOUtils;


public class XmlUtils {

	public String readResource(String file) {
		

		try {
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(file);
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "utf-8");
			String result = writer.toString();
			System.out.println("result = " + result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static String readResource(URL url) throws IOException{
		
		StringWriter writer = new StringWriter();

		InputStream is = url.openStream();
		IOUtils.copy(is, writer, "utf-8");
		//IOUtils.copy(is, writer, "cp1252");
		return  writer.toString();
	}
	

}
