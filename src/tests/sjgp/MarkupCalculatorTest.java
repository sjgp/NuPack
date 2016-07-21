package tests.sjgp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import sjgp.MarkupCalculator;

public class MarkupCalculatorTest {

	//
	// tests for estimate()
	//
	@Test
	public void estimate_Minimal() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(105.0d, calc.estimate(new BigDecimal(100.0d)).doubleValue(), 0.001);
	}
	
	@Test	// 100.75 + 5.0375
	public void estimate_RoundUp() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(105.79d, calc.estimate(new BigDecimal(100.75d)).doubleValue(), 0.001);
	}
	
	@Test	// 100.25 + 5.0125
	public void estimate_RoundDown() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(105.26d, calc.estimate(new BigDecimal(100.25d)).doubleValue(), 0.001);
	}
	
	
	//
	// Tests for calculateBaseMarkup()
	//
	@Test
	public void calculateBaseMarkup_ExactDollarResult() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(5.0d, calc.calculateBaseMarkup(new BigDecimal(100.0d)).doubleValue(), 0.001);
	}

	@Test
	public void calculateBaseMarkup_ExactCentsResult() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(5.75d, calc.calculateBaseMarkup(new BigDecimal(115.0d)).doubleValue(), 0.001);
	}
	
	@Test
	public void calculateBaseMarkup_InexactCentsResult_DoNotRoundDown() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(5.0125d, calc.calculateBaseMarkup(new BigDecimal(100.25d)).doubleValue(), 0.0001);
	}
	
	@Test
	public void calculateBaseMarkup_InexactCentsResult_DoNotRoundUp() {
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(5.0375d, calc.calculateBaseMarkup(new BigDecimal(100.75d)).doubleValue(), 0.0001);
	}
	
}
