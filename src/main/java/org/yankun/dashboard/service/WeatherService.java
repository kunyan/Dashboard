package org.yankun.dashboard.service;

import org.yankun.dashboard.model.weather.Weather;

public interface WeatherService {
	Weather getWeather();
	
	int getWeatherRate(Weather weather);
}
