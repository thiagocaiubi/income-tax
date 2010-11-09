package com.caiubi.action;

import java.io.Serializable;
import java.util.logging.Logger;

import com.caiubi.incometax.view.Page;

import net.sourceforge.stripes.action.ActionBeanContext;

public class CaiubiActionBeanContext extends ActionBeanContext implements Serializable {

	private static final long serialVersionUID = -1482656088898883483L;
	
	private static final Logger log = Logger.getLogger(CaiubiActionBeanContext.class.getName());
	
	private static final String CAIUBI_CONTEXT = "caiubi.context";

	public CaiubiContext getCaiubiContext() {
		CaiubiContext caiubiContext = getFromSession(CAIUBI_CONTEXT);
		if (caiubiContext == null) {
			caiubiContext = new CaiubiContext();
			setCaiubiContext(caiubiContext);
			log.info("Context created. Session id: " + getRequest().getSession().getId());
		}
		return caiubiContext;
	}

	@SuppressWarnings("unchecked")
	private <T> T getFromSession(String key) {
		return (T) getRequest().getSession().getAttribute(key);
	}

	private void setCaiubiContext(CaiubiContext context) {
		getRequest().getSession().setAttribute(CAIUBI_CONTEXT, context);
	}
	
	public class CaiubiContext implements Serializable {

		private static final long serialVersionUID = 3419095427561046919L;
		
		private String view = Page.INCOME.getDesktop();

		public String getView() {
			return view;
		}

		public void setView(String view) {
			this.view = view;
		}
	}
}