package org.yankun.dashboard.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.SettingService;
import org.yankun.dashboard.service.TaskService;
import org.yankun.dashboard.service.WeatherService;

@Component("saveDataTask") 
public class SaveDataTask {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private SettingService settingService;
	
	@Scheduled(cron = "0 0 0-23 * * ?")  
    public void testJob() {  
		Weather weather = weatherService.getWeather();
		
		if (!this.isSunExsits(weather)) {
			return;
		}
		
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		
		Double weatherRate = (double) (settingService.getWeatherRate(weather) / 100);
		
		PowerDefault powerDefault = settingService.getPowerDefaultByHour(hour);
		
        System.out.println("save data !!");
        
        System.err.println(this.isSunExsits(weather));
        Data data = new Data();
        double power = powerDefault.getPower() * weatherRate;
        data.setPowerTime(new Date());
        data.setPower(power);
        data.setSaveC(power * 0.4);
        data.setSaveCO2(power * 0.96);
        data.setIncome(15);
        data.setSaveIncome(16);
        data.setRestIncome(17);
        
        taskService.saveData(data);
    }
	
	public boolean isSunExsits(Weather weather) {
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		String nowTime = sdfTime.format(new Date());
		
		try {
			return (sdfTime.parse(nowTime).getTime() > weather.getSunRiseTime().getTime())
					&& (sdfTime.parse(nowTime).getTime() < weather.getSunDownTime().getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;

	}
}
