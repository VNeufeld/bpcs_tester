package com.bpcs.bpcs_tester.model;

public class Operator {
	protected long id;
	protected String name;

	public Operator(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

}
