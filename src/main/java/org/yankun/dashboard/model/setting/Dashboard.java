package org.yankun.dashboard.model.setting;

import java.io.Serializable;
import java.util.List;

import org.yankun.dashboard.model.weather.Weather;

public class Dashboard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 845111591229410538L;
	
	/**
	 * 天气
	 */
	private Weather weather;

	/**
	 * 装机容量
	 */
	private double systemTotalPower;
	
	/**
	 * 今日总发电量
	 */
	private double todayTotalPower;
	
	/**
	 * 今年总合发电量
	 */
	private double thisYearTotalPower;
	
	/**
	 * 生涯总合发电量
	 */
	private double careerTotalPower;
	
	/**
	 * 昨日发电量
	 */
	private double yesterdayPower;
	
	/**
	 * 上周发电量
	 */
	private double lastWeekPower;
	
	/**
	 * 上月发电量
	 */
	private double lastMonthPower;
	
	/**
	 * 生涯补贴
	 */
	private double careerSubsidies;
	
	/**
	 * 生涯节约
	 */
	private double careerSaveIncome;
	
	/**
	 * 生涯剩余
	 */
	private double careerSurplusIncome;
	
    /**
     * 日照高度发电数据
     */
    private List<SunPowerData> sunPowerData;

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public double getSystemTotalPower() {
		return systemTotalPower;
	}

	public void setSystemTotalPower(double systemTotalPower) {
		this.systemTotalPower = systemTotalPower;
	}

	public double getTodayTotalPower() {
		return todayTotalPower;
	}

	public void setTodayTotalPower(double todayTotalPower) {
		this.todayTotalPower = todayTotalPower;
	}

	public double getThisYearTotalPower() {
		return thisYearTotalPower;
	}

	public void setThisYearTotalPower(double thisYearTotalPower) {
		this.thisYearTotalPower = thisYearTotalPower;
	}

	public double getCareerTotalPower() {
		return careerTotalPower;
	}

	public void setCareerTotalPower(double careerTotalPower) {
		this.careerTotalPower = careerTotalPower;
	}

	public double getYesterdayPower() {
		return yesterdayPower;
	}

	public void setYesterdayPower(double yesterdayPower) {
		this.yesterdayPower = yesterdayPower;
	}

	public double getLastWeekPower() {
		return lastWeekPower;
	}

	public void setLastWeekPower(double lastWeekPower) {
		this.lastWeekPower = lastWeekPower;
	}

	public double getLastMonthPower() {
		return lastMonthPower;
	}

	public void setLastMonthPower(double lastMonthPower) {
		this.lastMonthPower = lastMonthPower;
	}

	public double getCareerSubsidies() {
		return careerSubsidies;
	}

	public void setCareerSubsidies(double careerSubsidies) {
		this.careerSubsidies = careerSubsidies;
	}

	public double getCareerSaveIncome() {
		return careerSaveIncome;
	}

	public void setCareerSaveIncome(double careerSaveIncome) {
		this.careerSaveIncome = careerSaveIncome;
	}

	public double getCareerSurplusIncome() {
		return careerSurplusIncome;
	}

	public void setCareerSurplusIncome(double careerSurplusIncome) {
		this.careerSurplusIncome = careerSurplusIncome;
	}

	public List<SunPowerData> getSunPowerData() {
		return sunPowerData;
	}

	public void setSunPowerData(List<SunPowerData> sunPowerData) {
		this.sunPowerData = sunPowerData;
	}

	
}
