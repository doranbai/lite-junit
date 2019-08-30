package com.bai.Calculator;

import com.bai.Calculator.Calculator;
import com.bai.litejunit.*;

public class CalculatorTest extends TestCase {
	Calculator calculator = new Calculator();
	public CalculatorTest(String name) {
		super(name);
	}
	
	public void testAdd() {
		calculator.add(10);
		Assert.assertEquals(1,calculator.getResult());
	}
	
	public void testSubtract() {
		calculator.subtract(5);
		Assert.assertEquals(-5,calculator.getResult());
	}

}
