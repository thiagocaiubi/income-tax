package com.caiubi.incometax.action;

import java.lang.reflect.Field;

import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import org.junit.Assert;
import org.junit.Test;

import com.caiubi.incometax.vo.Income;

public class IncomeActionTest {
	
	private static final Class<IncomeAction> INCOME_CLASS = IncomeAction.class;

	@Test
	public void testUrlBinding() {
		final UrlBinding annotation = INCOME_CLASS.getAnnotation(UrlBinding.class);
		Assert.assertEquals("/caiubi/income", annotation.value());
	}
	
	@Test
	public void testGrossIncomeAnnotation() throws Exception {
		final Field grossIncome = INCOME_CLASS.getDeclaredField("grossIncome");
		final Validate validate = grossIncome.getAnnotation(Validate.class);
		Assert.assertTrue(validate.required());
	}
	
	@Test
	public void testGrossIncomeDefaultValue() throws Exception {
		Assert.assertEquals(Income.MIN_INCOME, new IncomeAction().getGrossIncome());
	}
	
	@Test
	public void testDependentsAnnotation() throws Exception {
		final Field dependents = INCOME_CLASS.getDeclaredField("dependents");
		final Validate validate = dependents.getAnnotation(Validate.class);
		Assert.assertTrue(validate.required());
	}
}