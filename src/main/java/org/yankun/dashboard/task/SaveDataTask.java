package org.yankun.dashboard.task;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.model.setting.SunPowerData;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.DataService;
import org.yankun.dashboard.service.TaskService;
import org.yankun.dashboard.service.WeatherService;

@Component("saveDataTask") 
public class SaveDataTask {
	/**
	 * Logger
	 */
	protected Logger logger = LoggerFactory.getLogger(SaveDataTask.class);
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private DataService settingService;
	
	@Scheduled(cron = "0 0 * * * *")  
    public void testJob() {  
		Weather weather = weatherService.getWeather();
		if (!this.isSunExsits(weather)) {
			return;
		}
		
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		
		Double weatherRate = ((double) weatherService.getWeatherRate(weather) / 100);
		
		SunPowerData sunPowerData = settingService.getSunPowerDataByHour(hour);
		
        
        Data data = new Data();
        data.setPower(sunPowerData.getPower() * weatherRate);
        data.setUsed(this.getUsed(weather));

        taskService.saveData(data);
        logger.info("schedule save data !! ");
    }
	
	private boolean isSunExsits(Weather weather) {
		return new Date().after(weather.getSunRiseTime()) && new Date().before(weather.getSunDownTime());
	}
	
	private double getUsed(Weather weather) {
		int seconds = DateTime.now().getMinuteOfHour() * 60;
		long msStartDiff = new Date().getTime() - weather.getSunRiseTime().getTime();
		long msEndDiff = weather.getSunDownTime().getTime() - new Date().getTime();
		
		if (msStartDiff > 0 && msEndDiff < 0) {
			return (double)seconds * (3 / 60 / 60);
		}
		return 0;
	}
}
