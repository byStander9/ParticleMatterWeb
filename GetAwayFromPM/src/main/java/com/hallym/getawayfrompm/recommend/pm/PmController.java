package com.hallym.getawayfrompm.recommend.pm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/newPm")
@Controller
public class PmController {

	@Autowired
	PmService pmService;
	
	public String newTodayData() throws IOException {
		System.out.println("[PmController] newTodayData()!");
		List<String> lowestCities = new ArrayList<>();
		try {
			lowestCities = pmService.newTodayData();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/admin/main";
	}
	
	@PostMapping("/getNewPm")
	public String getTodayData(@RequestParam int reccomNum, Model model) {
		System.out.println("[PmController] getTodayData()! reccomNum: " + reccomNum);
		
		String nextPage = "/admin/main";
		
//		PMVo todayDataVo = pmService.getTodayData();
		
		model.addAttribute("updatedReccomNum", reccomNum+1);
		return nextPage;
		
	}
}
