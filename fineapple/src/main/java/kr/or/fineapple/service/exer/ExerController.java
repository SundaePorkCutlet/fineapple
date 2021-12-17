package kr.or.fineapple.service.exer;

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
	
}
