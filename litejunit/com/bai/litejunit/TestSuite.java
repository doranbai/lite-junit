package com.bai.litejunit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TestSuite implements Test{
	private String name;
	private List<TestFailure> failures = new ArrayList<>();
	private List<Test> tests =  new ArrayList<>();
	
	private void addTest(Test test) {
		tests.add(test);
	}
	
	private boolean isTestMethod(Method m) {
		String name = m.getName();
		Parameter[] parameters = m.getParameters();
		Class<?> returnType = m.getReturnType();
		return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
	}
	
	private boolean isPublicTestMethod(Method m) {
		return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
	}
	
	private void addTestMethod(Method m, Constructor<?> constructor) {
		if(isPublicTestMethod(m)) {
			try {
				addTest((Test) constructor.newInstance(m.getName()));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + name + tests;
	}
	
	public TestSuite(Class<?> theClass) throws Exception {
		this.name = theClass.getName();
		Constructor<?> constructor = null;
		try {
			constructor = theClass.getConstructor(String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Exception(name + "class constructor");
		}
		
		Method[] methods = theClass.getDeclaredMethods();
		for(Method m : methods) {
			addTestMethod(m,constructor);
		}
	}
	
	@Override
	public void run(TestResult result) {
		for(Iterator<Test> e = tests.iterator(); e.hasNext();) {
			Test test = (Test)e.next();
			test.run(result);
		}
	}
	
}
