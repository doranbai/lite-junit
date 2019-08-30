package com.bai.litejunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase implements Test {
	private String name;
	
	public TestCase(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return super.toString() + name;
	}
	@Override
	public void run(TestResult result) {
		result.run(this);
	}
	
	protected void setUp() {
		
	}
	
	protected void tearDown() {
		
	}
	
	public void doRun() throws Throwable {
		setUp();
		try {
			runTest();
		} finally {
			tearDown();
		}
	}
	
	protected boolean runTest() throws Throwable{
		Method runMethod = null;
		try {
			runMethod = getClass().getMethod(name);
		} catch(NoSuchMethodException e ) {
			e.printStackTrace();
			return false;
		}
		try {
			runMethod.invoke(this);
		}
		catch(InvocationTargetException e) {
			//e.getTargetException().printStackTrace();
			throw e.getTargetException();
		}
		catch (IllegalAccessException e) {
			throw e;
		}
		return true;
	}
}
