package com.caiubi.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class MonetaryAmount extends Amount<BigDecimal> {

	private static final long serialVersionUID = 5119479219293005913L;
	
	public static final MonetaryAmount ZERO = new MonetaryAmount(BigDecimal.ZERO);
	
	public MonetaryAmount(int value) {
		this(new BigDecimal(value));
	}
	
	public MonetaryAmount(double value) {
		this(new BigDecimal(value));
	}
	
	public MonetaryAmount(BigDecimal value) {
		setValue(value);
	}
	
	@Override
	public BigDecimal getValue() {
		BigDecimal value = super.getValue();
		return value.setScale(2, RoundingMode.CEILING);
	}
	
	public MonetaryAmount add(MonetaryAmount value) {
		final BigDecimal add = this.getValue().add(value.getValue());
		return new MonetaryAmount(add);
	}
	
	public MonetaryAmount subtract(MonetaryAmount value) {
		final BigDecimal subtract = this.getValue().subtract(value.getValue());
		return new MonetaryAmount(subtract);
	}
	
	public MonetaryAmount multiply(MonetaryAmount value) {
		final BigDecimal multiply = this.getValue().multiply(value.getValue());
		return new MonetaryAmount(multiply);
	}
	
	public MonetaryAmount min(MonetaryAmount value) {
		final int compared = this.getValue().compareTo(value.getValue());
		return compared <= 0 ? this : value;
	}
	
	public MonetaryAmount max(MonetaryAmount value) {
		final int compared = this.getValue().compareTo(value.getValue());
		return compared >= 0 ? this : value;
	}
	
	public PercentAmount of(MonetaryAmount value) {
		if (this.floatValue() == 0 || value.floatValue() == 0)
			return PercentAmount.ZERO;
		return new PercentAmount(this.floatValue() / value.floatValue());
	}
	
	public boolean isGreaterThan(MonetaryAmount other) {
		validateForComparison(other);
		return getValue().compareTo(other.getValue()) > 0;
	}
	
	public boolean isGreaterOrEqualThan(MonetaryAmount other) {
		validateForComparison(other);
		return getValue().compareTo(other.getValue()) >= 0;
	}

	public boolean isLessThan(MonetaryAmount other) {
		validateForComparison(other);
		return getValue().compareTo(other.getValue()) < 0;
	}

	public boolean isLessOrEqualThan(MonetaryAmount other) {
		validateForComparison(other);
		return getValue().compareTo(other.getValue()) <= 0;
	}
	
	public boolean isBetween(MonetaryAmountRange range) {
		return isGreaterOrEqualThan(range.getBegin()) && isLessOrEqualThan(range.getEnd());
	}

	public boolean isGreaterThan(MonetaryAmountRange range) {
		return isGreaterThan(range.getEnd());
	}
	
	private void validateForComparison(MonetaryAmount other) throws NullPointerException, IllegalArgumentException {
		if (other == null) { 
			throw new NullPointerException("Monetary amount can't be null!");
		}
		if (!getLocale().equals(other.getLocale())) {
			throw new IllegalArgumentException("Can't compare different locales!");
		}
	}
	
	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getCurrencyInstance(getLocale());
		return format.format(getValue());
	}
 }