package org.yankun.dashboard.model.weather;

import java.io.Serializable;

public class WeatherType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6919252053775081301L;
	private int id;
	private String chinese;
	private int rate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
