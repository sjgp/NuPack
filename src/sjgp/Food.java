package sjgp;

import java.math.BigDecimal;

public class Food extends MaterialsBase {
	
	@Override
	public BigDecimal getMarkup() {
		return new BigDecimal(0.13);
	}

}
