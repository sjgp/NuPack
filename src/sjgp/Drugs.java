package sjgp;

import java.math.BigDecimal;

public class Drugs extends MaterialsBase {
	
	@Override
	public BigDecimal getMarkup() {
		return new BigDecimal(0.075);
	}

}
