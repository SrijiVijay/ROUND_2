package com.twofa.googleauth.sample.service;

public class User {
    private String login;
    private String password;
    private String secret;
    private String emailid;
    
    public User() {
	}

	public User(String login, String password, String emailid) {
        this.login = login;
        this.password = password;		
		this.emailid = emailid;
    }
    
    public User(String login, String password, String secret, String emailid) {
        this.login = login;
        this.password = password;
        this.secret = secret;
		if (emailid != null && emailid != "") {
			this.emailid = emailid;
		}
    }
    
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSecret() {
        return secret;
    }

	public String getEmailid() {
		return emailid;
	}

}