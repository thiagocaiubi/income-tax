package com.caiubi.incometax.vo.aliquot;

import java.util.LinkedHashMap;
import java.util.Map;

import com.caiubi.shared.MonetaryAmount;
import com.caiubi.shared.MonetaryAmountRange;
import com.caiubi.shared.PercentAmount;

public class Aliquot2010 implements Aliquot {

	private static final Map<MonetaryAmountRange, PercentAmount> INSS_ALIQUOT = new LinkedHashMap<MonetaryAmountRange, PercentAmount>();
	private static final MonetaryAmount CEIL_INSS = new MonetaryAmount(381.41);
	
	private static final Map<PercentAmount, MonetaryAmount[]> IRRF_ALIQUOT = new LinkedHashMap<PercentAmount, MonetaryAmount[]>(); 
	
	private static final MonetaryAmount DEPENDENT = new MonetaryAmount(150.69);
	
	private static final PercentAmount FGTS_ALIQUOT = new PercentAmount(.08f);
	
	static {
		INSS_ALIQUOT.put(new MonetaryAmountRange(MonetaryAmount.ZERO, new MonetaryAmount(1040.22)), new PercentAmount(.08f));
		INSS_ALIQUOT.put(new MonetaryAmountRange(new MonetaryAmount(1040.23), new MonetaryAmount(1733.70)), new PercentAmount(.09f));
		INSS_ALIQUOT.put(new MonetaryAmountRange(new MonetaryAmount(1733.71), new MonetaryAmount(3467.40)), new PercentAmount(.11f));
		
		IRRF_ALIQUOT.put(new PercentAmount(0f), new MonetaryAmount[]{MonetaryAmount.ZERO, MonetaryAmount.ZERO});
		IRRF_ALIQUOT.put(new PercentAmount(0.075f), new MonetaryAmount[]{new MonetaryAmount(1499.15), new MonetaryAmount(112.43)});
		IRRF_ALIQUOT.put(new PercentAmount(0.15f), new MonetaryAmount[]{new MonetaryAmount(2246.75), new MonetaryAmount(280.94)});
		IRRF_ALIQUOT.put(new PercentAmount(0.225f), new MonetaryAmount[]{new MonetaryAmount(2995.70), new MonetaryAmount(505.62)});
		IRRF_ALIQUOT.put(new PercentAmount(0.275f), new MonetaryAmount[]{new MonetaryAmount(3743.19), new MonetaryAmount(692.78)});
	}

	public Map<MonetaryAmountRange, PercentAmount> getInssAliquot() {
		return INSS_ALIQUOT;
	}

	public MonetaryAmount getCeilInss() {
		return CEIL_INSS;
	}

	public Map<PercentAmount, MonetaryAmount[]> getIrrfAliquot() {
		return IRRF_ALIQUOT;
	}

	public MonetaryAmount getDependent() {
		return DEPENDENT;
	}

	public PercentAmount getFgtsAliquot() {
		return FGTS_ALIQUOT;
	}
}