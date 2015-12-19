package com.bpcs.bpcs_tester.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bpcs.bpcs_tester.model.ModelProvider;
import com.bpcs.bpcs_tester.model.json.Administration;
import com.bpcs.bpcs_tester.model.json.Agency;
import com.bpcs.bpcs_tester.model.json.DayAndHour;
import com.bpcs.bpcs_tester.model.json.Location;
import com.bpcs.bpcs_tester.model.json.LocationTypeRequest;
import com.bpcs.bpcs_tester.model.json.OfferFilter;
import com.bpcs.bpcs_tester.model.json.Station;
import com.bpcs.bpcs_tester.model.json.TravelInformation;
import com.bpcs.bpcs_tester.model.json.VehicleRequest;


public class CreateVehicleRequestUtils {

	public static VehicleRequest createVehicleRequest() {
		
		VehicleRequest request = createRequest();

		Administration admin = createAdministrator();
		
		request.setAdministration(admin);
		
		Agency agency = new Agency();
		agency.setAgencyNo(ModelProvider.INSTANCE.agencyNo);
		request.setAgency(agency);
		return request;
		
		
	}
	
	
	private static VehicleRequest createRequest() {

		VehicleRequest request = new VehicleRequest();
		TravelInformation ti = new TravelInformation();
		
		Location pickUpLocation = createPickupLocation();
		Location dropOffLocation = createDropoffLocation(pickUpLocation);
		
		ti.setPickUpLocation(pickUpLocation);
		ti.setDropOffLocation(dropOffLocation);
		
		LocalDateTime pickupDate = ModelProvider.INSTANCE.pickupDateTime;
		LocalDateTime dropoffDate = ModelProvider.INSTANCE.dropoffDateTime;
		
		DayAndHour dh = createDate(pickupDate);
		ti.setPickUpTime(dh);

		dh = createDate(dropoffDate);
		ti.setDropOffTime(dh);

		request.setTravel(ti);
		
		OfferFilter offerFilter = createFilter();
		if (offerFilter != null )
			request.setFilter(offerFilter);

		return request;

	}

	private static Location createPickupLocation() {
		Location pickupLocation = new Location();

		String aptCode = StringUtils.trimToEmpty(ModelProvider.INSTANCE.airport);
		long cityId = ModelProvider.INSTANCE.cityId;
		
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			pickupLocation.setAirport(aptCode);
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			pickupLocation.setCityId(cityId);
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.StationFilter) {
			
		}
		
		
		return pickupLocation;
	}

	private static Location createDropoffLocation(Location pickupLocation) {
		Location dropoffLocation = new Location();

		String aptCode = StringUtils.trimToEmpty(ModelProvider.INSTANCE.dropoffAirport);
		long cityId = ModelProvider.INSTANCE.dropoffCityId;
		
		if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.Airport) {
			if ( StringUtils.isNotEmpty(aptCode))
				dropoffLocation.setAirport(aptCode);
			else
				dropoffLocation.setAirport(pickupLocation.getAirport());
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.City) {
			if ( cityId > 0)
				dropoffLocation.setCityId(cityId);
			else
				dropoffLocation.setCityId(pickupLocation.getGeo() != null ? pickupLocation.getGeo().getCity() : -1);
		}
		else if ( ModelProvider.INSTANCE.locationTypeRequest == LocationTypeRequest.StationFilter) {
			
		}
		
		
		return dropoffLocation;
	}
	

	private static void createStationLocations(VehicleRequest request) {

		String stationFilter  = ModelProvider.INSTANCE.stationFilter;
		Long pickupStationId = null;
		Long dropofStationId = null;
		Long pickupSupplierId = null;
		Long dropoffSupplierId = null;
		if ( StringUtils.isNotEmpty(stationFilter)) {
			String[] parts = stationFilter.split(",");
			
			for ( String part : parts) {
				if(pickupStationId == null )
					pickupStationId =Long.parseLong(part);
				else
					if(dropofStationId == null )
						dropofStationId =Long.parseLong(part);
			}
			if ( dropofStationId == null)
				dropofStationId = pickupStationId;
		}
		
		if ( request.getFilter() != null && request.getFilter().getSuppliers() != null) {
			Long[] suppliers = request.getFilter().getSuppliers();
			if (suppliers.length > 0)
				pickupSupplierId = suppliers[0];
			if (suppliers.length > 1)
				dropoffSupplierId = suppliers[1];
			if ( dropoffSupplierId == null)
				dropoffSupplierId = pickupSupplierId;
		}
		
		if (pickupStationId != null && pickupSupplierId != null) {
			Location pickUpLocation = new Location();
			pickUpLocation.setStationId(pickupStationId);
			
			Station s = new Station(pickupStationId);
			s.setSupplierId(pickupSupplierId);
			s.setId(pickupStationId);
			pickUpLocation.setStation(s);
			
			request.getTravel().setPickUpLocation(pickUpLocation);
		}
		
		if ( dropofStationId != null && dropoffSupplierId != null) {
			
			
			Location dropOffLocation = new Location();
			dropOffLocation.setStationId(dropofStationId);
	
			Station s = new Station(dropofStationId);
			s.setId(dropofStationId);
			s.setSupplierId(dropoffSupplierId);
			dropOffLocation.setStation(s);
			
			request.getTravel().setDropOffLocation(dropOffLocation);
			
		}
		
		
		
		
	}


	private static OfferFilter createFilter() {
		OfferFilter offerFilter = new OfferFilter();

		String supplierFilter  = ModelProvider.INSTANCE.supplierFilter;
		String servcatFilter  = ModelProvider.INSTANCE.servcatFilter;
		
		boolean filterEnable = false;
		
		if ( StringUtils.isNotEmpty(supplierFilter)) {
			String[] parts = supplierFilter.split(",");
			List<Long> suppliers = new ArrayList<Long>();
			for ( String part : parts) {
				suppliers.add(Long.parseLong(part));
			}
			Long[] lsupp = suppliers.toArray(new Long[0]);
			offerFilter.setSuppliers(lsupp);
			
			filterEnable = true;;			
		}

		if ( StringUtils.isNotEmpty(servcatFilter)) {
			String[] parts = servcatFilter.split(",");
			List<Long> servcats = new ArrayList<Long>();
			for ( String part : parts) {
				servcats.add(Long.parseLong(part));
			}
			Long[] lsupp = servcats.toArray(new Long[0]);
			offerFilter.setServiceCatalogId(lsupp[0]);
			
			filterEnable = true;;			
			
		}
		if ( filterEnable)
			return offerFilter;
		else
			return null;
	}


	private static DayAndHour createDate(LocalDateTime pickupDate) {
		
		String sday = String.format("%4d-%02d-%02d",
				pickupDate.getYear(), pickupDate.getMonth().getValue(),
				pickupDate.getDayOfMonth());
		
		String sTime = String.format("%02d:%02d",
				pickupDate.getHour(), pickupDate.getMinute());

		DayAndHour dh = new DayAndHour();
		dh.setDate(sday);
		dh.setTime(sTime);
		return dh;
	}
	
	private static Administration createAdministrator() {
		Administration admin = new Administration();
		
		admin.setLanguage(ModelProvider.INSTANCE.languageCode);
		admin.setOperator(ModelProvider.INSTANCE.selectedOperator.getId());

		admin.setSalesChannel(7);
		admin.setCalledFrom(5);
		admin.setBroker(false);
		admin.setProviderId(1l);
		admin.setProvider("Internet");
		return admin;
	}



}
