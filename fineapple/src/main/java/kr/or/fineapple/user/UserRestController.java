package kr.or.fineapple.user;

import javax.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.User;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	public UserRestController(){
		
	}

	@RequestMapping(value="login/result",method=RequestMethod.POST )
    public ModelAndView login(@RequestBody User user, HttpSession session) throws Exception{
		System.out.println("/user/login:POST");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index/index.html");
		System.out.println("index¬Ô«Ù∂Û");
		return mav;
    }
	
}
