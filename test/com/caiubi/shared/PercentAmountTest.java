package com.caiubi.shared;

import static com.caiubi.shared.PercentAmount.ZERO;
import junit.framework.Assert;

import org.junit.Test;

public class PercentAmountTest {

	@Test
	public final void testToString() {
		Assert.assertEquals("0,0%", ZERO.toString());
	}

	@Test
	public final void testPercentAmount() {
		Assert.assertEquals(0f, ZERO.floatValue(), 0);
	}

	@Test
	public final void testOf() {
		Assert.assertEquals(new MonetaryAmount(1), new PercentAmount(.5f).of(new MonetaryAmount(2)));
	}
}