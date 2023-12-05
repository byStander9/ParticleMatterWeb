package com.hallym.getawayfrompm.recommend.pm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/newPm")
@Controller
public class PmController {

	@Autowired
	PmService pmService;
	
	@GetMapping("/getNewPm")
	public String getTodayData(Model model) throws IOException {
		System.out.println("[PmController] getTodayData()!");
		List<String> lowestCities = new ArrayList<>();
		try {
			lowestCities = pmService.getTodayData();
		} catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("lowestCities", lowestCities);
		return "/admin/main";
	}
}
