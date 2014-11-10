package org.yankun.dashboard.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yankun.dashboard.dao.BaseDao;
import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.SettingService;

@Service
@Transactional
public class SettingServiceImpl implements SettingService {
	
	protected final Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);
	
	@Autowired
	private BaseDao dao;
	

	@Override
	public PowerDefault getPowerDefaultByHour(int hour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PowerDefault> getPowerDefaults() {
		String sql = "select * from setting_power_tbl";
		return dao.query(sql,new BeanPropertyRowMapper<PowerDefault>(PowerDefault.class));
	}

	@Override
	public void updatePowerDefault(PowerDefault powerDefault) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWeatherRate(Weather weather) {
		String sql = "select rate from setting_weather_tbl where id = ? ";
		try {
			return dao.queryForObject(sql, Integer.class, weather.getWeatherType());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Can not find weather in database", e);
		}
		return 100;
	}
}
