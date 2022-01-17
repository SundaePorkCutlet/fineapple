package kr.or.fineapple.user;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="checkPassword")
	public String checkPassword(Model model) {
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","비밀번호 변경");
		return "user/changeUserPassword.html";
	}
	@RequestMapping(value="findPassword")
	public String findPassword(Model model) {
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","비밀번호 찾기(변경)");
		return "user/findPassword.html";
	}
	
	@RequestMapping(value="login",method = RequestMethod.GET)
    public String login(){
		System.out.println("redirect:/user/login.html:GET");
		return "user/login :: hong";
    }
	
	@RequestMapping(value="login/redirect",method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		
		System.out.println("login시도:POST" + user);
		User userDB = userService.getUser(user.getUserId());
		String result;
		
		System.out.println("userDB : "+userDB);
		
		if(user.getPassword().equals(userDB.getPassword())) {
			if(userDB.getUserLeaveStt() == 0) {
				session.setAttribute("user",userDB);
				System.out.println("stt == 0");
				result = "0";
				return result ;
			}
			else if (userDB.getUserLeaveStt() == 1) {
				System.out.println("Stt == 1");
				result = "1";
				return result;
			}
		}
		else {
			System.out.println("비밀번호 오류");
			result = "2";
			return result;
			
		}
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
	public ModelAndView addUser(@ModelAttribute("user") User user, HttpSession session,Model model){
		System.out.println("찐UserController:addUser()");
		System.out.println(user);
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","회원가입");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/addUser.html");
		 if(user.getUserId() != null) {
			 System.out.println("여기 들어왔니");
			 user.setPassword("kakao11");
			 mav.addObject(user);
		 }
		System.out.println();
		
		System.out.println(user);
		return mav;
	}
	
	
	
	@RequestMapping(value="addUser/redirect", method= RequestMethod.POST)
	public String addUserRedirect(@ModelAttribute("user") User user , @RequestParam(value="userImg1", required = false) MultipartFile file , HttpSession session) throws Exception {  
		System.out.println("addUserRedirect");
		System.out.println("user잘 들어갔나용" + user);
		if(user.getPassword() == null) {
			user.setPassword("kakao!!");
			user.setKakaoStt(1);
		}
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
		if(user.getPassword().equals("kakao!!")) {
			session.setAttribute("user",user);
		}
		return "redirect:/";
	}
	
	
	
	
	
	@RequestMapping(value="getUser",method=RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId,Model model)throws Exception{
		System.out.println("getUser:POST 들어왔나요");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","내 정보 보기");
		return "user/getUser.html";
		
	}
	
	@RequestMapping(value="updateUser", method=RequestMethod.GET)
	public String updateUser(@RequestParam("userId") String userId, Model model) throws Exception{
		
		System.out.println("updateUser:GET 들어왔나용");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","내 정보 수정");
		return "user/updateUser.html";
		
	}
	
	@RequestMapping(value="updateUser/result",method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, HttpSession session,@RequestParam(value="userImg1",required=false) MultipartFile file) throws Exception{
		
		System.out.println("updateUser:POST 들어왔나요");
		
		System.out.println(user);
		User userDB = userService.getUser(user.getUserId());
		
		if(file != null && !file.getOriginalFilename().isEmpty()) {
			System.out.println("if문 입장");
			file.transferTo(new File(filePath, file.getOriginalFilename()));
			user.setUserImg(file.getOriginalFilename());
			
		}else {
			System.out.println("기본이미지");
			user.setUserImg(userDB.getUserImg());
			System.out.println(userDB.getUserImg());
			
		}
		System.out.println(userDB);
		String sessionId =  ((User)session.getAttribute("user")).getUserId();
		
		userService.updateUser(user);
		userDB = userService.getUser(user.getUserId());
		if(sessionId.equals(userDB.getUserId())) {
			session.setAttribute("user",userDB);
		}
		System.out.println("끝났나요");
		
		return "redirect:/user/getUser?userId="+user.getUserId();
		
	}
	
	@RequestMapping(value="updateUserLeaveResult", method = RequestMethod.POST)
	public String updateUserLeave(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		System.out.println("회원탈퇴 들어왔나요");
		
		User userDB=userService.getUser(user.getUserId());
		String sessionId = ((User)session.getAttribute("user")).getUserId();
	    System.out.println("여기왔니");
		
		if(sessionId.equals(userDB.getUserId())) {
			System.out.println("첫번째 if문");
				if(user.getPassword().equals(userDB.getPassword())) {
					System.out.println("두번째 if문");

					System.out.println("user: " + user);
					userService.updateUserLeave(user.getUserId());
					System.out.println("여기는");
					
				}
		}
		System.out.println("회원탈퇴..제발 ㅜ 으어어ㅓ");
		return "redirect:/user/logout";
	}
	
	@RequestMapping(value ="updateUserLeave", method=RequestMethod.GET)
	public String updateUserLeave(@RequestParam("userId") String userId, Model model) throws Exception{
		System.out.println("updateUserLeave :GET 들어왔나여");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","회원탈퇴");
		return "user/updateUserLeave.html";
	}
	
	@RequestMapping(value="restoreUser", method= RequestMethod.GET)
	public String restoreUser(Model model){
		System.out.println("회원복구창 입!짱");
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","회원복구");
		return "user/restoreUser.html";
	}
	
	@RequestMapping(value="restoreUserResult", method=RequestMethod.POST)
	public String restoreUser(@ModelAttribute("user") User user) throws Exception{
		System.out.println("회원복구 시작~~");
		User userDB = userService.getUser(user.getUserId());
		if (user.getPassword().equals(userDB.getPassword())) {
			if(user.getUserId().equals(userDB.getUserId())) {
				userService.restoreUser(user);
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value ="getUserList")
	public String getUserList(Model model) throws Exception{
		System.out.println("아이디찾기 들어오세요");
		model.addAttribute("NavName1", "회원관리");
		model.addAttribute("NavName2","아이디찾기");
		return "user/findUserId.html";
	}
	

	

	


}
