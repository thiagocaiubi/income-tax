package com.caiubi.incometax.vo;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.caiubi.incometax.vo.aliquot.Aliquot;
import com.caiubi.incometax.vo.aliquot.Aliquot2010;
import com.caiubi.incometax.vo.tax.FGTS;
import com.caiubi.incometax.vo.tax.INSS;
import com.caiubi.incometax.vo.tax.IRRF;
import com.caiubi.shared.MonetaryAmount;

public class IncomeTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNullGrossIncome() {
		new Income(null, 0, new Aliquot2010());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAliquot() {
		new Income(new MonetaryAmount(new BigDecimal(510)), 0, null);
	}
	
	@Test
	public void testGetters() {
		final MonetaryAmount grossIncome = new MonetaryAmount(new BigDecimal(510));
		final int dependents = 0;
		final Aliquot aliquot = new Aliquot2010();
		final Income income = new Income(grossIncome, dependents, aliquot);
		Assert.assertEquals(dependents, income.getDependents());
		Assert.assertEquals(grossIncome, income.getGrossIncome());
		Assert.assertEquals(aliquot, income.getAliquot());
	}
	
	@Test
	public void testCalculate() {
		final MonetaryAmount grossIncome = new MonetaryAmount(new BigDecimal(510));
		final int dependents = 0;
		final Aliquot aliquot = new Aliquot2010();
		final Income income = new Income(grossIncome, dependents, aliquot);
		Assert.assertEquals(new FGTS(grossIncome, aliquot), income.getFgts());
		Assert.assertEquals(new INSS(grossIncome, aliquot), income.getInss());
		Assert.assertEquals(new IRRF(grossIncome, dependents, income.getInss(), aliquot), income.getIrrf());
		Assert.assertNotNull(income.getNetIncome());
	}
}
