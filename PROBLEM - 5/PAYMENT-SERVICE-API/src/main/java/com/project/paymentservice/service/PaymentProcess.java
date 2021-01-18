package com.project.paymentservice.service;

import com.project.paymentservice.domain.Payment;

/**
 * The Interface PaymentProcess.
 */
public interface PaymentProcess {

	/**
	 * Process.
	 *
	 * @param payment the payment
	 * @return the payment
	 */
	Payment process(Payment payment);

}
