package com.caiubi.incometax.vo.aliquot;

import java.util.Map;

import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.MonetaryAmountRange;
import com.caiubi.shared.PercentAmount;

public interface Aliquot {

	Map<MonetaryAmountRange, PercentAmount> getInssAliquot();
	MonetaryAmount getCeilInss();
	Map<PercentAmount, MonetaryAmount[]> getIrrfAliquot();
	MonetaryAmount getDependent();
	PercentAmount getFgtsAliquot();
}