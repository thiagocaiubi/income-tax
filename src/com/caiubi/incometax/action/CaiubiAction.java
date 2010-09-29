package com.caiubi.incometax.action;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import com.caiubi.action.CaiubiActionBean;

public class CaiubiAction extends CaiubiActionBean {
	
	@Override
	public Resolution view() {
		return new RedirectResolution(IncomeAction.class);
	}
}