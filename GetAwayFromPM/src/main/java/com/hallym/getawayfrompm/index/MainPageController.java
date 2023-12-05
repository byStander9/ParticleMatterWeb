package com.hallym.getawayfrompm.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

	@GetMapping("/main")
	public String mainPage() {
		
		System.out.println("[MainPageController] mainPage Out");
		String nextPage = "admin/main";
		
		return nextPage;
	}
}
