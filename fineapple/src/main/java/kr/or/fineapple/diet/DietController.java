package kr.or.fineapple.diet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

}
