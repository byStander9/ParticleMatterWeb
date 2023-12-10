package com.hallym.getawayfrompm.recommend.pm;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.PostConstruct;


@Controller
@RequestMapping("/pm")
public class PmController {

	private int pmDataIndex = 0;
    
	@Autowired
	PmService pmService;
	
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
		int result = pmService.getTodayData();
		
	}
	
	// For test - Get data from db and add to List<PMVo> pmvo
	@GetMapping("/hardGetNewPm")
	public String listTodayData() {
		System.out.println("[PmController] listTodayData()");
		
		int result = pmService.getTodayData();
		return "admin/main";
	}
	
	// 서버가 실행되고 의존성 주입이 완료된 후 즉시 실행
	@PostConstruct
	public void init() {
		System.out.println("[PmController] listTodayData()");
//		System.out.println("[PmController] newTodayData()!");
//		try {
//			pmService.newTodayData();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		int result = pmService.getTodayData();
	}
	
	
	// Get city one by one from List<PMVo> pmvo
	@GetMapping("/getNextPmRec")
	@ResponseBody
	public PMVo getNextData() {
		System.out.println("[PmController] getTodayData()! - " + pmDataIndex);
		if(pmDataIndex < 3) {
			PMVo todayDataVo = pmService.getNextData(pmDataIndex);
			pmDataIndex++;
			return todayDataVo;
		} else {
			pmDataIndex = 0;
			return null;
		}
	}
}
