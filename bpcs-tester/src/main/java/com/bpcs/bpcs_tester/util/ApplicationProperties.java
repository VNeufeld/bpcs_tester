package com.bpcs.bpcs_tester.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bpcs.bpcs_tester.model.Operator;

public class ApplicationProperties {
	private static Logger logger = Logger.getLogger(ApplicationProperties.class);	
	private final String  PICKUP_DATE = "pickupDate"; 
	private final String  PICKUP_TIME = "pickupTime"; 
	private final String  DROPOFF_DATE = "dropoffDate"; 
	private final String  DROPOFF_TIME = "dropoffTime";
	
	private final String  PICKUP_CITY = "pickupCity"; 
	private final String  DROPOFF_CITY = "dropoffCity"; 
	private final String  PICKUP_APT = "pickupApt"; 
	private final String  DROPOFF_APT = "dropoffApt"; 

	private final String  SERVER_URL = "serverUrl"; 
	

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String prop = config.getString(PICKUP_DATE);
		if ( StringUtils.isEmpty(prop)) {
			//Calendar cal = Calendar.getInstance();
			//LocalDateTime ldt = LocalDateTime.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1,  cal.get(Calendar.DAY_OF_MONTH),10,0);
			LocalDateTime ldt = LocalDateTime.now();
			prop = ldt.format(formatter);
			config.setProperty(PICKUP_DATE, prop);
		}
		
		if ( StringUtils.isNotEmpty(prop)) {
			try {
				LocalDate ldt = LocalDate.parse(prop,formatter);
				return ldt;
			}
			catch(DateTimeParseException dtm) {
				logger.error("can't parse "+prop+ " to pickupDate");
				
			}
		}
		return LocalDate.now();
		
	}
	
	public String getPickupTime() {
		String prop = config.getString(PICKUP_TIME);
		if ( StringUtils.isEmpty(prop)) {
			prop = "10:10";
			config.setProperty(PICKUP_TIME, prop);
		}
		return prop;
		
	}
	
	
	public void setPickupDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String df = date.format(formatter);
		config.setProperty(PICKUP_DATE,df);
	}
	public void setPickupTime(String time) {
		config.setProperty(PICKUP_TIME, time);
	}


	public LocalDate getDropoffDate() {
		String prop = config.getString(DROPOFF_DATE);
		if ( StringUtils.isEmpty(prop)) {
			LocalDateTime ldt = LocalDateTime.now();
			prop = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			config.setProperty(DROPOFF_DATE, prop);
		}
		
		if ( StringUtils.isNotEmpty(prop)) {
			try {
				return LocalDate.parse(prop);
			}
			catch(DateTimeParseException dtm) {
				logger.error("can't parse "+prop+ " to dropoffDate");
			}
		}
		return LocalDate.now();
		
	}
	public String getDropoffTime() {
		String prop = config.getString(DROPOFF_TIME);
		if ( StringUtils.isEmpty(prop)) {
			prop = "10:10";
			config.setProperty(DROPOFF_TIME, prop);
		}
		return prop;
		
	}

	public void setDropoffDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String df = date.format(formatter);
		config.setProperty(DROPOFF_DATE,df);
	}
	public void setDropoffTime(String time) {
		config.setProperty(DROPOFF_TIME, time);
	}

	public void setDropoffCity(String cityId) {
		if (cityId != null )
			config.setProperty(DROPOFF_CITY, cityId.toString());
	}
	public String getDropoffCity() {
		String prop = config.getString(DROPOFF_CITY);
		if (prop != null )
			return prop;
		return "";
	}

	public void setPickupCity(String cityId) {
		if (cityId != null )
			config.setProperty(PICKUP_CITY, cityId.toString());
	}
	public String getPickupCity() {
		String prop = config.getString(PICKUP_CITY);
		if (prop != null )
			return prop;
		return "";
	}

	public void setDropoffApt(String apt) {
		config.setProperty(DROPOFF_APT, apt);
	}
	public String getDropoffApt() {
		String prop = config.getString(DROPOFF_APT);
		if (prop != null )
			return prop;
		return "";
	}

	public void setPickupApt(String apt) {
		config.setProperty(PICKUP_APT, apt);
	}
	public String getPickupApt() {
		String prop = config.getString(PICKUP_APT);
		if (prop != null )
			return prop;
		return "";
	}

	public void setServerUrl(String text) {
		config.setProperty(SERVER_URL, text);
	}
	public String getServerUrl() {
		String prop = config.getString(SERVER_URL);
		if (prop != null )
			return prop;
		return "";
	}
	
}
