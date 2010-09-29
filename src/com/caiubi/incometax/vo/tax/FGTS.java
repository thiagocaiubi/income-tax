package com.caiubi.incometax.vo.tax;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public class FGTS implements Tax {
	
	private MonetaryAmount fgts =  MonetaryAmount.ZERO;
	private PercentAmount baseAliquot = PercentAmount.ZERO;
	private MonetaryAmount baseSalary = MonetaryAmount.ZERO;

	public FGTS(MonetaryAmount grossIncome, Aliquot aliquot) {
		baseSalary = grossIncome;
		baseAliquot = aliquot.getFgtsAliquot();
		fgts = baseAliquot.of(baseSalary);
	}

	public PercentAmount getBaseAliquot() {
		return baseAliquot;
	}

	public MonetaryAmount getBaseSalary() {
		return baseSalary;
	}

	public MonetaryAmount getValue() {
		return fgts;
	}
}