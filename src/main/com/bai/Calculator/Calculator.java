package com.bai.Calculator;


public class Calculator {
	private int result = 0;
	
	public void add (int x) {
		result += x;
	}
	
	public void subtract (int x) {
		result -= x;
	}
	public int getResult() {
		return result;
	}

}
