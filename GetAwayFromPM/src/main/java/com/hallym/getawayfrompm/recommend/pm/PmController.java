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
public class PmController {

//	gpt version code
//	private String[] infoList = {"Info 1", "Info 2", "Info 3"};
	private int pmDataIndex = 0;
    
	@Autowired
	PmService pmService;
	
	List<PMVo> pmvo = null;
	
	// Every 8am calls api, get PmData, delete existing last day data, insert new data
	@Scheduled(cron = "0 0 8 * * ?")
	public void newTodayData() throws IOException {
		System.out.println("[PmController] newTodayData()!");
		List<String> lowestCities = new ArrayList<>();
		try {
			lowestCities = pmService.newTodayData();
		} catch(Exception e) {
			e.printStackTrace();
		}
//		pmvo = pmService.getTodayData();
	}
	
//	@GetMapping("/getNewPm")
//	@ResponseBody
//	public PMVo getTodayData(Model model) {
//		System.out.println("[PmController] getTodayData()!");
//		
////		gpt version code
////		if (currentIndex < infoList.length) {
////            String nextInfo = infoList[currentIndex];
////            currentIndex++;
////            return nextInfo;
////        } else {
////            return "End of recommend. Press again";
////        }
//		
//		if(pmDataIndex < 3) {
//			PMVo todayDataVo = pmService.getTodayData(pmDataIndex);
//			return todayDataVo;
//		} else {
//			pmDataIndex = 0;
//			return null;
//		}
//	}
}
