package com.caiubi.controller;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

import com.caiubi.action.CaiubiActionBeanContext;

@Intercepts(LifecycleStage.ResolutionExecution)
public class UserAgentInterceptor implements Interceptor {

	public Resolution intercept(ExecutionContext context) throws Exception {
		final CaiubiActionBeanContext caiubi = (CaiubiActionBeanContext) context.getActionBeanContext();
		final String header = caiubi.getRequest().getHeader("user-agent");
		if (header!= null && header.toLowerCase().contains("iphone")) {
			caiubi.getCaiubiContext().setMobile(true);
		}
		return context.proceed();
	}
}