package sjgp;

import java.math.BigDecimal;

public class MaterialsBase implements MaterialsIF {

	@Override
	public BigDecimal getMarkup() {
		return BigDecimal.ZERO;
	}

}
