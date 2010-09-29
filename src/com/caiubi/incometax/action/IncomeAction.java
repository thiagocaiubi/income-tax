package com.caiubi.incometax.action;

import static com.caiubi.incometax.vo.Income.MIN_INCOME;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import com.caiubi.action.CaiubiActionBean;
import com.caiubi.incometax.vo.Income;
import com.caiubi.incometax.vo.IncomeChart;
import com.caiubi.incometax.vo.aliquot.Aliquot2010;

@UrlBinding("/caiubi/income")
public class IncomeAction extends CaiubiActionBean {
	
	private static final String INCOME = "/income.jsp";

	@ValidateNestedProperties({
		@Validate(field="grossIncome", required=true),
		@Validate(field="dependents", required=true)
	})
	private Income income = new Income(MIN_INCOME, 0, new Aliquot2010());
	private String chart;
	
	private final Integer[] dependents = new Integer[]{0,1,2,3,4,5,6,7,8,9,10};
	
	@Override
	@DontValidate
	public Resolution view() {
		return new ForwardResolution(INCOME);
	}
	
	public Resolution calculate() {
		income.calculate();
		chart = new IncomeChart(income, getContext().getLocale()).toString();
		addMessage(new LocalizableMessage("income.calculated"));
		return view();
	}
	
	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Integer[] getDependents() {
		return dependents;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}
}