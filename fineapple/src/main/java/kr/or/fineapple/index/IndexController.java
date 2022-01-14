package kr.or.fineapple.index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.exer.ExerService;

@Controller
public class IndexController {

	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;
	
	
	
    @GetMapping("/")
    public String index(Model model) throws Exception{
     System.out.println("index");
     
     
     
     Exer exer = new Exer();
     Random rand = new Random();
     Search search = new Search();
     
     search.setSearchCondition(0);
     search.setSearchKeyword("");
     
     
     Map<String, Object> map = exerService.getExerList(search);
	 
     List list  = new ArrayList();
     
     list = (List) map.get("list");
     
     int exerNo = list.size()+1;
     
     
     System.out.println("exerNo:  "+rand.nextInt(exerNo));
     
     exer = exerService.getExer(rand.nextInt(exerNo));
     
     
    // exer.setExerNo(exerNo);
     
    System.out.println(exer);
     
     model.addAttribute("exer", exer);
     model.addAttribute("NavName1","Home");
     model.addAttribute("NavName2","Main");
     
     
     
     
    	return "index/index.html";
    }
    
    
    

}
