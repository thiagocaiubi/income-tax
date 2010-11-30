package com.caiubi.incometax.view;

public enum Page {

	INCOME("/view/income/desktop.jsp", "/view/income/mobile.jsp");
	
	private String desktop;
	private String mobile;

	private Page(String desktop, String mobile) {
		this.desktop = desktop;
		this.mobile = mobile;
	}

	public String desktop() {
		return desktop;
	}

	public String mobile() {
		return mobile;
	}
}