package kr.or.fineapple.diet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.fineapple.domain.Food;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.community.CommunityService;
import kr.or.fineapple.service.diet.DietService;
import kr.or.fineapple.service.user.UserService;

@RestController
@RequestMapping("/diet/rest/*")
public class RestDietController {

	@Autowired
	@Qualifier("dietServiceImpl")
	private DietService dietService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	public RestDietController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="apiSearch")
	public String apiSearch(String foodName){
		System.out.println(foodName);
		
				
		Map<String,Object> map = new HashMap<String,Object>();
		
	    Map<String,String> bb = new HashMap();
		
	    JSONArray jsonArray = new JSONArray();
		
	try {	
		RestTemplate resttemplate = new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
		
		HttpEntity<?> entity = new HttpEntity<>(header);
		
		String baseUrl = "http://openapi.foodsafetykorea.go.kr/api/6dc83aa70289415fafb1/I2790/json/1/3/DESC_KOR="+foodName;
		
		
		ResponseEntity<Map> resultMap = resttemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity, Map.class);
		
		map.put("statusCode", resultMap.getStatusCodeValue());
		map.put("header", resultMap.getHeaders());
		map.put("body", resultMap.getBody()); 
		
	   ObjectMapper mapper = new ObjectMapper();
	  
	  	LinkedHashMap im = (LinkedHashMap)resultMap.getBody().get("I2790");
	  	ArrayList is = (ArrayList)im.get("row");

	  System.out.println(is);
	  	
	  	for(int i = 0; is.size()>i; i++) {
	  	LinkedHashMap aa = (LinkedHashMap)is.get(i);
	   JSONObject jsonObject = new JSONObject();	   
	  	jsonObject.put("foodName", aa.get("DESC_KOR").toString());
	  	jsonObject.put("servingSize",aa.get("SERVING_SIZE").toString());
	  	jsonObject.put("foodKcal", aa.get("NUTR_CONT1").toString());
	  	jsonObject.put("foodCarb", aa.get("NUTR_CONT2").toString());
	  	jsonObject.put("foodProtein", aa.get("NUTR_CONT3").toString());
	  	jsonObject.put("foodFat", aa.get("NUTR_CONT4").toString());
	   jsonArray.add(jsonObject);
	  	}

	  	System.out.println(jsonArray);
	 
	  	
		
	}catch(Exception e){
	      map.put("statusCode", "999");
	      map.put("body"  , "excpetion¿À·ù");
	       System.out.println(e.toString());
		
	}
	
	return jsonArray.toString();
}
	
	
	@RequestMapping("getPurchaseFoodList")
	public Map<String,Object> PurchasaeFoodList(Model model, @RequestBody Search search) throws Exception {
		System.out.println("post:getPurchaseFoodList");

		search.setPageSize(30);
		search.setSearchCondition(0);
		if (search.searchKeyword == "") {
			search.setSearchKeyword("»ø·¯µå");
		}

		System.out.println(search);
		int page = 1; 
		if(search.getCurrentPage()>0) {
			page = search.getCurrentPage();
		};
		search.setStartNum((page-1)*50 +1);
		search.setEndNum(page*50);
		
		List list = new ArrayList();
		String result = dietService.shoppingAPI(search.searchKeyword,search.getStartNum(),search.getEndNum());
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(result);
		System.out.println(obj);
		JSONParser parser2 = new JSONParser();
		Object obj2 = parser2.parse(obj.get("items").toString());
		JSONArray array2 = (JSONArray) obj2;
		for (int i = 0; i < array2.size(); i++) {
			System.out.println(((JSONObject) array2.get(i)).get("image"));
			Food food = new Food();
			if (((JSONObject) array2.get(i)).get("category1").toString().equals("½ÄÇ°")) {
				food.setFoodImg(((JSONObject) array2.get(i)).get("image").toString());
				food.setPrice(Integer.parseInt(((JSONObject) array2.get(i)).get("lprice").toString()));
				food.setFoodName(((JSONObject) array2.get(i)).get("title").toString());
				food.setPurchaseConnLink(((JSONObject) array2.get(i)).get("link").toString());
				food.setMakerName(((JSONObject) array2.get(i)).get("brand").toString());
				food.setStoreName(((JSONObject) array2.get(i)).get("maker").toString());

				list.add(food);
			}
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("search",search);
		
		model.addAttribute("list", list);
		model.addAttribute("search", search);

		return map;

	}
	
	@GetMapping("getAlarmCount")
	public int getAlarmCount(HttpServletRequest
			 request) throws Exception {
		int count=0;	
		if((User)request.getSession(true).getAttribute("user")!=null) {
		User user = (User)request.getSession(true).getAttribute("user");
		List list = new ArrayList();
		list = communityService.getAlarmList(user);
		System.out.println(list);
		count = list.size();
		}else{
		count = 0;
		}
		System.out.println(count);
		return count;
	}
	

	@RequestMapping("addAlarm")
	public void addAlarm(Model model,HttpServletRequest request,@RequestParam("tokenId")String tokenId) throws IOException {
			
		System.out.println(tokenId);
		String FCM_URL = "https://fcm.googleapis.com/fcm/send";

		String server_key = "AAAA3Uv06fw:APA91bFaRq_ymk-a15dn7yuMV5OnJzDeji7eRLg2KgzcZ8R3Ke45hoeojptfUeRjNMknxzq6x90rllLQUsfQvI6Wd-HqY-bDnWLCS5Dv-NBC7SghIU97Mz4FP63VUJ0hrwan0Jz5iwbw";

//		String tokenId = "dUL8ex9b6H3_xhH74F3u2O:APA91bFvzJP8Cfi4gV5wlOdkEBRiwlKUBwG4wbWeOej8eJqo_x3aIZF57BKhgYCjRyGkMphVSqCEGriMPM7BS-bbrJ9d_MoEYS1kpVoHJHBIGf-K6sOHUDDfrZ6LRhj93pOyRovgj-Kd";

		

		String result = "";

		URL url = new URL(FCM_URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();



		conn.setUseCaches(false);

		conn.setDoInput(true);

		conn.setDoOutput(true);



		conn.setRequestMethod("POST");

		conn.setRequestProperty("Authorization", "key=" + server_key);

		conn.setRequestProperty("Content-Type", "application/json");



		JSONObject json = new JSONObject();



		try {

			json.put("to", tokenId.trim());

			JSONObject data = new JSONObject();

			data.put("url", "https://fineapple.or.kr");

			data.put("icon", "logo.png"); 

			json.put("data", data);

			JSONObject info = new JSONObject();

			info.put("title", "Çª½Ã Å×½ºÆ® ÁßÀÔ´Ï´ç"); 

			info.put("body", "»ß»Ç»ß»Ç");

			json.put("notification", info);

		} catch (Exception e1) {

			e1.printStackTrace();

		}



		try {

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(json.toString());

			wr.flush();



			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));



			String output;

			System.out.println("Output from Server .... \n");

			while ((output = br.readLine()) != null) {

				System.out.println(output);

			}

			result = "succcess";

		} catch (Exception e) {

			e.printStackTrace();

			result = "failure";

		}

		System.out.println("GCM Notification is sent successfully : " + result);
		
		
	}
	
	
}
