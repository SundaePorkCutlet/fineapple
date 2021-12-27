package kr.or.fineapple.user;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.User;
import kr.or.fineapple.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("${file.upload.directory}")
	private String filePath ;
	
	public UserController(){
		System.out.println("UserController들어옴");
	}
	
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
		
		return "redirect:/";
		}
	
	@RequestMapping( value="logout",method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		System.out.println("로그아웃 시도합니당");
		
		session.invalidate();
		
		System.out.println("로그아웃 됐나요");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="addUser")
	public String addUser(){
		System.out.println("UserController:addUser()");
		return "user/addUser.html";
	}
	
	
	@RequestMapping(value="addUser/redirect", method= RequestMethod.POST)
	public String addUserRedirect(@ModelAttribute("user") User user , @RequestParam(value="userImg1", required = false) MultipartFile file) throws Exception {  
		System.out.println("addUserRedirect");
		System.out.println("user잘 들어갔나용" + user);
		
		
		if(!file.getOriginalFilename().isEmpty()) {
			System.out.println("if문 입장");
			file.transferTo(new File(filePath, file.getOriginalFilename()));
			user.setUserImg(file.getOriginalFilename());
			
		}
		else {
			System.out.println("기본이미지");
			user.setUserImg("defaultProfile.jpg"); 
		}

		user.setStrdWtrIntake(user.getWeight()*0.03);	//적정수분섭취량 계산식(몸무게*0.03L) 적용
		userService.addUser(user);
		System.out.println("user:"+user.toString());
		System.out.println("회원가입 됐나용");
		return "redirect:/user/login";
	}
	
	@RequestMapping(value="getUser",method=RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId,Model model)throws Exception{
		System.out.println("getUser:POST 들어왔나요");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		
		return "user/getUser.html";
		
	}
	

	
	

}
