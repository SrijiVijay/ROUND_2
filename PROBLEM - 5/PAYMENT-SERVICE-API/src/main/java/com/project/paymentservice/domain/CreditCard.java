package com.project.paymentservice.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class CreditCard.
 */
@Getter
@Setter
 @AllArgsConstructor
 @NoArgsConstructor
public class CreditCard {

	/** The holder name. */
	@NotNull(message = "Credit card holder name must not be null.")
    @Size(min=1, message="Credit card holder name must not be empty.")
	private String holderName;
	
	/** The number. */
	@NotNull(message = "Credit Card number must not be null.")
    @Size(min=16, message="Credit Card number must not be empty.")
	@CreditCardNumberContraint
	private String number;
	
	/** The expiration date. */
	@NotNull(message = "Credit Card expiration date must not be null.")
    @Size(min=1, message="Credit Card expiration date must not be empty.")
	private String expirationDate;
	
	/** The cvv. */
	@NotNull(message = "Credit Card CVV must not be null.")
    @Size(min=3, message="Credit Card CVV must not be empty.")
	private String cvv;

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String  getBrand() {
		return new  br.com.moip.validators.CreditCard(this.getNumber()).getBrand().name();
	}

	/**
	 * Gets the holder name.
	 *
	 * @return the holder name
	 */
	public String getHolderName() {
		return holderName;
	}

	/**
	 * Sets the holder name.
	 *
	 * @param holderName the new holder name
	 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the expiration date.
	 *
	 * @return the expiration date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the new expiration date
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the cvv.
	 *
	 * @return the cvv
	 */
	public String getCvv() {
		return cvv;
	}

	/**
	 * Sets the cvv.
	 *
	 * @param cvv the new cvv
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	/**
	 * Instantiates a new credit card.
	 *
	 * @param holderName the holder name
	 * @param number the number
	 * @param expirationDate the expiration date
	 * @param cvv the cvv
	 */
	public CreditCard(String holderName, String number, String expirationDate, String cvv) {
		this.holderName = holderName;
		this.number = number;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	
	/**
	 * Instantiates a new credit card.
	 */
	public CreditCard() {
		super();
	}
	
}