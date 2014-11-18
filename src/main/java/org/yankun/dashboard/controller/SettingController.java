package org.yankun.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.model.weather.WeatherType;
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
	private DataService dataService;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private CalculatorService calculatorService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model) {
		
		model.addAttribute("powerList", dataService.getSunPowerDataList());
		model.addAttribute("weatherList", dataService.getWeatherTypeList());
		return new ModelAndView("setting/index", model);
	}

	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public List<SunPowerData> getPowerDefaults() {
		Weather weather = weatherService.getWeather();
		Double weatherRate = ((double)weatherService.getWeatherRate(weather) / 100);
		
		List<SunPowerData> list = dataService.getSunPowerDataList();
		
		for (SunPowerData pd : list) {
			pd.setPower(pd.getPower() * weatherRate);
		}
		
		return list;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/power/{hour}/{sunHeight}/{power}", method = RequestMethod.POST)
	public void updateSunPowerDataByHour(@PathVariable("hour") int hour,
			@PathVariable("sunHeight") int sunHeight,
			@PathVariable("power") double power) {
		SunPowerData spd = new SunPowerData();
		spd.setHour(hour);
		spd.setPower(power);
		spd.setSunHeight(sunHeight);
		dataService.updateSunPowerData(spd);;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/weather/{id}/{rate}", method = RequestMethod.POST)
	public void updateWeatherType(@PathVariable("id") int id,
			@PathVariable("rate") int rate) {
		WeatherType wt = new WeatherType();
		wt.setId(id);
		wt.setRate(rate);

		dataService.updateWeatherType(wt);
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
