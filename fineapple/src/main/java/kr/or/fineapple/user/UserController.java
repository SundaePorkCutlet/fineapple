package kr.or.fineapple.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@RequestMapping(value="banner")
    public String banner() {
    	System.out.println("banner");
    	return "index/index.html";
    }
	
	@RequestMapping(value="login")
    public String login(){
	//	System.out.println("/user/login:GET");
	//	ModelAndView mav = new ModelAndView();
	//	mav.setViewName("redirect:/user/login.html");
		System.out.println("redirect:/user/login.html:GET");
		return "user/login.html";
    }
	
	@RequestMapping(value="addUser")
	public String addUser(){
		System.out.println("UserController:addUser()");
		return "user/addUser.html";
	}
	
	@RequestMapping(value="addUser/redirect", method = RequestMethod.POST)
	public String addUserRedirect(@ModelAttribute("user") User user) throws Exception {
		System.out.println("addUserRedirect");
		System.out.println("user:" + user.toString());
		userService.addUser(user);
		return "forward:/user/login.jsp";
	}
	
	

}
