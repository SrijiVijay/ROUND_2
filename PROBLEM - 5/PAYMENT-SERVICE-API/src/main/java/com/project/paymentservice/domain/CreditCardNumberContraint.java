package com.project.paymentservice.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The Interface CreditCardNumberContraint.
 */
@Documented
@Constraint(validatedBy = CreditCardNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardNumberContraint {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "Credit card number is not valid.";
    
    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};
    
    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};
    
}
