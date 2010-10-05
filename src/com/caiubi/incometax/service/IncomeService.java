package com.caiubi.incometax.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.caiubi.incometax.vo.Income;
import com.caiubi.incometax.vo.tax.INSS;
import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.PercentAmount;

public class IncomeService implements Serializable {

	private static final long serialVersionUID = -5920314275192240386L;

	private static final Logger log = Logger.getLogger(IncomeService.class.getName());
	
	private static final Map<MonetaryAmount, PercentAmount> INSS_ALIQUOT = new LinkedHashMap<MonetaryAmount, PercentAmount>();
	private static final MonetaryAmount CEIL_INSS = new MonetaryAmount(375.82);
	
	private static final Map<PercentAmount, MonetaryAmount[]> IRRF_ALIQUOT = new LinkedHashMap<PercentAmount, MonetaryAmount[]>(); 
	
	private static final MonetaryAmount DEPENDENT = new MonetaryAmount(150.69);
	
	private static final PercentAmount FGTS_ALIQUOT = new PercentAmount(.08f);
	
	{
		INSS_ALIQUOT.put(new MonetaryAmount(1024.97), new PercentAmount(.08f));
		INSS_ALIQUOT.put(new MonetaryAmount(1708.27), new PercentAmount(.09f));
		INSS_ALIQUOT.put(new MonetaryAmount(3416.54), new PercentAmount(.11f));
		
		IRRF_ALIQUOT.put(new PercentAmount(0f), new MonetaryAmount[]{MonetaryAmount.ZERO, MonetaryAmount.ZERO});
		IRRF_ALIQUOT.put(new PercentAmount(0.075f), new MonetaryAmount[]{new MonetaryAmount(1499.15), new MonetaryAmount(112.43)});
		IRRF_ALIQUOT.put(new PercentAmount(0.15f), new MonetaryAmount[]{new MonetaryAmount(2995.70), new MonetaryAmount(280.94)});
		IRRF_ALIQUOT.put(new PercentAmount(0.225f), new MonetaryAmount[]{new MonetaryAmount(2995.70), new MonetaryAmount(505.62)});
		IRRF_ALIQUOT.put(new PercentAmount(0.275f), new MonetaryAmount[]{new MonetaryAmount(3743.19), new MonetaryAmount(692.78)});
	}
	
	public Income calculate(Income income) {
		log.info(" === INCOME BEGIN === ");
		calculateINSS(income);
		calculateIRPF(income);
		calculateFGTS(income);
		log.info("Net income => " + income.getNetIncome());
		log.info(" === INCOME END ===");
		return income;
	}
	
	private void calculateINSS(Income income) {
		MonetaryAmount baseSalary = MonetaryAmount.ZERO;
		PercentAmount baseAliqout = PercentAmount.ZERO;
		for (Entry<MonetaryAmount, PercentAmount> aliquot : INSS_ALIQUOT.entrySet()) {
			baseSalary = aliquot.getKey().min(income.getGrossIncome());
			baseAliqout = aliquot.getValue();
			if (income.getGrossIncome().equals(baseSalary)) {
				break;
			}
		}
		log.info("INSS => Base salary => " + baseSalary);
		log.info("INSS => Base aliquot => " + baseAliqout);
		final MonetaryAmount salary = baseAliqout.of(income.getGrossIncome());
		final MonetaryAmount inss = CEIL_INSS.min(salary);
		log.info("INSS => " + inss);
	}
	
	private void calculateIRPF(Income income) {
		MonetaryAmount baseSalary = MonetaryAmount.ZERO;
		MonetaryAmount deductInstallment = MonetaryAmount.ZERO;
		PercentAmount baseAliquot = PercentAmount.ZERO;
		for (Entry<PercentAmount, MonetaryAmount[]> aliquot : IRRF_ALIQUOT.entrySet()) {
			baseSalary = aliquot.getValue()[0].min(income.getGrossIncome());
			if (income.getGrossIncome() == baseSalary) {
				break;
			}
			deductInstallment = aliquot.getValue()[1];
			baseAliquot = aliquot.getKey();
		}
		final INSS inss = income.getInss();
		log.info("IRRF => Base salary => "+ baseSalary);
		log.info("IRRF => Base aliquot => "+ baseAliquot);
		log.info("IRRF => Deduct installment => "+ deductInstallment);
		final MonetaryAmount subtract = income.getGrossIncome().subtract(inss.getValue());
		final MonetaryAmount dependents = new MonetaryAmount(income.getDependents()).multiply(DEPENDENT);
		final MonetaryAmount subtract2 = subtract.subtract(dependents);
		final MonetaryAmount multiply = baseAliquot.of(subtract2);
		final MonetaryAmount irpf = multiply.subtract(deductInstallment).max(MonetaryAmount.ZERO);
		log.info("IRRF => "+ irpf);
	}
	
	private void calculateFGTS(Income income) {
		log.info("FGTS => Base aliquot => "+ FGTS_ALIQUOT);
		final MonetaryAmount fgts = FGTS_ALIQUOT.of(income.getGrossIncome());
		log.info("FGTS => "+ fgts);
	}
}