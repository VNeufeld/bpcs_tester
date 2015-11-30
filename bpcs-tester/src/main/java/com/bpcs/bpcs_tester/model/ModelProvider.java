package com.bpcs.bpcs_tester.model;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Calendar;

import com.bpcs.bpcs_tester.controls.TravelTextElement;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;
import com.bpcs.bpcs_tester.model.json.OfferFilter;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;

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
	
	public LocalDateTime pickupDateTimex = LocalDateTime.now();
	
	public String languageCode;

	public String supplierFilter;

	public String servcatFilter;

	public String stationFilter;
	
	public LocationTypeRequest  locationTypeRequest;
	
	public TravelTextElement travelTextElement;
	
	public VehicleRequest vehicleRequest = new VehicleRequest();


	
}
