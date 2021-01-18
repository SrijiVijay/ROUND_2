package com.kgisl.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import com.kgisl.common.utils.CommonConstants;

/**
 * The Class AgeCalculatorService.
 */
@Component
public class AgeCalculatorService {
	
	/**
	 * Calculate premium by age.
	 *
	 * @param enterAge the enter age
	 * @return the double
	 */
	public double calculatePremiumByAge(int enterAge) {
		double amount = 0;
		double resultPercentage = 0;
		if (enterAge >= 18 && enterAge <= 25) {
			amount = CommonConstants.MIN_PREMIUM_AMOUNT * 10 / 100;
			System.out.println("18-25 Premium : "+ amount + CommonConstants.MIN_PREMIUM_AMOUNT);
			return amount + CommonConstants.MIN_PREMIUM_AMOUNT;
		} else if (enterAge > 25 && enterAge <= 40) {
			resultPercentage = new BigDecimal(((double) enterAge-25.0)/5.0).setScale(0, BigDecimal.ROUND_UP).intValue();
			amount = (CommonConstants.MIN_PREMIUM_AMOUNT) * ((double) resultPercentage * 10 / 100);
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 10 / 100);
			System.out.println("26-40 Premium : "+ amount + CommonConstants.MIN_PREMIUM_AMOUNT);
			return amount + CommonConstants.MIN_PREMIUM_AMOUNT;
		} else if (enterAge > 40) {
			resultPercentage = new BigDecimal(((double) enterAge-40.0)/5.0).setScale(0, BigDecimal.ROUND_UP).intValue();
			amount = (CommonConstants.MIN_PREMIUM_AMOUNT) * ((double) resultPercentage * 20.0 / 100);
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 30.0 / 100);
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 10.0 / 100);
			System.out.println("above 40 Premium : "+ amount);
			return amount + CommonConstants.MIN_PREMIUM_AMOUNT;
		} else if (enterAge < 18) {
			System.out.println("Less than 18 : ");
			return CommonConstants.MIN_PREMIUM_AMOUNT;
		}
		return amount;
	}
}