package com.bai.litejunit;

public class TestRunner {
	
	protected Class<?> loadSuiteClass(String suiteClassName) throws ClassNotFoundException {
		return Class.forName(suiteClassName);
	}
	
	public Test getTest(String suiteClassName) {
		if (suiteClassName.length() <= 0) {
			return null;
		}
		Class<?> testClass = null;
		try {
			testClass = loadSuiteClass(suiteClassName);
			return new TestSuite(testClass);
		} catch (Exception e) {
			return null;
		}
	}
	
	public TestResult doRun(Test suite) {
		TestResult result = new TestResult();
		long startTime = System.currentTimeMillis();
		suite.run(result);
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.println("Time:" + runtime);
		result.print();
		return result;
	}
	
	protected TestResult start(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Wrong Usage: No TestCase name");
		}
		String testCase = args[0];
		Test suite = getTest(testCase);
		if(suite == null) {
			throw new Exception("Can not create TestCase class"+ testCase);
		}		
		System.out.println("TestSuite:"+ suite);
		return doRun(suite);
	}
	
	public static void main(String[] args) {
		TestRunner testRunner = new TestRunner();
		try {
			TestResult tResult = testRunner.start(args);
			if (tResult.wasSuccessful() == false) {
				System.exit(-1);
			}
			System.exit(0);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(-2);
		}
	}
}
