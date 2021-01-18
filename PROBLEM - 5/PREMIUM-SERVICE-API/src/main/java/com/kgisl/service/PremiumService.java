package com.kgisl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.common.utils.CommonConstants;
import com.kgisl.model.FormModel;

/**
 * The Class PremiumService.
 */
@Service
public class PremiumService {

	/** The calculate age. */
	@Autowired
	private AgeCalculatorService calculateAge;

	/**
	 * Gets the premium amount.
	 *
	 * @param form the form
	 * @return the premium amount
	 */
	public double getPremiumAmount(FormModel form) {
		double amountToBePaid = 0;
		// Age based Calculation
		amountToBePaid = calculateAmtByAgeLimit(form.getAge());
		System.out.println("Age based Calculation : "+ amountToBePaid);
		
		// Gender based Calculation
		amountToBePaid = calculateAmtByGender(form.getGender(), amountToBePaid);
		
		// Health and Habits based Calculation
		amountToBePaid = calculateAmtByHealthStatus(form, amountToBePaid);
		return amountToBePaid;
	}

	/**
	 * Calculate amt by age limit.
	 *
	 * @param age the age
	 * @return the double
	 */
	private double calculateAmtByAgeLimit(int age) {
		double amount = 0;
		amount = calculateAge.calculatePremiumByAge(age);
		return amount;
	}

	/**
	 * Calculate amt by gender.
	 *
	 * @param gender the gender
	 * @param amount the amount
	 * @return the double
	 */
	private double calculateAmtByGender(String gender, double amount) {
		if (gender.equalsIgnoreCase(CommonConstants.MALE)) {
			return amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.02);
		}
		return amount;
	}

	/**
	 * Calculate amt by health status.
	 *
	 * @param form the form
	 * @param amount the amount
	 * @return the double
	 */
	private double calculateAmtByHealthStatus(FormModel form, double amount) {
		if (form.getBloodPressure().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.01);
		}
		if (form.getBloodSugar().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.01);
		}
		if (form.getHyperTension().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.01);
		}
		if (form.getOverWeight().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.01);
		}
		if (form.getAlcohol().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.03);
		}
		if (form.getDrugs().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.03);
		}
		if (form.getSmoking().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount + (CommonConstants.MIN_PREMIUM_AMOUNT * 0.03);
		}
		if (form.getDailyExercise().equalsIgnoreCase(CommonConstants.YES)) {
			amount = amount - (CommonConstants.MIN_PREMIUM_AMOUNT * 0.03);
		}
		return amount;
	}
}