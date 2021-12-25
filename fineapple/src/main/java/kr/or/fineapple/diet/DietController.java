package kr.or.fineapple.diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/diet/*")
public class DietController {

	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@GetMapping("addDietService")
	public String addDietServiceget(HttpServletRequest request,Model model) throws Exception {
		
		System.out.println("get:addDietService");
		
		if((User)request.getSession(true).getAttribute("user")!=null) {
			User user =(User)request.getSession(true).getAttribute("user");
			System.out.println(user);
		
			DietServ serv = dietService.getDietService(user.getUserId());
			System.out.println(serv);
			if(serv!=null) {
					if(serv.getUserServiceNo()!=0) {
			
						model.addAttribute("user",user);
						model.addAttribute("dietServ",serv);
						return "diet/getDietService.html";
						}else {
							return "diet/addDietService.html";}
			}else {
				model.addAttribute("user",user);
				return "diet/addDietService.html";}
			
		}else {
			
			return "../user/login";}
		
	}
		
		
	
	

	@PostMapping("addDietService")
	public String addDietService(@ModelAttribute("DietServ")DietServ serv,
								HttpServletRequest request,
								Model model) throws Exception{
		System.out.println("post:addDietService");
		System.out.println(serv);
		
		User user =(User)request.getSession(true).getAttribute("user");
		String userId = user.getUserId();
		serv.setUserId(userId);
		
		if(user.getDietServiceNo()==0) {
		dietService.addDietService(serv);
		}else {
			dietService.updateDietService(serv);
		}
				
		serv = dietService.getDietService(userId);
		model.addAttribute("dietServ",serv);
		model.addAttribute("user",user);
		
		

		return "diet/getDietService.html";
	}
	
	@GetMapping("getDietService")
	public String getDietService(Model model, HttpServletRequest request) {
		
		
		
		return "diet/getDietService.html";
	}
	
	@GetMapping("updateDietService")
	public String updateDietService(Model model, HttpServletRequest request) throws Exception {
		System.out.println("get:updateDietService");
		
		User user =(User)request.getSession(true).getAttribute("user");
		System.out.println(user);
	
		DietServ serv = dietService.getDietService(user.getUserId());
		
		
		model.addAttribute("user",user);
		model.addAttribute("dietServ",serv);
		
		return "diet/updateDietService.html";
			
		
	}
	
	

	@GetMapping("getFoodList")
	public String getFoodList(Model model, @ModelAttribute("search") Search search) throws Exception {
			
		Search search1 = new Search();
		search1.setCurrentPage(1);
		search1.setPageSize(30);
		search1.setSearchCondition(0);
		search1.setSearchKeyword(search.searchKeyword);

		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		
		map2 = dietService.getFoodList(search1);
		map3.put("list", dietService.getFoodAPIlist(search));
		map4.putAll(map2);
		map4.putAll(map3);
		model.addAttribute("list", map4.get("list"));
		model.addAttribute("search", search);

		return "diet/getFoodList.html";
	}

	@PostMapping("getFood")
	public String getFood(@ModelAttribute("food")Food fod, Model model) {
		System.out.println("getFood");
		
		if("U".equals(fod.getFoodCd().substring(0,0))){
			
			
			
			return "diet/getFood.html";
		
		
		}else {
		fod.setFoodNo(234);
		Map<String, Object> map = new HashMap<String, Object>();

		JSONArray jsonArray = new JSONArray();

		try {
			RestTemplate resttemplate = new RestTemplate();

			HttpHeaders header = new HttpHeaders();

			HttpEntity<?> entity = new HttpEntity<>(header);

			String baseUrl = "";
				baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/30/NUM="
						+"D"+fod.getFoodNo();
			

			ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity,
					Map.class);

			map.put("statusCode", resultMap.getStatusCodeValue());
			map.put("header", resultMap.getHeaders());
			map.put("body", resultMap.getBody());

			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap im = (LinkedHashMap) resultMap.getBody().get("I2790");
			ArrayList is = (ArrayList) im.get("row");

			for (int i = 0; is.size() > i; i++) {
				LinkedHashMap aa = (LinkedHashMap) is.get(i);
				
				Food food = new Food();

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
			map.put("body", "excpetion오류");
			System.out.println(e.toString());

		}
		
		return "diet/getFood.html";}
	}
	
	
	
	
	@GetMapping("getPurchaseFoodList")
	public String PurchasaeFoodList(Model model,@ModelAttribute("search") Search search) throws Exception {
		System.out.println("getPurchaseFoodList");

		search.setCurrentPage(1);
		search.setPageSize(30);
		search.setSearchCondition(0);
		if(search.searchKeyword=="") {
			search.setSearchKeyword("닭갈비");
		}

		List list = new ArrayList();
		String result = dietService.shoppingAPI(search.searchKeyword);
		  JSONParser parser = new JSONParser();
          JSONObject obj = (JSONObject)parser.parse(result);
          System.out.println(obj);
          JSONParser parser2 = new JSONParser();
          Object obj2 = parser2.parse(obj.get("items").toString());
          JSONArray array2 = (JSONArray)obj2;
          for(int i=0; i<array2.size(); i++) {
          System.out.println(((JSONObject)array2.get(i)).get("image"));
          Food food = new Food();
         if(((JSONObject)array2.get(i)).get("category1").toString().equals("식품")) {
          food.setFoodImg(((JSONObject)array2.get(i)).get("image").toString());
         food.setPrice(Integer.parseInt(((JSONObject)array2.get(i)).get("lprice").toString()));
         food.setFoodName(((JSONObject)array2.get(i)).get("title").toString());
         food.setPurchaseConnLink(((JSONObject)array2.get(i)).get("link").toString());
         
          list.add(food);
          }
          }
          
          model.addAttribute("list",list);
		
		
		

		return "diet/getPurchaseFoodList.html";

	
}

		


}



