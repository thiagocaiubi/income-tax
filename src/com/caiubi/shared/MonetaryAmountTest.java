package com.caiubi.shared;

import static com.caiubi.shared.MonetaryAmount.ZERO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

public class MonetaryAmountTest {
	
	private static final MonetaryAmount ONE = new MonetaryAmount(1);
	private static final MonetaryAmount TWO = new MonetaryAmount(2);
	private static final MonetaryAmount THREE = new MonetaryAmount(3);
	
	private static final MonetaryAmountRange RANGE = new MonetaryAmountRange(ZERO, TWO);
	
	@Test
	public final void testToString() {
		Assert.assertEquals("R$ 0,00", ZERO.toString());
	}

	@Test
	public final void testMonetaryAmountInt() {
		Assert.assertEquals(1f, new MonetaryAmount(1).floatValue(), 0);
	}

	@Test
	public final void testMonetaryAmountDouble() {
		Assert.assertEquals(1f, new MonetaryAmount(1d).floatValue(), 0);
	}

	@Test
	public final void testMonetaryAmountBigDecimal() {
		Assert.assertEquals(1f, new MonetaryAmount(new BigDecimal(1)).floatValue(), 0);
	}

	@Test
	public final void testGetValue() {
		BigDecimal expected = new BigDecimal(1);
		expected = expected.setScale(2, RoundingMode.CEILING);
		Assert.assertEquals(expected, ONE.getValue());
	}

	@Test
	public final void testAdd() {
		Assert.assertEquals(3f, ONE.add(TWO).floatValue(), 0);
	}

	@Test
	public final void testSubtract() {
		Assert.assertEquals(1f, TWO.subtract(ONE).floatValue(), 0);
	}

	@Test
	public final void testMultiply() {
		Assert.assertEquals(2f, ONE.multiply(TWO).floatValue(), 0);
	}

	@Test
	public final void testMin() {
		Assert.assertEquals(ZERO, ZERO.min(ONE));
	}
	
	@Test
	public final void testMax() {
		Assert.assertEquals(ONE, ZERO.max(ONE));
	}

	@Test
	public final void testOf() {
		Assert.assertEquals(.5f, ONE.of(TWO).floatValue(), 0);
	}

	@Test
	public final void testIsGreaterThanMonetaryAmount() {
		Assert.assertTrue(TWO.isGreaterThan(ONE));
	}

	@Test
	public final void testIsGreaterOrEqualThan() {
		Assert.assertTrue(ONE.isGreaterOrEqualThan(ONE));
	}

	@Test
	public final void testIsLessThan() {
		Assert.assertTrue(ONE.isLessThan(TWO));
	}

	@Test
	public final void testIsLessOrEqualThan() {
		Assert.assertTrue(ONE.isLessOrEqualThan(ONE));
	}

	@Test
	public final void testIsBetween() {
		Assert.assertTrue(ONE.isBetween(RANGE));
	}

	@Test
	public final void testIsGreaterThanMonetaryAmountRange() {
		Assert.assertTrue(THREE.isGreaterThan(RANGE));
	}
}