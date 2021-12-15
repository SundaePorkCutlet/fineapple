package kr.or.fineapple.service.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@RequestMapping(value="banner")
    public String banner() {
    	System.out.println("banner");
    	return "index/index.html";
    }
	
	
	
	
	@RequestMapping(value="login")
    public String login(){
     System.out.println("login");
    	return "user/login.html";
    }

}
