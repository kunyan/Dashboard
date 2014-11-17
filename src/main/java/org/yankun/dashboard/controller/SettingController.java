package org.yankun.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.CalculatorService;
import org.yankun.dashboard.service.DataService;
import org.yankun.dashboard.service.WeatherService;

/**
 * 
 * @author kun.yan
 *
 */
@Controller
@RequestMapping("/setting")
public class SettingController {
	
	@Autowired
	private DataService settingService;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private CalculatorService calculatorService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model) {
		
		model.addAttribute("powerList", settingService.getSunPowerDataList());
		return new ModelAndView("setting/index", model);
	}

	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public List<SunPowerData> getPowerDefaults() {
		Weather weather = weatherService.getWeather();
		Double weatherRate = ((double)weatherService.getWeatherRate(weather) / 100);
		
		List<SunPowerData> list = settingService.getSunPowerDataList();
		
		for (SunPowerData pd : list) {
			pd.setPower(pd.getPower() * weatherRate);
		}
		
		return list;
	}
	
	@RequestMapping(value = "/power/{hour}", method = RequestMethod.PUT)
	public List<SunPowerData> setSunPowerDataByHour(int hour) {
		return settingService.getSunPowerDataList();
	}
	
//	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
//	public Dashboard getDashboard() {
//		Dashboard dashboard = new Dashboard();
//		
//		dashboard.setWeather(weatherService.getWeather());
//		dashboard.setTodayTotalPower(todayTotalPower);
//		return settingService.getDashboardData();
//	}
}
