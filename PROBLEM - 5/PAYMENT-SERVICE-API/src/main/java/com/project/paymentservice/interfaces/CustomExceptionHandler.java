package com.project.paymentservice.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.paymentservice.exception.PaymentNonexistentException;

/**
 * The Class CustomExceptionHandler.
 */
@ControllerAdvice
public class CustomExceptionHandler {

	/**
	 * Handle payment nonexistent exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(PaymentNonexistentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handlePaymentNonexistentException(PaymentNonexistentException ex) {
		return createErrorResponse(ex.getMessage());
	}
	
	/**
	 * Handle method argument not valid exception.
	 *
	 * @param ex the ex
	 * @return the list
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> createErrorResponse(fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

	/**
	 * Creates the error response.
	 *
	 * @param message the message
	 * @return the error response
	 */
	private ErrorResponse createErrorResponse(String message) {
		return new ErrorResponse(message);
	}

}