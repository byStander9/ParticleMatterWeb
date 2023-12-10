package com.hallym.getawayfrompm.recommend.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/place")
public class PlaceController {
	
	@Autowired
	PlaceService placeService;
	int placeDataIndex = 0;
	
	
	
	
	@GetMapping("/getNextPlaceRec")
	@ResponseBody
	public PlaceVo getNextData(@RequestParam("param") String parameterValue) {
		System.out.println("[PlaceController] getNextData()! - " + placeDataIndex);
		if(placeDataIndex < 3) {
			PlaceVo todayDataVo = placeService.getNextData(placeDataIndex, parameterValue);
			placeDataIndex++;
			return todayDataVo;
		} else {
			placeDataIndex = 0;
			return null;
		}
	}
}
