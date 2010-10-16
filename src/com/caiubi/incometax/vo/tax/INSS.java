package com.caiubi.incometax.vo.tax;

import java.util.Map.Entry;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.MonetaryAmountRange;
import com.caiubi.shared.PercentAmount;

public class INSS implements Tax {

	private MonetaryAmount inss =  MonetaryAmount.ZERO;
	private MonetaryAmount baseSalary = MonetaryAmount.ZERO;
	private PercentAmount baseAliquot = PercentAmount.ZERO;

	public INSS(MonetaryAmount grossIncome, Aliquot aliquot) {
		for (Entry<MonetaryAmountRange, PercentAmount> inssAliquot : aliquot.getInssAliquot().entrySet()) {
			final MonetaryAmountRange range = inssAliquot.getKey();
			baseAliquot = inssAliquot.getValue();
			if (grossIncome.isBetween(range)) {
				baseSalary = grossIncome;
				break;
			} else if (grossIncome.isGreaterThan(range)) {
				baseSalary = range.getEnd();
			}
		}
		final MonetaryAmount salary = baseAliquot.of(grossIncome);
		inss = aliquot.getCeilInss().min(salary);
	}

	public MonetaryAmount getValue() {
		return inss;
	}

	public MonetaryAmount getBaseSalary() {
		return baseSalary;
	}

	public PercentAmount getBaseAliquot() {
		return baseAliquot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAliquot == null) ? 0 : baseAliquot.hashCode());
		result = prime * result + ((baseSalary == null) ? 0 : baseSalary.hashCode());
		result = prime * result + ((inss == null) ? 0 : inss.hashCode());
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
		INSS other = (INSS) obj;
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
		if (inss == null) {
			if (other.inss != null)
				return false;
		} else if (!inss.equals(other.inss))
			return false;
		return true;
	}
}