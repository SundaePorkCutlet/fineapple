package kr.or.fineapple.service.diet.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.DietMapper;
import kr.or.fineapple.service.diet.DietService;

@Service
public class DietServiceImpl implements DietService{

	@Autowired
	private DietMapper dietMapper;
	

	@Override
	public int addDietService(DietServ diet) throws Exception {
				dietMapper.updateServiceTrgt(diet);
				dietMapper.updateBodyInfo(diet);
		return	dietMapper.insertDietService(diet);
	}

	

	@Override
	public int updateDietService(DietServ diet) throws Exception {

		return dietMapper.updateDietService(diet);
	}



	@Override
	public DietServ getDietService(String userId) throws Exception {
		return dietMapper.getDietService(userId);
	}

	@Override
	public int addIntakeRecord(IntakeRecord record) throws Exception {

		// TODO Auto-generated method stub
		return dietMapper.insertIntakeRecord(record);
	}

	@Override
	public Map<String ,Object> getFoodList(Search search) throws Exception {
		
		List<Food> list = dietMapper.getFoodList(search);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("search", search);
		
		return map;
	}



	@Override
	public int addFavMeal(FavMeal favMeal) throws Exception {
		return dietMapper.insertFavMeal(favMeal);
	}



	@Override
	public int updateFavMealName(FavMeal favMeal) throws Exception {
		return dietMapper.updateFavMealName(favMeal);
	}



	@Override
	public int deleteFavMeal(int favMealNo) throws Exception {
		return dietMapper.deleteFavMeal(favMealNo);
	}



