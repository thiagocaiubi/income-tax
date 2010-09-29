package com.caiubi.incometax.vo.tax;

import java.util.Map.Entry;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public class IRRF implements Tax {
	
	private MonetaryAmount irpf =  MonetaryAmount.ZERO;
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
		irpf = multiply.subtract(deductInstallment).max(MonetaryAmount.ZERO);
	}
	
	public PercentAmount getBaseAliquot() {
		return baseAliquot;
	}

	public MonetaryAmount getBaseSalary() {
		return baseSalary;
	}

	public MonetaryAmount getValue() {
		return irpf;
	}
	
	public MonetaryAmount getDeductInstallment() {
		return deductInstallment;
	}
}