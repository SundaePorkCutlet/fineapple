package kr.or.fineapple.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	/*
	 * @Autowired
	 * 
	 * @Qualifier private UserService userService;
	 */
	
	public UserRestController(){
		
	}

	@RequestMapping(value="login/result",method=RequestMethod.POST )
    public ModelAndView login(@RequestBody User user, HttpSession session) throws Exception{
		System.out.println("/user/login:POST");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index/index.html");
		System.out.println("index찍혀라");
		return mav;
    }
	
	/*
	 * @RequestMapping(value="json/checkDuplication", method=RequestMethod.POST)
	 * public String checkDuplication(@RequestParam("userId") String userId, Model
	 * model) throws Exception{ System.out.println("중복체크 들어왔나요");
	 * 
	 * boolean result = userService.checkDuplication(userId);
	 * 
	 * model.addAttribute("result", new Boolean(result));
	 * model.addAttribute("userId",userId);
	 * 
	 * 
	 * 
	 * return "forward:/user"; }
	 */
	
}
