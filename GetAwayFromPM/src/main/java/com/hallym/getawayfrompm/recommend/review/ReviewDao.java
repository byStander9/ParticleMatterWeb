package com.hallym.getawayfrompm.recommend.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hallym.getawayfrompm.admin.member.AdminMemberVo;

@Component
public class ReviewDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int insertReview(ReviewVo reviewVo) {
		
		
		System.out.println("[ReviewDao] insertReview()");
			
			List<String> args = new ArrayList<String>();
			
			String sql = "INSERT INTO reviews(";
			
			sql += "userName, ";
			args.add(reviewVo.getUserName());
			
			sql += "placeName, ";
			args.add(reviewVo.getPlaceName());
			
			sql += "review) ";
			args.add(reviewVo.getReview());
			
			sql += "VALUES(?, ?, ?)";
			
			int result = -1;
			
			try {
				result = jdbcTemplate.update(sql, args.toArray());
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return result;
	}
	
	public List<ReviewVo> selectReviews() {
		
		System.out.println("[ReviewDao] selectReviews()");
		
		String sql = "SELECT * FROM reviews";
		
		List<ReviewVo> reviewVos = new ArrayList<ReviewVo>();
		
		try {
			reviewVos = jdbcTemplate.query(sql, new RowMapper<ReviewVo>() {
				@Override
				public ReviewVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReviewVo reviewVo = new ReviewVo();
					
					reviewVo.setUserName(rs.getString("userName"));
					reviewVo.setPlaceName(rs.getString("placeName"));
					reviewVo.setReview(rs.getString("review"));
					
					return reviewVo;
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return reviewVos;
	}
}
