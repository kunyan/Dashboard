package org.yankun.dashboard.model;

import java.io.Serializable;
import java.util.Date;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4461379614140250432L;

	private long id;
	
	private Date powerTime;
	
	private double power;
	
	private double saveC;
	
	private double saveCO2;
	
	private double income;
	
	private double saveIncome;
	
	private double restIncome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPowerTime() {
		return powerTime;
	}

	public void setPowerTime(Date powerTime) {
		this.powerTime = powerTime;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getSaveC() {
		return saveC;
	}

	public void setSaveC(double saveC) {
		this.saveC = saveC;
	}

	public double getSaveCO2() {
		return saveCO2;
	}

	public void setSaveCO2(double saveCO2) {
		this.saveCO2 = saveCO2;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getSaveIncome() {
		return saveIncome;
	}

	public void setSaveIncome(double saveIncome) {
		this.saveIncome = saveIncome;
	}

	public double getRestIncome() {
		return restIncome;
	}

	public void setRestIncome(double restIncome) {
		this.restIncome = restIncome;
	}
	
	
	
}
