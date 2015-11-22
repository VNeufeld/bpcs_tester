package com.bpcs.bpcs_tester.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.Operator;

public class ApplicationProperties {
	private static Logger logger = Logger.getLogger(ApplicationProperties.class);	

	private PropertiesConfiguration config = null;
	private static ApplicationProperties instance = null;
	
	
	public static ApplicationProperties getInstance() {
		if ( instance == null)
			instance = new ApplicationProperties();
		return instance;
	}

	public ApplicationProperties() {
		try {
			String userPath = System.getProperty("user.home");
			File f = new File(userPath+"/bpcs.test.app.properties");
			if ( f.exists())
				config = new PropertiesConfiguration(f);
			else {
				PropertiesConfiguration configb = new PropertiesConfiguration("app.properties");
				configb.save(f);
				config = new PropertiesConfiguration(f);
			}
			config.setAutoSave(true);
		} catch (ConfigurationException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void close() {
		try {
			config.save();
		} catch (ConfigurationException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public Long getSelectedOperators() {
		return config.getLong("operators.selected");
	}

	public void setSelectedOperators(Long op) {
		config.setProperty("operators.selected",op);
	}
	
	public List<Operator> getOperators() {
		List<Operator> operators = new ArrayList<Operator>();
		String[] xops = config.getStringArray("operators");
		for ( String s : xops ) {
			String parts[] = s.split(":");
			Operator op = new Operator(Long.valueOf(parts[0]),parts[1].trim());
			operators.add(op);
		}
		return operators;
	}

	public boolean isDummy() {
		return true;
	}
	
	public LocalDate getPickupDate() {
		String prop = config.getString("pickupDate");
		if ( StringUtils.isNotEmpty(prop)) {
			try {
				LocalDate t = LocalDate.parse(prop);
				return t;
			}
			catch(DateTimeParseException dtm) {
				logger.error("can't parse "+prop+ " to pickupDate");
			}
		}
		return null;
		
	}
	
	public void setPickupDate(LocalDate date) {
		String df = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		config.setProperty("pickupDate",df);
	}


	public LocalDateTime getDropoffDate() {
		String prop = config.getString("dropoffDate");
		if ( StringUtils.isNotEmpty(prop)) {
			try {
				LocalDateTime t = LocalDateTime.parse(prop);
				return t;
			}
			catch(DateTimeParseException dtm) {
				logger.error("can't parse "+prop+ " to dropoffDate");
			}
		}
		return null;
		
	}
	

}
