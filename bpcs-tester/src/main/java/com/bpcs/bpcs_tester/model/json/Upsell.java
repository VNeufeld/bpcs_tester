package com.bpcs.bpcs_tester.model.json;

import java.net.URI;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class Upsell {
	private String carGroupName;
	private Long carCategoryId;
	private Long vehicleId;
	private MoneyAmount price;
	private MoneyAmount stdPrice;
	private MoneyAmount diffPrice;
	private MoneyAmount diffPricePerDay;
	private UUID id;
	private URI link;
	
//	@JsonDeserialize(using = AvailabilityJsonDesirializer.class)
//	private Availability status;
	private String status;

	public Upsell() {
	}

	public Upsell(Offer upsellOffer, Offer offer) {
		this.carGroupName = upsellOffer.getName();
		this.carCategoryId = upsellOffer.getCarCategoryId();
		this.vehicleId = upsellOffer.getVehicleId();
		this.price = upsellOffer.getPrice();
		this.stdPrice = upsellOffer.getStdPrice();
		this.id = upsellOffer.getId();
		this.link = upsellOffer.getLink();
		this.status = upsellOffer.getStatus();
		this.diffPrice = MoneyAmount.subtract(upsellOffer.getPrice(), offer.getPrice());
	}


	public Long getVehicleId() {
		return vehicleId;
	}


	public MoneyAmount getPrice() {
		return price;
	}


	public MoneyAmount getStdPrice() {
		return stdPrice;
	}


	public UUID getId() {
		return id;
	}


	public URI getLink() {
		return link;
	}



	public Long getCarCategoryId() {
		return carCategoryId;
	}

	public MoneyAmount getDiffPrice() {
		return diffPrice;
	}

	public void calculatePricePerDay(int rentDays) {
		diffPricePerDay = MoneyAmount.divide(diffPrice, rentDays);
		
	}

	public MoneyAmount getDiffPricePerDay() {
		return diffPricePerDay;
	}

	public String getCarGroupName() {
		return carGroupName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
