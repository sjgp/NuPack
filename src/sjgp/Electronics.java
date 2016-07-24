package sjgp;

import java.math.BigDecimal;

public class Electronics extends MaterialsBase {
	
	@Override
	public BigDecimal getMarkup() {
		return new BigDecimal(0.02);
	}

}
