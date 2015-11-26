package com.bpcs.bpcs_tester.model.json;

public enum LocationTypeRequest {
	
	StationFilter (1),
	City(2),
	Airport (6);
	
	LocationTypeRequest (int ordinal) {
		this.ordinal = ordinal;
	}
	
	int ordinal;
	
	public int getOrdinal (){
		return ordinal;
	}

}
