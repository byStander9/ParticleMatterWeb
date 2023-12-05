package com.hallym.getawayfrompm.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	RecommendService recommendService;
	
	
	@GetMapping("/getNewRecommend")
	public String getNewRecommend(Model model) {
		System.out.println("[RecommendController] getNewRecommend!");
		
		String nextPage = "admin/main";
		
		recommendService.getNewRecommend();
		
		model.addAttribute("name", "Hello World");
		return nextPage;
	}
}
