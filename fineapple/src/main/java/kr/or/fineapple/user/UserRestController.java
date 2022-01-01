package kr.or.fineapple.user;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public UserRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="rest/getUserList",method=RequestMethod.POST)
	public List<Object> getUserList (@RequestBody User user) throws Exception{
		//user.setUserName()
		System.out.println("getUserList rest타나요");
		
		
		List<Object> list = userService.getUserList(user);
		
		return list;
	}
	
	@RequestMapping(value = "checkDuplication",method = RequestMethod.POST)
	public String checkDuplication(@RequestBody User user) throws Exception{
		System.out.println("checkDuplication");
		
		
		user.setUserId(user.getUserId());
		
		String result = userService.checkDuplication(user);
		
		/*
		 * Map<String, Object> map = new HashMap<>(); map.put("result", result);
		 * map.put("user", user);
		 */
		return result;
		
	}
	
	@RequestMapping(value = "checkDuplication/kakao",method = RequestMethod.POST)
	public Map<String,Object> kakaocheckDuplication(@RequestBody User user) throws Exception{
		System.out.println("checkDuplicationKakao");
		
		
		user.setUserId(user.getUserId());
		
		String result = userService.checkDuplication(user);
		
		
		  Map<String, Object> map = new HashMap<>(); map.put("result", result);
		  map.put("user", user);
		 
		return map;
		
	}
	
	@RequestMapping(value="rest/addUser", method= RequestMethod.POST)
	public ModelAndView addUserRedirect(@RequestBody User user) throws Exception {  

		System.out.println("네비게이션");
		ModelAndView mav = new ModelAndView();
		mav.addObject(user);
		mav.setViewName("user/addUser.html");
		return mav;
	}
	
	@RequestMapping(value ="kakaoLogin/{userId}",method=RequestMethod.GET)
	public ModelAndView kakaoLogin(@PathVariable String userId,HttpSession session) throws Exception{
		User userDB = userService.getUser(userId);
		
		session.setAttribute("user",userDB);
		ModelAndView mav = new ModelAndView();
		mav.addObject(session);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value = "sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestParam("userId") String userId) throws Exception {
		int rannum = (int)((Math.random() * (99999-1000 + 1)) + 1000);
		
		String from = "dkssud3537@naver.com";
		String to = userId;
		String title = "fineapple에 가입에 필요한 인증번호입니다.";
		String content = "[인증번호]" + rannum + "입니다. <br/> 인증번호 확인란에 기입해주십시오.";
		String num="";
		


		
		
		return "";
		
	}
	
}
	
	
	


