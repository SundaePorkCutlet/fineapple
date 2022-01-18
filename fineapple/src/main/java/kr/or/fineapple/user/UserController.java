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
		System.out.println("UserController����");
	}
	
	@RequestMapping(value="banner")
    public String banner() {
    	System.out.println("banner");
    	return "index/index.html";
    }
	
	@RequestMapping(value="checkPassword")
	public String checkPassword(Model model) {
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","��й�ȣ ����");
		return "user/changeUserPassword.html";
	}
	@RequestMapping(value="findPassword")
	public String findPassword(Model model) {
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","��й�ȣ ã��(����)");
		return "user/findPassword.html";
	}
	
	@RequestMapping(value="login",method = RequestMethod.GET)
    public String login(){
		System.out.println("redirect:/user/login.html:GET");
		return "user/login :: hong";
    }
	
	@RequestMapping(value="login/redirect",method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		
		System.out.println("login�õ�:POST" + user);
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
			System.out.println("��й�ȣ ����");
			result = "2";
			return result;
			
		}
		return "redirect:/";		
	}
	
	@RequestMapping( value="logout",method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		System.out.println("�α׾ƿ� �õ��մϴ�");
		
		session.invalidate();
		
		System.out.println("�α׾ƿ� �Ƴ���");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="addUser")
	public ModelAndView addUser(@ModelAttribute("user") User user, HttpSession session,Model model){
		ModelAndView mav = new ModelAndView();
		
		System.out.println("��UserController:addUser()");
		System.out.println(user);
		/*
		 * if(user.getPassword().equals("kakao11")) { mav.clear(); }
		 */
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","ȸ������");
		mav.setViewName("user/addUser.html");
		 if(user.getUserId() != null) {
			 System.out.println("���� ���Դ�");
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
		System.out.println("user�� ������" + user);
		if(user.getPassword() == null) {
			user.setPassword("kakao11");
			user.setKakaoStt(1);
		}
		if(!file.getOriginalFilename().isEmpty()) {
			System.out.println("if�� ����");
			file.transferTo(new File(filePath, file.getOriginalFilename()));
			user.setUserImg(file.getOriginalFilename());
			
		}
		else {
			System.out.println("�⺻�̹���");
			user.setUserImg("defaultProfile.jpg"); 
		}

		user.setStrdWtrIntake(user.getWeight()*0.03);	//�������м��뷮 ����(������*0.03L) ����
		userService.addUser(user);
		userService.afterAddOrRestoreUser(user);		//userBodyInfo, Badge ���̺� ������ ����
		System.out.println("user:"+user.toString());
		System.out.println("ȸ������ �Ƴ���");
		if(user.getPassword().equals("kakao!!")) {
			session.setAttribute("user",user);
		}
		return "redirect:/";
	}
	
	
	
	
	
	@RequestMapping(value="getUser",method=RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId,Model model)throws Exception{
		System.out.println("getUser:POST ���Գ���");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","�� ���� ����");
		return "user/getUser.html";
		
	}
	
	@RequestMapping(value="updateUser", method=RequestMethod.GET)
	public String updateUser(@RequestParam("userId") String userId, Model model) throws Exception{
		
		System.out.println("updateUser:GET ���Գ���");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","�� ���� ����");
		return "user/updateUser.html";
		
	}
	
	@RequestMapping(value="updateUser/result",method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, HttpSession session,@RequestParam(value="userImg1",required=false) MultipartFile file) throws Exception{
		
		System.out.println("updateUser:POST ���Գ���");
		
		System.out.println(user);
		User userDB = userService.getUser(user.getUserId());
		
		if(file != null && !file.getOriginalFilename().isEmpty()) {
			System.out.println("if�� ����");
			file.transferTo(new File(filePath, file.getOriginalFilename()));
			user.setUserImg(file.getOriginalFilename());
			
		}else {
			System.out.println("�⺻�̹���");
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
		System.out.println("��������");
		
		return "redirect:/user/getUser?userId="+user.getUserId();
		
	}
	
	@RequestMapping(value="updateUserLeaveResult", method = RequestMethod.POST)
	public String updateUserLeave(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		System.out.println("ȸ��Ż�� ���Գ���");
		
		User userDB=userService.getUser(user.getUserId());
		String sessionId = ((User)session.getAttribute("user")).getUserId();
	    System.out.println("����Դ�");
		
		if(sessionId.equals(userDB.getUserId())) {
			System.out.println("ù��° if��");
				if(user.getPassword().equals(userDB.getPassword())) {
					System.out.println("�ι�° if��");

					System.out.println("user: " + user);
					userService.updateUserLeave(user.getUserId());
					System.out.println("�����");
					
				}
		}
		System.out.println("ȸ��Ż��..���� �� ������");
		return "redirect:/user/logout";
	}
	
	@RequestMapping(value ="updateUserLeave", method=RequestMethod.GET)
	public String updateUserLeave(@RequestParam("userId") String userId, Model model) throws Exception{
		System.out.println("updateUserLeave :GET ���Գ���");
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","ȸ��Ż��");
		return "user/updateUserLeave.html";
	}
	
	@RequestMapping(value="restoreUser", method= RequestMethod.GET)
	public String restoreUser(Model model){
		System.out.println("ȸ������â ��!¯");
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","ȸ������");
		return "user/restoreUser.html";
	}
	
	@RequestMapping(value="restoreUserResult", method=RequestMethod.POST)
	public String restoreUser(@ModelAttribute("user") User user) throws Exception{
		System.out.println("ȸ������ ����~~");
		User userDB = userService.getUser(user.getUserId());
		if (user.getPassword().equals(userDB.getPassword())) {
			if(user.getUserId().equals(userDB.getUserId())) {
				userService.restoreUser(user);
				userService.afterAddOrRestoreUser(userDB);	//���� ����� default�� ������ ���� userBodyInfo, Badge ���̺� ������ ����
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value ="getUserList")
	public String getUserList(Model model) throws Exception{
		System.out.println("���̵�ã�� ��������");
		model.addAttribute("NavName1", "ȸ������");
		model.addAttribute("NavName2","���̵�ã��");
		return "user/findUserId.html";
	}
	

	

	


}
