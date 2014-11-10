package org.yankun.dashboard.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yankun.dashboard.model.User;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.WeatherService;

@Controller
@RequestMapping("/api")
public class APIController {
	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser() {		
		User user = new User();
		user.setUsername("Yankun");
		user.setPassword("123432");
		return user;
	}
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public Weather getWeather() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		Weather weather = weatherService.getWeather();
		System.err.println(sdf.format(weather.getSunRiseTime()));
		return weather;
	}
}
