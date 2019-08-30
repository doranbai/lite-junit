package com.bai.litejunit;

public class TestFailure {
	private Test failedTest;
	private Throwable thrownException;
	
	public TestFailure(Test failedTest, Throwable thrownException) {
		this.failedTest = failedTest;
		this.thrownException = thrownException;
	}

	public Throwable getThrownException() {
		return thrownException;
	}

}
