package com.twofa.googleauth.sample.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final String username="sriji.n@kgisl.com";
	private static final String password = "Yuvan@2021";

	public boolean sendEmail(String emailid, String twoFaCode) throws AddressException, MessagingException {
		Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "10.100.1.123");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("sriji.n@kgisl.com"));
		message.setSubject("Two Factor Authentication code from our Service");
		message.setText("Your Two Factor Authentication code is:" + twoFaCode);
		Transport.send(message);
		return true;
	}
}