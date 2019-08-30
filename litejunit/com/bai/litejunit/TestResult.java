package com.bai.litejunit;

import java.util.List;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class TestResult {
	private List<TestFailure> failures;
	private List<TestFailure> errors;
	private int testcount = 0;
	
	public TestResult() {
		failures = new ArrayList<>();
		errors = new ArrayList<>();
		testcount = 0;
	}
	
	public void addError(Test test, Throwable t) {
		errors.add(new TestFailure(test,t));
	}
	public void addFailure(Test test, Throwable t) {
		failures.add(new TestFailure(test,t));
	}
	
	public boolean wasSuccessful() {
		return failures.isEmpty() && errors.isEmpty();
	}
	
	public void run(TestCase testCase) {
		testcount ++;
		try {
			testCase.doRun();
			System.out.println(testCase + "------pass");
		}catch (AssertionFailedError e) {
			System.out.println(testCase + "------fail");	
			printFailure(e);
			addFailure(testCase, e);
		}
		catch (Throwable e) {
			System.out.println(testCase + "------error");
			printFailure(e);
			addError(testCase, e);	
		}
	}

	public int failureCount() {
		return failures.size();
	}

	public Iterator<TestFailure> failure() {
		return failures.iterator();
	}
	
	public String getFiltedTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	public void printFailure(Throwable t) {
				System.out.println(getFiltedTrace(t));
	}
	public void printFailures() {
		if (failureCount() != 0) {
			System.out.println("" + failureCount() + " failure(s)");
			for (Iterator<TestFailure> e = failure(); e.hasNext();) {
				TestFailure failure = e.next();
				System.out.println(getFiltedTrace(failure.getThrownException()));
			}
		}
	}
	
	public void print() {
		StringBuilder sb =  new StringBuilder();
		sb.append("Total:").append(testcount).append(" sucess:").append(testcount - failureCount()).append(" fail:").append(failureCount());
		System.out.println(sb);
	}
}
