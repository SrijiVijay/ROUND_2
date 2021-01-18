package com.project.paymentservice.interfaces;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.paymentservice.config.AesUtil;
import com.project.paymentservice.config.EncrypUtil;
import com.project.paymentservice.domain.Payment;
import com.project.paymentservice.exception.PaymentNonexistentException;
import com.project.paymentservice.repository.Payments;
import com.project.paymentservice.service.PaymentProcess;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * The Class PaymentController.
 */
@RestController
@RequiredArgsConstructor
@Api(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

	/** The payments. */
	@Autowired
	private Payments payments;
	
	/** The payment process. */
	@Autowired
	private PaymentProcess paymentProcess;
	
	@Autowired
    private EncrypUtil encrypUtil;

	/**
	 * Creates the.
	 *
	 * @param payment the payment
	 * @return the response entity
	 */
	@ApiOperation(value = "Create payment")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Payment created OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Some issue regarding the input will cause the application not to proceed with the operation"),
			@ApiResponse(code = 409, message = "Conflict with the input provide"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/payment")
	public ResponseEntity<Payment> create(@RequestBody @Valid Payment payment) {
		return ResponseEntity.ok(paymentProcess.process(payment));
	}

	@ApiOperation(value = "Create payments")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Payment created OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Some issue regarding the input will cause the application not to proceed with the operation"),
			@ApiResponse(code = 409, message = "Conflict with the input provide"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/payments")
	public ResponseEntity<Payment> createa(@RequestBody @Valid Payment payment) throws Exception, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			String cardInfo = null;
			String password = encrypUtil.decryptData(payment.getEncryptedData());
			String decryptedCardInfo =  new String(java.util.Base64.getDecoder().decode(payment.getCreditCard().getNumber()));
			AesUtil aesUtil = new AesUtil(128, 1000);
			
//			if (decryptedCardInfo != null && decryptedCardInfo.split("::").length == 3) {
//		        System.out.println("UserName decrypted successfully for Password - " + payment.getCreditCard().getNumber());
//		        cardInfo = aesUtil.decrypt(decryptedCardInfo.split("::")[1], decryptedCardInfo.split("::")[0], "1234567891234567", decryptedUserName.split("::")[2]);
//		        System.out.println(cardInfo);   
//		    }
		return ResponseEntity.ok(paymentProcess.process(payment));
	}	
	
	
	/**
 * Gets the status by payment id.
 *
 * @param paymentId the payment id
 * @return the status by payment id
 */
@ApiOperation(value = "Get payment status by payment id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Payment found OK"),
			@ApiResponse(code = 400, message = "Bad Request"), 
			@ApiResponse(code = 404, message = "Payment not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/payment/{paymentId}/status")
	public ResponseEntity<Optional<Payment>> getStatusByPaymentId(@PathVariable String paymentId) {
		Optional<Payment> payment = payments.findById(paymentId);
		if (!payment.isPresent()) {
			throw new PaymentNonexistentException(paymentId);
		}
		return ResponseEntity.ok(payment);
	}

}
