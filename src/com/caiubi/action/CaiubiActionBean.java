package com.caiubi.action;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Message;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.validation.ValidationError;

import com.caiubi.action.CaiubiActionBeanContext.CaiubiContext;

public abstract class CaiubiActionBean implements ActionBean {

	private CaiubiActionBeanContext context;

	@DefaultHandler
	@DontValidate
	public abstract Resolution view();
	
	public CaiubiActionBeanContext getContext() {
		return context;
	}

	public void setContext(ActionBeanContext context) {
		this.context = (CaiubiActionBeanContext) context;
	}

	public CaiubiContext getCaiubiContext() {
		return context.getCaiubiContext();
	}
	
	public HttpServletRequest getRequest() {
		return context.getRequest();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameter(String key) {
		return (T) getRequest().getParameter(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFromRequest(String key) {
		return (T) getRequest().getAttribute(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFromSession(String key) {
		return (T) getRequest().getSession().getAttribute(key);
	}
	
	public void addValidationError(String field, ValidationError error) {
		context.getValidationErrors().add(field, error);
	}
	
	public void addMessage(Message message) {
		context.getMessages().add(message);
	}
	
	public String getMessage(String key) {
		Locale locale = getRequest().getLocale();
		ResourceBundle bundle = StripesFilter.getConfiguration().getLocalizationBundleFactory().getFormFieldBundle(locale);
		return bundle.getString(key); 
	}
}
