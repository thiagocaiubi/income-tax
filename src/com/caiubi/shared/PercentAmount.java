package com.caiubi.shared;

import java.text.NumberFormat;

public class PercentAmount extends Amount<Float> {

	private static final long serialVersionUID = 8307061908823734960L;
	
	public static final PercentAmount ZERO = new PercentAmount(0f);

	public PercentAmount(float value) {
		setValue(value);
	}
	
	public MonetaryAmount of(MonetaryAmount monetaryAmount) {
		return new MonetaryAmount(this.floatValue() * monetaryAmount.floatValue());
	}
	
	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getPercentInstance(getLocale());
		format.setMinimumIntegerDigits(1);
		format.setMinimumFractionDigits(1);
		format.setMaximumFractionDigits(2);
		return format.format(getValue());
	}
}