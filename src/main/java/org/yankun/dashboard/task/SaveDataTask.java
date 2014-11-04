package org.yankun.dashboard.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.service.TaskService;

@Component("saveDataTask") 
public class SaveDataTask {
	
	@Autowired
	private TaskService taskService;
	
	@Scheduled(cron = "0 0-59 * * * ?")  
    public void testJob() {  
        System.out.println("save data !!");
        Data data = new Data();
        data.setPowerTime(new Date());
        data.setPower(12);
        data.setSaveC(13);
        data.setSaveCO2(14);
        data.setIncome(15);
        data.setSaveIncome(16);
        data.setRestIncome(17);
        
        taskService.saveData(data);
    }
}
