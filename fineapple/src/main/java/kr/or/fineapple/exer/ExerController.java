package kr.or.fineapple.exer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exer/*")
public class ExerController {

@RequestMapping(value="addExerService")
public String addExerService() {
	
    System.out.println("addExerService");

	return "exer/addExerService.html";
	
}


@RequestMapping(value="listExer")
public String listExer() {
	
	  System.out.println("listExer");
	
	return "exer/listExer.html";	
}
	
}
