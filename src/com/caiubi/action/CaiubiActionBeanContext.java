package com.caiubi.action;

import java.io.Serializable;

import net.sourceforge.stripes.action.ActionBeanContext;

public class CaiubiActionBeanContext extends ActionBeanContext implements Serializable {

	private static final long serialVersionUID = -1482656088898883483L;
	
	private static final String CAIUBI_CONTEXT = "caiubi.context";

	public CaiubiContext getCaiubiContext() {
		CaiubiContext caiubiContext = (CaiubiContext) getRequest().getSession().getAttribute(CAIUBI_CONTEXT);
		if (caiubiContext == null) {
			caiubiContext = new CaiubiContext();
			setCaiubiContext(caiubiContext);
		}
		return caiubiContext;
	}

	public void setCaiubiContext(CaiubiContext context) {
		getRequest().getSession().setAttribute(CAIUBI_CONTEXT, context);
	}
	
	public void logout() {
		getRequest().getSession().invalidate();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFromSession(String key) {
		return (T) getRequest().getSession().getAttribute(key);
	}
	
	public void setToSession(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getFromRequest(String key) {
		return (T) getRequest().getAttribute(key);
	}
	
	public void setToRequest(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	public class CaiubiContext implements Serializable {

		private static final long serialVersionUID = 3419095427561046919L;
		
		private boolean mobile;
		
		public void setMobile(boolean mobile) {
			this.mobile = mobile;
		}

		public boolean isMobile() {
			return mobile;
		}
	}
}