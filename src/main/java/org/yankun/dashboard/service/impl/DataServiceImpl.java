package org.yankun.dashboard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yankun.dashboard.dao.BaseDao;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.service.DataService;

@Service
@Transactional
public class DataServiceImpl implements DataService {
	
	protected final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
	
	@Autowired
	private BaseDao dao;
	

	@Override
	public SunPowerData getSunPowerDataByHour(int hour) {
		String sql = "select * from setting_power_tbl where hour = ?";
		return dao.queryForObject(
				sql, new Object[] { hour }, 
				new BeanPropertyRowMapper<SunPowerData>(SunPowerData.class));
	}

	@Override
	public List<SunPowerData> getSunPowerDataList() {
		String sql = "select * from setting_power_tbl";
		return dao.query(sql,new BeanPropertyRowMapper<SunPowerData>(SunPowerData.class));
	}

	@Override
	public void updateSunPowerData(SunPowerData sunPowerData) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public Data getSumDataByDatePeriod(Date startDate, Date endDate){
		try {
			String sql = "select COALESCE(SUM(power), 0) as power, COALESCE(SUM(used), 0) as used from power_data_tbl where createDateTime between ? and ? ";
			return dao.queryForObject(
					sql, new Object[] { startDate, endDate }, 
					new BeanPropertyRowMapper<Data>(Data.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Data();
	}
	
	@Override
	public List<Data> getDataListByDatePeriod(Date startDate, Date endDate){
		try {
			String sql = "select createDateTime , power, used from power_data_tbl where createDateTime between ? and ? ";
			
			return dao.query(
					sql, new Object[] { startDate, endDate }, 
					new BeanPropertyRowMapper<Data>(Data.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Data>();
	}
	
}
