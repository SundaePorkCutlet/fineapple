package kr.or.fineapple.exer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.fineapple.domain.common.Search;
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


@GetMapping("getExerList")
public String getExerList(Model model) throws Exception {
	
	  System.out.println("getExerList");

	  Search search = new Search();
	  
	  search.setCurrentPage(1);
	  search.setPageSize(1);
	  search.setSearchCondition(0);
	  search.setSearchKeyword("±â");
	  
	  Map<String, Object> map = exerService.getExerList(search);
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
			  
		
	return "exer/getExerList.html";	
	
}
	
}
