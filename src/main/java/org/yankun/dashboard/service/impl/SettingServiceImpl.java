package org.yankun.dashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yankun.dashboard.dao.BaseDao;
import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.service.SettingService;

@Service
@Transactional
public class SettingServiceImpl implements SettingService {
	
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

}
