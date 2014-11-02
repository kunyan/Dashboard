package org.yankun.dashboard.model.weather;

import java.io.Serializable;

public class WeatherType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6919252053775081301L;
	private String id;
	private String chinses;
	private String english;
	private double percent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChinses() {
		return chinses;
	}
	public void setChinses(String chinses) {
		this.chinses = chinses;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
}
