package tests.sjgp;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import sjgp.MarkupCalculator;
import sjgp.MaterialsBase;
import sjgp.MaterialsIF;

public class MarkupCalculatorTest {
	
	//
	// tests for estimate()
	// Assumes base markup = 5%; per person markup = 1.2%
	//
	@Test
	public void estimate_Minimal_DefaultNumberPeople() {
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.0d));
		double expected = 100.0d + 5.0d + (105.0 * 1.2 / 100.0);
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test	// 100.75 + 5.0375
	public void estimate_RoundUp() {
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.75d), 0);
		double expected = 100.75d + 5.04d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test	// 100.25 + 5.0125
	public void estimate_RoundDown() {
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.25d), 0);
		double expected = 100.25d + 5.01d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test
	public void estimate_WaivePeopleFee() {
		int people = 0;
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.0d), people); 
		double expected = 100.0d + 5.0d;
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test
	public void estimate_MultiplePeople() {
		int people = 2;
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.0d), people); 
		double expected = 100.0d + 5.0d + (105.0 * 2.4 / 100.0);
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	@Test
	public void estimate_PeopleAndMaterials() {
		int people = 1;
		MarkupCalculator calc = new MarkupCalculator();
		MaterialsIF material = new FakeMaterials();
		BigDecimal estimate = calc.estimate(new BigDecimal(100.0d), people, material); 
		double expected = 100.0d + 5.0d + (105.0 * 1.2 / 100.0) + (105.0 * 10.0 / 100.0);
		assertEquals(expected, estimate.doubleValue(), 0.001);
	}
	
	//
	// Tests for calculateMaterialsMarkup()
	//
	@Test
	public void calculateMaterialsMarkup_MaterialWithZeroMarkupReturnsAmountParameter() {
		MaterialsIF material = new MaterialsBase();
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal amount = calc.calculateMaterialsMarkup(new BigDecimal(100.0d), material); 
		assertEquals(BigDecimal.ZERO, amount);
	}
	
	@Test
	public void calculateMaterialsMarkup_PositiveAmount() {
		MaterialsIF material = new FakeMaterials();
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal amount = calc.calculateMaterialsMarkup(new BigDecimal(100.0d), material); 
		assertEquals(10.0d, amount.doubleValue(), 0.001);
	}
	
	@Test
	public void calculateMaterialsMarkup_NegativeAmount() {
		MaterialsIF material = new FakeMaterials();
		((FakeMaterials)material).changeMarkup(-0.2d);
		MarkupCalculator calc = new MarkupCalculator();
		BigDecimal amount = calc.calculateMaterialsMarkup(new BigDecimal(100.0d), material); 
		assertEquals(-20.0d, amount.doubleValue(), 0.001);
	}
	
	
	
	//
	// Tests for calculatePersonsMarkup()
	//
	@Test	// 1 @ 1.2%
	public void calculatePersonsMarkup_OnePerson() {
		int numberPeople = 1;
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(1.2d, calc.calculatePersonsMarkup(new BigDecimal(100), numberPeople).doubleValue(), 0.001);
	}
	
	@Test	// 3 @ 1.2%
	public void calculatePersonsMarkup_ThreePeople() {
		int numberPeople = 3;
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(3.6d, calc.calculatePersonsMarkup(new BigDecimal(100), numberPeople).doubleValue(), 0.001);
	}
	
	@Test
	public void calculatePersonsMarkup_NoPeople() {
		int numberPeople = 0;
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(BigDecimal.ZERO, calc.calculatePersonsMarkup(new BigDecimal(100), numberPeople));
	}
	
	@Test
	public void calculatePersonsMarkup_NegativePeople_TreatAsZero() {
		int numberPeople = -1;
		MarkupCalculator calc = new MarkupCalculator();
		assertEquals(BigDecimal.ZERO, calc.calculatePersonsMarkup(new BigDecimal(100), numberPeople));
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

	//
	// private inner helper class
	//
	private class FakeMaterials implements MaterialsIF {
		private double markupPct = 0.1;
		@Override
		public BigDecimal getMarkup() {
			return new BigDecimal(markupPct);
		}
		
		public void changeMarkup(double percent) {
			markupPct = percent;
		}
	}
}
