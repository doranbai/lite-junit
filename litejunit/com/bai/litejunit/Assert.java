package com.bai.litejunit;

public class Assert {
	public static void assertEquals(Integer expected, Integer actual) {
		assertEquals(null,expected,actual);
	}
	
	public static void assertEquals(String message, Integer expected, Integer actual) {
		if(expected == null && actual == null)
			return;
		if(expected != null && expected.equals(actual))
			return;
		failNotEquals(message,expected,actual);
	}
	
	public static void failNotEquals(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null) {
			formatted = message + "";
		}
		assertionFail(formatted + "expected:<" + expected + "> but was:<" + actual +">");
	}
	
	public static void assertionFail(String message) {
		throw new AssertionFailedError(message);
	}
	
	public static void fail(String message) {
		throw new Error(message);
	}
}
