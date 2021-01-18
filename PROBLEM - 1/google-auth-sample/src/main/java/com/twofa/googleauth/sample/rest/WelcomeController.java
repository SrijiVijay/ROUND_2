package com.twofa.googleauth.sample.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twofa.googleauth.sample.totp.AesUtil;
import com.twofa.googleauth.sample.totp.Credentials;

@RestController
@RequestMapping(path = "/user/")
public class WelcomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping(value={"/login1"},method = RequestMethod.GET)
    public String loginPage1(HttpServletRequest request){
		LOGGER.info("Received request for login page with id - " + request.getSession().getId());
		String randomKey = UUID.randomUUID().toString();
		//String uniqueKey = randomKey.substring(randomKey.length()-17, randomKey.length() -1);
        String uniqueKey = "1234567891234567";
	    request.getSession().setAttribute("key", uniqueKey);
        /*LoginToken token = new LoginToken();
        token.setActive(true);
        token.setCreatedOn(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        token.setExpiredOn(Date.from(LocalDateTime.now().plus(30, ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant()));
        token.setTokenValue(uniqueKey);
        token.setSessionId(request.getSession().getId());
        tokenRepo.save(token);*/
	    return "index";
    }

	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody Credentials credentials) {
		
        String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(credentials.getPassword()));
        AesUtil aesUtil = new AesUtil(128, 1000);
        if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
            LOGGER.info("Password decrypted successfully for username - " + credentials.getLogin());
            String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "1234567891234567", decryptedPassword.split("::")[2]);
            System.out.println(password);
        }

        return "success";
    }

}
