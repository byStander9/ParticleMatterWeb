package com.hallym.getawayfrompm.recommend.pm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	
	public int deleteAllPmData() {
		
		System.out.println("[PmDao] deleteAllPmData()");
				
				String sql =  "DELETE FROM lowestcities";
				
				int result = -1;
				
				try {
					
					result = jdbcTemplate.update(sql);
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
		
		return result;
	}
	
	public List<PMVo> getTodayData() {
		System.out.println("[PmDao] getTodayData()");
		
		String sql =  "SELECT * FROM lowestcities ";
		
		List<PMVo> pmvos = null;
		try {
					
				pmvos = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
		            PMVo pmvo = new PMVo();
		            pmvo.setCity_no(resultSet.getInt("city_no"));
		            pmvo.setCity_name(resultSet.getString("city_name"));
		            pmvo.setCity_airQuality(resultSet.getString("city_airQuality"));
		            pmvo.setCity_pm10(resultSet.getString("city_pm10"));
		            pmvo.setCity_pm2_5(resultSet.getString("city_pm2_5"));
		            pmvo.setUpload_date(resultSet.getString("upload_date"));
		            return pmvo;
		        });
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
		return pmvos;
	}
}
