package com.caiubi.incometax.vo.tax;

import java.util.Map.Entry;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public class IRRF implements Tax {
	
	private MonetaryAmount irrf =  MonetaryAmount.ZERO;
	private MonetaryAmount baseSalary = MonetaryAmount.ZERO;
	private PercentAmount baseAliquot = PercentAmount.ZERO;
	private MonetaryAmount deductInstallment = MonetaryAmount.ZERO;

	public IRRF(MonetaryAmount grossIncome, int dependents, INSS inss, Aliquot aliquot) {
		baseSalary = MonetaryAmount.ZERO;
		deductInstallment  = MonetaryAmount.ZERO;
		baseAliquot = PercentAmount.ZERO;
		for (Entry<PercentAmount, MonetaryAmount[]> irpfAliquot : aliquot.getIrrfAliquot().entrySet()) {
			baseSalary = irpfAliquot.getValue()[0].min(grossIncome);
			if (grossIncome == baseSalary) {
				break;
			}
			deductInstallment = irpfAliquot.getValue()[1];
			baseAliquot = irpfAliquot.getKey();
		}
		final MonetaryAmount subtract = grossIncome.subtract(inss.getValue());
		final MonetaryAmount xyz = new MonetaryAmount(dependents).multiply(aliquot.getDependent());
		final MonetaryAmount subtract2 = subtract.subtract(xyz);
		final MonetaryAmount multiply = baseAliquot.of(subtract2);
		irrf = multiply.subtract(deductInstallment).max(MonetaryAmount.ZERO);
	}
	
	public PercentAmount getBaseAliquot() {
		return baseAliquot;
	}

	public MonetaryAmount getBaseSalary() {
		return baseSalary;
	}

	public MonetaryAmount getValue() {
		return irrf;
	}
	
	public MonetaryAmount getDeductInstallment() {
		return deductInstallment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAliquot == null) ? 0 : baseAliquot.hashCode());
		result = prime * result + ((baseSalary == null) ? 0 : baseSalary.hashCode());
		result = prime * result + ((deductInstallment == null) ? 0 : deductInstallment.hashCode());
		result = prime * result + ((irrf == null) ? 0 : irrf.hashCode());
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
		IRRF other = (IRRF) obj;
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
		if (deductInstallment == null) {
			if (other.deductInstallment != null)
				return false;
		} else if (!deductInstallment.equals(other.deductInstallment))
			return false;
		if (irrf == null) {
			if (other.irrf != null)
				return false;
		} else if (!irrf.equals(other.irrf))
			return false;
		return true;
	}
}