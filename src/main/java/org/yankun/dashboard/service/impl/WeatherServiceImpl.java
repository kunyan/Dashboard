package org.yankun.dashboard.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yankun.dashboard.dao.BaseDao;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.WeatherService;
import org.yankun.dashboard.util.KeyUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {
	/**
	 * Logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Autowired
	private BaseDao dao;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weather.appId}")
	private String appId;

	@Value("${weather.privateKey}")
	private String privateKey;

	@Value("${weather.areaId}")
	private String areaId;

	@Value("${weather.type}")
	private String weatherType;

	public Weather getWeather() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	    
		try {
			URIBuilder builder = new URIBuilder();
			builder.setScheme("http");
			builder.setHost("open.weather.com.cn");
			builder.setPath("/data/");
			
			builder.setParameter("areaid", areaId);
			builder.setParameter("type", weatherType);
			builder.setParameter("date", sdf.format(new Date()));
			builder.setParameter("appid", appId);
			String tempURLForKey = builder.build().toString();
			String key = KeyUtils.getKey(tempURLForKey, privateKey);
			
			builder.setParameter("appid", appId.substring(0, 6));
			builder.setParameter("key", key);
			
			URI uri = builder.build();
			
			String jsonString = restTemplate.getForObject(uri, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode weatherJson = mapper.readTree(jsonString);
			
			String sun = weatherJson.get("f").get("f1").get(0).get("fi").asText();
			
			Weather weather = new Weather();
			String sunRiseStr =  sun.substring(0, sun.indexOf("|"));
			String sunDownStr =  sun.substring(sun.indexOf("|") + 1);

			
			Calendar sunRiseCal = Calendar.getInstance();
			Calendar sunDownCal = Calendar.getInstance();
			
			sunRiseCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sunRiseStr.substring(0, sunRiseStr.indexOf(":"))));
			sunRiseCal.set(Calendar.MINUTE, Integer.parseInt(sunRiseStr.substring(sunRiseStr.indexOf(":") + 1)));
			sunRiseCal.set(Calendar.SECOND, 0);
			
			sunDownCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sunDownStr.substring(0, sunDownStr.indexOf(":"))));
			sunDownCal.set(Calendar.MINUTE, Integer.parseInt(sunDownStr.substring(sunDownStr.indexOf(":") + 1)));
			sunDownCal.set(Calendar.SECOND, 0);

			weather.setForecastTime(sdf.parse(weatherJson.get("f").get("f0").asText()));
			String dayWeatherType = weatherJson.get("f").get("f1").get(0).get("fa").asText();
			String nightWeatherType = weatherJson.get("f").get("f1").get(0).get("fb").asText();
			
			String weatherType = dayWeatherType != null && dayWeatherType.trim().length() != 0 ? dayWeatherType : nightWeatherType;
			weather.setWeatherType(weatherType);
			weather.setSunRiseTime(sunRiseCal.getTime());
			weather.setSunDownTime(sunDownCal.getTime());
			
			return weather;

		} catch (URISyntaxException e) {
			logger.error("Weather URI Error", e);
		} catch (JsonParseException e) {
			logger.error("Weather Json Error", e);
		} catch (JsonMappingException e) {
			logger.error("Weather Json Error", e);
		} catch (IOException e) {
			logger.error("Weather IO Error", e);
		} catch (ParseException e) {
			logger.error("Weather Date Parse Error", e);
		}
	    
		return null;
	}

	@Override
	public int getWeatherRate(Weather weather) {
		logger.info("Weather Type:" + weather.getWeatherType());
		String sql = "select rate from setting_weather_tbl where id = ? ";
		try {
			return dao.queryForObject(sql, Integer.class, weather.getWeatherType());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Can not find weather in database", e);
		}
		return 100;
	
	}
}
