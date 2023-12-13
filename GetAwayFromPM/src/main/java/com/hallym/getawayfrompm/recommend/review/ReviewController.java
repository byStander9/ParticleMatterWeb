package com.hallym.getawayfrompm.recommend.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
    @PostMapping("/saveText")
    @ResponseBody
    public String saveText(@RequestParam("review") String review, @RequestParam("placeName") String placeName, @RequestParam("userName") String userName) {
        System.out.println("저장된 텍스트: " + review + " 도시명: " + placeName + "유저명: " + userName);
        
        ReviewVo reviewVo = new ReviewVo();
        reviewVo.setUserName(userName);
        reviewVo.setPlaceName(placeName);
        reviewVo.setReview(review);
        reviewService.insertReview(reviewVo);
        
        return "Review Saved!";
    }
    
    @GetMapping("/reviewPage")
    public String reviewPage(Model model) {
    	System.out.println("[ReviewController] reviewPage()");
    	String nextPage = "admin/reviewPage";
		
		List<ReviewVo> reviewVos = reviewService.listupReview();
		
		model.addAttribute("reviewVos", reviewVos);
		
    	return nextPage;
    }
}
