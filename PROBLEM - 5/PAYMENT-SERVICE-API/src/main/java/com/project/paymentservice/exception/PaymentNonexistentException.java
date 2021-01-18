package com.project.paymentservice.exception;

/**
 * The Class PaymentNonexistentException.
 */
public class PaymentNonexistentException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new payment nonexistent exception.
	 *
	 * @param paymentId the payment id
	 */
	public PaymentNonexistentException(String paymentId) {
		 super(String.format("Payment with Id  %s not exists.", paymentId));
	}
	
}
