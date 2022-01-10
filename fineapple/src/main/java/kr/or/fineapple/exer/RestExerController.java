package kr.or.fineapple.exer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.exer.ExerService;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/exer/rest/*")
public class RestExerController {

	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
@RequestMapping("getExerList")
public Map<String,Object> getExerList( @RequestBody Search search, Model model) throws Exception {
	
	System.out.println("rest:postGetExerList");

	    search.setPageSize(30);
		//search.setSearchCondition(0);
		

		System.out.println(search);
		
		
		int page = 1; 
		
		if(search.getCurrentPage()>0) {
			page = search.getCurrentPage();
		};
		
		
		search.setStartNum(page + 1);   //1   16
		search.setEndNum(page + 14);    //15  30
			  
		/*
		 * int pageSize = 3; int pageUnit = 5;
		 * 
		 * if(search.getCurrentPage() ==0 ){ search.setCurrentPage(1); }
		 * 
		 * search.setPageSize(pageSize);
		 */
	
		
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
		/*
		 * Page resultPage = new Page( search.getCurrentPage(),
		 * ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 * 
		 * 
		 * System.out.println(resultPage);
		 */  
	  	
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
		//model.addAttribute("resultPage", resultPage);
		
		
		
	return map;	
	
}

	}


