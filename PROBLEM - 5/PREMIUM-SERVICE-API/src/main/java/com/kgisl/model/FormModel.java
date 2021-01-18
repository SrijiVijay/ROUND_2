package com.kgisl.model;

/** 
 * @author sriji.n
 *
 */
public class FormModel {
	
	/** The name. */
	private String name;
	
	/** The gender. */
	private String gender;
	
	/** The age. */
	private int age;
	
	/** The smoking. */
	private String smoking;
	
	/** The alcohol. */
	private String alcohol;
	
	/** The daily exercise. */
	private String dailyExercise;
	
	/** The drugs. */
	private String drugs;
	
	/** The hyper tension. */
	private String hyperTension;
	
	/** The blood pressure. */
	private String bloodPressure;
	
	/** The blood sugar. */
	private String bloodSugar;
	
	/** The over weight. */
	private String overWeight;

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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the smoking.
	 *
	 * @return the smoking
	 */
	public String getSmoking() {
		return smoking;
	}

	/**
	 * Sets the smoking.
	 *
	 * @param smoking the new smoking
	 */
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	/**
	 * Gets the alcohol.
	 *
	 * @return the alcohol
	 */
	public String getAlcohol() {
		return alcohol;
	}

	/**
	 * Sets the alcohol.
	 *
	 * @param alcohol the new alcohol
	 */
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	/**
	 * Gets the daily exercise.
	 *
	 * @return the daily exercise
	 */
	public String getDailyExercise() {
		return dailyExercise;
	}

	/**
	 * Sets the daily exercise.
	 *
	 * @param dailyExercise the new daily exercise
	 */
	public void setDailyExercise(String dailyExercise) {
		this.dailyExercise = dailyExercise;
	}

	/**
	 * Gets the drugs.
	 *
	 * @return the drugs
	 */
	public String getDrugs() {
		return drugs;
	}

	/**
	 * Sets the drugs.
	 *
	 * @param drugs the new drugs
	 */
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}

	/**
	 * Gets the hyper tension.
	 *
	 * @return the hyper tension
	 */
	public String getHyperTension() {
		return hyperTension;
	}

	/**
	 * Sets the hyper tension.
	 *
	 * @param hyperTension the new hyper tension
	 */
	public void setHyperTension(String hyperTension) {
		this.hyperTension = hyperTension;
	}

	/**
	 * Gets the blood pressure.
	 *
	 * @return the blood pressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * Sets the blood pressure.
	 *
	 * @param bloodPressure the new blood pressure
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * Gets the blood sugar.
	 *
	 * @return the blood sugar
	 */
	public String getBloodSugar() {
		return bloodSugar;
	}

	/**
	 * Sets the blood sugar.
	 *
	 * @param bloodSugar the new blood sugar
	 */
	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	/**
	 * Gets the over weight.
	 *
	 * @return the over weight
	 */
	public String getOverWeight() {
		return overWeight;
	}

	/**
	 * Sets the over weight.
	 *
	 * @param overWeight the new over weight
	 */
	public void setOverWeight(String overWeight) {
		this.overWeight = overWeight;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PremiumForm [name=" + name + ", gender=" + gender + ", age="
				+ age + ", smoking=" + smoking + ", alcohol=" + alcohol
				+ ", dailyExercise=" + dailyExercise + ", drugs=" + drugs
				+ ", Hypertension=" + hyperTension + ", bloodPressure="
				+ bloodPressure + ", bloodSugar=" + bloodSugar
				+ ", overweight=" + overWeight + "]";
	}

}
