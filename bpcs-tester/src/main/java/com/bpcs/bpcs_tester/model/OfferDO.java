package com.bpcs.bpcs_tester.model;

import javafx.beans.property.SimpleStringProperty;

public class OfferDO {

	private SimpleStringProperty name;
	private SimpleStringProperty vehicle;
	private SimpleStringProperty supplier;
	private SimpleStringProperty station;
	private SimpleStringProperty serviceCatalog;
	private SimpleStringProperty price;
	private SimpleStringProperty status;
	private SimpleStringProperty oneWay;
	private SimpleStringProperty inclKm;
	
	public SimpleStringProperty getName() {
		return name;
	}
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	public SimpleStringProperty getVehicle() {
		return vehicle;
	}
	public void setVehicle(SimpleStringProperty vehicle) {
		this.vehicle = vehicle;
	}
	public SimpleStringProperty getSupplier() {
		return supplier;
	}
	public void setSupplier(SimpleStringProperty supplier) {
		this.supplier = supplier;
	}
	public SimpleStringProperty getStation() {
		return station;
	}
	public void setStation(SimpleStringProperty station) {
		this.station = station;
	}
	public SimpleStringProperty getServiceCatalog() {
		return serviceCatalog;
	}
	public void setServiceCatalog(SimpleStringProperty serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}
	public SimpleStringProperty getPrice() {
		return price;
	}
	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}
	public SimpleStringProperty getStatus() {
		return status;
	}
	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}
	public SimpleStringProperty getOneWay() {
		return oneWay;
	}
	public void setOneWay(SimpleStringProperty oneWay) {
		this.oneWay = oneWay;
	}
	public SimpleStringProperty getInclKm() {
		return inclKm;
	}
	public void setInclKm(SimpleStringProperty inclKm) {
		this.inclKm = inclKm;
	}
	

}
