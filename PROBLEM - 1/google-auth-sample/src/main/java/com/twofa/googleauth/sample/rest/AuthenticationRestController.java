package com.twofa.googleauth.sample.rest;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twofa.googleauth.sample.service.EncrypUtil;
import com.twofa.googleauth.sample.service.TotpService;
import com.twofa.googleauth.sample.service.User;
import com.twofa.googleauth.sample.service.UserService;
import com.twofa.googleauth.sample.totp.AuthenticationStatus;
import com.twofa.googleauth.sample.totp.Credentials;

@RestController
@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
public class AuthenticationRestController {
	
    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TotpService totpService;
    
    @Autowired
    private EncrypUtil encrypUtil;

	/**
	 * Method to authenticate user credentials and check for 2FA enabled (GOTP, OTP)
	 * 
	 * @param credentials
	 * @return
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
    @RequestMapping(value = "/authenticateUser/{login}", method = RequestMethod.POST)
    public AuthenticationStatus authenticate(@PathVariable String login,  @RequestBody Credentials credential) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	String password = encrypUtil.decryptData(credential.getEncryptedData());
    	Optional<User> user = userService.findUser(login,password);

        if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }
        if (!isTwoFaEnabled) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login,password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return AuthenticationStatus.AUTHENTICATED;
		} else {
			if (user.get().getEmailid() != null && user.get().getEmailid() != "") {
				return AuthenticationStatus.SEND_EMAIL_OTP;
			} else {
				SecurityContextHolder.getContext().setAuthentication(null);
				return AuthenticationStatus.SEND_GOTP;
			}
		}
	}
    
	/**
	 * Method is to validate the given time-based codes with secretKey (GOTP)
	 * 
	 * @param credentials
	 * @param token
	 * @return
	 */
    @RequestMapping(value = "/gotpTokenCheck/{login}/{otpCode}", method = RequestMethod.POST)
    public AuthenticationStatus tokenCheck(@PathVariable String login, @PathVariable String otpCode) {
    	Optional<User> user = userService.findByUser(login);

        if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }

        if (!totpService.verifyCode(otpCode, user.get().getSecret())) {
            return AuthenticationStatus.FAILED;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get().getLogin(), user.get().getPassword(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthenticationStatus.AUTHENTICATED;
    }
    
	/**
	 * Method to verify 2FA code
	 * 
	 * @param userid
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/otpTokenCheck/{login}/{otpCode}", method = RequestMethod.POST)
	public AuthenticationStatus verify(@PathVariable String login, @PathVariable String otpCode) {
		Optional<User> user = userService.findValidOTP(login, otpCode);
		if (!user.isPresent()) {
            return AuthenticationStatus.FAILED;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get().getLogin(), user.get().getPassword(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return AuthenticationStatus.AUTHENTICATED;
	}
	
	/**
	 * Method to send OTP through SMS
	 * 
	 * @param id
	 * @param mobile
	 * @return
	 */
//	@RequestMapping(value = "/users/{username}/mobilenumbers/{mobilenumber}/2fa", method = RequestMethod.POST, consumes = "application/json")
//	public ResponseEntity<Object> send2faCodeinSMS(@RequestBody String username, String mobile) {
//		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
//		smsService.send2FaCode(mobile, twoFaCode);
//		daoService.update2FAProperties(username, twoFaCode);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        SecurityContextHolder.clearContext();
    }

}