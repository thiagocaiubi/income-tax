package com.caiubi.incometax.vo; 

import java.io.Serializable;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.incometax.vo.aliquot.Aliquot2010;
import com.caiubi.incometax.vo.tax.FGTS;
import com.caiubi.incometax.vo.tax.INSS;
import com.caiubi.incometax.vo.tax.IRRF;
import com.caiubi.shared.MonetaryAmount;

public class Income implements Serializable {

	private static final long serialVersionUID = 3004050262967058997L;
	
	public static final MonetaryAmount MIN_INCOME = new MonetaryAmount(510);
	
	private Aliquot aliquot = new Aliquot2010();
	private MonetaryAmount grossIncome = MonetaryAmount.ZERO;
	private int dependents = 0;
	
	private INSS inss;
	private IRRF irrf;
	private FGTS fgts;
	private MonetaryAmount netIncome;
	
	private boolean calculated = false;
	
	public Income(MonetaryAmount grossIncome, int dependents, Aliquot aliquot) {
		if (grossIncome == null)
			throw new IllegalArgumentException("Gross income can't be null!");
		if (aliquot == null)
			throw new NullPointerException("Aliquot can't be null!");
		this.grossIncome = grossIncome;
		this.dependents = dependents;
	}

	public void calculate() {
		inss = new INSS(grossIncome, aliquot);
		irrf = new IRRF(grossIncome, dependents, inss, aliquot);
		fgts = new FGTS(grossIncome, aliquot);
		netIncome = grossIncome.subtract(inss.getValue()).subtract(irrf.getValue());
		calculated = true;
	}
	
	public MonetaryAmount getGrossIncome() {
		return grossIncome;
	}
	
	public void setGrossIncome(MonetaryAmount grossIncome) {
		if (grossIncome == null)
			throw new NullPointerException("Gross income can not be null!");
		this.grossIncome = grossIncome;
	}
	
	public int getDependents() {
		return dependents;
	}
	
	public void setDependents(int dependents) {
		this.dependents = dependents;
	}

	public MonetaryAmount getNetIncome() {
		validateIncomeState();
		return netIncome;
	}
	
	public INSS getInss() {
		validateIncomeState();
		return inss;
	}
	
	public IRRF getIrrf() {
		validateIncomeState();
		return irrf;
	}
	
	public FGTS getFgts() {
		validateIncomeState();
		return fgts;
	}
	
	public boolean isCalculated() {
		return calculated;
	}
	
	private void validateIncomeState() {
		if (!calculated)
			throw new IllegalStateException("Income must be calculated first!");
	}
}