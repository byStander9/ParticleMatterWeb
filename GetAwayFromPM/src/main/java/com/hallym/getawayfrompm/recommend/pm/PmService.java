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
import org.springframework.stereotype.Service;

@Service
public class PmService {

	public List<String> getTodayData() throws IOException {
		System.out.println("[PmService] getTodayData()!");
		List<String> lowestCities = new ArrayList<>();
		try {
			HashMap<String, Integer> citiesResult = new HashMap<String, Integer>();
			// "서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "세종"
			final String citiesName[] = {"서울", "부산", "대구"};
			
			for(String str : citiesName) {
				System.out.print(str + " ");
				String cityName = str;
				int pm10ValE = 0;
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
			        
			        pm10ValE = 0;
			        for(int i = 0; i < responseBodyItems.size(); i++) {
			        	JSONObject tmpItem = (JSONObject)responseBodyItems.get(i);
			        	System.out.print((String)tmpItem.get("pm10Value") + " ");
			        	if(tmpItem.get("pm10Value").equals("-") || (tmpItem.get("pm10Value")).equals(null)) {
			        		errorPassedNum++;
			        		continue;
			        	}
			        		
			        	pm10ValE += Integer.parseInt((String)tmpItem.get("pm10Value"));		        		
			        }
			        pm10ValE = pm10ValE/(responseBodyItems.size()-errorPassedNum);
			        System.out.println("\n" + pm10ValE);
				} catch(Exception e) {
					e.printStackTrace();
				}
				citiesResult.put(cityName, pm10ValE);
			}
			System.out.println(citiesResult);
			List<String> keySet = new ArrayList<>(citiesResult.keySet());
			keySet.sort((o1, o2) -> citiesResult.get(o1).compareTo(citiesResult.get(o2)));

			// pm10수치가 가장 낮은 도시 3개
			lowestCities.add(keySet.get(0));
			lowestCities.add(keySet.get(1));
			lowestCities.add(keySet.get(2));
	        System.out.println(citiesResult.get(keySet.get(0)) + " : " + keySet.get(0));
	        System.out.println(citiesResult.get(keySet.get(1)) + " : " + keySet.get(1));
	        System.out.println(citiesResult.get(keySet.get(2)) + " : " + keySet.get(2));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lowestCities;
	}
	
	public static void main(String args[]) throws IOException{
		
		
	}
}
