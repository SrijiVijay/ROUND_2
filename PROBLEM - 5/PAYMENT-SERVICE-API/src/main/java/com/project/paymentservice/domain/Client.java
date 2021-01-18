package com.project.paymentservice.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Client.
 */
@Getter
@Setter
 @AllArgsConstructor
 @NoArgsConstructor
public class Client {

	/** The Constant ID_NULL_VALIDATION_MESSAGE. */
	public static final String ID_NULL_VALIDATION_MESSAGE = "Client ID must not be null";
	
	/** The id. */
	@NotNull(message = ID_NULL_VALIDATION_MESSAGE)
    @Size(min=1, message="Client ID not be empty")
	private String id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Instantiates a new client.
	 *
	 * @param id the id
	 */
	public Client(String id) {
		this.id = id;
	}
	
	/**
	 * Instantiates a new client.
	 */
	public Client() {
		super();
	}
}
