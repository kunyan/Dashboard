package org.yankun.dashboard.service;

import java.util.Date;
import java.util.List;

import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.model.setting.SunPowerData;

public interface DataService {
	SunPowerData getSunPowerDataByHour(int hour);
	List<SunPowerData> getSunPowerDataList();
	void updateSunPowerData(SunPowerData sunPowerData);
	Data getSumDataByDatePeriod(Date startDate, Date endDate);
	List<Data> getDataListByDatePeriod(Date startDate, Date endDate);
}
