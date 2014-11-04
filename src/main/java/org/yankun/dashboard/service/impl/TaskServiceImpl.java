package org.yankun.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yankun.dashboard.dao.BaseDao;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private BaseDao dao;
	
	@Override
	public void saveData(Data data) {
		String sql = "insert into power_data_tbl(powerTime, power, saveC, saveCO2, income, saveIncome, restIncome) "
				+ "values(?,?,?,?,?,?,?)";
		dao.update(sql, new Object[]{
				data.getPowerTime(),
				data.getPower(),
				data.getSaveC(),
				data.getSaveCO2(),
				data.getIncome(),
				data.getSaveIncome(),
				data.getRestIncome()});

	}

}
