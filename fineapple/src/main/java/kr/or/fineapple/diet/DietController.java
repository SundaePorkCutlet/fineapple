package kr.or.fineapple.diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			

			return "user/login"; }

		
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
		List list = new ArrayList();
		List list2 = new ArrayList();
		
		list.add(dietService.getFoodAPIlist(search));
		
		map2 = dietService.getFoodList(search1);
		
		
		
		map3.put("list", dietService.getFoodAPIlist(search));
		map4.putAll(map2);
		map4.putAll(map3);
		model.addAttribute("list", map3.get("list"));
		model.addAttribute("search", search);

		return "diet/getFoodList.html";
	}

	@GetMapping("getFood")
	public String getFood(@RequestParam("foodCd")String foodCd, Model model) throws Exception {
		
		Food food = new Food();
		food = dietService.getFood(foodCd);
		System.out.println(foodCd);
		model.addAttribute("food",food);
		return "diet/getFood.html";
		}
	
	@GetMapping("getFood1")
	public String getFood() throws Exception {
		
		
		return "diet/getFood.html";
		}

	
	
	@GetMapping("getPurchaseFoodList")
	public String PurchasaeFoodList(Model model,@ModelAttribute("search") Search search) throws Exception {
		System.out.println("post:getPurchaseFoodList");

		search.setCurrentPage(1);
		search.setPageSize(30);
		search.setSearchCondition(0);
		if(search.searchKeyword=="") {
			search.setSearchKeyword("»ø·¯µå");
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
         if(((JSONObject)array2.get(i)).get("category1").toString().equals("½ÄÇ°")) {
          food.setFoodImg(((JSONObject)array2.get(i)).get("image").toString());
         food.setPrice(Integer.parseInt(((JSONObject)array2.get(i)).get("lprice").toString()));
         food.setFoodName(((JSONObject)array2.get(i)).get("title").toString());
         food.setPurchaseConnLink(((JSONObject)array2.get(i)).get("link").toString());
         food.setMakerName(((JSONObject)array2.get(i)).get("brand").toString());
         food.setStoreName(((JSONObject)array2.get(i)).get("maker").toString());
         
          list.add(food);
          }
          }
          
          model.addAttribute("list",list);
          model.addAttribute("search",search);
		
		

		return "diet/getPurchaseFoodList.html";

	
}
	@GetMapping("getFavMealList")
	public String getFavMealList(Model model,HttpServletRequest request)	throws Exception {
			System.out.println("getFavMealList");
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			User user =(User)request.getSession(true).getAttribute("user");
			DietServ serv = dietService.getDietService(user.getUserId());
			map = dietService.getFavMealList(serv.getUserServiceNo());
			System.out.println(map);
			model.addAttribute("list",map.get("list"));
		return "diet/getFavMealList.html";
	}
		
@GetMapping("getAddDaily")
public String getAddDaily(Model model,@RequestParam("chekarray")String[] foodCd)throws Exception{
	
	System.out.println(foodCd);
	
	
	
	return "diet/getAddDailyIntakeMeal.html";
}
	
	

}



