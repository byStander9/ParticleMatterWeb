package com.hallym.getawayfrompm.recommend.pm;

import org.springframework.stereotype.Component;

@Component
public class PMVo{
	
	int city_no;
	String city_name;
	String city_airQuality;
	String city_pm10;
	String city_pm2_5;
	String upload_date;
	
	public PMVo() {
		
	}
	public int getCity_no() {
		return city_no;
	}
	public void setCity_no(int city_no) {
		this.city_no = city_no;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_airQuality() {
		return city_airQuality;
	}
	public void setCity_airQuality(String city_airQuality) {
		this.city_airQuality = city_airQuality;
	}
	public String getCity_pm10() {
		return city_pm10;
	}
	public void setCity_pm10(String city_pm10) {
		this.city_pm10 = city_pm10;
	}
	public String getCity_pm2_5() {
		return city_pm2_5;
	}
	public void setCity_pm2_5(String city_pm2_5) {
		this.city_pm2_5 = city_pm2_5;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	
	
}
