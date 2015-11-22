package com.bpcs.bpcs_tester.model;

import java.net.URI;
import java.util.Calendar;

import com.bpcs.bpcs_tester.model.json.OfferFilter;

public enum ModelProvider {
	INSTANCE;

	public Operator selectedOperator;
	
	public URI  serverUrl;

	public long languageId;
	
	public OfferFilter offerFilter;
	
	public int pageSize;
	
	public String agencyNo;

	public long cityId;
	public long dropoffCityId;

	public long selectedPickupStationId;
	
	public String airport;
	public String dropoffAirport;
	
	public Calendar pickupDateTime;
	public Calendar dropoffDateTime;
	
	public String languageCode;

	public String supplierFilter;

	public String servcatFilter;

	public String stationFilter;


	
}
