package org.yankun.dashboard.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yankun.dashboard.model.weather.Weather;
import org.yankun.dashboard.service.WeatherService;
import org.yankun.dashboard.util.KeyUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {

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
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	    
		try {
			URIBuilder builder = new URIBuilder();
			builder.setScheme("http");
			builder.setHost("open.weather.com.cn");
			builder.setPath("/data/");
			
			builder.setParameter("areaid", areaId);
			builder.setParameter("type", weatherType);
			builder.setParameter("date", "201411091720");
			builder.setParameter("appid", appId);
			String tempURLForKey = builder.build().toString();
			String key = KeyUtils.getKey(tempURLForKey, privateKey);
			System.err.println(key);
			
			builder.setParameter("appid", appId.substring(0, 6));
			builder.setParameter("key", key);
			
			URI uri = builder.build();
			String url = builder.build().toString();
			
			System.err.println(url);
			
			String jsonString = restTemplate.getForObject(uri, String.class);
			
			System.err.println(jsonString);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode weatherJson = mapper.readTree(jsonString);
			
			String sun = weatherJson.get("f").get("f1").get(0).get("fi").asText();
			Weather weather = new Weather();
			weather.setForecastTime(sdf.parse(weatherJson.get("f").get("f0").asText()));
			weather.setWeatherType(weatherJson.get("f").get("f1").get(0).get("fa").asText());
			weather.setSunRiseTime(sdfTime.parse(sun.substring(0, sun.indexOf("|"))));
			weather.setSunDownTime(sdfTime.parse(sun.substring(sun.indexOf("|") + 1)));
			
			return weather;

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
	}

	@Override
	public int getWeatherRate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
