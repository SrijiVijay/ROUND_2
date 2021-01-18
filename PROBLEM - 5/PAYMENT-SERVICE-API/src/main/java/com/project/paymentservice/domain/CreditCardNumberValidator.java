package com.project.paymentservice.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The Class CreditCardNumberValidator.
 */
public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumberContraint, String> {

	/**
	 * Checks if is valid.
	 *
	 * @param creditCardNumber the credit card number
	 * @param context the context
	 * @return true, if is valid
	 */
	@Override
	public boolean isValid(String creditCardNumber, ConstraintValidatorContext context) {
		return new br.com.moip.validators.CreditCard(creditCardNumber).isValid();
	}

}
