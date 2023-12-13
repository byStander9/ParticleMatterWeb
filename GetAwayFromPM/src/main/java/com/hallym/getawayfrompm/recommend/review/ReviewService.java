package com.hallym.getawayfrompm.recommend.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	public int insertReview(ReviewVo reviewVo) {
		
		reviewDao.insertReview(reviewVo);
		
		return 1;
	}
	
	public List<ReviewVo> listupReview() {
		
		
		return reviewDao.selectReviews();
	}
}
