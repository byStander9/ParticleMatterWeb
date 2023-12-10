package com.hallym.getawayfrompm.recommend.pm;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmService {

	@Autowired
	PmDao pmDao;
	
	@Autowired
	PMVo pmVo;
	
	List<PMVo> pmvos = new ArrayList<>();
	
	public void newTodayData() throws IOException {
		System.out.println("[PmService] getTodayData()!");
		List<String> lowestCities = new ArrayList<>();
		try {
			// not needed
			HashMap<String, Integer> cityResult;
			HashMap<String, HashMap<String, Integer>> citiesResult = new HashMap<String, HashMap<String, Integer>>();
			int khaiValE = 0;
			int pm10ValE = 0;
			int pm25ValE = 0;
			// "서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "세종"
			final String citiesName[] = {"서울"};
			
			for(String cityName : citiesName) {
				System.out.print(cityName + " ");
				khaiValE = 0;
				pm10ValE = 0;
				pm25ValE = 0;
				cityResult  = new HashMap<String, Integer>();
				int errorPassedNum = 0;
				try {
					String result = "";
					
					StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
			        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=oFRpPSnvjdJY9T81ZSVVRT0GXjX8P3u8Y3pl82Ic3GLriKfVT4XR3lfIuFD0P1tp8SzCuItLZ2Ge325MsEqGNw%3D%3D"); /*Service Key*/
			        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
			        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*한 페이지 결과 수*/
			        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(cityName, "UTF-8")); /*시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
			        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /*버전별 상세 결과 참고*/
			        URL url = new URL(urlBuilder.toString());
			        
			        BufferedReader bf;
			        
			        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			        result = bf.readLine();
			        
			        JSONParser jsonParser = new JSONParser();
			        JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
			        JSONObject response = (JSONObject)jsonObject.get("response");
			        JSONObject responseBody = (JSONObject)response.get("body");
			        JSONArray responseBodyItems = (JSONArray)responseBody.get("items");
			        
			        
			        // Getting "cityName", "khaiValue", "pm10Value", "pm25Value"
			        khaiValE = 0;
			        pm10ValE = 0;
			        pm25ValE = 0;
			        for(int i = 0; i < responseBodyItems.size(); i++) {
			        	JSONObject tmpItem = (JSONObject)responseBodyItems.get(i);
			        	System.out.print((String)tmpItem.get("pm10Value") + " " + (String)tmpItem.get("pm25Value") + " " + (String)tmpItem.get("khaiValue") + "/ ");
			        	if(tmpItem.get("pm10Value").equals("-") || (tmpItem.get("pm10Value")).equals(null) || tmpItem.get("pm25Value").equals("-") || (tmpItem.get("pm25Value")).equals(null) 
			        			|| tmpItem.get("pm25Value").equals("-") || (tmpItem.get("pm25Value")).equals(null)) {
			        		errorPassedNum++;
			        		continue;
			        	}
			        	khaiValE += Integer.parseInt((String)tmpItem.get("khaiValue"));
			        	pm10ValE += Integer.parseInt((String)tmpItem.get("pm10Value"));	
			        	pm25ValE += Integer.parseInt((String)tmpItem.get("pm25Value"));	
			        }
			        khaiValE = khaiValE/(responseBodyItems.size()-errorPassedNum);
			        pm10ValE = pm10ValE/(responseBodyItems.size()-errorPassedNum);
			        pm25ValE = pm25ValE/(responseBodyItems.size()-errorPassedNum);
			        System.out.println("\n khaiValE: " + khaiValE + " pm10ValE: " + pm10ValE + " pm25ValE " + pm25ValE);
				} catch(Exception e) {
					e.printStackTrace();
				}
				cityResult.put("khaiValE", khaiValE);
				cityResult.put("pm10ValE", pm10ValE);
				cityResult.put("pm25ValE", pm25ValE);
				
				citiesResult.put(cityName, cityResult);
			}
			System.out.println(citiesResult);
			List<String> keySet = new ArrayList<>(citiesResult.keySet());
			keySet.sort((o1, o2) -> citiesResult.get(o1).get("khaiValE").compareTo(citiesResult.get(o2).get("khaiValE")));

			// pm10수치가 가장 낮은 도시 3개
			lowestCities.add(keySet.get(0));
			lowestCities.add(keySet.get(1));
			lowestCities.add(keySet.get(2));
	        System.out.println(citiesResult.get(keySet.get(0)) + " : " + keySet.get(0));
	        System.out.println(citiesResult.get(keySet.get(1)) + " : " + keySet.get(1));
	        System.out.println(citiesResult.get(keySet.get(2)) + " : " + keySet.get(2));
	        
	        int deleteResult = pmDao.deleteAllPmData();
	        System.out.println("Deleted all PmData status: " + deleteResult);
	        for(int i = 0; i < keySet.size(); i++) {
	        	System.out.println("insert" + i + " ");
	        	pmVo.setCity_name(keySet.get(i));
	        	pmVo.setCity_airQuality(Integer.toString(citiesResult.get(keySet.get(i)).get("khaiValE")));
	        	pmVo.setCity_pm10(Integer.toString(citiesResult.get(keySet.get(i)).get("pm10ValE")));
	        	pmVo.setCity_pm2_5(Integer.toString(citiesResult.get(keySet.get(i)).get("pm25ValE")));
	        	
	        	int result = pmDao.insertPmData(pmVo);
	        	System.out.println("result: " + result);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public int getTodayData() {
		System.out.println("[PmService] getTodayData()");
		
		pmvos = pmDao.getTodayData();
		
		return 1;
	}
	
	public PMVo getNextData(int pmDataIndex) {
		return pmvos.get(pmDataIndex);
	}
}
