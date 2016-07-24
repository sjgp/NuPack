package tests.sjgp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import sjgp.MarkupCalculator;

public class AssignmentGivenExamplesTest {

	@Test
	public void example3_4People_Books() {
		int people = 4;
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(12456.95d), people); 
		double expected = 13707.63d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}

}
