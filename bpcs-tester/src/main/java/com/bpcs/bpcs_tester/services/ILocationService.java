package com.bpcs.bpcs_tester.services;

import com.bpcs.bpcs_tester.model.json.HitType;
import com.bpcs.bpcs_tester.model.json.LocationSearchResult;

public interface ILocationService {
	public LocationSearchResult joiLocationSearch(String searchString, HitType filter, String country);

}
