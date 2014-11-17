package org.yankun.dashboard.model;

import java.io.Serializable;
import java.util.Date;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4461379614140250432L;

	private long id;
	
	private Date createDateTime;
	
	private double power;
	
	private double used;
	
	private int powerHour;
	
	private Date powerMaxDateTime;
	
	private double avgPower;
	
	private double saveCoal;
	
	private double saveCO2;
	
	private double subsidies;
	
	private double saveIncome;
	
	private double surplusIncome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public int getPowerHour() {
		return powerHour;
	}

	public void setPowerHour(int powerHour) {
		this.powerHour = powerHour;
	}

	public Date getPowerMaxDateTime() {
		return powerMaxDateTime;
	}

	public void setPowerMaxDateTime(Date powerMaxDateTime) {
		this.powerMaxDateTime = powerMaxDateTime;
	}

	public double getAvgPower() {
		return avgPower;
	}

	public void setAvgPower(double avgPower) {
		this.avgPower = avgPower;
	}

	public void setSaveCoal(double saveCoal) {
		this.saveCoal = saveCoal;
	}

	public void setSaveCO2(double saveCO2) {
		this.saveCO2 = saveCO2;
	}

	public void setSubsidies(double subsidies) {
		this.subsidies = subsidies;
	}

	public void setSaveIncome(double saveIncome) {
		this.saveIncome = saveIncome;
	}

	public void setSurplusIncome(double surplusIncome) {
		this.surplusIncome = surplusIncome;
	}

	public double getSaveCoal() {
		return this.power * 0.4;
	}

	public double getSaveCO2() {
		return this.power * 0.96;
	}

	public double getSubsidies() {
		return this.power * 0.42;
	}

	public double getSaveIncome() {
		return this.used;
	}

	public double getSurplusIncome() {
		return (this.power - this.used) > 0 ? (this.power - this.used) * 0.4 : 0;
	}
	
}
