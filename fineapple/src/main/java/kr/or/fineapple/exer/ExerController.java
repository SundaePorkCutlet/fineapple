package kr.or.fineapple.exer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.fineapple.domain.Exer;
import kr.or.fineapple.domain.ExerServ;
import kr.or.fineapple.domain.Routine;
import kr.or.fineapple.domain.User;
import kr.or.fineapple.domain.common.Search;
import kr.or.fineapple.service.exer.ExerService;


@Controller
@RequestMapping("/exer/*")
public class ExerController {
	
	@Autowired
	@Qualifier("ExerServiceImpl")
	private ExerService exerService;

	public ExerController() {
	}


@GetMapping(value="addUserService")
public String addUserService(HttpServletRequest request,Model model) throws Exception  {
	
    System.out.println("get: addUserService");


	if((User)request.getSession(true).getAttribute("user")!=null) {
		
		User user =(User)request.getSession(true).getAttribute("user");
		
		System.out.println(user);
	
		ExerServ serv = exerService.getUserService(user.getUserId());
		
		System.out.println(serv);
		
		if(serv!=null) {
				if(serv.getUserServiceNo()!=0) {
		
					model.addAttribute("user",user);
					model.addAttribute("exerServ",serv);
					return "exer/getUserService.html";
					}else {
						return "exer/addUserService.html";}
		}else {
			model.addAttribute("user",user);
			return "exer/addUserService.html";}
		
	}else {
		
		return "user/login"; }
	
}
    
@PostMapping("addUserService")
public String addUserService(@ModelAttribute("ExerServ")ExerServ serv,
							HttpServletRequest request,
							Model model) throws Exception{
	System.out.println("post:addUserService");
	System.out.println(serv);
	
	User user =(User)request.getSession(true).getAttribute("user");
	String userId = user.getUserId();
	serv.setUserId(userId);
	
	if(user.getExerServiceNo()==0) {
	exerService.addUserService(serv);
	}else {
		exerService.updateUserService(serv);
	}
			
	serv = exerService.getUserService(userId);
	model.addAttribute("exerServ",serv);
	model.addAttribute("user",user);
	
	

	return "exer/getUserService.html";
}

@GetMapping("updateUserService")
public String updateUserService(Model model, HttpServletRequest request) throws Exception {
	System.out.println("get:updateUserService");
	
	User user =(User)request.getSession(true).getAttribute("user");
	System.out.println(user);

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	
	model.addAttribute("user",user);
	model.addAttribute("exerServ",serv);
	
	return "exer/updateUserService.html";
		
	
}
	


@RequestMapping("getExerList")
public String getExerList( @ModelAttribute("search") Search search, Model model) throws Exception {
	
	  System.out.println("getExerList");
 
	  
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
			  
		
	return "exer/getExerList.html";	
	
}



@GetMapping("getExer")
public String getExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception{
	
	
	exer = exerService.getExer(exer.getExerNo());
	
	System.out.println("getExer");
	
	if(exer.getExerVideoName() == null) {
		exer.setExerVideoName("null");
		
		System.out.println("exerVideoName이 null체크" + exer);
		
		model.addAttribute("exer", exer);
		
	}else {
	
	System.out.println("exerVideoName이 null체크" + exer);
		
	model.addAttribute("exer", exer);
	
	}
	
	return "exer/getExer.html";
	
	
}



@GetMapping("getUpdateExer")
public String getUpdateExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	exer= exerService.getExer(exer.getExerNo());
	
	model.addAttribute("exer", exer);
	
	System.out.println("getUpdateExer");
	
	
	return "exer/updateExer.html";
	
}


private static String FILE_SERVER_PATH = "C:\\Users\\82105\\git\\fineapple\\fineapple\\src\\main\\resources\\templates\\assets\\video";

