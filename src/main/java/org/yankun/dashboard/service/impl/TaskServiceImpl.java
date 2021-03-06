package org.yankun.dashboard.service.impl;

import java.util.Date;

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
		String sql = "insert into power_data_tbl(createDateTime, power, used) "
				+ "values(?,?,?)";
		dao.update(sql, new Object[]{
				new Date(),
				data.getPower(),
				data.getUsed()});

	}

}
