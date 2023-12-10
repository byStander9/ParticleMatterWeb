package com.hallym.getawayfrompm.recommend.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PlaceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<PlaceVo> getTodayData(String parameterValue) {
		System.out.println("[PlaceDao] getTodayData()");
			
		String sql =  "SELECT * FROM fun_places where city_name = ?";
				
			List<PlaceVo> placeVos = null;
			try {
						
				placeVos = jdbcTemplate.query(sql, new Object[]{parameterValue}, (resultSet, rowNum) -> {
		            PlaceVo placevo = new PlaceVo();
		            placevo.setPlace_no(resultSet.getInt("place_no"));
		            placevo.setCity_name(resultSet.getString("city_name"));
		            placevo.setPlace_name(resultSet.getString("place_name"));
		            placevo.setPlace_link(resultSet.getString("place_link"));
		            placevo.setPlace_roadAddress(resultSet.getString("place_roadAddress"));
		            placevo.setPlace_img(resultSet.getString("place_img"));
		            return placevo;
		        });
					
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
			return placeVos;
	}
	
}
