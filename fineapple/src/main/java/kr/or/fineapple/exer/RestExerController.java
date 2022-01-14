package kr.or.fineapple.exer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.fineapple.domain.Exer;
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

	/*
	 * search.setPageSize(30); //search.setSearchCondition(0);
	 * 
	 * 
	 * System.out.println(search);
	 * 
	 * 
	 * int page = 1;
	 * 
	 * if(search.getCurrentPage()>0) { page = search.getCurrentPage(); };
	 * 
	 * 
	 * search.setStartNum(page + 1); //1 16 search.setEndNum(page + 14); //15 30
	 */			  
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
	  	
	  
	    //model.addAttribute("list", map.get("list"));
		//model.addAttribute("search", search);
		//model.addAttribute("resultPage", resultPage);
		
		
		
	return map;	
	
}



@PostMapping("getExerListJSON")
//@JsonProperty("search")
public @ResponseBody String getExerListJSON(Model model) throws Exception {
	
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	
	List<Exer> data = exerService.getExerListJSON();
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	String str = objectMapper.writeValueAsString(data);
	
	//System.out.println(str);
	
	
	model.addAttribute("data", str);
	model.addAttribute("NavName1","款悼包府");
	model.addAttribute("NavName2","款悼府胶飘");
	
	return str;
	
}

	}


