package com.raul.katastringcalculator;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {

	
	@Test
	public void EmptyStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String emptyString = "";
		int result = 0;
		
		assertEquals(result, calculadora.calcula(emptyString));
	}
	
	@Test
	public void oneNumberStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String oneNumberString = "1";
		int result = 1;
		
		assertEquals(result, calculadora.calcula(oneNumberString));
	}
	
	@Test
	public void twoNumberStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String twoNumberString = "1,2";
		int result = 3;
		
		assertEquals(result, calculadora.calcula(twoNumberString));
	}

	@Test
	public void anyNumberStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String anyNumberString = "1,2,1,2,1,2";
		int result = 9;
		
		assertEquals(result, calculadora.calcula(anyNumberString));
	}
	
	@Test
	public void acceptLineJumpsStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String acceptLineJumpsString = "1\n2,3";
		int result = 6;
		
		assertEquals(result, calculadora.calcula(acceptLineJumpsString));
	}
	
	@Test
	public void anyDelimiterStringtest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String anyDelimiterString = "//;\n1;2";
									 
		int result = 3;
		
		assertEquals(result, calculadora.calcula(anyDelimiterString));
	}
	
	@Test
	public void negativeNumbersStringtest() {
		Calculadora calculadora = new Calculadora();
		String negativeNumbers = "1,4,-1,-2,-8";
									 
		String expect = "negatives not allowed: -1,-2,-8";
		
		try{
			calculadora.calcula(negativeNumbers);
			fail ("Excepcion no lanzada");
		}catch(NegativeNumberNotAllowedException ex){
			assertEquals(expect, ex.getMessage() );
		}
	}
	
	@Test
	public void ignoreBigNumberstest() throws NegativeNumberNotAllowedException {
		Calculadora calculadora = new Calculadora();
		String ignoreBigNumbers = "//;\n1;2;1001;1";
		int result = 4;
		
		assertEquals(result, calculadora.calcula(ignoreBigNumbers));
	}
	
}
