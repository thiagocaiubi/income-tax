package com.caiubi.incometax.vo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.logging.Logger;

import com.caiubi.bundle.CaiubiResourceBundle;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public class IncomeChart implements Serializable {

	private static final long serialVersionUID = -4366464890933678227L;

	private static final Logger log = Logger.getLogger(IncomeChart.class.getName());
	
	private static final String GOOGLE_CHART_API = "http://chart.apis.google.com/chart?cht=p3&chs=400x100&chl={0}|{1}|{2}&chd=t:{3},{4},{5}&chco=6699CC";
	
	private Income income;
	private Locale locale;
	
	public IncomeChart(Income income, Locale locale) {
		if (income == null)
			throw new IllegalArgumentException("Income can't be null!");
		this.income = income;
		if (locale == null)
			throw new IllegalArgumentException("Locale can't be null!");
		this.locale = locale;
	}
	
	@Override
	public String toString() {
		MonetaryAmount grossIncome = income.getGrossIncome();
		
		PercentAmount inss = income.getInss().getValue().of(grossIncome);
		PercentAmount irpf = income.getIrrf().getValue().of(grossIncome);
		PercentAmount netIncome = income.getNetIncome().of(grossIncome);
		
		String inssLabel = encode(CaiubiResourceBundle.getMessage("income.inss", locale).concat(" ").concat(inss.toString()));
		String irpfLabel = encode(CaiubiResourceBundle.getMessage("income.irrf", locale).concat(" ").concat(irpf.toString()));
		String netIncomeLabel = encode(CaiubiResourceBundle.getMessage("income.netIncome", locale).concat(" ").concat(netIncome.toString()));
		
		MessageFormat format = new MessageFormat(GOOGLE_CHART_API);
		String url = format.format(new String[]{
			inssLabel, 
			irpfLabel, 
			netIncomeLabel,
			inss.getValue().toString(),
			irpf.getValue().toString(),
			netIncome.getValue().toString()
		});
		log.info("Chart => " + url);
		return url;
	}
	
	private String encode(String s) {
		try {
			return URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
}