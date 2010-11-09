package com.caiubi.incometax.action;

import java.util.logging.Logger;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.caiubi.action.CaiubiActionBean;
import com.caiubi.incometax.view.Page;

@UrlBinding("/caiubi/view/{$event}")
public class ViewAction extends CaiubiActionBean {

	private static final Logger log = Logger.getLogger(ViewAction.class.getName());
	
	@Override
	public Resolution view() {
		return new RedirectResolution(IncomeAction.class);
	}
	
	public Resolution mobile() {
		getCaiubiContext().setView(Page.INCOME.getMobile());
		log.info("Set mobile view. Session id:" + getRequest().getSession().getId());
		return view();
	}
	
	public Resolution desktop() {
		getCaiubiContext().setView(Page.INCOME.getDesktop());
		log.info("Set desktop view. Session id:" + getRequest().getSession().getId());
		return view();
	}
}