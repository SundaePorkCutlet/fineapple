package kr.or.fineapple.diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/diet/*")
public class DietController {
	
	
@GetMapping(value="addDietService")
	    public String addDietService(@RequestParam(value="menu", required=false) int menu){
	     System.out.println("addDietService");
	     System.out.println(menu);
	     
	     if(menu==0) {
	    	return "diet/addDietService.html";
	    }
	     
	     return "diet/getDietService.html";
}

@PostMapping(value="getFoodList")
		public String getFoodList(){
		System.out.println("getFoodList");
		
		return "diet/getFoodList.html";
		
}


		}
		







