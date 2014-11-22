package org.yankun.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.model.User;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.DataService;
import org.yankun.dashboard.service.WeatherService;
import org.yankun.dashboard.util.DateUtils;

@Controller
@RequestMapping("/api")
public class APIController {
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser() {		
		User user = new User();
		user.setUsername("Yankun");
		user.setPassword("123432");
		return user;
	}
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public Weather getWeather() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Weather weather = weatherService.getWeather();
		return weather;
	}
	
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public Map<String, Object> getData() {
		
		Data careerTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getSystemFirstDay(),
				DateUtils.getTomorrow());
		
		Data thisYearTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getThisYearFirstDay(),
				DateUtils.getNextYearFirstDay());
		
		Data lastMonthTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getLastMonthFirstDay(),
				DateUtils.getThisMonthFirstDay());
		
		Data lastWeekTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getLastWeekFirstDay(),
				DateUtils.getThisWeekFirstDay());
		
		Data yesterdayTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getYesterday(),
				DateUtils.getToday());
		
		Data todayTotal = dataService.getSumDataByDatePeriod(
				DateUtils.getToday(),
				DateUtils.getTomorrow());
		
		Weather weather = weatherService.getWeather();
		
		Double weatherRate = ((double)weatherService.getWeatherRate(weather) / 100);
		
		List<SunPowerData> list = new ArrayList<SunPowerData>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(weather.getSunRiseTime());
		int sunRiseHour = calendar.get(Calendar.HOUR_OF_DAY);
		
		calendar.setTime(weather.getSunDownTime());
		int sunDownHour = calendar.get(Calendar.HOUR_OF_DAY);
		
		for (SunPowerData spd : dataService.getSunPowerDataList()) {
			
			if (sunRiseHour <= spd.getHour() && spd.getHour() <= sunDownHour) {
				spd.setPower(spd.getPower() * weatherRate);	
				spd.setSunHeight((int)(spd.getSunHeight() * weatherRate));
				list.add(spd);
			}
			
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weather", weather);
		map.put("careerTotal", careerTotal);
		map.put("thisYearTotal", thisYearTotal);
		map.put("lastMonthTotal", lastMonthTotal);
		map.put("lastWeekTotal", lastWeekTotal);
		map.put("yesterdayTotal", yesterdayTotal);
		map.put("todayTotal", todayTotal);
		map.put("sunPowerData", list);

		
		return map;
	}
}
