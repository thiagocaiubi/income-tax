package com.caiubi.shared;

import java.util.Locale;


public class MonetaryAmountRange {

	private MonetaryAmount begin;
	private MonetaryAmount end;
	
	private Locale locale;
	
	public MonetaryAmountRange(MonetaryAmount begin, MonetaryAmount end) {
		if (begin == null || end == null) {
			throw new NullPointerException("First and end can't be null!");
		}
		if (begin.isGreaterThan(end)) {
			throw new IllegalArgumentException("Range must be created with begin lesser than end!");
		}
		if (!begin.getLocale().equals(end.getLocale())) {
			throw new IllegalArgumentException("Range must be created using same currency!");
		}
		this.begin = begin;
		this.end = end;
		this.locale = begin.getLocale();
	}

	public MonetaryAmount getBegin() {
		return begin;
	}

	public MonetaryAmount getEnd() {
		return end;
	}
	
	public Locale getLocale() {
		return locale;
	}
}
