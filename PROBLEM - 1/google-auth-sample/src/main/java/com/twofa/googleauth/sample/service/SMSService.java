package com.twofa.googleauth.sample.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {

	private final static String ACCOUNT_SID = "ACd6a53e72de701290ec3d594760a90f58";
	private final static String AUTH_ID = "a0f745df001f3ba60ab4f52ad3126f44";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public boolean send2FaCode(String mobilenumber, String twoFaCode) {
		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+15005550006"),
				"Your Two Factor Authentication code is: " + twoFaCode).create();
		return true;
	}
}