package org.yankun.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.model.weather.WeatherType;
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
	@RequestMapping(value = "/power", method = RequestMethod.POST)
	public void updateSunPowerDataByHour(@RequestParam("hour") int hour,
			@RequestParam("sunHeight") int sunHeight,
			@RequestParam("power") double power) {
		SunPowerData spd = new SunPowerData();
		spd.setHour(hour);
		spd.setPower(power);
		spd.setSunHeight(sunHeight);
		dataService.updateSunPowerData(spd);;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public void updateWeatherType(@RequestParam("id") int id,
			@RequestParam("rate") int rate) {
		WeatherType wt = new WeatherType();
		wt.setId(id);
		wt.setRate(rate);

		dataService.updateWeatherType(wt);
	}
}
