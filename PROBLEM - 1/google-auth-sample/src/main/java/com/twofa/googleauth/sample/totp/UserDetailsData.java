package com.twofa.googleauth.sample.totp;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsData {

	public void userDetailsDecrypt(Credentials credentials, String userName, String password, String emailid) {
		String decryptedEmailId = null;
		String decryptedUserName =  new String(java.util.Base64.getDecoder().decode(credentials.getLogin()));
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(credentials.getPassword()));
		AesUtil aesUtil = new AesUtil(128, 1000);
		
		if (decryptedUserName != null && decryptedUserName.split("::").length == 3) {
	        System.out.println("UserName decrypted successfully for Password - " + credentials.getLogin());
	        userName = aesUtil.decrypt(decryptedUserName.split("::")[1], decryptedUserName.split("::")[0], "1234567891234567", decryptedUserName.split("::")[2]);
	        System.out.println(userName);   
	    }
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			System.out.println("Password decrypted successfully for username - " + credentials.getPassword());
			password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "1234567891234567", decryptedPassword.split("::")[2]);
    	    System.out.println(password);
		}
		
		if (credentials.getEmailid() != null && credentials.getEmailid() != "") {
			decryptedEmailId =  new String(java.util.Base64.getDecoder().decode(credentials.getEmailid()));
			System.out.println("Password decrypted successfully for emailId - " + credentials.getEmailid());
			emailid = aesUtil.decrypt(decryptedEmailId.split("::")[1], decryptedEmailId.split("::")[0], "1234567891234567", decryptedEmailId.split("::")[2]);
    	    System.out.println(emailid);
		} 
		
	}
}