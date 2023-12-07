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
	
//	public PMVo getTodayData(int pmDataIndex) {
//		System.out.println("[PmDao] getTodayData()");
//		
//		String sql =  "SELECT * FROM lowestcities "
//				+ "WHERE city_no LIKE ? ";
//		
//		PMVo pmvo = null;
//		try {
//					
//					pmvo = jdbcTemplate.query(sql, new RowMapper<PMVo>() {
//		
//						@Override
//						public PMVo mapRow(ResultSet rs, int rowNum) throws SQLException {
//							
//							PMVo bookVo = new PMVo();
//							
//							PMVo.setCity_name(rs.getString("b_no"));
//							bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
//							bookVo.setB_name(rs.getString("b_name"));
//							bookVo.setB_author(rs.getString("b_author"));
//							bookVo.setB_publisher(rs.getString("b_publisher"));
//							bookVo.setB_publish_year(rs.getString("b_publish_year"));
//							bookVo.setB_isbn(rs.getString("b_isbn"));
//							bookVo.setB_call_number(rs.getString("b_call_number"));
//							bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
//							bookVo.setB_reg_date(rs.getString("b_reg_date"));
//							bookVo.setB_mod_date(rs.getString("b_mod_date"));
//							
//							return bookVo;
//							
//						}
//						
//					}, "%" + bookVo.getB_name() + "%");
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//					
//				}
//		
//		return null;
//	}
}
