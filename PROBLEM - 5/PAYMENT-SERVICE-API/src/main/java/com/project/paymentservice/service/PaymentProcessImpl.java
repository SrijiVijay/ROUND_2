package com.project.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.paymentservice.domain.Payment;
import com.project.paymentservice.domain.Payment.PaymentStatus;
import com.project.paymentservice.domain.Payment.PaymentType;
import com.project.paymentservice.repository.Payments;

import lombok.RequiredArgsConstructor;

/**
 * The Class PaymentProcessImpl.
 */
@RequiredArgsConstructor
@Service
public class PaymentProcessImpl implements PaymentProcess {

	/** The payments. */
	@Autowired
	private Payments payments;

	/**
	 * Process.
	 *
	 * @param payment the payment
	 * @return the payment
	 */
	public Payment process(Payment payment) {
		payment.setStatus(PaymentStatus.APPROVED);
		Payment paymentSaved = payments.save(payment);

		if (payment.getType().equals(PaymentType.SLIP)) {
			payment.setBarcode("1111111 1 2222222 2 333333 3 44444 4");
			payment.setReferenceNumber(paymentSaved.getObjectId().toString());			
			return payment;
		} else {
			System.out.println("paymentSaved :" + paymentSaved.getObjectId());
			paymentSaved.setReferenceNumber(paymentSaved.getObjectId().toString());
			return paymentSaved;
		}
	}

}
