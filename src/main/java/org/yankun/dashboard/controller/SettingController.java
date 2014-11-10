package org.yankun.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.SettingService;
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
	private SettingService settingService;
	
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model) {
		
		model.addAttribute("powerList", settingService.getPowerDefaults());
		return new ModelAndView("setting/index", model);
	}

	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public List<PowerDefault> getPowerDefaults() {
		Weather weather = weatherService.getWeather();
		Double weatherRate = (double) (settingService.getWeatherRate(weather) / 100);
		List<PowerDefault> list = settingService.getPowerDefaults();
		
		for (PowerDefault pd : list) {
			pd.setPower(pd.getPower() * weatherRate);
		}
		return list;
	}
	
	@RequestMapping(value = "/power/{hour}", method = RequestMethod.PUT)
	public List<PowerDefault> setPowerDefaultsByHour(int hour) {
		return settingService.getPowerDefaults();
	}
}
