package com.hallym.getawayfrompm.recommend.place;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

	@Autowired
	PlaceDao placeDao;
	
	List<PlaceVo> placevos = new ArrayList<>();
	
	public int getTodayData(String parameterValue) {
		System.out.println("[PmService] getTodayData()");
		
		placevos = placeDao.getTodayData(parameterValue);
		
		return 1;
	}
	
	public PlaceVo getNextData(int placeDataIndex, String parameterValue) {
		if(placevos.size() == 0 || placevos.get(0).getCity_name() != parameterValue) {
			getTodayData(parameterValue);
		}
		
		return placevos.get(placeDataIndex);
	}
	
}
