package kr.or.fineapple.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
		
		System.out.println("login�õ�:rest" + user);
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
			System.out.println("��й�ȣ ����");
			result = "2";
			return result;
			
		}
		}
		return "redirect:/";	
	}
	
	@RequestMapping(value="rest/getUserList",method=RequestMethod.POST)
	public List<Object> getUserList (@RequestBody User user) throws Exception{
		//user.setUserName()
		System.out.println("getUserList restŸ����");
		
		
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
		
		System.out.println("kakaoStt�� ����");
		
		user.setUserId(user.getUserId());
		
		String result = userService.kakaoStt(user);
		
		System.out.println(result);
		
		
		return result;
		
	}
	@RequestMapping(value = "checkPassword/rest", method=RequestMethod.POST)
	public String checkPassword(@RequestBody User user) throws Exception{
		System.out.println("��й�ȣ Ȯ��â ����");
		
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
		System.out.println("user�� ������" + user);
		System.out.println("�⺻�̹���");
		user.setUserImg("defaultProfile.jpg"); 
		//user.setStrdWtrIntake(user.getWeight()*0.03);	//�������м��뷮 ����(������*0.03L) ����
		System.out.println("user:"+user.toString());
		System.out.println("ȸ������ �Ƴ���");
		return user;
	}
	
	@RequestMapping(value="rest/addUser", method= RequestMethod.POST)
	public ModelAndView addUserRedirect(@RequestBody User user) throws Exception {  

		System.out.println("�׺���̼�");
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
		
		System.out.println("login�õ�:POST" + user);
		User userDB = userService.getUser(user.getUserId());
		
		System.out.println("login ��Ʈ�ѷ� īī�� ����");
		System.out.println("userDB : "+userDB);
		
		if(user.getPassword().equals(userDB.getPassword())) {
			if(userDB.getUserLeaveStt() == 0) {
				session.setAttribute("user",userDB);
				System.out.println("stt == 0");
				session.setAttribute("user", userDB);
				System.out.println("user : "+user);
				System.out.println("Ż������ ���� īī�� ȸ���϶� ");
				
				return userDB.getUserLeaveStt() + "";
			}
			else if (userDB.getUserLeaveStt() == 1) {
				System.out.println("Stt == 1");
				System.out.println("����");
				String password = "kakaopassword";
				session.setAttribute("kakao", password);
				return userDB.getUserLeaveStt()+"";
			}
		}
		System.out.println("������");
			return "589";
	}
	
	@Autowired
	public JavaMailSender emailSender;
	
	@RequestMapping(value="/sendMail")
	public String SendMail(@RequestBody User user, HttpSession session) {
		SimpleMailMessage message =  new SimpleMailMessage();
		
		System.out.println("email ���� ����");
		
		Random random  = new Random();
		String key ="";
		
		System.out.println(user.getUserId());
		message.setTo(user.getUserId());
		System.out.println(message);
		for(int i =0; i<3;i++) {
			int index=random.nextInt(25)+65; 
			key+=(char)index;
		}
		int numIndex=random.nextInt(9999)+1000; 
		key+=numIndex;
		System.out.println("������� �Դ�");
		message.setSubject("[FineApple] �̸��� ������ȣ");
		message.setText("Fineapple���� ���۵� ������ȣ : "+key);
		System.out.println("������ �޼���: " + message);
		emailSender.send(message);
        return key;
		
	}
	
	@RequestMapping(value="changePassword", method=RequestMethod.POST)
	public String changePassword(@RequestBody User user,HttpSession session) throws Exception{
		System.out.println("changePassword ��Ʈ�ѷ� ���Գ���");
		
	//	User userDB= userService.getUser(user.getUserId());
		
	//	System.out.println(userDB);
		
	//	String sessionId = ((User)session.getAttribute("user")).getUserId();
		
		userService.changePassword(user);
		
		/*
		 * userDB=userService.getUser(user.getUserId());
		 * if(sessionId.equals(userDB.getUserId())) {
		 * session.setAttribute("user",userDB); }
		 */
		System.out.println("��?");
		String changePassword =user.getPassword();
	
			session.invalidate();
		
		return changePassword;
	}
	

	
	
}
	
	
	


