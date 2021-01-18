package com.kgisl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.common.utils.CommonConstants;
import com.kgisl.model.FormModel;
import com.kgisl.model.ResponseModel;
import com.kgisl.service.PremiumService;

/**
 * The Class PremiumController.
 */
@RestController
public class PremiumController {
	
	/** The premium service. */
	@Autowired
	private PremiumService premiumService;

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Calculate total premium.
	 *
	 * @param formModelDto the form model dto
	 * @param httpServletRequest the http servlet request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping(value = "/calculatePremium")
	public ResponseEntity<ResponseModel> calculateTotalPremium (@RequestBody FormModel formModelDto, 
			HttpServletRequest httpServletRequest) throws Exception {
		double totalPremiumAmount = 0;
		ResponseModel response = new ResponseModel();
		try {
			logger.info("Input Request :" + formModelDto.toString());
			totalPremiumAmount = premiumService.getPremiumAmount(formModelDto);
			response.setAmount(totalPremiumAmount);
			response.setName(formModelDto.getName());
		} catch (Exception exception) {
			logger.error("Error :" + exception.getMessage());
			throw new Exception(CommonConstants.ERROR_MESSAGE, exception);
		}
		logger.info("Response of calculateTotalPremium() " + response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}