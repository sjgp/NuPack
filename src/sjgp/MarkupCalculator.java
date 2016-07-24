package sjgp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MarkupCalculator {
	
	private static final BigDecimal baseMarkup = new BigDecimal(0.05);
	private static final BigDecimal perPersonMarkup = new BigDecimal(0.012);
	private static final int defaultNbrPeople = 1;

	public BigDecimal calculateBaseMarkup(BigDecimal baseAmount) {
		return baseAmount.multiply(baseMarkup);
	}
	
	public BigDecimal calculatePersonsMarkup(BigDecimal baseAmount, int numberPeople) {
		if (numberPeople <= 0)
			return BigDecimal.ZERO;
		else
			return baseAmount.multiply(perPersonMarkup.multiply(new BigDecimal(numberPeople)));
	}
	
	public BigDecimal calculateMaterialsMarkup(BigDecimal baseAmount, MaterialsIF material) {
		return baseAmount.multiply(material.getMarkup());
	}
	
	public BigDecimal estimate(BigDecimal baseAmount) {
		return estimate(baseAmount, defaultNbrPeople);
	}

	public BigDecimal estimate(BigDecimal baseAmount, int numberPeople) {
		return estimate(baseAmount, numberPeople, new MaterialsBase());
	}

	public BigDecimal estimate(BigDecimal baseAmount, int numberPeople, MaterialsIF materials) {
		BigDecimal basePlusFlatMarkup = baseAmount.add(calculateBaseMarkup(baseAmount));
		BigDecimal total = basePlusFlatMarkup.add(calculatePersonsMarkup(basePlusFlatMarkup, numberPeople));
		total = total.add(calculateMaterialsMarkup(basePlusFlatMarkup, materials));
		return total.setScale(2, RoundingMode.HALF_UP);
	}
	
}
