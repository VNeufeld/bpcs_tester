package com.bpcs.bpcs_tester.model.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleResponse extends Response {

	private VehicleSummary summary;

	private List<VehicleGroup> groups = new ArrayList<VehicleGroup>();
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	private List<Insurance> insurances = new ArrayList<Insurance>();
	
	private RatingView ratingView;

	private List<Offer> recommendations;
	
	private long operator;

	private GuiTextElements texts;
	private Long remainingCacheSeconds = null;
	
	private OfferFilterTemplate  offerFilterTemplate;
	
	private String pageInfo;
	
	private String pageNo;

	private String pageCount;


	public VehicleResponse() {
		
	}
	
	public VehicleResponse(VehicleResponse vehicleResponse) {
		setOperator(vehicleResponse.getOperator());
		setLink(vehicleResponse.getLink());
		setRequestId(vehicleResponse.getRequestId());
		setSummary(vehicleResponse.getSummary());
		
		setTexts(vehicleResponse.getTexts());
		setOfferFilterTemplate(vehicleResponse.getOfferFilterTemplate());
		setInsurances(vehicleResponse.getInsurances());
		
	}

	public List<VehicleGroup> getGroups() {
		return groups;
	}
	
	public void addGroup(VehicleGroup group) {
		this.groups.add(group);
	}

	public long getOperator() {
		return operator;
	}
	public Long getRemainingCacheSeconds() {
		return remainingCacheSeconds;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}
	
	public VehicleSummary getSummary() {
		return summary;
	}

	public GuiTextElements getTexts() {
		return texts;
	}
	
	public void setGroups(List<VehicleGroup> groups) {
		if (groups == null)
			throw new NullPointerException("set null for group is not permitted.");
		this.groups = groups;
	}

	public void setOperator(long operator) {
		this.operator = operator;
	}

	public void setRemainingCacheSeconds(Long remainingCacheSeconds) {
		this.remainingCacheSeconds = remainingCacheSeconds;
	}

	public void setSummary(VehicleSummary summary) {
		this.summary = summary;
	}

	public void setTexts(GuiTextElements texts) {
		this.texts = texts;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "VehicleResponse [summary=" + summary + ", groups=" + groups
				+ ", vehicles=" + vehicles + ", operator=" + operator
				+ ", texts=" + texts + ", remainingCacheSeconds="
				+ remainingCacheSeconds + "]"+
				"Super ["+super.toString()+ "]";
	}
	
	public Vehicle findVehicle(long id) {
		if ( this.getVehicles() != null) {
			for ( Vehicle vehicle : vehicles) {
				if ( vehicle.getId() == id)
					return vehicle;
			}
		}
		return null;
	}

	public Set<Long> getVehicleIdsFromOffers() {
		Set<Long> vids = new HashSet<Long>();
		for ( VehicleGroup group : groups) {
			for ( Offer offer : group.getOfferList() ) {
				vids.add(offer.getVehicleId());
			}
		}
		return vids;
	}
	public List<Offer> getAllOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		
		if ( ratingView != null) {
			return ratingView.getAllOffers();
		}
		
		for ( VehicleGroup group : groups) {
			offers.addAll(group.getOfferList() );
		}
		return offers;
	}
	
	@JsonProperty("filterTemplate")
	public OfferFilterTemplate getOfferFilterTemplate() {
		return offerFilterTemplate;
	}

	public void setOfferFilterTemplate(OfferFilterTemplate offerFilterTemplate) {
		this.offerFilterTemplate = offerFilterTemplate;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public void calculateTotalSummary() {
		getSummary().setTotalQuantityVehicles(getVehicles().size());
		getSummary().setTotalQuantityOffers(getAllOffers().size());
		getSummary().setTotalQuantityGroups(getGroups().size());
		
	}

	public List<Offer> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<Offer> recommendations) {
		this.recommendations = recommendations;
	}

	public RatingView getRatingView() {
		return ratingView;
	}

	public void setRatingView(RatingView ratingView) {
		this.ratingView = ratingView;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public Offer findOfferForVehicle(String acriss) {
		List<Offer> offers = getAllOffers();
		for ( Offer offer :  offers ) {
			for ( Vehicle v : vehicles ) {
				if ( v.getACRISS().equals(acriss) && offer.getVehicleId() == v.getId())
					return offer;
			}
		}
		return null;
	}

}