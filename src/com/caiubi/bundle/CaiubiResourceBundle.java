package com.caiubi.bundle;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.localization.LocalizationBundleFactory;

public class CaiubiResourceBundle implements Serializable {

	private static final long serialVersionUID = 376226143017729473L;
	
	private static final LocalizationBundleFactory factory;
	
	static {
		Configuration configuration = StripesFilter.getConfiguration();
		factory = configuration.getLocalizationBundleFactory();
	}
	
	public static String getMessage(String key, Locale locale) {
		final ResourceBundle bundle = factory.getFormFieldBundle(locale);
		return bundle.getString(key);
	}
}