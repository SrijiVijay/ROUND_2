package com.twofa.googleauth.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    private static final List<User> users = new ArrayList<>();
    
    @Value("${2fa.secretsize}")
    private int secret_size;

    @Value("${2fa.enabled}")
    private boolean isTwoFaEnabled;
    
    @Autowired
	private EmailService emailService;

    /**
     * @param userName
     * @param password
     * @return
     */
    public User register(String userName, String password) {
        User user = new User(userName, password, generateSecret(), null);
        users.add(user);
        System.out.println("encodedSecret user"+ user.getSecret());
        return user;
    }
    
    /**
     * @param userName
     * @param password
     * @param emailId
     * @return
     * @throws AddressException
     * @throws MessagingException
     */
    public User registerEmail(String userName, String password, String emailId) throws AddressException, MessagingException {
    	String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
        User user = new User(userName, password, twoFaCode, emailId);
        emailService.sendEmail(emailId, twoFaCode);
        users.add(user);
        System.out.println("encodedSecret user"+ user.getSecret());
        return user;
    }

    /**
     * @param login
     * @param password
     * @return
     */
    public Optional<User> findUser(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }
    
    /**
     * @param login
     * @param secret
     * @return
     */
    public Optional<User> findValidOTP(String login, String secret) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getSecret().equals(secret))
                .findFirst();
    }
    
    public Optional<User> findByUser(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
    
    /**
     * @return
     */
    private String generateSecret() {
        return RandomStringUtils.random(secret_size, true, true).toUpperCase();
    }
}
