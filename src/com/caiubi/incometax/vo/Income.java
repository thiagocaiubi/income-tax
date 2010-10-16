package com.caiubi.incometax.vo; 

import java.io.Serializable;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.incometax.vo.tax.FGTS;
import com.caiubi.incometax.vo.tax.INSS;
import com.caiubi.incometax.vo.tax.IRRF;
import com.caiubi.shared.MonetaryAmount;

public class Income implements Serializable {

	private static final long serialVersionUID = 3004050262967058997L;
	
	public static final MonetaryAmount MIN_INCOME = new MonetaryAmount(510);
	
	private Aliquot aliquot;
	private MonetaryAmount grossIncome;
	private int dependents;
	
	private INSS inss;
	private IRRF irrf;
	private FGTS fgts;
	private MonetaryAmount netIncome;
	
	public Income(MonetaryAmount grossIncome, int dependents, Aliquot aliquot) {
		if (grossIncome == null)
			throw new IllegalArgumentException("Gross income can't be null!");
		this.grossIncome = grossIncome;
		if (aliquot == null)
			throw new NullPointerException("Aliquot can't be null!");
		this.aliquot = aliquot;
		this.dependents = dependents;
		calculate();
	}

	private void calculate() {
		inss = new INSS(grossIncome, aliquot);
		irrf = new IRRF(grossIncome, dependents, inss, aliquot);
		fgts = new FGTS(grossIncome, aliquot);
		netIncome = grossIncome.subtract(inss.getValue()).subtract(irrf.getValue());
	}
	
	public MonetaryAmount getGrossIncome() {
		return grossIncome;
	}
	
	public int getDependents() {
		return dependents;
	}
	
	public MonetaryAmount getNetIncome() {
		return netIncome;
	}
	
	public INSS getInss() {
		return inss;
	}
	
	public IRRF getIrrf() {
		return irrf;
	}
	
	public FGTS getFgts() {
		return fgts;
	}
	
	public Aliquot getAliquot() {
		return aliquot;
	}
	
	public boolean isCalculated() {
		return MonetaryAmount.ZERO.isLessOrEqualThan(netIncome);
	}
}