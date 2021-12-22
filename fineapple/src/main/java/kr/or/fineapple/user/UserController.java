package kr.or.fineapple.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="login",method = RequestMethod.GET)
    public String login(){
		System.out.println("redirect:/user/login.html:GET");
		return "user/login.html";
    }
	
	@RequestMapping(value="login/redirect",method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		
		System.out.println("login시도:POST");
		
		User userDB = userService.getUser(user.getUserId());
		if(user.getPassword().equals(userDB.getPassword())) {
			session.setAttribute("user",userDB);
		}
		System.out.println("user : "+userDB);
		System.out.println("login 성공했나요");
		
		return "redirect:../../";
				}
	
	
	@RequestMapping(value="addUser")
	public String addUser(){
		System.out.println("UserController:addUser()");
		return "user/addUser.html";
	}
	
	@RequestMapping(value="addUser/redirect", method= RequestMethod.POST)
	public String addUserRedirect(@ModelAttribute("user") User user) throws Exception {
		System.out.println("addUserRedirect");
		System.out.println("user:"+user.toString());
		userService.addUser(user);
		return "redirect:/user/login";
	}
	
	

}
