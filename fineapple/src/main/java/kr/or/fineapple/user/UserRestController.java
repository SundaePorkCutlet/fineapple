package kr.or.fineapple.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	
	@PostMapping(value="login/rest")
	public String logincheck(@RequestBody User user, HttpSession session)throws Exception{
		
		System.out.println("login시도:rest" + user);
		User userDB = userService.getUser(user.getUserId());
		String result;
		
		
		if(userDB == null) {
			result="3";
			return result;
		}else {
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
		}
		return "redirect:/";	
	}
	
	@RequestMapping(value="rest/getUserList",method=RequestMethod.POST)
	public List<Object> getUserList (@RequestBody User user) throws Exception{
		//user.setUserName()
		System.out.println("getUserList rest타나요");
		
		
		List<Object> list = userService.getUserList(user);
		
		return list;
	}
	@RequestMapping(value="addUser/rest",method = RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception{
		System.out.println("UserController:addUser/rest");
		//session.setAttribute("user", user);
		System.out.println(user);
		//user.setKakaoStt(1);
		return user;
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
	
	@RequestMapping(value="kakaoStt",method=RequestMethod.POST)
	public String kakaoStt(@RequestBody User user)throws Exception{
		
		System.out.println("kakaoStt비교 입장");
		
		user.setUserId(user.getUserId());
		
		String result = userService.kakaoStt(user);
		
		System.out.println(result);
		
		
		return result;
		
	}
	@RequestMapping(value = "checkPassword/rest", method=RequestMethod.POST)
	public String checkPassword(@RequestBody User user) throws Exception{
		System.out.println("비밀번호 확인창 입장");
		
		user.setPassword(user.getPassword());
		user.setUserId(user.getUserId());
		
		String result= userService.checkPassword(user);
		
		return result;
	}
	

	
	@RequestMapping(value = "checkDuplication/kakao",method = RequestMethod.POST)
	public Map<String,Object> kakaocheckDuplication(@RequestBody User user) throws Exception{
		System.out.println("checkDuplicationKakao");
		System.out.println(user);
		
		user.setUserId(user.getUserId());
		
		
		String result = userService.checkDuplication(user);
		
		
		  Map<String, Object> map = new HashMap<>(); 
		  map.put("result", result);
		  map.put("user", user);
		 
		return map;
		
	}
	
	
	
	
	@RequestMapping(value="addUser/kakao", method= RequestMethod.POST)
	public User addUserKaKao(@RequestBody User user) throws Exception {  
		System.out.println("addUserRedirect");
		System.out.println("user잘 들어갔나용" + user);
		System.out.println("기본이미지");
		user.setUserImg("defaultProfile.jpg"); 
		//user.setStrdWtrIntake(user.getWeight()*0.03);	//적정수분섭취량 계산식(몸무게*0.03L) 적용
		System.out.println("user:"+user.toString());
		System.out.println("회원가입 됐나용");
		return user;
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
	
	@RequestMapping(value="login/kakao",method = RequestMethod.POST)
	public String login(@RequestBody User user, HttpSession session) throws Exception{
		
		System.out.println("login시도:POST" + user);
		User userDB = userService.getUser(user.getUserId());
		
		System.out.println("login 컨트롤러 카카오 입장");
		System.out.println("userDB : "+userDB);
		
		if(user.getPassword().equals(userDB.getPassword())) {
			if(userDB.getUserLeaveStt() == 0) {
				session.setAttribute("user",userDB);
				System.out.println("stt == 0");
				session.setAttribute("user", userDB);
				System.out.println("user : "+user);
				System.out.println("탈퇴하지 않은 카카오 회원일때 ");
				
				return userDB.getUserLeaveStt() + "";
			}
			else if (userDB.getUserLeaveStt() == 1) {
				System.out.println("Stt == 1");
				System.out.println("들어와");
				String password = "kakaopassword";
				session.setAttribute("kakao", password);
				return userDB.getUserLeaveStt()+"";
			}
		}
		System.out.println("마지막");
			return "589";
	}
	
	@Autowired
	public JavaMailSender emailSender;
	
	@RequestMapping(value="/sendMail")
	public String SendMail(@RequestBody User user, HttpSession session) throws Exception{
		//SimpleMailMessage message =  new SimpleMailMessage();
		MimeMessage message = emailSender.createMimeMessage();
		System.out.println("email 전송 시작");
		
		Random random  = new Random();
		String key ="";
		
		System.out.println(user.getUserId());
		message.addRecipients(RecipientType.TO,user.getUserId());
		System.out.println(message);
		for(int i =0; i<3;i++) {
			int index=random.nextInt(25)+65; 
			key+=(char)index;
		}
		int numIndex=random.nextInt(9999)+1000; 
		key+=numIndex;
		
		String msg;
		msg="";
		msg+="<p align =\"center\"><img alt=\"Logo\" src=\"https://fineapple.or.kr/assets/img/banner.png\" style=\"height:150px\"class=\"ban\"></p>";
		msg+="<p align=\"center\" style=\"text-align: center; \">FineApple에 가입해주셔서 감사합니다.&nbsp;</p><p align=\"center\" style=\"text-align: center; \">아래 번호를 입력해 인증완료 해주세요.</p><p style=\"text-align: center; \" align=\"center\">감사합니다.&nbsp;</p><br><br>\r\n";
		msg+="<div align=\"center\"style=\"font-weight:bold;\">"+key+"</div>";
		
		System.out.println("여기까지 왔니");
		message.setSubject("[FineApple] 이메일 인증번호");
		message.setText(msg,"utf-8","html");
		System.out.println("마지막 메세지: " + message);
		emailSender.send(message);
        return key;
		
	}
	
	@RequestMapping(value="changePassword", method=RequestMethod.POST)
	public String changePassword(@RequestBody User user,HttpSession session) throws Exception{
		System.out.println("changePassword 컨트롤러 들어왔나요");
		
	//	User userDB= userService.getUser(user.getUserId());
		
	//	System.out.println(userDB);
		
	//	String sessionId = ((User)session.getAttribute("user")).getUserId();
		
		userService.changePassword(user);
		
		/*
		 * userDB=userService.getUser(user.getUserId());
		 * if(sessionId.equals(userDB.getUserId())) {
		 * session.setAttribute("user",userDB); }
		 */
		System.out.println("끝?");
		String changePassword =user.getPassword();
	
			session.invalidate();
		
		return changePassword;
	}
	

	
	
}
	
	
	


