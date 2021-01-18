package com.project.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.paymentservice.domain.Payment;

/**
 * The Interface Payments.
 */
public interface Payments extends MongoRepository<Payment, String> {

}
