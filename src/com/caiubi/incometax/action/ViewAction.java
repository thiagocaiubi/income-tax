package com.caiubi.incometax.action;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.caiubi.action.CaiubiActionBean;

@UrlBinding("/caiubi/view/{$event}")
public class ViewAction extends CaiubiActionBean {

	@Override
	public Resolution view() {
		return new RedirectResolution(IncomeAction.class);
	}
	
	public Resolution check() {
		final String userAgent = getRequest().getHeader("user-agent");
		if (userAgent.indexOf("iPhone") == -1) {
			return desktop();
		}
		return mobile();
	}
	
	public Resolution mobile() {
		setMobile("yes");
		return view();
	}
	
	public Resolution desktop() {
		setMobile("no");
		return view();
	}

	private void setMobile(String isMobile) {
		getRequest().getSession().setAttribute("isMobile", isMobile);
	}
}