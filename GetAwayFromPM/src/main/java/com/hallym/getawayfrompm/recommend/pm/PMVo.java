package com.hallym.getawayfrompm.recommend.pm;

import com.hallym.getawayfrompm.recommend.TopVo;

public class PMVo implements TopVo{
	
	String cityName;
	String airQuality;
	String pm10;
	String pm2_5;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAirQuality() {
		return airQuality;
	}
	public void setAirQuality(String airQuality) {
		this.airQuality = airQuality;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getPm2_5() {
		return pm2_5;
	}
	public void setPm2_5(String pm2_5) {
		this.pm2_5 = pm2_5;
	}
}