@PostMapping("postUpdateExer")
public String postUpdateExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {
	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("운동영상 수정업로드 성공~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	System.out.println("postUpdateExer");
	
	exerService.postUpdateExer(exer);
	

	model.addAttribute("exer", exer);
	
	
	return "exer/getExer";
	
}

@RequestMapping("deleteExer")
public String deleteExer(@ModelAttribute("exer") Exer exer , Model model) throws Exception {
	
	System.out.println("deleteExer");
	
	exerService.deleteExer(exer.getExerNo());
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
	
	
	
	model.addAttribute("exer", exer);
	
	return "exer/getExerList";
}


@PostMapping("postAddExer")
public String postAddExer(@ModelAttribute("exer") Exer exer , Model model, @RequestParam("exerFile") MultipartFile exerFile ) throws Exception {

	
	if(!exerFile.getOriginalFilename().isEmpty()) {
		
		exerFile.transferTo(new File(FILE_SERVER_PATH, exerFile.getOriginalFilename()));
		
		model.addAttribute("msg", "File uploaded successfully");
		
		System.out.println("운동영상 등록업로드 성공~");
		
	}else {
		
		model.addAttribute("msg", "Please select a valid mediaFile..");
		
	}
	
	exer.setExerVideoName(exerFile.getOriginalFilename());
	
	exerService.addExer(exer);
	
	System.out.println("addExer");
	
	Search search = new Search();
	
	search.setCurrentPage(1);
	search.setPageSize(1);
	search.setSearchCondition(0);
	search.setSearchKeyword("");
	
	  Map<String, Object> map = exerService.getExerList(search);
	  
	  
	    model.addAttribute("list", map.get("list"));
		model.addAttribute("search", search);
	
		
	
	model.addAttribute("exer", exer);
	
	//리스트가 바로 안뜨는 이유는 컨디션, 키워드 페이지가 유지되지 않기 때문에 다시 설정 해줘서 가져옴!!!
	return "exer/getExerList";
}


@GetMapping("getAddExer")
public String getAddExer(@ModelAttribute("exer") Exer exer, Model model) throws Exception {
	
	
	System.out.println("getAddExer");
	
	
	return "exer/addExer.html";	
	
}


@RequestMapping(value="addDailyBurnning")
public String addDailyBurnning() {

System.out.println("addDailyBurnning");

return "exer/addDailyBurnning.html";


}


@RequestMapping(value="searchExerPlace")
public String searchExerPlace() {
	
	
	System.out.println("searchExerPlace");

	
	return "exer/searchExerPlace.html";
	
	
}



@RequestMapping(value="exerIndex")
public String timer(Model model,HttpServletRequest request) {
	
	System.out.println("timer");
	
	
	User user =(User)request.getSession(true).getAttribute("user");
 
	
	model.addAttribute("user", user);
	
	return "exer/exerIndex.html";
	
	
}



@GetMapping("getRoutineList")
public String getRoutineList(Model model,HttpServletRequest request) throws Exception {
	
	
	System.out.println("getRoutineList");
	

	User user =(User)request.getSession(true).getAttribute("user");

	ExerServ serv = exerService.getUserService(user.getUserId());
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineList(serv.getUserServiceNo());
	
	model.addAttribute("list", map.get("list"));
	
	return "exer/getRoutineList.html";

}



@GetMapping("getRoutine")
public String getRoutineInfoList(@ModelAttribute("routine") Routine routine, Model model) throws Exception {

	routine = exerService.getRoutine(routine.getRoutineNo());
	
	
	System.out.println("getRoutine");
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	
	map = exerService.getRoutineInfoList(routine.getRoutineNo());
	
	
	
	model.addAttribute("list", map.get("list"));
	model.addAttribute("routine", routine);
	
	return "exer/getRoutine.html";
	

}



@RequestMapping("addRoutineInfo")
public String addRoutineInfo() {
	
	
	System.out.println("addRoutineInfo");
	
	
	
	return "exer/getExerList.html";
	

}




@GetMapping("addRoutine")
public String addRoutine() {

	
	
	System.out.println("addRoutine");
	

	return "exer/getExerList.html";
	
}



@GetMapping("recommandExerList")
public String recommandExerList(Model model, HttpServletRequest request) throws Exception {
	
	System.out.println("recommandExerList");
	
	User user =(User)request.getSession().getAttribute("user");

	
	System.out.println(user.getUserId());
	Double sumIntakeKcal = exerService.sumIntakeKcal(user.getUserId());
	System.out.println(sumIntakeKcal);
	
	user = exerService.needDaliyIntakeKcal(user.getUserId());
	System.out.println(user);
	
	
	Double  dailyIntakeKcal	= 0.0;
	Double 	totaldailyIntakeKcal = 0.0;
	
	if(user.getGender().equals("male")) {
		
	  
	  dailyIntakeKcal	= 66 + (13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge());
		
		
	} else {
		
	 dailyIntakeKcal = 655 + (9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge());
		
		
	}
	System.out.println(dailyIntakeKcal);
	
	if(user.getServiceTrgt().equals("1")) {
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.55;
		
	} if (user.getServiceTrgt().equals("2")){
		
		totaldailyIntakeKcal= dailyIntakeKcal * 1.375;
		
	} else {
		
		totaldailyIntakeKcal = dailyIntakeKcal * 1.2;
		
	}
	
	System.err.println(totaldailyIntakeKcal);
	
	
	
	Double overKcal = (totaldailyIntakeKcal - sumIntakeKcal) ;
	
	
	
	System.out.println("초과 칼로리 입니다   " + overKcal);
	
	
	if(overKcal >= 0) {
		
		
	
	List list = exerService.recommandExerList(Math.abs(overKcal));
	
	
	
	model.addAttribute("overKcal", overKcal);
	model.addAttribute("list", list);
	
	
	return "exer/recommandExerList.html";
	
	
	} else {
		
		
		return "user/login";
		
		
	}

	

}



}
