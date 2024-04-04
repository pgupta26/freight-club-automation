package com.qualitesoft.core;

import org.testng.asserts.SoftAssert;

public class SoftAssertion { 
	
	public static void assertEquals(String actual, String expected) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(actual, expected);
			Log.info("[" + actual + "] matched with [" + expected + "]");
		} catch (AssertionError e) {
			Log.error(e.getMessage());
			throw e;
		}
	}
}
