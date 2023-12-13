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
	int placeDataIndex;
	
	public int getTodayData(String parameterValue) {
		System.out.println("[PmService] getTodayData()");
		
		placevos = placeDao.getTodayData(parameterValue);
		
		return 1;
	}
	
	public PlaceVo getNextData(String parameterValue) {
		
		PlaceVo placevo = new PlaceVo();
		if(placevos.size() == 0 || !placevos.get(0).getCity_name().equals(parameterValue)) {
			placeDataIndex = 0;
			getTodayData(parameterValue);
		}
		
		if(placeDataIndex < placevos.size()) {
			placevo = placevos.get(placeDataIndex);
			placeDataIndex++;
			return placevo;
		} else {
			placeDataIndex = 0;
			return null;
		}
		
		
	}
	
}
