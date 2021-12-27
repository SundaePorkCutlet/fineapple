package kr.or.fineapple.persistence;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class dietMapperTest {


	@Autowired
	private DietMapper dietMapper;
	 
	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;

	
//	@Test
	public void contextLoads() throws Exception {
		IntakeRecord record = new IntakeRecord();
		Food food = new Food() ;
		DietServ dietServ = new DietServ();
		
		String userId = "zzz@gmail.com";
		
		food.setFoodCarb(1.1);
		food.setFoodImg(null);
		food.setFoodIntoStt(1);
		food.setFoodKcal(313.2);
		food.setFoodName("ȫ��ȣ");
		food.setFoodNo(1);
		food.setFoodProtein(23.1);
		food.setFoodSodium(1.1);
		food.setFoodSugar(33.1);
		food.setIsAPI(0);
		food.setPrice(1002320);
		food.setPurchaseConnLink(null);
		food.setServingSize(100);
		food.setStoreName(null);
		
		
		dietServ.setBodyFat(40.1);
		dietServ.setDailyTrgtIntakeCarb(35.1);
		dietServ.setDailyTrgtIntakeFat(36.1);
		dietServ.setDailyTrgtIntakeKcal(213.2);
		dietServ.setDailyTrgtIntakeProtein(23.1);
		dietServ.setDietServiceTrgt("ü������");
		dietServ.setUserId("aaa123@naver.com");
		
		
		record.setFood(food);
		record.setUserFoodIntake(313.7);
		record.setMeal("��");
		record.setUserId("aaa123@naver.com");


//        log.info("addDietService : " + dietService.addDietService(dietServ));
//        log.info("addIntakeRecord : " + dietService.addIntakeRecord(record));
//        log.info("getDietService : " + dietService.getDietService(userId));
		  log.info("updatetrgt : " + dietMapper.updateBodyInfo(dietServ));

	}
	
//	@Test
	public void addfavmeal() throws Exception{
		FavMeal favMeal = new FavMeal();
		Food food = new Food();
		DietServ diet = new DietServ();
		food.setFoodCarb(1.1);
		food.setFoodImg(null);
		food.setFoodIntoStt(1);
		food.setFoodKcal(313.2);
		food.setFoodName("ȫ��ȣ");
		food.setFoodNo(1);
		food.setFoodProtein(23.1);
		food.setFoodSodium(1.1);
		food.setFoodSugar(33.1);
		food.setIsAPI(0);
		food.setPrice(1002320);
		food.setPurchaseConnLink(null);
		food.setServingSize(100);
		food.setStoreName(null);
		
		diet = dietService.getDietService("aaa123@naver.com");
		favMeal.setFavMealKcal(0);
		favMeal.setUserServiceNo(diet.getUserServiceNo());
		favMeal.setUserId("aaa123@naver.com");
		favMeal.setFavMealName("«������");
		
		log.info("++"+dietService.addFavMeal(favMeal));
		
		
	}
	
//	@Test
	public void updateFavMealName() throws Exception {
		FavMeal favMeal = new FavMeal();
		favMeal.setFavMealName("¥������");
		favMeal.setFavMealNo(2);
		
		log.info("++"+dietService.updateFavMealName(favMeal));
		
		
	}
	
//	@Test
	public void deleteFavMeal() throws Exception{
		
		int FavMealNo = 2;
		
		log.info("++"+dietService.deleteFavMeal(FavMealNo));
		
	}
	
//	@Test
	public void getFavMealList() throws Exception{
		
		DietServ serv = dietService.getDietService("aaa123@naver.com");
		
		int dierServiceNo = serv.getUserServiceNo();
		System.out.println(dierServiceNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dietService.getFavMealList(dierServiceNo));
		
		log.info("++"+map.get("list"));
		
		
		
	}
	
	@Test
	public void naverApi() throws Exception{
		
		Search search = new Search();
		search.setSearchKeyword("�ο�");
//		response.setHeader("Access-Control-Allow-Origin", "*");
	        String id = "8zcCrXfCacF47jmSOLA9";
	        String secret = "nKU9InZEfJ";
	        Map<String, Object> map = new HashMap<>();
	        final String baseUrl = "https://openapi.naver.com/v1/search/shop.json?query=";
	        
	        try {
	            String url1 = URLEncoder.encode(search.searchKeyword, "UTF-8");
	            
	            HttpURLConnection con = null;
	            String result2 = "";
	            try {
	                URL url2 = new URL(baseUrl+url1);
	                con = (HttpURLConnection) url2.openConnection();
	                con.setRequestMethod("GET");
	                con.setRequestProperty("X-Naver-Client-Id", id);
	                con.setRequestProperty("X-Naver-Client-Secret", secret);
	                int responseCode = con.getResponseCode();
	                if (responseCode == HttpURLConnection.HTTP_OK) {
	                	 InputStreamReader streamReader = new InputStreamReader(con.getInputStream(),"UTF-8");
	 	                try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	 	                    StringBuilder responseBody = new StringBuilder();
	 	                    String line;
	 	                    while ((line = lineReader.readLine()) != null) {
	 	                        responseBody.append(line);
	 	                    }
	 	                    result2 =  responseBody.toString();
	 	                } catch (IOException e) {
	 	                    throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
	 	                }
	 	            }
	                	
	                	
	                
	               
	                else {
	                	 InputStreamReader streamReader = new InputStreamReader(con.getErrorStream());
	 	                try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	 	                    StringBuilder responseBody = new StringBuilder();
	 	                    String line;
	 	                    while ((line = lineReader.readLine()) != null) {
	 	                        responseBody.append(line);
	 	                    }
	 	                   result2 =  responseBody.toString();
	 	                } catch (IOException e) {
	 	                    throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
	 	                }
	 	            }
	            } catch (Exception e) {
	                System.out.println("���� ���� : " + e);
	            } finally {
	                con.disconnect();
	            }
	            JSONParser parser = new JSONParser();
	            JSONObject obj = (JSONObject)parser.parse(result2);
	            System.out.println(obj);
	            JSONParser parser2 = new JSONParser();
	            Object obj2 = parser2.parse(obj.get("items").toString());
	            JSONArray array2 = (JSONArray)obj2;
	            for(int i=0; i<array2.size(); i++) {
	            	System.out.println(((JSONObject)array2.get(i)).get("lprice"));
	            }
	            
	            

	            
	            
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }

	    }
		
	}
	
	
	
	
	
	
	


