package org.yankun.dashboard.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("saveDataTask") 
public class SaveDataTask {
	@Scheduled(cron = "0-59 * * * * ?")  
    public void testJob() {  
        System.out.println("this is a test");  
    }
}