package com.caiubi.incometax.validation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Collection;
import java.util.Locale;

import net.sourceforge.stripes.validation.ScopedLocalizableError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

import com.caiubi.shared.MonetaryAmount;

public class MonetaryAmountTypeConverter implements TypeConverter<MonetaryAmount> {

    private Locale locale;

	public MonetaryAmount convert(String input, Class<? extends MonetaryAmount> targetType, Collection<ValidationError> errors) {
    	NumberFormat format = DecimalFormat.getCurrencyInstance(locale);
    	((DecimalFormat) format).setParseBigDecimal(true);
    	ParsePosition pp = new ParsePosition(0);
		Number number = format.parse(input, pp);
    	final BigDecimal value = (BigDecimal) number;
    	if (number == null && input.length() != pp.getIndex()) {
    		 errors.add( new ScopedLocalizableError("converter.monetaryAmount", "invalidMonetaryAmount"));
    	}
		return new MonetaryAmount(value);
    }

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}