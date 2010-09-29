package com.caiubi.incometax.vo.tax;

import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public interface Tax {

	MonetaryAmount getValue();
	MonetaryAmount getBaseSalary();
	PercentAmount getBaseAliquot();
}
