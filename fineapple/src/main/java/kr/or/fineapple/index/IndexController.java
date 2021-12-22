package kr.or.fineapple.index;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.fineapple.domain.User;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
     System.out.println("index");
    	return "index/index.html";
    }
    


}
