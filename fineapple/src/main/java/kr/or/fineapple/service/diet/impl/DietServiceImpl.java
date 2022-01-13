package kr.or.fineapple.service.diet.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
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
import kr.or.fineapple.domain.Recipe;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.common.ViewDuration;
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
				dietMapper.insertDietService(diet);
			diet=dietMapper.getDietService(diet.getUserId());
			
				
				
		return	dietMapper.updateDietServiceNo(diet);
	}

	

	@Override
	public int updateDietServiceNo(DietServ diet) throws Exception {
		return dietMapper.updateDietServiceNo(diet);
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
	public List getFoodList(Search search) throws Exception {
		
		List<Food> list = dietMapper.getFoodList(search);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("search", search);
		
		return list;
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
	public String shoppingAPI(String searchKeyword,int startNum,int endNum) throws Exception {
//		response.setHeader("Access-Control-Allow-Origin", "*");
	    String result2 = "";  
		String id = "8zcCrXfCacF47jmSOLA9";
	        String secret = "nKU9InZEfJ";
	        final String baseUrl = "https://openapi.naver.com/v1/search/shop.json?start="+startNum+"display="+endNum+"&query=";
	        
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
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/"+search.getStartNum()+"/"+search.getEndNum()+"/DESC_KOR="
						+ search.searchKeyword;
			} else {
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/"+search.getStartNum()+"/"+search.getEndNum()+"/MAKER_NAME="
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
				food.setIsAPI(1);

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
		System.out.println("++"+FoodCd.substring(0,1));
		
		if(FoodCd.substring(0,1).equals("U")) {
			
			food = dietMapper.getFood(FoodCd);
			
			
		}else {
			
		
		try {
			RestTemplate resttemplate = new RestTemplate();

			HttpHeaders header = new HttpHeaders();

			HttpEntity<?> entity = new HttpEntity<>(header);

			String baseUrl = "";
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/50/FOOD_CD="+FoodCd;

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
		}
		return food;
		
	
		
		
		
		
	}



	@Override
	public int addFavMealItem(FavMeal favMeal) throws Exception {
		return dietMapper.insertFavMealItem(favMeal);
	}



	@Override
	public List<FavMeal> getFavMealItemList(int favMealNo) throws Exception {
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
	public List<IntakeRecord> getIntakeRecordList(int dietServiceNo) throws Exception {
		return dietMapper.getIntakeRecordList(dietServiceNo);
	}



	@Override
	public int addFood(Food food) throws Exception {
		return dietMapper.addFood(food);
	}



	@Override
	public int updateFood(Food food) throws Exception {
		return dietMapper.updateFood(food);
	}



	@Override
	public int deleteFood(String foodCd) throws Exception {
		return dietMapper.deleteFood(foodCd);
	}



	@Override
	public int updateIntakeRecord(IntakeRecord record) throws Exception {
		return dietMapper.updateIntakeRecord(record);
	}



	@Override
	public void deleteIntakeRecord(int IntakeRecordNo) throws Exception {
		dietMapper.deleteIntakeRecord(IntakeRecordNo);
		
	}



	@Override
	public FavMeal getFavMealItem(int favMealInfoNo) {
		return dietMapper.getFavMealItem(favMealInfoNo);
	}



	@Override
	public JSONArray getrcpList(Search search) {
			
		
		
			Map<String, Object> map = new HashMap<String, Object>();

			JSONArray jsonArray = new JSONArray();

			try {
				RestTemplate resttemplate = new RestTemplate();

				HttpHeaders header = new HttpHeaders();

				HttpEntity<?> entity = new HttpEntity<>(header);

				String baseUrl = "";
					if(search.searchKeyword.equals("")) {
					baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/COOKRCP01/json/"+search.startNum+"/"+search.endNum+"/CHNG_DT=20000101";
					}else {
						
						baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/COOKRCP01/json/1/2/RCP_NM="+search.searchKeyword;
					}
					ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity,
							Map.class);

					map.put("statusCode", resultMap.getStatusCodeValue());
					map.put("header", resultMap.getHeaders());
					map.put("body", resultMap.getBody());

						
					LinkedHashMap im = (LinkedHashMap) resultMap.getBody().get("COOKRCP01");
					
					ArrayList is = (ArrayList) im.get("row");
					System.out.println(is);
					
					for (int i = 0; is.size() > i; i++) {
						LinkedHashMap aa = (LinkedHashMap) is.get(i);
						
					Recipe rcp = new Recipe();
					String rcpPartsDtls = aa.get("RCP_PARTS_DTLS").toString();
					String rcpCd = aa.get("RCP_SEQ").toString();
					String rcpName = aa.get("RCP_NM").toString();
					String rcpWay = aa.get("RCP_WAY2").toString();
					String rcpPat = aa.get("RCP_PAT2").toString();
					String rcpKcal = aa.get("INFO_ENG").toString(); 
					String rcpMainImg = aa.get("ATT_FILE_NO_MAIN").toString();
					String HASH_TAG = aa.get("HASH_TAG").toString();
					
					rcp.setRcpPartsDtls(rcpPartsDtls);
					rcp.setRcpCd(rcpCd);
					rcp.setRcpKcal(rcpKcal);
					rcp.setRcpMainImg(rcpMainImg);
					rcp.setRcpName(rcpName);
					rcp.setRcpWay(rcpWay);
					rcp.setRcpPat(rcpPat);
					rcp.setHASH_TAG(HASH_TAG);
					
					jsonArray.add(rcp);
					}
					
					
			}catch (Exception e) {
				map.put("statusCode", "999");
				map.put("body", "excpetion¿À·ù");
				System.out.println(e.toString());

			}
			
			return jsonArray;
		}



	@Override
	public Recipe getRcp(String rcpCd) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		JSONArray jsonArray = new JSONArray();
		Recipe rcp = new Recipe();

		try {
			RestTemplate resttemplate = new RestTemplate();

			HttpHeaders header = new HttpHeaders();

			HttpEntity<?> entity = new HttpEntity<>(header);

			String baseUrl = "";
			System.out.println("+++++++"+rcpCd);
							
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/COOKRCP01/json/1/2/RCP_NM="+rcpCd;
				System.out.println("---"+baseUrl);
				ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity,
						Map.class);

				map.put("statusCode", resultMap.getStatusCodeValue());
				map.put("header", resultMap.getHeaders());
				map.put("body", resultMap.getBody());

					
				LinkedHashMap im = (LinkedHashMap) resultMap.getBody().get("COOKRCP01");
				
				ArrayList is = (ArrayList) im.get("row");
				System.out.println(is);
				
					LinkedHashMap aa = (LinkedHashMap) is.get(0);
					
				String rcpPartsDtls = aa.get("RCP_PARTS_DTLS").toString();
				String rcpName = aa.get("RCP_NM").toString();
				String rcpWay = aa.get("RCP_WAY2").toString();
				String rcpPat = aa.get("RCP_PAT2").toString();
				String rcpKcal = aa.get("INFO_ENG").toString(); 
				String rcpMainImg = aa.get("ATT_FILE_NO_MAIN").toString();
				String HASH_TAG = aa.get("HASH_TAG").toString();
				String manual01 = aa.get("MANUAL01").toString();
				String manualImg01 = aa.get("MANUAL_IMG01").toString();
				String manual02 = aa.get("MANUAL02").toString();
				String manualImg02 = aa.get("MANUAL_IMG02").toString();
				String manual03 = aa.get("MANUAL03").toString();
				String manualImg03 = aa.get("MANUAL_IMG03").toString();
				String manual04 = aa.get("MANUAL04").toString();
				String manualImg04 = aa.get("MANUAL_IMG04").toString();
				String manual05 = aa.get("MANUAL05").toString();
				String manualImg05 = aa.get("MANUAL_IMG05").toString();
				String manual06 = aa.get("MANUAL06").toString();
				String manualImg06 = aa.get("MANUAL_IMG06").toString();
				String manual07 = aa.get("MANUAL07").toString();
				String manualImg07 = aa.get("MANUAL_IMG07").toString();
				String manual08 = aa.get("MANUAL08").toString();
				String manualImg08 = aa.get("MANUAL_IMG08").toString();
				String manual09 = aa.get("MANUAL09").toString();
				String manualImg09 = aa.get("MANUAL_IMG09").toString();
				String manual10 = aa.get("MANUAL10").toString();
				String manualImg10 = aa.get("MANUAL_IMG10").toString();
				String manual11 = aa.get("MANUAL11").toString();
				String manualImg11 = aa.get("MANUAL_IMG11").toString();
				String manual12 = aa.get("MANUAL12").toString();
				String manualImg12 = aa.get("MANUAL_IMG12").toString();
				String manual13 = aa.get("MANUAL13").toString();
				String manualImg13 = aa.get("MANUAL_IMG13").toString();
				String manual14 = aa.get("MANUAL14").toString();
				String manualImg14 = aa.get("MANUAL_IMG14").toString();
				String manual15 = aa.get("MANUAL15").toString();
				String manualImg15 = aa.get("MANUAL_IMG15").toString();
				String manual16 = aa.get("MANUAL16").toString();
				String manualImg16 = aa.get("MANUAL_IMG16").toString();
				String manual17 = aa.get("MANUAL17").toString();
				String manualImg17 = aa.get("MANUAL_IMG17").toString();
				String manual18 = aa.get("MANUAL18").toString();
				String manualImg18 = aa.get("MANUAL_IMG18").toString();
				String manual19 = aa.get("MANUAL19").toString();
				String manualImg19 = aa.get("MANUAL_IMG19").toString();
				String manual20 = aa.get("MANUAL20").toString();
				String manualImg20 = aa.get("MANUAL_IMG20").toString();
				
				
				
				
				
				rcp.setRcpPartsDtls(rcpPartsDtls);
				rcp.setRcpCd(rcpCd);
				rcp.setRcpKcal(rcpKcal);
				rcp.setRcpMainImg(rcpMainImg);
				rcp.setRcpName(rcpName);
				rcp.setRcpWay(rcpWay);
				rcp.setRcpPat(rcpPat);
				rcp.setHASH_TAG(HASH_TAG);
				rcp.setManual01(manual01);
				rcp.setManual02(manual02);
				rcp.setManual03(manual03);
				rcp.setManual04(manual04);
				rcp.setManual05(manual05);
				rcp.setManual06(manual06);
				rcp.setManual07(manual07);
				rcp.setManual08(manual08);
				rcp.setManual09(manual09);
				rcp.setManual10(manual10);
				rcp.setManual11(manual11);
				rcp.setManual12(manual12);
				rcp.setManual13(manual13);
				rcp.setManual14(manual14);
				rcp.setManual15(manual15);
				rcp.setManual16(manual16);
				rcp.setManual17(manual17);
				rcp.setManual18(manual18);
				rcp.setManual19(manual19);
				rcp.setManual20(manual20);
				rcp.setManualImg01(manualImg01);
				rcp.setManualImg02(manualImg02);
				rcp.setManualImg03(manualImg03);
				rcp.setManualImg04(manualImg04);
				rcp.setManualImg05(manualImg05);
				rcp.setManualImg06(manualImg06);
				rcp.setManualImg07(manualImg07);
				rcp.setManualImg08(manualImg08);
				rcp.setManualImg09(manualImg09);
				rcp.setManualImg10(manualImg10);
				rcp.setManualImg11(manualImg11);
				rcp.setManualImg12(manualImg12);
				rcp.setManualImg13(manualImg13);
				rcp.setManualImg14(manualImg14);
				rcp.setManualImg15(manualImg15);
				rcp.setManualImg16(manualImg16);
				rcp.setManualImg17(manualImg17);
				rcp.setManualImg18(manualImg18);
				rcp.setManualImg19(manualImg19);
				rcp.setManualImg20(manualImg20);
				
				
				
				
				
				
		}catch (Exception e) {
			map.put("statusCode", "999");
			map.put("body", "excpetion¿À·ù");
			System.out.println(e.toString());

		}
		
		return rcp;
	}
		
		
	
	
	
	
	////´ÙÀÌ¾î¸® ÁøÇàÀÚ ÇÏ¸®´Ï°¡ ÀÛ¼º: Æ¯Á¤ ÀÏÀÚÀÇ ÀÏÀÏ ½Ä´Ü Á¤º¸ Á¶È¸
	@Override
	public List<IntakeRecord> getIntakeRecordListForDiary(LocalDate date, int userServiceNo) {
		///SELECTÀ» À§ÇÑ WHERE Á¶°ÇÀ» map¿¡ ³Ö¾î Àü´Þ
		Map<String, Object> map = new HashMap<>();
		map.put("date", date);
		map.put("userServiceNo", userServiceNo);
		return dietMapper.getIntakeRecordListForDiary(map);
	}

	@Override
	public TotalRecord getTotalDietRecord(ViewDuration viewDuration) {
		return dietMapper.getTotalDietRecord(viewDuration);
	}
	
	
}
	
	
	
	
	

	

