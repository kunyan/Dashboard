package org.yankun.dashboard.model.setting;

import java.io.Serializable;

public class SunPowerData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3056924238151965428L;
	/**
	 * Hour for power
	 */
	private int hour;
	/**
	 * The sun height
	 */
	private int sunHeight;
	/**
	 * Power for this hour
	 */
	private double power;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getSunHeight() {
		return sunHeight;
	}
	public void setSunHeight(int sunHeight) {
		this.sunHeight = sunHeight;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	
	
}
