package com.bpcs.bpcs_tester;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bpcs.bpcs_tester.util.Time24HoursValidator;

public class TestValidator {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		String time ="12:23";
		Time24HoursValidator thvalHoursValidator = new Time24HoursValidator();
		boolean valid = thvalHoursValidator.validate(time);
	    System.out.println("Time24Hours is valid : " + time + " , " + valid);
	    Assert.assertEquals(true, valid); 
		
	}
	
	private Time24HoursValidator time24HoursValidator;
    

	public Object[][] ValidTime24HoursProvider() {
		return new Object[][]{
			new Object[] {"01:00"}, new Object[] {"02:00"},
                        new Object[] {"13:00"}, new Object[] {"1:00"}, 
                        new Object[] {"2:00"},new Object[] {"13:01"},
		        new Object[] {"23:59"}, new Object[] {"15:00"},
		        new Object[] {"00:00"}, new Object[] {"0:00"}
		};
	}	
	
	public Object[][] InvalidTime24HoursProvider() {
		return new Object[][]{
			new Object[] {"24:00"},new Object[] {"12:60"},
			new Object[] {"0:0"},new Object[] {"13:1"},
			new Object[] {"101:00"}
		};
	}

	public void ValidTime24HoursTest(String time) {
	    boolean valid = time24HoursValidator.validate(time);
	    System.out.println("Time24Hours is valid : " + time + " , " + valid);
	    Assert.assertEquals(true, valid);
	}
	
	
	public void InValidTime24HoursTest(String time) {
	    boolean valid = time24HoursValidator.validate(time);
	    System.out.println("Time24Hours is valid : " + time + " , " + valid);
	    Assert.assertEquals(false, valid); 
	}	


}
