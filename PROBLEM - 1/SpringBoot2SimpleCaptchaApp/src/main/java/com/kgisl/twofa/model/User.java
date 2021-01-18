package com.kgisl.twofa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

/**
 * The Class User.
 */
@Data
@Entity
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue
	private Integer id;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The hidden. */
	@Transient
	private String hidden;
	
	/** The captcha. */
	@Transient
	private String captcha;
	
	/** The image. */
	@Transient
	private String image;
	
	/** The password. */
	private String encryptedData;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the hidden.
	 *
	 * @return the hidden
	 */
	public String getHidden() {
		return hidden;
	}

	/**
	 * Sets the hidden.
	 *
	 * @param hidden the new hidden
	 */
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	/**
	 * Gets the captcha.
	 *
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * Sets the captcha.
	 *
	 * @param captcha the new captcha
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return
	 */
	public String getEncryptedData() {
		return encryptedData;
	}

	/**
	 * @param encryptedData
	 */
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	
	
	
}
