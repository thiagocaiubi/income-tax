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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAliquot == null) ? 0 : baseAliquot.hashCode());
		result = prime * result + ((baseSalary == null) ? 0 : baseSalary.hashCode());
		result = prime * result + ((fgts == null) ? 0 : fgts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FGTS other = (FGTS) obj;
		if (baseAliquot == null) {
			if (other.baseAliquot != null)
				return false;
		} else if (!baseAliquot.equals(other.baseAliquot))
			return false;
		if (baseSalary == null) {
			if (other.baseSalary != null)
				return false;
		} else if (!baseSalary.equals(other.baseSalary))
			return false;
		if (fgts == null) {
			if (other.fgts != null)
				return false;
		} else if (!fgts.equals(other.fgts))
			return false;
		return true;
	}
}