package org.yankun.dashboard.service;

import java.util.List;

import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.model.weather.Weather;

public interface SettingService {
	PowerDefault getPowerDefaultByHour(int hour);
	List<PowerDefault> getPowerDefaults();
	void updatePowerDefault(PowerDefault powerDefault);
	int getWeatherRate(Weather weather);
}
