package com.caiubi.incometax.vo.tax;

import java.util.Map.Entry;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.incometax.vo.aliquot.Aliquot2010;
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
	
	public static void main(String[] args) {
		final INSS inss = new INSS(new MonetaryAmount(1500), new Aliquot2010());
		System.out.println(inss.getBaseAliquot() + " - " + inss.getBaseSalary());
	}
}