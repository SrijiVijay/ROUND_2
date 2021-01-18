package com.project.paymentservice.interfaces;

import java.io.Serializable;

import lombok.Getter;

/**
 * The Class ErrorResponse.
 */
@Getter
public class ErrorResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6523609406142456613L;
	
	/** The error. */
	@SuppressWarnings("unused")
	private String error;

    /**
     * Instantiates a new error response.
     *
     * @param error the error
     */
    public ErrorResponse(String error) {
        this.error = error;
    }
}
