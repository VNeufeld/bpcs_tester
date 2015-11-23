package com.bpcs.bpcs_tester.services;

import com.bpcs.bpcs_tester.model.json.Request;
import com.bpcs.bpcs_tester.model.json.Response;

public interface IService {
	Response execute(Request request);

}
