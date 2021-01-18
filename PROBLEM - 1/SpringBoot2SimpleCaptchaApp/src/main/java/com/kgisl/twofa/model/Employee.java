package com.kgisl.twofa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

/**
 * The Class Employee.
 */
@Data
@Entity
public class Employee {

	/** The id. */
	@Id
	@GeneratedValue
	private Integer id;

	/** The name. */
	private String name;

	/** The salary. */
	private Double salary;

	/** The captcha. */
	@Transient
	private String captcha;

	/** The hidden. */
	@Transient
	private String hidden;

	/** The image. */
	@Transient
	private String image;

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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * Sets the salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
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

}