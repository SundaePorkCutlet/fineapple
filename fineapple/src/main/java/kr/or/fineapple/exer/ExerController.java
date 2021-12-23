package kr.or.fineapple.exer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.mapper.ExerMapper;
import kr.or.fineapple.service.exer.ExerService;


@Controller
@RequestMapping("/exer/*")
public class ExerController {
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	public ExerController() {
	}

	
@RequestMapping(value="addUserService")
public String addUserService() {
	
    System.out.println("addUserService");

	return "exer/addUserService.html";
	
	
}


@RequestMapping("getExerList")
public String getExerList( @ModelAttribute("search") Search search, Model model) throws Exception {
	
	  System.out.println("getExerList");
 

	  Map<String, Object> map = exerService.getExerList(search);
	  System.out.println("2"+map);
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
			  
		
	return "exer/getExerList.html";	
	
}



@GetMapping("getExer")
public String getExer(@RequestParam(value = "exerName", required=false) String exerName, Model model) throws Exception{
	
	System.out.println("getExer");
	
	if(exerName == null) {
		
		return "redirect:/exer/getExerList.html";
	}

	Exer exer = exerService.getExer(exerName);
	
	model.addAttribute("exer", exer);
	
	return "exer/getExer.html";

}

}
