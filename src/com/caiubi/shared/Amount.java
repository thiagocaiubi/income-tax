package com.caiubi.shared;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Amount<T extends Number> implements Serializable {

	private static final long serialVersionUID = 4315217240786754639L;
	
	private T value;
	//TODO for monetary amount, work with currency!!!
	private Locale locale = new Locale("pt", "BR");
	
	public T getValue() {
		return value;
	}

	public final void setValue(T value) {
		if (value == null || !validate(value))
			throw new IllegalArgumentException("Illegal amount value!");
		this.value = value;
	}

	protected boolean validate(T value) {
		return true;
	}
	
	public final Locale getLocale() {
		return locale;
	}
	
	public final void setLocale(Locale locale) {
		if (locale == null)
			throw new IllegalArgumentException("Locale can't be null!");
		this.locale = locale;
	}
	
	public final float floatValue() {
		return getValue().floatValue();
	}

	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getInstance(getLocale());
		return format.format(getValue());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + locale.hashCode();
		result = prime * result + value.hashCode();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Amount<T> other = (Amount<T>) obj;
		if (!value.equals(other.value) || !locale.equals(other.locale))
			return false;
		return true;
	}
}