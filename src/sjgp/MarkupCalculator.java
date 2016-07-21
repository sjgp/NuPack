package sjgp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MarkupCalculator {
	
	private static final BigDecimal baseMarkup = new BigDecimal(0.05);

	public BigDecimal calculateBaseMarkup(BigDecimal baseAmount) {
		return baseAmount.multiply(baseMarkup);
	}
	
	public BigDecimal estimate(BigDecimal baseAmount) {
		BigDecimal total = baseAmount.add(calculateBaseMarkup(baseAmount));
		total = total.setScale(2, RoundingMode.HALF_UP);
		return total;
	}
	
}
