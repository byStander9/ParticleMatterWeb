package com.hallym.getawayfrompm.recommend.pm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PmDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public int insertPmData(PMVo pmVo) {
		System.out.println("[PmDao] insertPmData()");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "INSERT INTO lowestcities(";
//		if(adminMemberVo.getA_m_id().equals("super admin")) {
//			sql += "a_m_approval, ";
//			args.add("1");
//		}
		
		sql += "city_name, ";
		args.add(pmVo.getCity_name());
		
		sql += "city_airQuality, ";
		args.add(pmVo.getCity_airQuality());
		
		sql += "city_pm10, ";
		args.add(pmVo.getCity_pm10());
		
		sql += "city_pm2_5, ";
		args.add(pmVo.getCity_pm2_5());
		
		sql += "upload_date) ";

		
		sql += "VALUES(?, ?, ?, ?, NOW())";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, args.toArray());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public PMVo getTodayData() {
		System.out.println("[PmDao] getTodayData()");
		
		
		
		return null;
	}
}
