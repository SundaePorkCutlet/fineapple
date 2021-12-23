package kr.or.fineapple.diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.diet.DietService;

@Controller
@RequestMapping("/diet/*")
public class DietController {
	
	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	
	
	
@GetMapping(value="addDietService")
	    public String addDietService(@RequestParam(value="menu", required=false) int menu){
	     System.out.println("addDietService");
	     System.out.println(menu);
	     
	     if(menu==0) {
	    	return "diet/addDietService.html";
	    }
	     
	     return "diet/getDietService.html";
}

@GetMapping("getFoodList")
public String getFoodList(Model model, @ModelAttribute("search") Search search) throws Exception{
	
String foodName = "핫도그";
	
Map<String,Object> map = new HashMap<String,Object>();

Map<String,String> bb = new HashMap();

JSONArray jsonArray = new JSONArray();




			try {	
RestTemplate resttemplate = new RestTemplate();

HttpHeaders header = new HttpHeaders();

HttpEntity<?> entity = new HttpEntity<>(header);

String baseUrl = "";
if(search.searchCondition==0) {
baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/DESC_KOR="+search.searchKeyword;
}else {
	baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/MAKER_NAME="+search.searchKeyword;
}

ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity, Map.class);

map.put("statusCode", resultMap.getStatusCodeValue());
map.put("header", resultMap.getHeaders());
map.put("body", resultMap.getBody()); 

ObjectMapper mapper = new ObjectMapper();

	LinkedHashMap im = (LinkedHashMap)resultMap.getBody().get("I2790");
	ArrayList is = (ArrayList)im.get("row");

	
	for(int i = 0; is.size()>i; i++) {
	LinkedHashMap aa = (LinkedHashMap)is.get(i);
//JSONObject jsonObject = new JSONObject();	   
	Food food = new Food();
	
	String makerName = aa.get("MAKER_NAME").toString();	
	String name = aa.get("DESC_KOR").toString();
	String serv = aa.get("SERVING_SIZE").toString();
	if(serv==null || serv=="") { serv="0.0";}
	String kcal = aa.get("NUTR_CONT1").toString();
	if(kcal==null || kcal=="") { kcal="0.0";}
	String carb = aa.get("NUTR_CONT2").toString();
	if(carb==null || carb=="") { carb="0.0";}
	String protein = aa.get("NUTR_CONT3").toString();
	if(protein==null || protein=="") { protein="0.0";}
	String fat = aa.get("NUTR_CONT4").toString();
	if(fat==null || fat=="") { fat="0.0";}

	
	food.setFoodName(name);
	food.setMakerName(makerName);
	food.setServingSize(Double.parseDouble(serv));
	food.setFoodKcal(Double.parseDouble(kcal));
	food.setFoodCarb(Double.parseDouble(carb));
	food.setFoodProtein(Double.parseDouble(protein));
	food.setFoodFat(Double.parseDouble(fat));

//	jsonObject.put("foodName", aa.get("DESC_KOR").toString());
//	jsonObject.put("servingSize",aa.get("SERVING_SIZE").toString());
//	jsonObject.put("foodKcal", aa.get("NUTR_CONT1").toString());
//	jsonObject.put("foodCarb", aa.get("NUTR_CONT2").toString());
//	jsonObject.put("foodProtein", aa.get("NUTR_CONT3").toString());
//	jsonObject.put("foodFat", aa.get("NUTR_CONT4").toString());
	jsonArray.add(food);
	}


	

}catch(Exception e){
  map.put("statusCode", "999");
  map.put("body"  , "excpetion오류");
   System.out.println(e.toString());

}
	
	
	
	
	
	
	Search search1 = new Search();
	search1.setCurrentPage(1);
	search1.setPageSize(30);
	search1.setSearchCondition(0);
	search1.setSearchKeyword(search.searchKeyword);
	
	Map<String, Object> map2 = new HashMap<String,Object>();
	Map<String, Object> map3 = new HashMap<String,Object>();
	Map<String, Object> map4 = new HashMap<String,Object>();
	
	map2=dietService.getFoodList(search1);
	map3.put("list", jsonArray);
	map4.putAll(map2);
	map4.putAll(map3);
	model.addAttribute("list",map4.get("list"));
	model.addAttribute("search",search);
	
	
	
	
	return "diet/getFoodList.html";
}



@GetMapping("getPurchaseFoodList")
		public String PostFoodList(@ModelAttribute("search") Search search,
									Model model) throws Exception{
		System.out.println("getPurchaseFoodList");
	
		Map<String,Object> map = new HashMap<String,Object>();

		Map<String,String> bb = new HashMap();

		JSONArray jsonArray = new JSONArray();




					try {	
		RestTemplate resttemplate = new RestTemplate();

		HttpHeaders header = new HttpHeaders();

		HttpEntity<?> entity = new HttpEntity<>(header);
		
		String baseUrl = "https://openapi.naver.com/v1/search/shop.json?query=";
				
				
		ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity, Map.class);

		map.put("statusCode", resultMap.getStatusCodeValue());
		map.put("header", resultMap.getHeaders());
		map.put("body", resultMap.getBody()); 

		ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap im = (LinkedHashMap)resultMap.getBody().get("I2790");
			ArrayList is = (ArrayList)im.get("row");
		
		
		
		
					}catch(Exception e){
						  map.put("statusCode", "999");
						  map.put("body"  , "excpetion오류");
						   System.out.println(e.toString());

						}
		
		return "diet/getPurchaseFoodList.html";
		
}


@RequestMapping(value="addDailyIntakeMeal")
		public String addDailyIntakeMeal() {
		System.out.println("addDailyIntakeMeal");
		
		return "diet/addDailyIntakeMeal.html";
	
}





		}
		







