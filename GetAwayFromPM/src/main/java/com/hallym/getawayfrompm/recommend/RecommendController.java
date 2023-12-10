package com.hallym.getawayfrompm.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class RecommendController {

	@Autowired
	RecommendService recommendService;
	
	@GetMapping("/admin")
	public String mainPage() {
		
		System.out.println("[MainPageController] mainPage Out");
		String nextPage = "admin/main";
		
		return nextPage;
	}
	
}
