package com.bpcs.bpcs_tester.model.json;



public enum HitType {

	UNINITIALIZED(-1),

	STATION (1),
	CITY(2),
	RAILWAY_STATION (3),
	AREA (4),
	COUNTRY (5),
	AIRPORT (6),
	PREFERED_HITS (7);
	
	
	HitType (int ordinal) {
		this.ordinal = ordinal;
	}
	
	int ordinal;
	
	public int getOrdinal (){
		return ordinal;
	}
	
	public static HitType parseInt (int value) {
		for (HitType hit : values()) {
			if (hit.ordinal == value) {
				return hit;
			}
		}
		throw new BadValueException("unknown availability : " + value);
	}

	public static HitType parseString(String s) {
		try {
			
			if ( "CITY".equalsIgnoreCase(s))
				return HitType.CITY;
			else if ("AIRPORT".equalsIgnoreCase(s))
				return HitType.AIRPORT;
			else if ("COUNTRY".equalsIgnoreCase(s))
				return HitType.COUNTRY;
			
			int i = Integer.parseInt(s);
			return parseInt(i);
		} catch (Exception e) {
			throw new BadValueException("could not interprete value as int " + s);
		}
	}

}
