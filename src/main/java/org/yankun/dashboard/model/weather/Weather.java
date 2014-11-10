package org.yankun.dashboard.model.weather;

import java.io.Serializable;
import java.util.Date;

public class Weather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7276484178511673780L;

	/**
	 * f0
	 */
	private Date forecastTime;
	
	/**
	 * fa
	 */
	private String weatherType;
	
	/**
	 * fi
	 */
	private Date sunRiseTime;
	
	/**
	 * fi
	 */
	private Date sunDownTime;

	public Date getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(Date forecastTime) {
		this.forecastTime = forecastTime;
	}

	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}

	public Date getSunRiseTime() {
		return sunRiseTime;
	}

	public void setSunRiseTime(Date sunRiseTime) {
		this.sunRiseTime = sunRiseTime;
	}

	public Date getSunDownTime() {
		return sunDownTime;
	}

	public void setSunDownTime(Date sunDownTime) {
		this.sunDownTime = sunDownTime;
	}
	
	
}
