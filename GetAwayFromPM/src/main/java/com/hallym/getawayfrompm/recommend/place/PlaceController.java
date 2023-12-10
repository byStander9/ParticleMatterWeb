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
	
	
	
	
	@GetMapping("/getNextPlaceRec")
	@ResponseBody
	public PlaceVo getNextData(@RequestParam("param") String parameterValue) {
		
		if(parameterValue == null) {
			return null;
		}
		PlaceVo todayDataVo = placeService.getNextData(parameterValue);
		
		
		return todayDataVo;
	}
}
