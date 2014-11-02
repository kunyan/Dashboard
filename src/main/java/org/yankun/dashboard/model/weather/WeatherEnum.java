package org.yankun.dashboard.model.weather;

public enum WeatherEnum {
	SUNNY("00","晴", "Sunny"),
	CLOUDY("01","多云", "Cloudy"),
	OVERCAST("02","阴", "Overcast"),
	SHOWER("03","阵雨", "Shower"),
	THUNDERSHOWER("04","雷阵雨", "Thundershower"),
	THUNDERSHOWERWITHHAIL("05","雷阵雨伴有冰雹", "Thundershower with hail"),
	SLEET("06","雨夹雪", "Sleet"),
	LIGHTRAIN("07","小雨", "Light rain"),
	MODERATERAIN("08","中雨", "Moderate rain"),
	HEAVYRAIN("09","大雨", "Heavy rain"),
	STORM("10","暴雨", "Storm"),
	HEAVYSTORM("11","大暴雨", "Heavy storm"),
	SEVERESTORM("12","特大暴雨", "Severe storm"),
	SNOWFLURRY("13","阵雪", "Snow flurry"),
	LIGHTSNOW("14","小雪", "Light snow"),
	MODERATESNOW("15","中雪", "Moderate snow"),
	HEAVYSNOW("16","大雪", "Heavy snow"),
	SNOWSTORM("17","暴雪", "Snowstorm"),
	FOGGY("18","雾", "Foggy"),
	ICERAIN("19","冻雨", "Ice rain"),
	DUSTSTORM("20","沙尘暴", "Duststorm"),
	LIGHTTOMODERATERAIN("21","小到中雨", "Light to moderate rain"),
	MODERATETOHEAVYRAIN("22","中到大雨", "Moderate to heavy rain"),
	HEAVYRAINTOSTORM("23","大到暴雨", "Heavy rain to storm"),
	STORMTOHEAVYSTORM("24","暴雨到大暴雨", "Storm to heavy storm"),
	HEAVYTOSEVERESTORM("25","大暴雨到特大暴雨", "Heavy to severe storm"),
	LIGHTTOMODERATESNOW("26","小到中雪", "Light to moderate snow"),
	MODERATETOHEAVYSNOW("27","中到大雪", "Moderate to heavy snow"),
	HEAVYSNOWTOSNOWSTORM("28","大到暴雪", "Heavy snow to snowstorm"),
	DUST("29","浮尘", "Dust"),
	SAND("30","扬沙", "Sand"),
	SANDSTORM("31","强沙尘暴", "Sandstorm"),
	HAZE("53","霾", "Haze"),
	UNKNOWN("99","无", "Unknown");
	
	public final String id;
	public final String chinese;
	public final String english;
	
	private WeatherEnum(String id, String chinese, String english) {
		this.id = id;
		this.chinese = chinese;
		this.english = english;
	}
	
}
