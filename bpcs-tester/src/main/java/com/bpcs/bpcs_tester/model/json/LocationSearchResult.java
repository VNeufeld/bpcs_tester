package com.bpcs.bpcs_tester.model.json;

import java.util.ArrayList;
import java.util.List;

public class LocationSearchResult extends Response {

	private int nrTotalHits;
	
	private int nrGroups;

	private List<HitGroup> groupedList;

	public List<Hit> getAllLocations() {
		List<Hit> hits = new ArrayList<Hit>();
		for ( HitGroup group : groupedList ) {
			hits.addAll(group.getHitGroup());
		}
		return hits;
	}
	
	
	public void addGroup (HitGroup hl) {
		if (groupedList == null)
			groupedList = new ArrayList<HitGroup>();
		groupedList.add(hl);
		nrGroups++;
		nrTotalHits += hl.getNr();
	}
	
	public List<HitGroup> getGroupedList() {
		return groupedList;
	}

	public int getNrGroups() {
		return nrGroups;
	}

	public int getNrTotalHits() {
		return nrTotalHits;
	}

	public void setGroupedList(List<HitGroup> groupedList) {
		this.groupedList = groupedList;
		nrGroups = groupedList.size();
		nrTotalHits = 0;
		for (HitGroup innerList : groupedList){
			nrTotalHits += innerList.getNr();
		}
	}

	public void setNrGroups(int nrGroups) {
		this.nrGroups = nrGroups;
	}
	
	public void setNrTotalHits(int nrTotalHits) {
		this.nrTotalHits = nrTotalHits;
	}

	@Override
	public String toString() {
		return "LocationSearchResult [nrTotalHits=" + nrTotalHits + ", nrGroups=" + nrGroups + ", groupedList="
				+ groupedList + "]";
	}
}