	@Override
	public Map<String, Object> getFavMealList(int dietServiceNo) throws Exception {
		List<FavMeal> list = dietMapper.getFavMealList(dietServiceNo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		return map;
	}



	@Override
	public String shoppingAPI(String searchKeyword) throws Exception {
//		response.setHeader("Access-Control-Allow-Origin", "*");
	    String result2 = "";  
		String id = "8zcCrXfCacF47jmSOLA9";
	        String secret = "nKU9InZEfJ";
	        final String baseUrl = "https://openapi.naver.com/v1/search/shop.json?display=50&query=";
	        
	        try {
	            String url1 = URLEncoder.encode(searchKeyword, "UTF-8");
	            
	            HttpURLConnection con = null;
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
	 	                    throw new RuntimeException("API ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ð´Âµï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½.", e);
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
	 	                    throw new RuntimeException("API ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ð´Âµï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß½ï¿½ï¿½Ï´ï¿½.", e);
	 	                }
	 	            }
	            } catch (Exception e) {
	                System.out.println("ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ : " + e);
	            } finally {
	                con.disconnect();
	            }
	            
	            

	            
	            
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }

	    
		return result2;
	}



	@Override
	public JSONArray getFoodAPIlist(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

		JSONArray jsonArray = new JSONArray();

		try {
			RestTemplate resttemplate = new RestTemplate();

			HttpHeaders header = new HttpHeaders();

			HttpEntity<?> entity = new HttpEntity<>(header);

			String baseUrl = "";
			if (search.searchCondition == 0) {
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/DESC_KOR="
						+ search.searchKeyword;
			} else {
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/MAKER_NAME="
						+ search.searchKeyword;
			}

			ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity,
					Map.class);

			map.put("statusCode", resultMap.getStatusCodeValue());
			map.put("header", resultMap.getHeaders());
			map.put("body", resultMap.getBody());


			LinkedHashMap im = (LinkedHashMap) resultMap.getBody().get("I2790");
			ArrayList is = (ArrayList) im.get("row");

			for (int i = 0; is.size() > i; i++) {
				LinkedHashMap aa = (LinkedHashMap) is.get(i);
				
				Food food = new Food();
				String foodCd = aa.get("FOOD_CD").toString();
				String makerName = aa.get("MAKER_NAME").toString();
				String name = aa.get("DESC_KOR").toString();
				String serv = aa.get("SERVING_SIZE").toString();
				if (serv == null || serv == "") {
					serv = "0.0";
				}
				String kcal = aa.get("NUTR_CONT1").toString();
				if (kcal == null || kcal == "") {
					kcal = "0.0";
				}
				String carb = aa.get("NUTR_CONT2").toString();
				if (carb == null || carb == "") {
					carb = "0.0";
				}
				String protein = aa.get("NUTR_CONT3").toString();
				if (protein == null || protein == "") {
					protein = "0.0";
				}
				String fat = aa.get("NUTR_CONT4").toString();
				if (fat == null || fat == "") {
					fat = "0.0";
				}
				String sugar = aa.get("NUTR_CONT5").toString();
				if (sugar == null || sugar == "") {
					sugar = "0.0";
				}
				String sodium = aa.get("NUTR_CONT6").toString();
				if (sodium == null || sodium == "") {
					sodium = "0.0";
				}
				String cholesterol = aa.get("NUTR_CONT7").toString();
				if (cholesterol == null || cholesterol == "") {
					cholesterol = "0.0";
				}
				String saturatedFattyAcid = aa.get("NUTR_CONT8").toString();
				if (saturatedFattyAcid == null || saturatedFattyAcid == "") {
					saturatedFattyAcid = "0.0";
				}
				String transFat = aa.get("NUTR_CONT8").toString();
				if (transFat == null || transFat == "") {
					transFat = "0.0";
				}
				
				
				food.setFoodCd(foodCd);
				food.setFoodName(name);
				food.setMakerName(makerName);
				food.setServingSize(Double.parseDouble(serv));
				food.setFoodKcal(Double.parseDouble(kcal));
				food.setFoodCarb(Double.parseDouble(carb));
				food.setFoodProtein(Double.parseDouble(protein));
				food.setFoodFat(Double.parseDouble(fat));
				food.setFoodSugar(Double.parseDouble(sugar));
				food.setFoodSodium(Double.parseDouble(sodium));
				food.setFoodCholesterol(Double.parseDouble(cholesterol));
				food.setFoodSaturatedFattyAcid(Double.parseDouble(saturatedFattyAcid));
				food.setFoodTransFat(Double.parseDouble(transFat));

				jsonArray.add(food);
				
			}

		} catch (Exception e) {
			map.put("statusCode", "999");
			map.put("body", "excpetion¿À·ù");
			System.out.println(e.toString());

		}
		
		
		 return jsonArray;
	}
	
	@Override
	public Food getFood(String FoodCd) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();

		Food food = new Food();
		JSONArray jsonArray = new JSONArray();

		try {
			RestTemplate resttemplate = new RestTemplate();

			HttpHeaders header = new HttpHeaders();

			HttpEntity<?> entity = new HttpEntity<>(header);

			String baseUrl = "";
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/FOOD_CD="+FoodCd;

			ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity,
					Map.class);

			map.put("statusCode", resultMap.getStatusCodeValue());
			map.put("header", resultMap.getHeaders());
			map.put("body", resultMap.getBody());


			LinkedHashMap im = (LinkedHashMap) resultMap.getBody().get("I2790");
			ArrayList is = (ArrayList) im.get("row");

				LinkedHashMap aa = (LinkedHashMap) is.get(0);
				
				String foodCd = aa.get("FOOD_CD").toString();
				String makerName = aa.get("MAKER_NAME").toString();
				String name = aa.get("DESC_KOR").toString();
				String serv = aa.get("SERVING_SIZE").toString();
				if (serv == null || serv == "") {
					serv = "0.0";
				}
				String kcal = aa.get("NUTR_CONT1").toString();
				if (kcal == null || kcal == "") {
					kcal = "0.0";
				}
				String carb = aa.get("NUTR_CONT2").toString();
				if (carb == null || carb == "") {
					carb = "0.0";
				}
				String protein = aa.get("NUTR_CONT3").toString();
				if (protein == null || protein == "") {
					protein = "0.0";
				}
				String fat = aa.get("NUTR_CONT4").toString();
				if (fat == null || fat == "") {
					fat = "0.0";
				}
				String sugar = aa.get("NUTR_CONT5").toString();
				if (sugar == null || sugar == "") {
					sugar = "0.0";
				}
				String sodium = aa.get("NUTR_CONT6").toString();
				if (sodium == null || sodium == "") {
					sodium = "0.0";
				}
				String cholesterol = aa.get("NUTR_CONT7").toString();
				if (cholesterol == null || cholesterol == "") {
					cholesterol = "0.0";
				}
				String saturatedFattyAcid = aa.get("NUTR_CONT8").toString();
				if (saturatedFattyAcid == null || saturatedFattyAcid == "") {
					saturatedFattyAcid = "0.0";
				}
				String transFat = aa.get("NUTR_CONT8").toString();
				if (transFat == null || transFat == "") {
					transFat = "0.0";
				}
				
				
				food.setFoodCd(foodCd);
				food.setFoodName(name);
				food.setMakerName(makerName);
				food.setServingSize(Double.parseDouble(serv));
				food.setFoodKcal(Double.parseDouble(kcal));
				food.setFoodCarb(Double.parseDouble(carb));
				food.setFoodProtein(Double.parseDouble(protein));
				food.setFoodFat(Double.parseDouble(fat));
				food.setFoodSugar(Double.parseDouble(sugar));
				food.setFoodSodium(Double.parseDouble(sodium));
				food.setFoodCholesterol(Double.parseDouble(cholesterol));
				food.setFoodSaturatedFattyAcid(Double.parseDouble(saturatedFattyAcid));
				food.setFoodTransFat(Double.parseDouble(transFat));

				

		} catch (Exception e) {
			map.put("statusCode", "999");
			map.put("body", "excpetion¿À·ù");
			System.out.println(e.toString());

		}
		
		return food;
		
	
		
		
		
		
	}



	@Override
	public int addFavMealItem(FavMeal favMeal) throws Exception {
		return dietMapper.insertFavMealItem(favMeal);
	}



	@Override
	public List getFavMealItemList(int favMealNo) throws Exception {
		return dietMapper.getFavMealItemList(favMealNo);
	}



	@Override
	public int delteFavMealItem(int favMealInfoNo) throws Exception {
		return dietMapper.deleteFavMealItem(favMealInfoNo);
	}



	@Override
	public int updateFavMealItem(FavMeal favMeal) throws Exception {
		return dietMapper.updateFavMealItem(favMeal);
	}



	@Override
	public IntakeRecord getIntakeRecordList(int dietServiceNo) throws Exception {
		return dietMapper.getIntakeRecordList(dietServiceNo);
	}
	
	
	
	
	

	
}
