package com.project.paymentservice.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Buyer.
 */
@Getter
@Setter
 @AllArgsConstructor
 @NoArgsConstructor
public class Buyer {

	/** The Constant NAME_NULL_VALIDATION_MESSAGE. */
	public static final String NAME_NULL_VALIDATION_MESSAGE = "Buyer name must not be null";
	
	/** The Constant NAME_FORMAT_INVALID_MESSAGE. */
	public static final String NAME_FORMAT_INVALID_MESSAGE = "Name must be alphanumerical";

	/** The Constant EMAIL_NULL_VALIDATION_MESSAGE. */
	public static final String EMAIL_NULL_VALIDATION_MESSAGE = "Buyer email must not be null";
	
	/** The Constant EMAIL_FORMAT_INVALID_MESSAGE. */
	public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Buyer email invalid format";

	/** The Constant CPF_NULL_VALIDATION_MESSAGE. */
	public static final String CPF_NULL_VALIDATION_MESSAGE = "Buyer cpf must not be null";
	
	/** The Constant CPF_FORMAT_INVALID_MESSAGE. */
	public static final String CPF_FORMAT_INVALID_MESSAGE = "Buyer cpf invalid format (999.999.999-99)";

	/** The name. */
	@NotNull(message = NAME_NULL_VALIDATION_MESSAGE)
	@Size(min = 1, message = "Buyer name must not be empty")
	@Pattern(regexp = "[[A-Z][a-z]* [A-Z][a-z]*]+", message = NAME_FORMAT_INVALID_MESSAGE)
	private String name;

	/** The email. */
	@NotNull(message = EMAIL_NULL_VALIDATION_MESSAGE)
	@Size(min = 3)
	@Pattern(message = EMAIL_FORMAT_INVALID_MESSAGE, regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	private String email;

	/** The cpf. */
	@NotNull(message = CPF_NULL_VALIDATION_MESSAGE)
	@Size(min = 11)
	@Pattern(regexp = "([0-9]{2}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\\\.]?[0-9]{3}[\\\\.]?[0-9]{3}[-]?[0-9]{2})", message = CPF_FORMAT_INVALID_MESSAGE)
	private String cpf;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Instantiates a new buyer.
	 *
	 * @param name the name
	 * @param email the email
	 * @param cpf the cpf
	 */
	public Buyer(String name, String email, String cpf) {
		this.name= name;
		this.email = email;
		this.cpf = cpf;
	}
	
	/**
	 * Instantiates a new buyer.
	 */
	public Buyer() {
		super();
	}
	
}