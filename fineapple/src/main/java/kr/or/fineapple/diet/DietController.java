package kr.or.fineapple.diet;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.fineapple.domain.DietServ;
import kr.or.fineapple.domain.FavMeal;
import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.IntakeRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Page;
import kr.or.fineapple.domain.common.Pagination;
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
	public String addDietServiceget(HttpServletRequest request, Model model) throws Exception {

		System.out.println("get:addDietService");

		if ((User) request.getSession(true).getAttribute("user") != null) {
			User user = (User) request.getSession(true).getAttribute("user");
			user = userService.getUser(user.getUserId());
			System.out.println(user);

			DietServ serv = dietService.getDietService(user.getUserId());
			System.out.println(serv);
			if (serv != null) {
				if (user.getDietServiceNo() != 0) {
					   Double  dailyIntakeKcal   = 0.0;
					   Double   totaldailyIntakeKcal = 0.0;

					
					 if(user.getGender().equals("male")) {
					      
					     
					     dailyIntakeKcal   = 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
					      
					      
					   } else {
					      
					    dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
					      
					      
					   }
					   
					   System.out.println(dailyIntakeKcal);
					   
					   if(user.getServiceTrgt().equals("체중증량")) {
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
					      
					   } if (user.getServiceTrgt().equals("체중유지")){
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
					      
					   } else {
					      
					      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
					      
					   }

					System.out.println(totaldailyIntakeKcal);
					model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
					
					
					
					
					
					
					model.addAttribute("user", user);
					model.addAttribute("dietServ", serv);
					return "diet/getDietService.html";
				} else {
					System.out.println("설마여기?");
					  Double  dailyIntakeKcal   = 0.0;
					   Double   totaldailyIntakeKcal = 0.0;

					
					 if(user.getGender().equals("male")) {
					      
					     
					     dailyIntakeKcal   = 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
					      
					      
					   } else {
					      
					    dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
					      
					      
					   }
					   
					   System.out.println(dailyIntakeKcal);
					   
					   if(user.getServiceTrgt().equals("체중증량")) {
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
					      
					   } if (user.getServiceTrgt().equals("체중유지")){
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
					      
					   } else {
					      
					      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
					      
					   }

					model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
					
					
					model.addAttribute("user", user);
					
					
					return "diet/addDietService.html";
				}
			} else {
				   Double  dailyIntakeKcal   = 0.0;
				   Double   totaldailyIntakeKcal = 0.0;

				
				 if(user.getGender().equals("male")) {
				      
				     
				     dailyIntakeKcal   = 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
				      
				      
				   } else {
				      
				    dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
				      
				      
				   }
				   
				   System.out.println(dailyIntakeKcal);
				   
				   if(user.getServiceTrgt().equals("체중증량")) {
				      
				      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
				      
				   } if (user.getServiceTrgt().equals("체중유지")){
				      
				      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
				      
				   } else {
				      
				      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
				      
				   }

				System.out.println(totaldailyIntakeKcal);
				model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
				model.addAttribute("user", user);
				return "diet/addDietService.html";
			}

		} else {
			
			System.out.println("그럼여기?");
			return "user/login";
		}

	}

	@PostMapping("addDietService")
	public String addDietService(@ModelAttribute("DietServ") DietServ serv, HttpServletRequest request, Model model,HttpSession session)
			throws Exception {
		System.out.println("post:addDietService");
		System.out.println(serv);

		User user = (User) request.getSession(true).getAttribute("user");
		String userId = user.getUserId();
		serv.setUserId(userId);

		if (user.getDietServiceNo() == 0) {
			dietService.addDietService(serv);
		} else {
			dietService.updateDietService(serv);
		}

		serv = dietService.getDietService(userId);
		dietService.updateDietServiceNo(serv);
		
		model.addAttribute("dietServ", serv);
		model.addAttribute("user", user);
		
		
		user = userService.getUser(userId);

		session.setAttribute("user",user);

		
		
		

		return "diet/getDietService.html";
	}

	@GetMapping("getDietService")
	public String getDietService(Model model, HttpServletRequest request) {

		return "diet/getDietService.html";
	}

	@GetMapping("updateDietService")
	public String updateDietService(Model model, HttpServletRequest request) throws Exception {
		System.out.println("get:updateDietService");

		User user = (User) request.getSession(true).getAttribute("user");
		System.out.println(user);

		DietServ serv = dietService.getDietService(user.getUserId());

		
		model.addAttribute("user", user);
		model.addAttribute("dietServ", serv);

		return "diet/updateDietService.html";

	}

	@RequestMapping("getFoodList")
	public String getFoodList(Model model, @ModelAttribute("search") Search search, HttpServletRequest request,
								@RequestParam(value="page",defaultValue = "1")int page)
			throws Exception {

		
		Search search1 = new Search();
		search1.setCurrentPage(1);
		search1.setPageSize(30);
		search1.setSearchCondition(0);
		search1.setSearchKeyword(search.searchKeyword);
		
		
		
		

		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		List list = new ArrayList();
		List<Food> list2 = new ArrayList<Food>();

		list2 = dietService.getFoodList(search1);
		int startNum;
		int endNum;
		
		System.out.println(page);
		int size=list2.size();
			
		if(page!=1) {
			startNum= (page-1)*50+1-size;  
		}else {
			startNum=1;
		}
			endNum = 50*page- size;
	
		search.setStartNum(startNum);
		search.setEndNum(endNum);
		list = dietService.getFoodAPIlist(search);
			
		if(page==1){
			list.addAll(list2);		
		}
	
		
		
		

		model.addAttribute("list", list);
		model.addAttribute("search", search);
		return "diet/getFoodList.html";
	}
	


	@GetMapping("getFood")
	public String getFood(@RequestParam("foodCd") String foodCd, Model model) throws Exception {

		Food food = new Food();
		food = dietService.getFood(foodCd);
		System.out.println(food);
		model.addAttribute("food", food);
		return "diet/getFood.html";
	}

	@GetMapping("getPurchaseFoodList")
	public String PurchasaeFoodList(Model model, @ModelAttribute("search") Search search) throws Exception {
		System.out.println("post:getPurchaseFoodList");

		search.setCurrentPage(1);
		search.setPageSize(30);
		search.setSearchCondition(0);
		if (search.searchKeyword == "") {
			search.setSearchKeyword("샐러드");
		}

		List list = new ArrayList();
		String result = dietService.shoppingAPI(search.searchKeyword,1,50);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(result);
		System.out.println(obj);
		JSONParser parser2 = new JSONParser();
		Object obj2 = parser2.parse(obj.get("items").toString());
		JSONArray array2 = (JSONArray) obj2;
		for (int i = 0; i < array2.size(); i++) {
			System.out.println(((JSONObject) array2.get(i)).get("image"));
			Food food = new Food();
			if (((JSONObject) array2.get(i)).get("category1").toString().equals("식품")) {
				food.setFoodImg(((JSONObject) array2.get(i)).get("image").toString());
				food.setPrice(Integer.parseInt(((JSONObject) array2.get(i)).get("lprice").toString()));
				food.setFoodName(((JSONObject) array2.get(i)).get("title").toString());
				food.setPurchaseConnLink(((JSONObject) array2.get(i)).get("link").toString());
				food.setMakerName(((JSONObject) array2.get(i)).get("brand").toString());
				food.setStoreName(((JSONObject) array2.get(i)).get("maker").toString());

				list.add(food);
			}
		}

		model.addAttribute("list", list);
		model.addAttribute("search", search);

		return "diet/getPurchaseFoodList.html";

	}

	@GetMapping("getFavMealList")
	public String getFavMealList(Model model, HttpServletRequest request, @RequestParam("menu") int menu)
			throws Exception {
		System.out.println("getFavMealList");

		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) request.getSession(true).getAttribute("user");
		DietServ serv = dietService.getDietService(user.getUserId());
		map = dietService.getFavMealList(serv.getUserServiceNo());
		System.out.println(map);
		model.addAttribute("list", map.get("list"));
		System.out.println(menu);
		model.addAttribute("menu", menu);
		return "diet/getFavMealList :: getFavMealList";
	}

	@GetMapping("getAddDaily")
	public String getAddDaily(Model model) throws Exception {

		return "diet/getAddDailyIntakeMeal.html";
	}

	@GetMapping("modal")
	public String modal(Model model, HttpServletRequest request, @RequestParam("checkarray") String foodCd)
			throws Exception {

		String hong = "byung";
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) request.getSession(true).getAttribute("user");
		DietServ serv = dietService.getDietService(user.getUserId());
		map = dietService.getFavMealList(serv.getUserServiceNo());
		System.out.println(map);
		model.addAttribute("list", map.get("list"));
		System.out.println(foodCd);
		model.addAttribute("foodCd", foodCd);

		return "diet/getFavMealList :: hong";

	}

	@GetMapping("addFavMealItem")
	public String getaddFavMeal(Model model, @RequestParam("checkarray") String foodCd, HttpServletRequest request)
			throws Exception {

		System.out.println("오긴온다");

		Food food = new Food();

		food = dietService.getFood(foodCd);

		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) request.getSession(true).getAttribute("user");
		DietServ serv = dietService.getDietService(user.getUserId());
		map = dietService.getFavMealList(serv.getUserServiceNo());
		System.out.println(map);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("food", food);

		return "diet/addFavMealItem :: addFavMealItem";
	}

	@PostMapping("addFavMealItem")
	public String postaddFavMeal(Model model, @RequestParam("favMealNo") int favMealNo,
								@RequestParam("userFoodIntake") double userFoodIntake,
								@RequestParam("foodCd") String foodCd)
			throws Exception {

		System.out.println("addFav진입");
		System.out.println(favMealNo + "+" + userFoodIntake + "+" + foodCd);

		double Intake = 0;
		Food food = new Food();
		if (!foodCd.substring(0).equals("U")) {
			food = dietService.getFood(foodCd);

		}

		FavMeal fav = new FavMeal();
		Intake = (userFoodIntake / food.getServingSize());
		food.setFoodKcal(food.getFoodKcal() * Intake);
		food.setFoodCarb(food.getFoodCarb() * Intake);
		food.setFoodProtein(food.getFoodProtein() * Intake);
		food.setFoodFat(food.getFoodFat() * Intake);

		fav.setIntake(food.getServingSize() * Intake);
		fav.setFood(food);
		fav.setFavMealNo(favMealNo);

		dietService.addFavMealItem(fav);

		return "diet/addFood.html";
	}

	@GetMapping("getaddDailyIntakeMeal")
	public String getaddDailyIntakeMeal(Model model, @RequestParam("checkarray") String foodCd,
			HttpServletRequest request) throws Exception {

		Food food = new Food();

		food = dietService.getFood(foodCd);

		model.addAttribute("food", food);

		return "diet/getaddDailyIntakeMeal :: getaddDailyIntakeMeal";
	}

	@PostMapping("postaddDailyIntakeMeal")
	public String postaddDailyIntakeMeal(Model model, @RequestParam("userFoodIntake") double userFoodIntake,
										@RequestParam("foodCd") String foodCd, 
										@RequestParam("meal") String meal, 
										HttpServletRequest request)
			throws Exception {

		System.out.println("오긴온다");

		Food food = new Food();
		double Intake = 0;
		if (!foodCd.substring(0).equals("U")) {
			food = dietService.getFood(foodCd);

		}

		Intake = (userFoodIntake / food.getServingSize());
		food.setFoodKcal(food.getFoodKcal() * Intake);
		food.setFoodCarb(food.getFoodCarb() * Intake);
		food.setFoodProtein(food.getFoodProtein() * Intake);
		food.setFoodFat(food.getFoodFat() * Intake);

		IntakeRecord record = new IntakeRecord();
		User user = (User) request.getSession(true).getAttribute("user");

		record.setFood(food);
		record.setMeal(meal);
		record.setUserFoodIntake(userFoodIntake);
		record.setUserId(user.getUserId());
		System.out.println(record);
		dietService.addIntakeRecord(record);

		List<IntakeRecord> list = new ArrayList<IntakeRecord>();
		int radio = 0;

		DietServ serv = dietService.getDietService(user.getUserId());
		list = dietService.getIntakeRecordList(serv.getUserServiceNo());
		model.addAttribute("list", list);
		model.addAttribute("serv", serv);
		model.addAttribute("radio", radio);

		return "diet/getDailyIntakeMeal.html";
	}

	@GetMapping("getDailyIntakeMeal")
	public String getDailyIntakeMeal(Model model, HttpServletRequest request,
									@RequestParam(value = "radio", required = false) String radio) throws Exception {

		if (radio == null) {
			radio = "0";

		}

		List<IntakeRecord> list = new ArrayList<IntakeRecord>();
		User user = (User) request.getSession(true).getAttribute("user");

		DietServ serv = dietService.getDietService(user.getUserId());
		list = dietService.getIntakeRecordList(serv.getUserServiceNo());
		model.addAttribute("list", list);
		model.addAttribute("serv", serv);
		model.addAttribute("radio", radio);

		return "diet/getDailyIntakeMeal.html";
	}

	@GetMapping("deleteIntakeMeal")
	public String deleteIntakeMeal(Model model, HttpServletRequest request, @RequestParam("IntakeNo") int IntakeNo)
			throws Exception {
		List<IntakeRecord> list = new ArrayList<IntakeRecord>();

		System.out.println(IntakeNo);
		dietService.deleteIntakeRecord(IntakeNo);
		
		int radio = 0;
		User user = (User) request.getSession(true).getAttribute("user");

		DietServ serv = dietService.getDietService(user.getUserId());
		list = dietService.getIntakeRecordList(serv.getUserServiceNo());
		model.addAttribute("list", list);
		model.addAttribute("serv", serv);
		model.addAttribute("radio", radio);

		return "diet/getDailyIntakeMeal.html";

	}

	@GetMapping("getaddFood")
	public String getaddFood(Model model) {

		return "diet/addFood.html";
	}

	@PostMapping("postaddFood")
	public String postaddFood(@ModelAttribute Food food, Model model) throws Exception {

		System.out.println(food);
		dietService.addFood(food);

		Search search1 = new Search();
		search1.setCurrentPage(1);
		search1.setPageSize(30);
		search1.setSearchCondition(0);
		search1.setSearchKeyword(food.getFoodName());
		List<Food> list2 = new ArrayList<Food>();

		list2 = dietService.getFoodList(search1);

		model.addAttribute("list", list2);
		model.addAttribute("search", search1);

		return "diet/getFoodList.html";
	}

	@GetMapping("getFavMealItem")
	public String getFavMealItem(Model model, @RequestParam("favMealNo") int favMealNo,
								@RequestParam("favMealName") String favMealName) throws Exception {

		List<FavMeal> list = new ArrayList<FavMeal>();

		list = dietService.getFavMealItemList(favMealNo);

		System.out.println("++" + favMealName);

		model.addAttribute("list", list);
		model.addAttribute("favMealName", favMealName);
		model.addAttribute("favMealNo", favMealNo);

		return "diet/getFavMealItem :: getFavMealItem";
	}

	@GetMapping("FavMealItemAddIntakeRecord")
	public String favMealItemAddIntakeRecord(Model model, @RequestParam("checkarray") String favMealItemNo,
											HttpServletRequest request) throws Exception {

		String[] arr = favMealItemNo.split(",");
		User user = (User) request.getSession(true).getAttribute("user");
		for (int i = 0; arr.length > i; i++) {
			FavMeal fav = new FavMeal();
			Food food = new Food();
			IntakeRecord record = new IntakeRecord();

			int No = Integer.parseInt(arr[i]);

			fav = dietService.getFavMealItem(No);

			food.setFoodCarb(fav.getFood().getFoodCarb());
			food.setFoodCd(fav.getFood().getFoodCd());
			food.setFoodFat(fav.getFood().getFoodFat());
			food.setFoodKcal(fav.getFood().getFoodKcal());
			food.setFoodName(fav.getFood().getFoodName());
			food.setServingSize(fav.getIntake());
			food.setFoodProtein(fav.getFood().getFoodProtein());

			record.setFood(food);

			record.setUserId(user.getUserId());
			record.setUserFoodIntake(fav.getIntake());
			record.setMeal("간식");

			dietService.addIntakeRecord(record);

		}

		return "redirect:../diet/getDailyIntakeMeal?radio=0";
	}

	@GetMapping("deleteFavMealItem")
	public String deletefavMealItem(Model model, @RequestParam("checkarray") String favMealItemNo,
									@RequestParam("favMealNo") int favMealNo, 
									@RequestParam("favMealName") String favMealName,
									HttpServletRequest request) throws Exception {

		String[] arr = favMealItemNo.split(",");
		for (int i = 0; arr.length > i; i++) {
			int No = Integer.parseInt(arr[i]);
			System.out.println(No);
			dietService.delteFavMealItem(No);

		}

		String name = URLEncoder.encode(favMealName, "UTF-8");

		System.out.println(favMealName);
		return "redirect:../diet/getFavMealItem?favMealNo=" + favMealNo + "&favMealName=" + name;
	}

	@PostMapping("addFavMeal")
	public String addFavMeal(Model model,@RequestParam("favMealName")String favMealName,
							HttpServletRequest request) throws Exception {
		
		User user = (User) request.getSession(true).getAttribute("user");
		FavMeal fav = new FavMeal();
		fav.setFavMealName(favMealName);
		fav.setUserId(user.getUserId());	
		dietService.addFavMeal(fav);
		
		
		
		return "redirect:../diet/getFavMealList?menu=0";
	}
	
	@PostMapping("deleteFavMeal")
	public String deleteFavMeal(Model model,@RequestParam("favMealNo")int favMealNo,
							HttpServletRequest request) throws Exception {
		
		dietService.deleteFavMeal(favMealNo);
		
		
		
		return "redirect:../diet/getFavMealList?menu=0";
	}
	
	@PostMapping("updateFavMeal")
	public String updateFavMeal(Model model,@RequestParam("favMealNo")String favMealNo,
								@RequestParam("favMealName")String favMealName) throws Exception {
		FavMeal fav = new FavMeal();
		int no = Integer.parseInt(favMealNo);
		
		fav.setFavMealNo(no);
		fav.setFavMealName(favMealName);
		System.out.println(favMealName);
		dietService.updateFavMealName(fav);
		
		
		return "redirect:../diet/getFavMealList?menu=0";
	}
	
	
}
