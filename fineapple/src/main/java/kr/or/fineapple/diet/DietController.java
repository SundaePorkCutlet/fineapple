package kr.or.fineapple.diet;

import java.net.URLEncoder;
import java.time.LocalDate;
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
import kr.or.fineapple.domain.Recipe;
import kr.or.fineapple.domain.TotalRecord;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.UserServ;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.domain.common.ViewDuration;
import kr.or.fineapple.domain.community.Alarm;
import kr.or.fineapple.service.community.CommunityService;
import kr.or.fineapple.service.diary.DiaryService;
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
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@Autowired
	@Qualifier("diaryServiceImpl")
	private DiaryService diaryService;

	@GetMapping("addDietService")
	public String addDietServiceget(HttpServletRequest request, Model model) throws Exception {

		System.out.println("get:addDietService");

			User user = (User) request.getSession(true).getAttribute("user");
			user = userService.getUser(user.getUserId());
			System.out.println("++++++++"+user);

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
					   
					   if(user.getServiceTrgt().equals("ü������")) {
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
					      
					   } if (user.getServiceTrgt().equals("ü������")){
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
					      
					   } else {
					      
					      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
					      
					   }

					System.out.println(totaldailyIntakeKcal);
					model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
					
					
					
					
					
					
					model.addAttribute("user", user);
					model.addAttribute("dietServ", serv);
					model.addAttribute("NavName1","�Ĵܰ���");
					model.addAttribute("NavName2","�Ĵ� ���� Ȱ��ȭ");
					
					
					return "diet/getDietService.html";
				} else {
					System.out.println("��������?");
					  Double  dailyIntakeKcal   = 0.0;
					   Double   totaldailyIntakeKcal = 0.0;

					
					 if(user.getGender().equals("male")) {
					      
					     
					     dailyIntakeKcal   = 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
					      
					      
					   } else {
					      
					    dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
					      
					      
					   }
					   
					   System.out.println(dailyIntakeKcal);
					   
					   if(user.getServiceTrgt().equals("ü������")) {
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
					      
					   } if (user.getServiceTrgt().equals("ü������")){
					      
					      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
					      
					   } else {
					      
					      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
					      
					   }

					   
					   
					model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
					
					
					model.addAttribute("user", user);
					model.addAttribute("NavName1","�Ĵܰ���");
					model.addAttribute("NavName2","�Ĵ� ���� Ȱ��ȭ");
					
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
				   
				   if(user.getServiceTrgt().equals("ü������")) {
				      
				      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
				      
				   } if (user.getServiceTrgt().equals("ü������")){
				      
				      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
				      
				   } else {
				      
				      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
				      
				   }

				System.out.println(totaldailyIntakeKcal);
				model.addAttribute("dailyIntakeKcal",totaldailyIntakeKcal);
				model.addAttribute("user", user);
				model.addAttribute("NavName1","�Ĵܰ���");
				model.addAttribute("NavName2","�Ĵ� ���� Ȱ��ȭ");
				return "diet/addDietService.html";
			}

	

	}

	@PostMapping("addDietService")
	public String addDietService(@ModelAttribute("DietServ") DietServ serv, HttpServletRequest request, Model model,HttpSession session)
			throws Exception {
		System.out.println("post:addDietService");
		System.out.println(serv);
		DietServ serv2 = new DietServ();
		User user = (User) request.getSession(true).getAttribute("user");
		System.out.println(user);
		
		String userId = user.getUserId();
		serv.setUserId(userId);
				
		if(dietService.getDietService(userId)==null) {		
			dietService.addDietService(serv);
		}else {
			serv2 = dietService.getDietService(userId);
			serv2.setBodyFat(serv.getBodyFat());
			serv2.setTrgtBodyFat(serv.getTrgtBodyFat());
			serv2.setDailyTrgtIntakeKcal(serv.getDailyTrgtIntakeKcal());
			
			dietService.updateDietService(serv2);
		}
			
			
		

		
		model.addAttribute("dietServ", serv);
		model.addAttribute("user", user);
		
		
		user = userService.getUser(userId);

		session.setAttribute("user",user);

		
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","�Ĵ� ���� Ȱ��ȭ");
		

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

		 Double  dailyIntakeKcal   = 0.0;
		   Double   totaldailyIntakeKcal = 0.0;

		
		 if(user.getGender().equals("male")) {
		      
		     
		     dailyIntakeKcal   = 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
		      
		      
		   } else {
		      
		    dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
		      
		      
		   }
		   
		   System.out.println(dailyIntakeKcal);
		   
		   if(user.getServiceTrgt().equals("ü������")) {
		      
		      totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
		      
		   } if (user.getServiceTrgt().equals("ü������")){
		      
		      totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
		      
		   } else {
		      
		      totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
		      
		   }
		
		
		model.addAttribute("totaldailyIntakeKcal",totaldailyIntakeKcal);
		model.addAttribute("user", user);
		model.addAttribute("dietServ", serv);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","�Ĵ� ���� Ȱ��ȭ");

		return "diet/updateDietService.html";

	}
	
	@PostMapping("updateDietService")
	public String postupdateDietService(@ModelAttribute("DietServ") DietServ serv,Model model, HttpServletRequest request) throws Exception {
		System.out.println("post:updateDietService");
		System.out.println(serv);

		User user = (User) request.getSession(true).getAttribute("user");
		System.out.println(user);
		
		String userId = user.getUserId();
		serv.setUserId(userId);


		dietService.updateDietService(serv);
		
		model.addAttribute("dietServ", serv);
		model.addAttribute("user", user);
		
		
		user = userService.getUser(userId);


		return "redirect:../diet/addDietService";

	}

	@RequestMapping("getFoodList")
	public String getFoodList(Model model, @ModelAttribute("search") Search search, HttpServletRequest request,
								@RequestParam(value="page",defaultValue = "1")int page)
			throws Exception {

		if ((User) request.getSession(true).getAttribute("user") == null) {
			
			return "redirect:../user/login";
		}
		
		
		
		
		
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
		int nextpage=1;
		
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
		int Apisize=list.size();	
			
		if(page==1){
			list.addAll(list2);		
		}
	
		if((size+Apisize)<50) {
			nextpage=0;
			
		}
		
		

		model.addAttribute("list", list);
		model.addAttribute("search", search);
		model.addAttribute("page",page);
		model.addAttribute("nextpage",nextpage);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ�˻�");
		
		
		return "diet/getFoodList.html";
	}
	


	@GetMapping("getFood")
	public String getFood(@RequestParam("foodCd") String foodCd, Model model) throws Exception {

		Food food = new Food();
		System.out.println(foodCd);
		food = dietService.getFood(foodCd);
		System.out.println(food);
		model.addAttribute("food", food);
		
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ������");
		return "diet/getFood.html";
	}

	@GetMapping("getPurchaseFoodList")
	public String PurchasaeFoodList(Model model, @ModelAttribute("search") Search search) throws Exception {
		System.out.println("post:getPurchaseFoodList");

		search.setCurrentPage(1);
		search.setPageSize(30);
		search.setSearchCondition(0);
		if (search.searchKeyword == "") {
			search.setSearchKeyword("������");
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
			if (((JSONObject) array2.get(i)).get("category1").toString().equals("��ǰ")) {
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
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ����");
		
			
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

		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","���ϼ���Ĵ�");
		
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

		System.out.println("����´�");

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

		System.out.println("addFav����");
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
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ�������");
		
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

		System.out.println("����´�");

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
		
		////������� ���� ����
		//�ʿ��� parameter
		String userId = user.getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(LocalDate.now());
		viewDuration.setEndDate(LocalDate.now());
		LocalDate date = LocalDate.now();
		//������ �� ���� Į�θ� ��ȸ
		TotalRecord totalDietRecord = dietService.getTotalDietRecord(viewDuration);
		//������� ��ǥ ���� Į�θ� ��ȸ
		UserServ userServ = diaryService.getUserServiceDetails(userId);
		if(totalDietRecord != null) {
			if(totalDietRecord.getTotalIntakeKcal() >= userServ.getDailyTrgtIntakeKcal()*0.9 &&
				totalDietRecord.getTotalIntakeKcal() <= userServ.getDailyTrgtIntakeKcal()*1.1) {
				//���� �� ���� Į�θ��� ��ǥ ���� Į�θ�*0.9~1.1 ���� ������ ��
				diaryService.updateBadgeByDiet(userId, 1, totalDietRecord.getTotalIntakeKcal(), date);
			} else {
				diaryService.updateBadgeByDiet(userId, 0, totalDietRecord.getTotalIntakeKcal(), date);
			}
		} else {
			diaryService.updateBadgeByDiet(userId, 0, 0, date);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("serv", serv);
		model.addAttribute("radio", radio);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","���ϼ���Ĵ�");

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
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","���ϼ���Ĵ�");

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
		
		////������� ���� ����
		//�ʿ��� parameter
		String userId = user.getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(LocalDate.now());
		viewDuration.setEndDate(LocalDate.now());
		LocalDate date = LocalDate.now();
		//������ �� ���� Į�θ� ��ȸ
		TotalRecord totalDietRecord = dietService.getTotalDietRecord(viewDuration);
		//������� ��ǥ ���� Į�θ� ��ȸ
		UserServ userServ = diaryService.getUserServiceDetails(userId);
		if(totalDietRecord != null) {
			if(totalDietRecord.getTotalIntakeKcal() >= userServ.getDailyTrgtIntakeKcal()*0.9 &&
				totalDietRecord.getTotalIntakeKcal() <= userServ.getDailyTrgtIntakeKcal()*1.1) {
				//���� �� ���� Į�θ��� ��ǥ ���� Į�θ�*0.9~1.1 ���� ������ ��
				diaryService.updateBadgeByDiet(userId, 1, totalDietRecord.getTotalIntakeKcal(), date);
			} else {
				diaryService.updateBadgeByDiet(userId, 0, totalDietRecord.getTotalIntakeKcal(), date);
			}
		} else {
			diaryService.updateBadgeByDiet(userId, 0, 0, date);
		}
		
		
		model.addAttribute("list", list);
		model.addAttribute("serv", serv);
		model.addAttribute("radio", radio);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","���ϼ���Ĵ�");

		return "diet/getDailyIntakeMeal.html";

	}

	@GetMapping("getaddFood")
	public String getaddFood(Model model) {

		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ�������");
		
		return "diet/addFood.html";
	}

	@PostMapping("postaddFood")
	public String postaddFood(@ModelAttribute Food food, Model model) throws Exception {

		System.out.println(food);
		dietService.addFood(food);
		int page = 1;
		int nextpage = 0;
		Search search1 = new Search();
		search1.setCurrentPage(1);
		search1.setPageSize(30);
		search1.setSearchCondition(0);
		search1.setSearchKeyword(food.getFoodName());
		List<Food> list2 = new ArrayList<Food>();

		list2 = dietService.getFoodList(search1);

		model.addAttribute("list", list2);
		model.addAttribute("search", search1);
		model.addAttribute("page",page);
		model.addAttribute("nextpage",nextpage);
		
		
		
		
		
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ�˻�");
		


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
											@RequestParam("meal")String meal,
											HttpServletRequest request) throws Exception {

		System.out.println(meal);
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
			record.setMeal(meal);

			dietService.addIntakeRecord(record);
		}

		////������� ���� ����
		//�ʿ��� parameter
		String userId = user.getUserId();
		ViewDuration viewDuration = new ViewDuration();
		viewDuration.setUserId(userId);
		viewDuration.setStartDate(LocalDate.now());
		viewDuration.setEndDate(LocalDate.now());
		LocalDate date = LocalDate.now();
		//������ �� ���� Į�θ� ��ȸ
		TotalRecord totalDietRecord = dietService.getTotalDietRecord(viewDuration);
		//������� ��ǥ ���� Į�θ� ��ȸ
		UserServ userServ = diaryService.getUserServiceDetails(userId);
		if(totalDietRecord != null) {
			if(totalDietRecord.getTotalIntakeKcal() >= userServ.getDailyTrgtIntakeKcal()*0.9 &&
				totalDietRecord.getTotalIntakeKcal() <= userServ.getDailyTrgtIntakeKcal()*1.1) {
				//���� �� ���� Į�θ��� ��ǥ ���� Į�θ�*0.9~1.1 ���� ������ ��
				diaryService.updateBadgeByDiet(userId, 1, totalDietRecord.getTotalIntakeKcal(), date);
			} else {
				diaryService.updateBadgeByDiet(userId, 0, totalDietRecord.getTotalIntakeKcal(), date);
			}
		} else {
			diaryService.updateBadgeByDiet(userId, 0, 0, date);
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
		
		
		
		return "redirect:../diet/getFavMealList?menu=1";
	}
	
	@PostMapping("deleteFavMeal")
	public String deleteFavMeal(Model model,@RequestParam("favMealNo")int favMealNo,
							HttpServletRequest request) throws Exception {
		
		dietService.deleteFavMeal(favMealNo);
		
		
		
		return "redirect:../diet/getFavMealList?menu=1";
	}
	
	@PostMapping("updateFavMeal")
	public String updateFavMeal(Model model,@RequestParam("favMealNo")String favMealNo,
								@RequestParam("favMealName")String favMealName) throws Exception {
		
		System.out.println("+++���İ�");
		
		FavMeal fav = new FavMeal();
		int no = Integer.parseInt(favMealNo);
		
		fav.setFavMealNo(no);
		fav.setFavMealName(favMealName);
		System.out.println(favMealName);
		dietService.updateFavMealName(fav);
		
		
		return "redirect:../diet/getFavMealList?menu=1";
	}
	
	@RequestMapping("getRcpList")
	public String getRcpList(Model model,@RequestParam(value="page",defaultValue = "1")int page
								,@ModelAttribute("search") Search search) {
		
		
		search.setStartNum((page-1)*20+1);
		search.setEndNum(page*20);
		
		System.out.println(search);
		List<Recipe> list2 = new ArrayList<Recipe>();
		
		list2 = dietService.getrcpList(search);
		int nextpage = 1;
		
		if(list2.size()<20) {
			
			nextpage = 0;
			
		}
				
		
		
		
		System.out.println(list2);
		model.addAttribute("list",list2);
		model.addAttribute("search",search);
		model.addAttribute("nextpage",nextpage);
		model.addAttribute("page",page);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ������");
		
		
		return "diet/getRcpList.html";
	}
	
	@RequestMapping("getRcp")
	public String getRcp(Model model,@RequestParam("rcpName")String rcpName,
								@ModelAttribute("search") Search search) {
		
		
		System.out.println(rcpName);
		
		String name = rcpName.replaceAll(" ", "_");
		Recipe rcp = new Recipe();
		rcp = dietService.getRcp(name);
		
		
		System.out.println(rcp);
		model.addAttribute("rcp",rcp);
		model.addAttribute("NavName1","�Ĵܰ���");
		model.addAttribute("NavName2","��ǰ�����ǻ�����");
		
		
		return "diet/getRcp.html";
	}
	
	

	@GetMapping("getAlarm")
	public String getAlarm(Model model,HttpServletRequest
			 request) throws Exception {

		User user = (User)request.getSession(true).getAttribute("user");
		//List list = new ArrayList();
		List list = communityService.getAlarmList(user);
		
		System.out.println(list);
		model.addAttribute("list",list);
		int count = list.size();
		model.addAttribute("count",count);
		
		
		return "diet/alarm :: getalarm";
	}
	
	@RequestMapping("deleteAlarm")
	public String deleteAlarm(Model model,HttpServletRequest request,
								@RequestParam("alarmNo")int alarmNo) {
		System.out.println(alarmNo);
		Alarm al = new Alarm();
		al.setAlarmNo(alarmNo);
		communityService.deleteAlarm(al);
		
		
		
		return "redirect:../diet/getAlarm";
	}
	
	@RequestMapping("deleteAllAlarm")
	public String deleteAllAlarm(Model model,HttpServletRequest request
								) {
		Alarm al = new Alarm();
		User user = (User)request.getSession(true).getAttribute("user");
		al.setUser(user);
		communityService.deleteAlarmAll(al);
		
		
		
		return "redirect:../diet/getAlarm";
	}
	
	

}
