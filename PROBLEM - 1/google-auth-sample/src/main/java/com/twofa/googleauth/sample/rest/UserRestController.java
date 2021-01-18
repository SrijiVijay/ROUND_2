package com.twofa.googleauth.sample.rest;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twofa.googleauth.sample.service.EncrypUtil;
import com.twofa.googleauth.sample.service.User;
import com.twofa.googleauth.sample.service.UserService;
import com.twofa.googleauth.sample.totp.AuthenticationStatus;
import com.twofa.googleauth.sample.totp.Credentials;

@RestController
@RequestMapping(value = "/user/", method = RequestMethod.POST)
public class UserRestController {
	
    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EncrypUtil encrypUtil;
    
    @RequestMapping(value = "/register/{login}/{emailid}", method = RequestMethod.POST, consumes = "application/json")
    public String register(@PathVariable String login, @PathVariable String emailid , @RequestBody Credentials credential) throws Exception {
    	String password = encrypUtil.decryptData(credential.getEncryptedData());
    	if (emailid.equalsIgnoreCase("N")) {
    		User user = userService.register(login, password);
	        String encodedSecret = new Base32().encodeToString(user.getSecret().getBytes());
	        System.out.println("encodedSecret :"+ encodedSecret);
	        return encodedSecret.replace("=", "");
		} else {
			userService.registerEmail(login, password, emailid);
			return AuthenticationStatus.SEND_EMAIL_OTP.toString();
		}
    }
}
