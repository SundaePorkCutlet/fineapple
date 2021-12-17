package kr.or.fineapple.diet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diet/*")
public class DietController {
	
	
@RequestMapping(value="addDietService")
	    public String addDietService(){
	     System.out.println("addDietService");
	     
	     
	     
	    	return "diet/addDietService.html";
	    }

}
