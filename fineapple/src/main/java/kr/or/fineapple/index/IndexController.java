package kr.or.fineapple.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
     System.out.println("index");
     
     model.addAttribute("NavName1","Home");
     model.addAttribute("NavName2","Main");
     
    	return "index/index.html";
    }
    


}
