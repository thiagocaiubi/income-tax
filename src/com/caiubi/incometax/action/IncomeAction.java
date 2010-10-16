package com.caiubi.incometax.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import com.caiubi.action.CaiubiActionBean;
import com.caiubi.incometax.vo.Income;
import com.caiubi.incometax.vo.IncomeChart;
import com.caiubi.incometax.vo.aliquot.Aliquot2010;
import com.caiubi.shared.MonetaryAmount;

@UrlBinding("/caiubi/income")
public class IncomeAction extends CaiubiActionBean {
	
	private static final String INCOME = "/income.jsp";

	@Validate(required=true)
	private MonetaryAmount grossIncome = Income.MIN_INCOME;
	
	@Validate(required=true)
	private int dependents;
	
	private Income income;
	private String chart;
	
	@Override
	public Resolution view() {
		return new ForwardResolution(INCOME);
	}
	
	public Resolution calculate() {
		Income income = new Income(grossIncome, dependents, new Aliquot2010());
		String chart = new IncomeChart(income, getContext().getLocale()).toString();
		setIncome(income);
		setChart(chart);
		addMessage(new LocalizableMessage("income.calculated"));
		return view();
	}
	
	public Income getIncome() {
		return income;
	}
	
	public void setIncome(Income income) {
		this.income = income;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	public MonetaryAmount getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(MonetaryAmount grossIncome) {
		this.grossIncome = grossIncome;
	}

	public int getDependents() {
		return dependents;
	}

	public void setDependents(int dependent) {
		this.dependents = dependent;
	}
}