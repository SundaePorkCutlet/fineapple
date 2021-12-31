package kr.or.fineapple.user;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> checkDuplication(@RequestBody User user) throws Exception{
		System.out.println("checkDuplication");
		
		
		user.setUserId(user.getUserId());
		
		String result = userService.checkDuplication(user);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
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
}
	
	
	


