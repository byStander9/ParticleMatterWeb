package com.hallym.getawayfrompm.recommend.pm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/pm")
public class PmController {

	private int pmDataIndex = 0;
    
	@Autowired
	PmService pmService;
	
	List<PMVo> pmvos = new ArrayList<>();
	
	// Every 8am calls api, get PmData, delete existing last day data, insert new data
	@Scheduled(cron = "0 0 8 * * ?")
	@GetMapping("/getNewPm")
	public void newTodayData() throws IOException {
		System.out.println("[PmController] newTodayData()!");
		try {
			pmService.newTodayData();
		} catch(Exception e) {
			e.printStackTrace();
		}
		pmvos = pmService.getTodayData();
		
	}
	
	// For test - Get data from db and add to List<PMVo> pmvo
	@GetMapping("/hardGetNewPm")
	public String listTodayData() {
		System.out.println("[PmController] listTodayData()");
		
		pmvos = pmService.getTodayData();
		System.out.println("pmvos: " + pmvos.get(0).getCity_name());
		return "./admin/main";
	}
	
	// Get city one by one from List<PMVo> pmvo
	@GetMapping("/getNextPmRec")
	@ResponseBody
	public PMVo getTodayData() {
		System.out.println("[PmController] getTodayData()! - " + pmDataIndex);
		System.out.println("todayData - " + pmvos.get(0).getCity_name());
		if(pmDataIndex < 3) {
			PMVo todayDataVo = pmvos.get(pmDataIndex);
			pmDataIndex++;
			return todayDataVo;
		} else {
			pmDataIndex = 0;
			return null;
		}
	}
}
