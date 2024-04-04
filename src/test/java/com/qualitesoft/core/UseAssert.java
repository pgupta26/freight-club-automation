package com.qualitesoft.core;

public class UseAssert {

	public static void assertEquals(String actual, String expected) {

		try {
			org.testng.Assert.assertEquals(actual, expected);
			Log.info("[" + actual + "] matched with [" + expected + "]");
		} catch (AssertionError e) {
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	public static void assertEquals(int actual, int expected) {

		try {
			org.testng.Assert.assertEquals(actual, expected);
			Log.info("[" + actual + "] matched with [" + expected + "]");
		} catch (AssertionError e) {
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	public static void assertEquals(double actual, double expected) {

		try {
			org.testng.Assert.assertEquals(actual, expected);
			Log.info("[" + actual + "] matched with [" + expected + "]");
		} catch (AssertionError e) {
			Log.error(e.getMessage());
			throw e;
		}
	}
	
	public static void assertEquals(boolean actual, boolean expected) {

		try {
			org.testng.Assert.assertEquals(actual, expected);
			Log.info("[" + actual + "] matched with [" + expected + "]");
		} catch (AssertionError e) {
			Log.error(e.getMessage());
			throw e;
		}
	}
}
