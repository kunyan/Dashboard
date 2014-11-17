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
		List<Data> list = new ArrayList<Data>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select ");
			sb.append(" DATE(createDateTime) ");
			sb.append(" as createDateTime, ");
			sb.append(" SUM(power) as power, ");
			sb.append(" SUM(used) as used ");
			sb.append(" from power_data_tbl ");
			sb.append(" where DATE(createDateTime) between DATE(?) and DATE(?)  ");
			sb.append(" group by ");
			sb.append(" DATE(createDateTime)  ");
			
			
			
			String sql = sb.toString();
			System.err.println(sql);
			list = dao.query(
					sql, new Object[] { startDate, endDate }, 
					new BeanPropertyRowMapper<Data>(Data.class));
			
			for (Data data : list) {
				data.setPowerHour(this.getPowerHour(data.getCreateDateTime()));
				data.setAvgPower(this.getAvgPowerByDate(data.getCreateDateTime()));
				data.setPowerMaxDateTime(this.getMaxPowerDateTimeByDate(data.getCreateDateTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Date getMaxPowerDateTimeByDate(Date date) {
		String sql = "select createDateTime,max(power) from power_data_tbl where DATE(createDateTime) = DATE(?)";
		try {
			Data data =  dao.queryForObject(
					sql, new Object[] { date }, 
					new BeanPropertyRowMapper<Data>(Data.class));
			
			return data.getCreateDateTime();
		} catch (Exception e) {
		}
		return null;
	}
	
	private Double getAvgPowerByDate(Date date) {
		Double avg = new Double(0);
		String sql = "select avg(power) as avgPower from power_data_tbl where DATE(createDateTime) = DATE(?)";
		try {
			avg = dao.queryForObject(
					sql, new Object[] { date }, 
					Double.class);
		} catch (Exception e) {
		}
		System.err.println("Avg:" + avg);
		return avg;
	}
	
	private int getPowerHour(Date date) {
		int power = 0;
		String sql = "select count(power) from power_data_tbl where DATE(createDateTime) = DATE(?)";
		try {
			power = dao.queryForObject(
					sql, new Object[] { date }, 
					Integer.class);
		} catch (Exception e) {
		}
		
		System.err.println("Power:" + power);
		return power;
	}
}
