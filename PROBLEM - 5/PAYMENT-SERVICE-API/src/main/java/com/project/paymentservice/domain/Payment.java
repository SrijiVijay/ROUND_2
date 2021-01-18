package com.project.paymentservice.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Payment.
 */
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	/** The Constant CLIENT_NULL_VALIDATION_MESSAGE. */
	public static final String CLIENT_NULL_VALIDATION_MESSAGE = "Client must not be null";
	
	/** The Constant BUYER_NULL_VALIDATION_MESSAGE. */
	public static final String BUYER_NULL_VALIDATION_MESSAGE = "Buyer must not be null";
	
	/** The Constant AMOUNT_NULL_VALIDATION_MESSAGE. */
	public static final String AMOUNT_NULL_VALIDATION_MESSAGE = "Amount must not be null";

	/** The object id. */
	@Id
	private ObjectId objectId;

	/** The amount. */
	@NotNull(message = AMOUNT_NULL_VALIDATION_MESSAGE)
	private Double amount;

	/** The type. */
	private PaymentType type;

	/** The client. */
	@NotNull(message = CLIENT_NULL_VALIDATION_MESSAGE)
	@Valid
	private Client client;

	/** The buyer. */
	@NotNull(message = BUYER_NULL_VALIDATION_MESSAGE)
	@Valid
	private Buyer buyer;
	
	@Valid
	private String encryptedData;

	/** The credit card. */
	@Field(value = "credit_card")
	@Valid
	private CreditCard creditCard;
	
//	@Field(value = "debit_card")
//	@Valid
//	private DebitCard dreditCard;

	/** The status. */
private PaymentStatus status;

	/** The barcode. */
	private String barcode;
	
	/** The reference number. */
	private String referenceNumber;

	/**
	 * The Enum PaymentType.
	 */
	public enum PaymentType {
		
		/** The slip. */
		SLIP, 
 /** The credit card. */
 CREDIT_CARD
	}

	/**
	 * The Enum PaymentStatus.
	 */
	public enum PaymentStatus {
		
		/** The approved. */
		APPROVED;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public PaymentType getType() {
		if (creditCard != null) {
			return PaymentType.CREDIT_CARD;
		} 
		return PaymentType.SLIP;
	}

	/**
	 * Instantiates a new payment.
	 *
	 * @param object the object
	 * @param d the d
	 * @param creditCard2 the credit card 2
	 * @param client2 the client 2
	 * @param buyer2 the buyer 2
	 * @param creditCard3 the credit card 3
	 * @param approved the approved
	 * @param referenceNumber the reference number
	 */
	public Payment(Object object, double d, PaymentType creditCard2, Client client2, Buyer buyer2,
			CreditCard creditCard3, PaymentStatus approved, String referenceNumber) {
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public PaymentStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	/**
	 * Gets the barcode.
	 *
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Sets the barcode.
	 *
	 * @param barcode the new barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	
	/**
	 * Gets the reference number.
	 *
	 * @return the reference number
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * Sets the reference number.
	 *
	 * @param referenceNumber the new reference number
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public ObjectId getId() {
		return objectId;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(ObjectId id) {
		this.objectId = id;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Sets the client.
	 *
	 * @param client the new client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Gets the buyer.
	 *
	 * @return the buyer
	 */
	public Buyer getBuyer() {
		return buyer;
	}

	/**
	 * Sets the buyer.
	 *
	 * @param buyer the new buyer
	 */
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	/**
	 * Gets the credit card.
	 *
	 * @return the credit card
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 * Sets the credit card.
	 *
	 * @param creditCard the new credit card
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(PaymentType type) {
		this.type = type;
	}

	/**
	 * Instantiates a new payment.
	 */
	public Payment() {
		super();
	}

	/**
	 * Gets the object id.
	 *
	 * @return the object id
	 */
	public ObjectId getObjectId() {
		return objectId;
	}

	/**
	 * Sets the object id.
	 *
	 * @param objectId the new object id
	 */
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	

}