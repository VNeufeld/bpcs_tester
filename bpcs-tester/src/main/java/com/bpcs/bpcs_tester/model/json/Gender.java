package com.bpcs.bpcs_tester.model.json;



public enum Gender {

	UNINITIALIZED(-1),

	MRS (1),
	MR(2);
	
	Gender (int ordinal) {
		this.ordinal = ordinal;
	}
	
	int ordinal;
	
	public int getOrdinal (){
		return ordinal;
	}
}
