package tests.sjgp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import sjgp.*;

public class AssignmentGivenExamplesTest {

	@Test
	public void example1_3People_Food() {
		int people = 3;
		MaterialsIF food = new Food();
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(1299.99d), people, food); 
		double expected = 1591.58d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test
	public void example2_1Person_Drugs() {
		int people = 1;
		MaterialsIF material = new Drugs();
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(5432d), people, material); 
		double expected = 6199.81d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test
	public void example3_4People_Books() {
		int people = 4;
		MaterialsIF books = new Books();
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(12456.95d), people, books); 
		double expected = 13707.63d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}

}
